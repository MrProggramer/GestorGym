package models.users;

import enums.TipoRol;

public class Staff extends User  {
    private String usuario;
    private String contrasenia;
    private boolean isAdmin;    //en vez de enums, podemos usar isAdmin y/o instanceof para acceder a ciertos métodos

    public Staff(String nombre, String dni, String mail, String telefono, String usuario, String contrasenia, String user, String pass, boolean isAdmin) {
        super(nombre, dni, mail, telefono, user, pass);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.isAdmin = isAdmin;
    }


//    public void asignarRutina(int id_cliente, int id_rutina){
//        //User user = c.getCliente(id_cliente); MAQUETA
//        //user.setRutina(a.getRtuna(id_rutina));
//    }




    //Getter&Setter
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
