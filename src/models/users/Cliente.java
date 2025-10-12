package models.users;

import interfaces.Identificable;
import interfaces.TransformableJSON;
import models.rutinas.Rutina;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Cliente extends User implements Identificable, TransformableJSON {
    private boolean coutaAlDia;
    private int dias;
    private Rutina rutina;

    public Cliente(String nombre, String dni, String mail, String telefono, String user, String pass, boolean coutaAlDia, int dias) {
        super(nombre, dni, mail, telefono, user, pass);
        this.coutaAlDia = coutaAlDia;
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
        User aux = null;

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
    public boolean isCoutaAlDia() {
        return coutaAlDia;
    }
    public void setCoutaAlDia(boolean coutaAlDia) {
        this.coutaAlDia = coutaAlDia;
    }

    public ArrayList<ArrayList<Rutina>> getListaRutinas() {
        return listaRutinas;
    }
    public void setListaRutinas(ArrayList<ArrayList<Rutina>> listaRutinas) {
        this.listaRutinas = listaRutinas;
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
                "coutaAlDia=" + coutaAlDia +
                ", listaRutinas=" + listaRutinas +
                ", dias=" + dias +
                '}';
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        object.put("cuotaAlDia", this.coutaAlDia);
        object.put("dias", this.dias);
        object.put("listaRutinas", this.listaRutinas);
        return object;
    }


}
