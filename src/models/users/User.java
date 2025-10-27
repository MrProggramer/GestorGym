package models.users;

import interfaces.Identificable;
import interfaces.TransformableJSON;
import org.json.JSONObject;

import java.util.Scanner;

public abstract class User implements Identificable {
    private int id;
    private final String type;
    private String nombre;
    private String dni;
    private String mail;
    private String telefono;
    private String user;
    private String pass;
    private boolean isActive;

    public String getType() {
        return type;
    }

    public User(String nombre, String dni, String mail, String telefono, String user, String pass) {
        this.type = getClass().getSimpleName();
        this.nombre = nombre;
        this.dni = dni;
        this.mail = mail;
        this.telefono = telefono;
        this.user = user;
        this.pass = pass;
        this.isActive = true;
    }

    // Constructor
    public User(){
        this.isActive = true;
        this.type = getClass().getTypeName();
    }

    //METODOS

    // Crear Usuario
    public abstract User crear(Scanner sc);

    @Override
    public String toString() {
        return "User{" +
                ", type='" + type + '\'' +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", mail='" + mail + '\'' +
                ", telefono='" + telefono + '\'' +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    //Getter&Setters

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }

}
