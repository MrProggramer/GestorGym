package models.rutinas;

import interfaces.Identificable;

import java.util.ArrayList;

public class Rutina implements Identificable {
    ArrayList<Ejercicio> listaEjercicios = new ArrayList<>();
    private String id;


    public Ejercicio crearEjercicio(){
        return null;
    }

    @Override
    public String getId() {
        return id;
    }

    public void guardarEjercicio(Ejercicio ejercicio){
        listaEjercicios.add(ejercicio);
    }

}
