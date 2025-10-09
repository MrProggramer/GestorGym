package models.users;

public class ClienteTemporal extends Cliente {
    public ClienteTemporal(String nombre, String dni, String mail, String telefono, String user, String pass, boolean coutaAlDia, int dias) {
        super(nombre, dni, mail, telefono, user, pass, coutaAlDia, dias);
        setDias(1);
    }
    public ClienteTemporal(){}
}
