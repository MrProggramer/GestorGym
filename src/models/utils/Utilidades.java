package models.utils;

import Exceptions.InvalidTypeException;
import Exceptions.TipoMuscularInvalidoException;
import enums.TipoGrupoMuscular;
import gestores.GenericGestor;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public abstract class Utilidades {

    //Transforma cualquier Object en un JSONobject (incluido listas)
    public static JSONObject ObjectToJSON(Object data) {
        JSONObject json = new JSONObject();
        Class<?> clazz = data.getClass();

        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(data);
                    if (value == null) continue;

                    if (value instanceof List<?> list) {
                        JSONArray array = new JSONArray();
                        for (Object item : list) {
                            if(isPrimitiveOrString(item) || item.getClass().isEnum()){
                                array.put(item.toString());
                            }else {
                                array.put(ObjectToJSON(item));
                            }
                        }
                        json.put(field.getName(), array);
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

    private static boolean isPrimitiveOrString(Object obj) {
        return obj instanceof String ||
                obj instanceof Number ||
                obj instanceof Boolean ||
                obj instanceof Character;
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
            case "Admin" -> new Admin();
            case "Cliente" -> new Cliente();
            case "ClienteTemporal" -> new ClienteTemporal();
            case "Profesor" -> new Profesor();
            default -> throw new InvalidTypeException("Tipo invalido");
        };

        user.setUser(json.getString("user"));
        user.setPass(json.getString("pass"));
        user.setNombre(json.getString("nombre"));
        user.setDni(json.getString("dni"));
        user.setMail(json.getString("mail"));
        user.setTelefono(json.getString("telefono"));
        user.setId(json.getInt("id"));
        user.setActive(json.getBoolean("isActive"));

        if(user instanceof Admin u){
            JSONArray jRutinas = json.getJSONArray("rutinas");
            GenericGestor<Rutina> gestorRutinas = new GenericGestor<>();
            for(Object elem : jRutinas){
                Rutina rutina = Utilidades.crearRutinaFromJSON((JSONObject) elem);
                gestorRutinas.altaItem(rutina);
            }
            u.setRutinas(gestorRutinas);
        }
        if(user instanceof Profesor u){
            //NADA
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


