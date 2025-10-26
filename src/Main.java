import enums.TipoGrupoMuscular;
import gestores.GenericGestor;
import models.database.ControlData;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.User;
import models.utils.Utilidades;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //GenericGestor<User> usuarios = new GenericGestor<>();
        GenericGestor<Ejercicio> ejj = new GenericGestor<>();
        GenericGestor<Rutina> rutinas = new GenericGestor<>();
        ejj.actualizarGestor("ejercicios");


        Rutina fullBody = new Rutina(3, "Foco en basicos y tecnicas", "Fullbody");
        Rutina upperLower = new Rutina(4,"Mas volumen, fuerza e hipertrofia", "UpperLower");
        Rutina pushPullLegs = new Rutina(6, "Full volumen","PushPullLegs");
        Rutina fuerza = new Rutina(4, "Ejercicios basicos pesados","Fuerza");
        Rutina hipertrofiaClasica = new Rutina(5, "Rutina muscular, un musculo por dia","HipertrofiaClasica");
        Rutina rutinaEnCircuito = new Rutina(2, "Todo el cuerpo en circuito", "RutinaEnCircuito");
        Rutina calistenia = new Rutina(4, "Solo peso corporal","Calistenia");
        Rutina fuerzaPotencia = new Rutina(4,"Ejercicios olimpicos simplificados","FuerzaPotencia");


        fullBody.guardarEjercicio(ejj.buscarItem(1));
        fullBody.guardarEjercicio(ejj.buscarItem(2));
        fullBody.guardarEjercicio(ejj.buscarItem(3));
        fullBody.guardarEjercicio(ejj.buscarItem(4));
        fullBody.guardarEjercicio(ejj.buscarItem(5));
        fullBody.guardarEjercicio(ejj.buscarItem(6));

        upperLower.guardarEjercicio(ejj.buscarItem(2));  // Press banca
        upperLower.guardarEjercicio(ejj.buscarItem(5));  // Press militar
        upperLower.guardarEjercicio(ejj.buscarItem(6));  // Dominadas
        upperLower.guardarEjercicio(ejj.buscarItem(3));  // Remo con mancuera
        upperLower.guardarEjercicio(ejj.buscarItem(1));  // Sentadilla
        upperLower.guardarEjercicio(ejj.buscarItem(11)); // Romanian DeadLift - Peso Muerto
        upperLower.guardarEjercicio(ejj.buscarItem(12)); // Hip Trust, empuje de cadera
        upperLower.guardarEjercicio(ejj.buscarItem(7));  // Gemelos

        pushPullLegs.guardarEjercicio(ejj.buscarItem(2));  // Press banca
        pushPullLegs.guardarEjercicio(ejj.buscarItem(5));  // Press militar
        pushPullLegs.guardarEjercicio(ejj.buscarItem(17)); // Fondos
        pushPullLegs.guardarEjercicio(ejj.buscarItem(32)); // Laterales
        pushPullLegs.guardarEjercicio(ejj.buscarItem(28)); // Jalon Triceps
        pushPullLegs.guardarEjercicio(ejj.buscarItem(6));  // Dominadas
        pushPullLegs.guardarEjercicio(ejj.buscarItem(3));  // Remo con mancuera
        pushPullLegs.guardarEjercicio(ejj.buscarItem(8));  // Face pull
        pushPullLegs.guardarEjercicio(ejj.buscarItem(10)); // Curl biceps
        pushPullLegs.guardarEjercicio(ejj.buscarItem(1));  // Sentadilla
        pushPullLegs.guardarEjercicio(ejj.buscarItem(11)); // Romanian DeadLift - Peso Muerto
        pushPullLegs.guardarEjercicio(ejj.buscarItem(12)); // Hip Trust, empuje de cadera
        pushPullLegs.guardarEjercicio(ejj.buscarItem(7));  // Gemelos
        pushPullLegs.guardarEjercicio(ejj.buscarItem(9));  // Zancadas caminando

        fuerza.guardarEjercicio(ejj.buscarItem(1));  // Sentadilla
        fuerza.guardarEjercicio(ejj.buscarItem(2));  // Press banca
        fuerza.guardarEjercicio(ejj.buscarItem(3));  // Remo con mancuera
        fuerza.guardarEjercicio(ejj.buscarItem(5));  // Press militar
        fuerza.guardarEjercicio(ejj.buscarItem(31)); // Peso Muerto

        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(2));  // Press banca
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(21)); // Prees Inclinado
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(22)); // Apertura
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(17)); // Fondos
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(6));  // Dominadas
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(3));  // Remo con mancuera
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(23)); // Jalon
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(8));  // Face pull
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(1));  // Sentadilla
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(33)); // Prensa
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(11)); // Romanian DeadLift - Peso Muerto
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(7));  // Gemelos
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(5));  // Press militar
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(32)); // Laterales
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(24)); // Pajaros
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(25)); // Encogimiento
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(13)); // Curl Barra
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(26)); // Curl Inclinado
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(27)); // Press Cerrado
        hipertrofiaClasica.guardarEjercicio(ejj.buscarItem(28)); // Jalon Triceps

        rutinaEnCircuito.guardarEjercicio(ejj.buscarItem(14)); // Burpees
        rutinaEnCircuito.guardarEjercicio(ejj.buscarItem(6));  // Dominadas
        rutinaEnCircuito.guardarEjercicio(ejj.buscarItem(15)); // Flexiones
        rutinaEnCircuito.guardarEjercicio(ejj.buscarItem(1));  // Sentadilla
        rutinaEnCircuito.guardarEjercicio(ejj.buscarItem(16)); // Planchas
        rutinaEnCircuito.guardarEjercicio(ejj.buscarItem(9));  // Zancadas caminando

        calistenia.guardarEjercicio(ejj.buscarItem(6));  // Dominadas
        calistenia.guardarEjercicio(ejj.buscarItem(17)); // Fondos
        calistenia.guardarEjercicio(ejj.buscarItem(18)); // Flexiones Pike
        calistenia.guardarEjercicio(ejj.buscarItem(16)); // Planchas
        calistenia.guardarEjercicio(ejj.buscarItem(19)); // Pistols
        calistenia.guardarEjercicio(ejj.buscarItem(9));  // Zancadas caminando
        calistenia.guardarEjercicio(ejj.buscarItem(12)); // Hip Trust, empuje de cadera
        calistenia.guardarEjercicio(ejj.buscarItem(20)); // Hollow Body

        fuerzaPotencia.guardarEjercicio(ejj.buscarItem(29)); // Power Clean
        fuerzaPotencia.guardarEjercicio(ejj.buscarItem(30)); // Push Press
        fuerzaPotencia.guardarEjercicio(ejj.buscarItem(1));  // Sentadilla
        fuerzaPotencia.guardarEjercicio(ejj.buscarItem(6));  // Dominadas
        fuerzaPotencia.guardarEjercicio(ejj.buscarItem(21)); // Prees Inclinado
        fuerzaPotencia.guardarEjercicio(ejj.buscarItem(7));  // Gemelos

        rutinas.altaItem(fullBody);
        rutinas.altaItem(upperLower);
        rutinas.altaItem(pushPullLegs);
        rutinas.altaItem(fuerza);
        rutinas.altaItem(hipertrofiaClasica);
        rutinas.altaItem(rutinaEnCircuito);
        rutinas.altaItem(calistenia);
        rutinas.altaItem(fuerzaPotencia);

        for(Rutina e: rutinas.getInventario()){
            System.out.println(e);
        }
        //ControlData.guardarData(rutinas, "rutinas"); // NO FUNCIONA

    }

}