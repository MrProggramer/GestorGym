import enums.TipoGrupoMuscular;
import gestores.GenericGestor;
import models.database.ControlData;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.Staff;
import models.users.User;
import models.utils.Utilidades;
import org.json.JSONObject;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GenericGestor<User> usuarios = new GenericGestor<>();
//        usuarios.actualizarGestor("usuarios");

        GenericGestor<Rutina> rutinas = new GenericGestor<>();
        rutinas.actualizarGestor("rutinas");

        GenericGestor<Ejercicio> ejercicios = new GenericGestor<>();
        ejercicios.actualizarGestor("ejercicios");

        System.out.println(ejercicios.getClass().getTypeName());
    }

}