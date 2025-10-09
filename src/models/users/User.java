package models.users;


import interfaces.Identificable;
import interfaces.TransformableJSON;
import netscape.javascript.JSObject;
import org.json.JSONObject;

public abstract class User implements Identificable, TransformableJSON {
    private final int id;
    private static int count;
    private final String type;
    private String nombre;
    private String dni;
    private String mail;
    private String telefono;
    private String user;
    private String pass;

    public String getType() {
        return type;
    }

    public User(String nombre, String dni, String mail, String telefono, String user, String pass) {
        this.id = count++;
        this.type = getClass().getSimpleName();
        this.nombre = nombre;
        this.dni = dni;
        this.mail = mail;
        this.telefono = telefono;
        this.user = user;
        this.pass = pass;
    }
    public User(){
        this.id = count++;
        this.type = getClass().getTypeName();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
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
    @Override
    public int getId() { return id; }

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

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }

    @Override
    public JSONObject toJSONObject(){
        JSONObject object = new JSONObject();
        object.put("Nombre", this.nombre);
        object.put("Dni", this.dni);
        object.put("Email", this.mail);
        object.put("Telefono", this.telefono);
        object.put("User", this.user);
        object.put("Password", this.pass);
        object.put("ID", this.id);

        return object;
    }
}
