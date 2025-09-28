package models.rutinas;

import interfaces.Identificable;

import java.util.ArrayList;

public class Rutina implements Identificable {
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

}
