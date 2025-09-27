package models.users;


import interfaces.Identificable;

public abstract class User {
    private final int id;
    private static int count;
    private String nombre;
    private String dni;
    private String mail;
    private String telefono;

    public User(String nombre, String dni, String mail, String telefono) {
        this.id = count++;
        this.nombre = nombre;
        this.dni = dni;
        this.mail = mail;
        this.telefono = telefono;
    }




    //Getter&Setters
    public static int getCount() {
        return count;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
