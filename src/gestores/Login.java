package gestores;

import Exceptions.UserNotFoundException;
import models.users.Cliente;
import models.users.Profesor;
import models.users.User;

import java.util.Objects;
import java.util.Scanner;

public class Login {
    protected GenericGestor<User> usuarios;

    public Login(GenericGestor<User> usuarios) { //De dónde recibo este gestor de usuarios?
        this.usuarios = usuarios;
    }


    public User autenticar(String user, String pass) throws UserNotFoundException, IllegalStateException, IllegalArgumentException {
        if(usuarios == null || usuarios.getInventario() == null)
            throw new IllegalStateException("Lista de usuarios sin inicializar");
        if(user == null || user.isBlank() || pass == null || pass.isBlank())
            throw new IllegalArgumentException("Usuario y contraseña no pueden estar vacios");

        /** Prueba de usuario */
        /* Funciona bien el autenticar y su llamada desde lo más arriba (main), pero al haber errores en la clase App, no puede recibir la lista de usuarios registrados, por lo tanto, hice esta prueba temporal para probar */
        //System.out.println(usuarios);
        //Profesor test = new Profesor("test", "1123", "@m", "4450", "test", "pass", null);
        //usuarios.altaItem(test);
        System.out.println(usuarios);


        for (User u : usuarios.getInventario()) {
            if (Objects.equals(u.getUser(), user) && Objects.equals(u.getPass(), pass)) {
                return u;
            }
        }
        throw new UserNotFoundException("Usuario o contraseña incorrectos");
    }

    public void crearUser(String user, String pass) throws IllegalArgumentException {





    }



    //GS
    public GenericGestor<User> getUsuarios() { return usuarios; }
    public void setUsuarios(GenericGestor<User> usuarios) { this.usuarios = usuarios; }
}
