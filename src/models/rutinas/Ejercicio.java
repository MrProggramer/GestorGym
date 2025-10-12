package models.rutinas;

import enums.TipoGrupoMuscular;
import interfaces.Identificable;

import java.util.Scanner;

public class Ejercicio implements Identificable {
    private final int id;
    private static int count;
    private final String type;
    private String nombre;
    private TipoGrupoMuscular tipoGrupoMuscular;
    private String descripcionEjericio;
    private int series;
    private int repeticiones;

    public Ejercicio(String nombre, TipoGrupoMuscular tipoGrupoMuscular, String descripcionEjericio, int series, int repeticiones) {
        this.id = count++;
        this.type = getClass().getTypeName();
        this.nombre = nombre;
        this.tipoGrupoMuscular = tipoGrupoMuscular;
        this.descripcionEjericio = descripcionEjericio;
        this.series = series;
        this.repeticiones = repeticiones;
    }
    public Ejercicio(){
        this.id = count++;
        this.type = getClass().getTypeName();
    }

    public Ejercicio crearEjercicio(Scanner sc){
        Ejercicio aux = new Ejercicio();

        System.out.println("¿Cual es el nombre del ejercicio?");
        aux.setNombre(sc.nextLine());

        System.out.println("¿En que grupo muscular se encuentra el Ejercicio?");
        //menu
        System.out.println("Elegí en cual tipo muscular esta: ");
        System.out.println("1 - Gemelos");
        System.out.println("2 - Cuadriceps");
        System.out.println("3 - Espalda");
        System.out.println("4 - Isquiotibiales");
        System.out.println("5 - Pecho");
        System.out.println("6 - Abdominales");
        System.out.println("7 - Multimuscular");
        System.out.println("8 - Aductores");
        System.out.println("9 - Biceps");
        System.out.println("10 - Gluteos");
        System.out.println("11 - Hombros");
        System.out.println("12 - Triceps");

        boolean f = false;
        int tipo = 0;
        while(!f){
            tipo = sc.nextInt();
            if(tipo > 0 && tipo < 14){
                f = true;
            }
        }
        sc.nextLine();

        switch (tipo){
            case 1 -> setTipoGrupoMuscular(TipoGrupoMuscular.TORSO_INFERIOR_GEMELOS);
            case 2 -> setTipoGrupoMuscular(TipoGrupoMuscular.TORSO_INFERIOR_CUADRICEPS);
            case 3 -> setTipoGrupoMuscular(TipoGrupoMuscular.TORSO_SUPERIOR_ESPALDA);
            case 4 -> setTipoGrupoMuscular(TipoGrupoMuscular.TORSO_INFERIOR_ISQUIOTIBIALES);
            case 5 -> setTipoGrupoMuscular(TipoGrupoMuscular.TORSO_SUPERIOR_PECHO);
            case 6 -> setTipoGrupoMuscular(TipoGrupoMuscular.TORSO_MEDIO_ABDOMINALES);
            case 7 -> setTipoGrupoMuscular(TipoGrupoMuscular.MULTIMUSCULAR);
            case 8 -> setTipoGrupoMuscular(TipoGrupoMuscular.TORSO_INFERIOR_ADUCTORES);
            case 9 -> setTipoGrupoMuscular(TipoGrupoMuscular.TORSO_SUPERIOR_BICEPS);
            case 10 -> setTipoGrupoMuscular(TipoGrupoMuscular.TORSO_INFERIOR_GLUTEOS);
            case 11 -> setTipoGrupoMuscular(TipoGrupoMuscular.TORSO_SUPERIOR_HOMBROS);
            case 12 -> setTipoGrupoMuscular(TipoGrupoMuscular.TORSO_SUPERIOR_TRICEPS);
            default -> throw new RuntimeException("ERROR OPCION INCORRECTA");
        }

        System.out.println("Inserte la descripcion del ejercicio");
        aux.setDescripcionEjericio(sc.nextLine());

        System.out.println("¿Cuantas series hay que hacer?");
        aux.setRepeticiones(sc.nextInt());
        sc.nextLine();

        System.out.println("¿Cuantas repeticiones por serie hay que hacer?");
        aux.setRepeticiones(sc.nextInt());
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
