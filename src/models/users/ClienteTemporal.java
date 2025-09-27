package models.users;

public class ClienteTemporal extends Cliente {
    public ClienteTemporal(String nombre, String dni, String mail, String telefono, boolean coutaAlDia, int dias) {
        super(nombre, dni, mail, telefono, coutaAlDia, dias);
        setDias(1);
    }
}
