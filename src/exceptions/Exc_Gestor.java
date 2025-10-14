package exceptions;

public class Exc_Gestor extends Exception {
    public Exc_Gestor(String mensaje) {
        super(mensaje);
    }

    public Exc_Gestor(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
