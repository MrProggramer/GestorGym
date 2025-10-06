package models.users;

import enums.TipoRol;
import interfaces.TransformableJSON;
import org.json.JSONObject;

public class Staff extends User implements TransformableJSON {
    private boolean isAdmin;    //en vez de enums, podemos usar isAdmin y/o instanceof para acceder a ciertos métodos

    public Staff(String nombre, String dni, String mail, String telefono,String user, String pass, boolean isAdmin) {
        super(nombre, dni, mail, telefono, user, pass);
        this.isAdmin = isAdmin;
    }


//    public void asignarRutina(int id_cliente, int id_rutina){
//        //User user = c.getCliente(id_cliente); MAQUETA
//        //user.setRutina(a.getRtuna(id_rutina));
//    }




    //Getter&Setter
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        object.put("isAdmin", this.isAdmin);
        return object;
    }
}
