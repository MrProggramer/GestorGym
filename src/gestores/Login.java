package gestores;

import Exceptions.UserNotFoundException;
import models.users.Cliente;
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

        for (User u : usuarios.getInventario()) {
            if (Objects.equals(u.getUser(), user) && Objects.equals(u.getPass(), pass)) {
                return u;
            }
        }
        throw new UserNotFoundException("Usuario o contraseña incorrectos");
    }

    public void crearUser(String user, String pass) {

    }




    //    public void test() {
//        User user1 = new Cliente("Pedro", "40123123", "mail@mail.com", "2231231234", "pedrito12", "passw", true, 31);
//
//        System.out.println(user1.getId());
//        System.out.println(this.getGestor());
//        this.altaItem(user1);
//        System.out.println(this.getGestor());
//        System.out.println("Buscar: ");
//        System.out.println(this.buscarItem(user1.getId()));
//    }


    //GS
    public GenericGestor<User> getUsuarios() { return usuarios; }
    public void setUsuarios(GenericGestor<User> usuarios) { this.usuarios = usuarios; }
}
