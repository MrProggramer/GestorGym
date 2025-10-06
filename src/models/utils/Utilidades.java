package models.utils;

import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.Cliente;
import models.users.Staff;
import models.users.User;
import org.json.JSONObject;


public abstract class Utilidades {
    public static JSONObject userToJSON(User data){
        JSONObject object = new JSONObject();
        object.put("Nombre", data.getNombre());
        object.put("Dni", data.getDni());
        object.put("Email", data.getMail());
        object.put("Telefono", data.getTelefono());
        object.put("User", data.getUser());
        object.put("Password", data.getPass());
        object.put("ID", data.getId());
        if(data instanceof Cliente){
            object.put("cuotaAlDia", ((Cliente) data).isCoutaAlDia());
            object.put("dias", ((Cliente) data).getDias());
            object.put("listaRutinas", ((Cliente) data).getListaRutinas());
        }
        if(data instanceof Staff){
            object.put("isAdmin", ((Staff) data).isAdmin());
        }
        return object;
    }
    public static JSONObject ejercicioToJSON(Ejercicio data){
        return null;
    }
    public static JSONObject rutinaToJSON(Rutina data){
        return null;
    }
}
