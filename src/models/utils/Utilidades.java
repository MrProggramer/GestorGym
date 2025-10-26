package models.utils;

import Exceptions.ArchivoInvalidoException;
import Exceptions.InvalidTypeException;
import Exceptions.TipoMuscularInvalidoException;
import enums.TipoGrupoMuscular;
import gestores.GenericGestor;
import models.database.ControlData;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.Cliente;
import models.users.ClienteTemporal;
import models.users.Staff;
import models.users.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class Utilidades<T> {

    //Metodo antiguo (manual)
    private static JSONObject userToJSON(User data) {
        JSONObject object = new JSONObject();
        object.put("Nombre", data.getNombre());
        object.put("Dni", data.getDni());
        object.put("Email", data.getMail());
        object.put("Telefono", data.getTelefono());
        object.put("User", data.getUser());
        object.put("Password", data.getPass());
        //object.put("ID", data.getId()); no tiene id
        if (data instanceof Cliente c) {
            object.put("cuotaAlDia", c.isCuotaAlDia());
            object.put("dias", c.getDias());
            object.put("rutina", c.getRutina());
        }
        if (data instanceof Staff s) {
            object.put("isAdmin", s.isAdmin());
        }
        return object;
    }

    //Transforma cualquier Object en un JSONobject (incluido listas)
    public static JSONObject ObjectToJSON(Object data) {
        JSONObject json = new JSONObject();
        Class<?> clazz = data.getClass();

        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(data);
                    if (value instanceof List<?>) {
                        JSONArray array = new JSONArray();
                        for (Object item : (List<?>) value) {
                            array.put(ObjectToJSON(item));
                        }
                    } else if (value instanceof Map<?,?>) {
                        JSONArray array = new JSONArray();
                        for(Map.Entry<?,?> entry : ((Map<?,?>)value).entrySet()){
                            //SOLO HACE PUT DEL VALUE, NO DE LA KEY
                            array.put(ObjectToJSON(entry.getValue()));
                        }
                    } else {
                        json.put(field.getName(), value);
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            clazz = clazz.getSuperclass();
        }
        return json;
    }



    public static Ejercicio crearEjercicioFromJSON(JSONObject _e) throws TipoMuscularInvalidoException{
        Ejercicio ejercicio = new Ejercicio();

        ejercicio.setNombre(_e.getString("nombre"));
        ejercicio.setDescripcionEjericio(_e.getString("descripcionEjericio"));
        ejercicio.setSeries(_e.getInt("series"));
        ejercicio.setRepeticiones(_e.getInt("repeticiones"));
        try{
            ejercicio.setTipoGrupoMuscular(TipoGrupoMuscular.valueOf(_e.getString("tipoGrupoMuscular")));
        } catch (JSONException | IllegalArgumentException e) {
            throw new TipoMuscularInvalidoException("Tipo muscular invalido: " + e.getMessage());
        }


        return ejercicio;
    }

    public static Rutina crearRutinaFromJSON(JSONObject jElem){
        Rutina rutina = new Rutina();

        rutina.setNombre(jElem.getString("nombre"));
        rutina.setDescripcionRutina(jElem.getString("descripcionRutina"));
        rutina.setCantidadDeDias(jElem.getInt("cantidadDeDias"));

        //OBTENER EL ARR DE EJERCICIOS
        JSONArray jListaEjercicios = jElem.getJSONArray("listaEjercicios");
        List<Ejercicio> listaEjercicios = new ArrayList<>();
        for(Object e : jListaEjercicios){
            listaEjercicios.add(Utilidades.crearEjercicioFromJSON((JSONObject) e));
        }
        rutina.setListaEjercicios(listaEjercicios);

        return rutina;
    }

    public static User crearUserFromJSON(JSONObject json) throws InvalidTypeException {
        String type = json.getString("type");
        User user = switch (type) {
            case "Staff" -> new Staff();
            case "Cliente" -> new Cliente();
            case "ClienteTemporal" -> new ClienteTemporal();
            default -> throw new InvalidTypeException("Tipo invalido");
        };

        user.setUser(json.getString("user"));
        user.setPass(json.getString("pass"));
        user.setNombre(json.getString("nombre"));
        user.setDni(json.getString("dni"));
        user.setMail(json.getString("mail"));
        user.setTelefono(json.getString("telefono"));

        if(user instanceof Staff u){
            u.setAdmin(json.getBoolean("isAdmin"));
            JSONArray jRutinas = json.getJSONArray("rutinas");
            GenericGestor<Rutina> gestorRutinas = new GenericGestor<>();
            for(Object elem : jRutinas){
                Rutina rutina = Utilidades.crearRutinaFromJSON((JSONObject) elem);
                gestorRutinas.altaItem(rutina);
            }
            u.setRutinas(gestorRutinas);
        }
        if(user instanceof Cliente u){
           u.setDias(json.getInt("dias"));
           u.setCuotaAlDia(json.getBoolean("cuotaAlDia"));
           u.setRutina(Utilidades.crearRutinaFromJSON(json.getJSONObject("rutina")));
        }
        //CLIENTE TEMPORAL NO SE CREO POR QUE NO PARECE SER NECESARIO "ES TEMPORAL", NO DEBERIA ESTAR GUARDADO.

        return user;
    }

}


