package models.rutinas;

import interfaces.Identificable;

import java.util.ArrayList;

public class Rutina implements Identificable { //falta ponerle una variable de nombre a la rutina, sino no se como se llama.
    ArrayList<Ejercicio> listaEjercicios = new ArrayList<>();
    private final int id;
    private static int count;
    private String descripcionRutina;

    private int cantidadDeDias;

    public Rutina(int cantidadDeDias, String descripcionRutina) {
        this.listaEjercicios = listaEjercicios;
        this.id = count++;
        this.descripcionRutina = descripcionRutina;
        this.cantidadDeDias = cantidadDeDias;
    }

    public Ejercicio crearEjercicio(){
        return null;
    }

    @Override
    public int getId() {
        return id;
    }

    public void guardarEjercicio(Ejercicio ejercicio){
        listaEjercicios.add(ejercicio);
    }

    @Override
    public String toString() {
        return "Rutina{" +
                "listaEjercicios=" + listaEjercicios +
                ", id=" + id +
                ", descripcionRutina='" + descripcionRutina + '\'' +
                ", cantidadDeDias=" + cantidadDeDias +
                '}';
    }

    public ArrayList<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    public void setListaEjercicios(ArrayList<Ejercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    public String getDescripcionRutina() {
        return descripcionRutina;
    }

    public void setDescripcionRutina(String descripcionRutina) {
        this.descripcionRutina = descripcionRutina;
    }

    public int getCantidadDeDias() {
        return cantidadDeDias;
    }

    public void setCantidadDeDias(int cantidadDeDias) {
        this.cantidadDeDias = cantidadDeDias;
    }
}
