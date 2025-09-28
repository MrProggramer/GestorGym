package models.rutinas;

import enums.TipoGrupoMuscular;
import interfaces.Identificable;

public class Ejercicio implements Identificable {
    private final int id;
    private static int count;
    private String nombre;
    private TipoGrupoMuscular tipoGrupoMuscular;
    private String descripcionEjericio;
    private int series;
    private int repeticiones;

    public Ejercicio(String nombre, TipoGrupoMuscular tipoGrupoMuscular, String descripcionEjericio, int series, int repeticiones) {
        this.id = count++;
        this.nombre = nombre;
        this.tipoGrupoMuscular = tipoGrupoMuscular;
        this.descripcionEjericio = descripcionEjericio;
        this.series = series;
        this.repeticiones = repeticiones;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Ejercicio.count = count;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoGrupoMuscular getTipoGrupoMuscular() {
        return tipoGrupoMuscular;
    }

    public void setTipoGrupoMuscular(TipoGrupoMuscular tipoGrupoMuscular) {
        this.tipoGrupoMuscular = tipoGrupoMuscular;
    }

    public String getDescripcionEjericio() {
        return descripcionEjericio;
    }

    public void setDescripcionEjericio(String descripcionEjericio) {
        this.descripcionEjericio = descripcionEjericio;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    @Override
    public String toString() {
        return "Ejercicio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipoGrupoMuscular=" + tipoGrupoMuscular +
                ", descripcionEjericio='" + descripcionEjericio + '\'' +
                ", series=" + series +
                ", repeticiones=" + repeticiones +
                '}';
    }
}
