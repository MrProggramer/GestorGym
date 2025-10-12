package models.rutinas;

import interfaces.Identificable;

import java.util.ArrayList;
import java.util.Scanner;

public class Rutina implements Identificable { //falta ponerle una variable de nombre a la rutina, sino no se como se llama.
    private ArrayList<Ejercicio> listaEjercicios;
    private final String type;
    private final int id;
    private static int count;
    private String nombre;
    private String descripcionRutina;
    private int cantidadDeDias;

    public Rutina(int cantidadDeDias, String descripcionRutina,String nombre) {
        this.listaEjercicios = new ArrayList<>();
        this.type = this.getClass().getTypeName();
        this.id = count++;
        this.nombre = nombre;
        this.descripcionRutina = descripcionRutina;
        this.cantidadDeDias = cantidadDeDias;
    }
    public Rutina(){
        this.listaEjercicios = new ArrayList<>();
        this.id = count++;
        this.type = this.getClass().getTypeName();
    }

    public Rutina crearEjercicio(Scanner sc){
        Rutina aux = new Rutina();

        System.out.println("Ingresa el nombre de la rutina");
        aux.setNombre(sc.nextLine());

        System.out.println("Ingresa el descripcion de la rutina");
        aux.setDescripcionRutina(sc.nextLine());

        System.out.println("La rutina, ¿cuantos días a la semana son?");
        aux.setCantidadDeDias(sc.nextInt());
        sc.nextLine();

        return aux;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public Rutina recrearRutina(String data){

        return null;
    }
}
