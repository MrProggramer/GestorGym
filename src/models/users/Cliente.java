package models.users;

import interfaces.Identificable;
import interfaces.TransformableJSON;
import models.rutinas.Rutina;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class Cliente extends User {
    private boolean cuotaAlDia;
    private int dias;
    private Rutina rutina;

    public Cliente(String nombre, String dni, String mail, String telefono, String user, String pass, boolean cuotaAlDia, int dias) {
        super(nombre, dni, mail, telefono, user, pass);
        this.cuotaAlDia = cuotaAlDia;
        this.dias = dias;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }

    public Cliente(){}


    @Override
    public User crear(Scanner sc) {
        User aux = new Cliente();

        System.out.println("Ingresa tu nombre completo");
        aux.setNombre(sc.nextLine());
        System.out.println("Ingresa tu dni");
        aux.setDni(sc.nextLine());
        System.out.println("Ingresa tu mail");
        aux.setMail(sc.nextLine());
        System.out.println("Ingresa tu telefono");
        aux.setTelefono(sc.nextLine());
        System.out.println("Ingresa el usuario que queres tener");
        aux.setUser(sc.nextLine());
        System.out.println("Ingresa la clave que queres tener");
        aux.setPass(sc.nextLine());

        return aux;
    }

    //Getter&Setter
    public boolean isCuotaAlDia() {
        return cuotaAlDia;
    }
    public void setCuotaAlDia(boolean cuotaAlDia) {
        this.cuotaAlDia = cuotaAlDia;
    }

    public int getDias() {
        return dias;
    }
    public void setDias(int dias) {
        this.dias = dias;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                super.toString() +
                "coutaAlDia=" + cuotaAlDia +
                ", dias=" + dias +
                '}';
    }


}
