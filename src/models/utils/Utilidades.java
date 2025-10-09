package models.utils;

import gestores.GenericGestor;
import javafx.beans.binding.ObjectExpression;
import models.database.ControlData;
import models.users.Cliente;
import models.users.ClienteTemporal;
import models.users.Staff;
import models.users.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public abstract class Utilidades {
    //Metodo antiguo (manual)
    private static JSONObject userToJSON(User data) {
        JSONObject object = new JSONObject();
        object.put("Nombre", data.getNombre());
        object.put("Dni", data.getDni());
        object.put("Email", data.getMail());
        object.put("Telefono", data.getTelefono());
        object.put("User", data.getUser());
        object.put("Password", data.getPass());
        object.put("ID", data.getId());
        if (data instanceof Cliente) {
            object.put("cuotaAlDia", ((Cliente) data).isCoutaAlDia());
            object.put("dias", ((Cliente) data).getDias());
            object.put("listaRutinas", ((Cliente) data).getListaRutinas());
        }
        if (data instanceof Staff) {
            object.put("isAdmin", ((Staff) data).isAdmin());
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

    public static User createUserFromJSON(JSONObject json){
        String type = json.getString("type");


        User res = switch (type){
            case "Staff" -> new Staff();
            case "Cliente" -> new Cliente();
            case "ClienteTemporal" -> new ClienteTemporal();
            default -> throw new IllegalArgumentException("tipo desconocido " + type);
        };
        res.setNombre(json.getString("nombre"));
        res.setDni(json.getString("dni"));
        res.setMail(json.getString("mail"));
        res.setTelefono(json.getString("telefono"));
        res.setUser(json.getString("user"));
        res.setPass(json.getString("pass"));
        if(res instanceof Staff){ ((Staff) res).setAdmin(json.getBoolean("isAdmin"));  }
        if(res instanceof Cliente){
            ((Cliente) res).setDias(json.getInt("dias"));
            ((Cliente) res).setCoutaAlDia(json.getBoolean("coutaAlDia"));
        }

        return res;
    }

    public static void cargarGestorUser(String archivo, GenericGestor<User> gestor){
        JSONArray jUsers = ControlData.recuperarData(archivo+".json");
        for(int i=0; i< jUsers.length(); i++){
            User u = Utilidades.createUserFromJSON(jUsers.getJSONObject(i));
            gestor.altaItem(u);
        }
    }

}


