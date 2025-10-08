package models.utils;

import models.users.Cliente;
import models.users.Staff;
import models.users.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;



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
                    if (value instanceof java.util.List<?>) {
                        JSONArray array = new JSONArray();
                        for (Object item : (java.util.List<?>) value) {
                            array.put(ObjectToJSON(item));
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



}


