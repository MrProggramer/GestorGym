package models.rutinas;

import java.util.ArrayList;

public class Rutina {
    ArrayList<Ejercicio> listaEjercicios = new ArrayList<>();


    public Ejercicio crearEjercicio(){

    }

    public void guardarEjercicio(Ejercicio ejercicio){
        listaEjercicios.add(ejercicio);
    }

}
