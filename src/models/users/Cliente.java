package models.users;

import interfaces.Identificable;
import models.rutinas.Rutina;

import java.util.ArrayList;
import java.util.ListIterator;

public class Cliente extends User implements Identificable {
    private boolean coutaAlDia;
    private ArrayList<ArrayList<Rutina>> listaRutinas;
    private int dias;

    public Cliente(String nombre, String dni, String mail, String telefono, boolean coutaAlDia, int dias) {
        super(nombre, dni, mail, telefono);
        this.coutaAlDia = coutaAlDia;
        this.dias = dias;
    }


    /* Deberian ir estos metodos en cliente o staff? Solo puede acceder el entrenador
    * Por ahora, los dejo acá */
    public void asignarRutina(ArrayList<Rutina> rutina) {
        listaRutinas.add(rutina);
    }
    public void eliminarRutina(int idRutina) {
        ListIterator<ArrayList<Rutina>> iterador = getListaRutinas().listIterator();

        while(iterador.hasNext()) {
            if(iterador.next().equals(idRutina)) {
                listaRutinas.remove(iterador);
            }
        }
    }




    @Override
    public String getId() {
        return this.getId();
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
}
