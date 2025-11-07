import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuCliente {
    private final String nombre;
    private final boolean isActive;
    private final Scanner sc;
    private final Map<Integer, String> rutinas;

    public MenuCliente(String nombre, boolean isActive, Scanner sc) {
        this(nombre, isActive, sc, defaultRutinas());
    }

    public MenuCliente(String nombre, boolean isActive, Scanner sc, Map<Integer, String> rutinas) {
        this.nombre = nombre;
        this.isActive = isActive;
        this.sc = sc;
        this.rutinas = rutinas != null ? rutinas : defaultRutinas();
    }

    // ----- Rutinas de ejemplo para ver como funciona el menu -----
    private static Map<Integer, String> defaultRutinas() {
        Map<Integer, String> m = new HashMap<>();
        m.put(1, "Rutina 1 - Cardio: 30 min. de trote, 3 series de salto de soga");
        m.put(2, "Rutina 2 - Fuerza: Sentadillas 3x12, Press inclinado 3x10.");
        m.put(3, "Rutina 3 - Flexibilidad: Estiramientos 20 min.");
        return m;
    }

    public void show() {
        System.out.printf("Bienvenido %s", nombre);
        int opt;
        do {
            System.out.println("\n1. Ver rutina asignada (por ID)");
            System.out.println("2. Estado de cuenta");
            System.out.println("3. Salir");
            opt = readInt();
            switch (opt) {
                case 1:
                    mostrarRutinaPorId();
                    break;
                case 2:
                    verEstadoCuenta();
                    break;
                case 3:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opt != 3);
    }

    private void mostrarRutinaPorId() {
        System.out.print("Ingrese el ID de la rutina: ");
        int id = readInt();
        String rutina = rutinas.get(id);
        if (rutina != null) {
            System.out.println("Rutina encontrada");
            System.out.println(rutina);
        } else {
            System.out.println("No existe una rutina con el ID " + id + ".");
        }
    }

    private void verEstadoCuenta() {
        if (isActive) {
            System.out.println("Sin deudas al momento");
        } else {
            System.out.println("Usted debe la cuota del mes");
        }
    }

    // ----- Sirve para evitar que se rompa el programa si se pone algo que no es un INT -----
    private int readInt() {
        try {
            String line = sc.nextLine();
            return Integer.parseInt(line.trim());
        } catch (Exception e) {
            return -1;
        }
    }
}
