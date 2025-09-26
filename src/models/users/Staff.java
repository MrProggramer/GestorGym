package models.users;

import gestores.GestorRutinas;
import gestores.GestorUsers;

public class Staff extends User{
    boolean isAdmin;
    GestorRutinas a;
    GestorUsers c;

    public void asignarRutina(int id_cliente, int id_rutina){
        //User user = c.getCliente(id_cliente); MAQUETA
        //user.setRutina(a.getRtuna(id_rutina));
    }
}
