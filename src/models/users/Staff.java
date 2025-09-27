package models.users;

import enums.TipoRol;
import gestores.GestorRutinas;
import gestores.GestorUsers;

public class Staff extends User  {
    private final int id;
    private static int  count;
    private String usuario;
    private String contraseña;
    private TipoRol tipoRol;
    boolean isAdmin;
    GestorRutinas a;
    GestorUsers c;

    public void asignarRutina(int id_cliente, int id_rutina){
        //User user = c.getCliente(id_cliente); MAQUETA
        //user.setRutina(a.getRtuna(id_rutina));
    }
}
