package gestores;

import models.users.Cliente;
import models.users.User;

public class GestorLogin {
    private GenericGestor<User> gestor_login;

    public GestorLogin() {
        this.gestor_login = new GenericGestor<User>();
    }

    public void test() {
        User user1 = new Cliente("Pedro", "40123123", "mail@mail.com", "2231231234", "pedrito12", "passw", true, 31);

        System.out.println(user1.getId());
        System.out.println(gestor_login.getGestor());
        gestor_login.altaItem(user1);
        System.out.println(gestor_login.getGestor());
        System.out.println("Buscar: ");
        System.out.println(gestor_login.buscarItem(user1.getId()));
    }

    public User autenticar(String user, String pass) {
        for(User u : gestor_login.getGestor().values()) {
            if(u.getUser().equals(user) && u.getUser().equals(pass)) {
                return u; //retorno el user para cargarlo luego a una clase Session y trabajar en el programa con ese usuario
            }
        }
        return null;
    }

}
