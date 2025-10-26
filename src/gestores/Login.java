package gestores;

import models.users.Cliente;
import models.users.User;

public class Login {
    private GenericGestor<User> usuarios;

    public Login(GenericGestor<User> usuarios) {
        this.usuarios = usuarios;
    }

//    public User autenticar(String user, String pass) {
//        for(User u : usuarios.getGestor().values()) {
//            if(u.getUser().equals(user) && u.getPass().equals(pass)) {
//                return u; //retorno el user para cargarlo luego a una clase Session y trabajar en el programa con ese usuario
//            }
//        }
//        return null; //crear exception
//    }

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
}
