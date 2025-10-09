import enums.TipoGrupoMuscular;
import gestores.GenericGestor;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.Staff;
import models.users.User;
import models.utils.Utilidades;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        GenericGestor<Rutina> rutinas = new GenericGestor<>();
        cargaTemporal(rutinas);


        User test = new Staff("test", "22", "ee@.c", "22344", "test_no_admin", "1234", false);

        System.out.println("JSON object");
        JSONObject json = Utilidades.ObjectToJSON(test);
        System.out.println(json);


        System.out.println("testeo");
        System.out.println(Utilidades.createUserFromJSON(json));

    }


    public static void cargaTemporal(GenericGestor<Rutina> gestor){

        Rutina fullBody = new Rutina(3, "Foco en basicos y tecnicas");
        Rutina upperLower = new Rutina(4,"Mas volumen, fuerza e hipertrofia");
        Rutina pushPullLegs = new Rutina(6, "Full volumen");
        Rutina fuerza = new Rutina(4, "Ejercicios basicos pesados");
        Rutina hipertrofiaClasica = new Rutina(5, "Rutina muscular, un musculo por dia");
        Rutina rutinaEnCircuito = new Rutina(2, "Todo el cuerpo en circuito");
        Rutina calistenia = new Rutina(4, "Solo peso corporal");
        Rutina fuerzaPotencia = new Rutina(4,"Ejercicios olimpicos simplificados");

        Ejercicio abdominales = new Ejercicio("Abdominales", TipoGrupoMuscular.TORSO_MEDIO_ABDOMINALES, "Ejercicio de torso intermedio", 6,12);
        Ejercicio sentadilla = new Ejercicio("Sentadilla", TipoGrupoMuscular.TORSO_INFERIOR_CUADRICEPS,"Con barra, rodillas altura hombro, y hasta abajo", 4,12);
        Ejercicio pressBanca = new Ejercicio("Press banca", TipoGrupoMuscular.TORSO_SUPERIOR_PECHO,"Pecho plano", 4,12);
        Ejercicio remoConMancuera = new Ejercicio("Remo con mancuera", TipoGrupoMuscular.TORSO_SUPERIOR_ESPALDA,"Ejercicio de espalda, con mancuerna", 4,12);
        Ejercicio pesoMuertoRumano = new Ejercicio("Peso muerto rumano", TipoGrupoMuscular.TORSO_INFERIOR_ISQUIOTIBIALES,"Ejercicio de piernas,", 4,12);
        Ejercicio pressMilitar = new Ejercicio("Press militar", TipoGrupoMuscular.TORSO_SUPERIOR_HOMBROS,"Ejercicio de hombro que se peude hacer con mancuernas o con barra", 4,10);
        Ejercicio dominadas = new Ejercicio("Dominadas",TipoGrupoMuscular.TORSO_SUPERIOR_ESPALDA ,"Ejercicio de piernas, con maquina o con mancuernas", 4,15);
        Ejercicio gemelos = new Ejercicio("Gemelos", TipoGrupoMuscular.TORSO_INFERIOR_GEMELOS,"Ejercicio de piernas, con mancuerna", 4,12);
        Ejercicio facePull = new Ejercicio("Face pull", TipoGrupoMuscular.TORSO_SUPERIOR_HOMBROS,"Ejercicio de hombros, con polea", 4,12);
        Ejercicio zancadasCaminando = new Ejercicio("Zancadas caminando", TipoGrupoMuscular.TORSO_INFERIOR_CUADRICEPS,"Ejercicio de piernas, sin mancuerna", 4,15);
        Ejercicio curlBiceps = new Ejercicio("Curl biceps", TipoGrupoMuscular.TORSO_SUPERIOR_BICEPS,"Ejercicio de biceps, con mancuerna", 3,12);
        Ejercicio RDL = new Ejercicio("Romanian DeadLift - Peso Muerto", TipoGrupoMuscular.TORSO_INFERIOR_ISQUIOTIBIALES,"Ejercicio de piernas, variacion del peso muerto rumano", 4,12);
        Ejercicio hipThurst = new Ejercicio("Hip Trust, empuje de cadera", TipoGrupoMuscular.TORSO_INFERIOR_GLUTEOS,"Ejercicio de piernas, con barra y banca", 4,12);
        Ejercicio curlBarra = new Ejercicio("Curl Barra", TipoGrupoMuscular.TORSO_SUPERIOR_BICEPS,"Ejercicio de biceps, con barra", 4,12);
        Ejercicio burpees = new Ejercicio("Burpees", TipoGrupoMuscular.MULTIMUSCULAR,"Ejercicio multimuscular, buscar tocar las puntas de los pies hacer una flexion y saltar", 4,8);
        Ejercicio flexiones = new Ejercicio("Flexiones", TipoGrupoMuscular.MULTIMUSCULAR,"Ejercicio multimuscular", 4,8);
        Ejercicio planchas = new Ejercicio("Planchas", TipoGrupoMuscular.TORSO_MEDIO_ABDOMINALES,"Ejercicio de abdominales, hacer la plancha paralelo al suelo con el abdomen", 6,30);
        Ejercicio fondos = new Ejercicio("Fondos", TipoGrupoMuscular.TORSO_SUPERIOR_TRICEPS,"Ejercicio de tricpes y pecho, se hace con barra paralelas o con banco plano", 6,10);
        Ejercicio flexionesPike = new Ejercicio("Flexiones Pike", TipoGrupoMuscular.TORSO_SUPERIOR_HOMBROS,"Ejercicio de hombros, son como flexiones pero con la cola levantada", 5,10);
        Ejercicio pistols = new Ejercicio("Pistols", TipoGrupoMuscular.MULTIMUSCULAR,"Ejercicio de piernas, son sentadillas pero a una pierna", 4,12);
        Ejercicio hollowBody = new Ejercicio("Hollow Body", TipoGrupoMuscular.MULTIMUSCULAR,"Ejercicio de multimuscular, acostado y subis baros y piernas sin tocar el piso", 5,15);
        Ejercicio pressInclinado = new Ejercicio("Prees Inclinado", TipoGrupoMuscular.TORSO_SUPERIOR_PECHO,"Ejercicio de pecho, con barra y banco inclinada", 4,10);
        Ejercicio apertura = new Ejercicio("Apertura", TipoGrupoMuscular.TORSO_SUPERIOR_PECHO,"Ejercicio de pecho, con mancuerna y banco plano", 4,10);
        Ejercicio jalon = new Ejercicio("Jalon", TipoGrupoMuscular.TORSO_SUPERIOR_ESPALDA,"Ejercicio de espalda, con maquina de jalon", 4,12);
        Ejercicio pajaros = new Ejercicio("Pajaros", TipoGrupoMuscular.TORSO_SUPERIOR_HOMBROS,"Ejercicio de hombros, con mancuernas y banco, sentado con la cabeza a las rodillas y abriendo los brazos", 4,12);
        Ejercicio encogimiento = new Ejercicio("Encogimiento", TipoGrupoMuscular.TORSO_SUPERIOR_HOMBROS,"Ejercicio de hombro, con mancuerna o barra", 6,8);
        Ejercicio curlInclinado = new Ejercicio("Curl Inclinado", TipoGrupoMuscular.TORSO_SUPERIOR_BICEPS,"Ejercicio de biceps, con mancuerna y banco inclinado", 4,10);
        Ejercicio pressCerrado = new Ejercicio("Press Cerrado", TipoGrupoMuscular.TORSO_SUPERIOR_TRICEPS,"Ejercicio de triceps, con barra y banco plano", 4,10);
        Ejercicio jalonTriceps = new Ejercicio("Jalon Triceps", TipoGrupoMuscular.TORSO_SUPERIOR_TRICEPS,"Ejercicio de triceps, con poleas", 4,10);
        Ejercicio powerClean = new Ejercicio("Power Clean", TipoGrupoMuscular.MULTIMUSCULAR,"Ejercicio de principalmente piernas, biceps y hombros. Con barra, se hace sentadilla y se termina power arriba", 3,8);
        Ejercicio pushPress = new Ejercicio("Push Press", TipoGrupoMuscular.MULTIMUSCULAR,"Ejercicio de hombros, triceps y trapecio. Con barra", 3,8);
        Ejercicio pesoMuerto = new Ejercicio("Peso Muerto", TipoGrupoMuscular.TORSO_INFERIOR_ISQUIOTIBIALES,"Ejercicio de isquiotibiales. Con barra", 4,10);
        Ejercicio laterales = new Ejercicio("Laterales", TipoGrupoMuscular.TORSO_SUPERIOR_HOMBROS,"Ejercicio de hombros, con mancuernas", 4,10);
        Ejercicio prensa = new Ejercicio("Prensa", TipoGrupoMuscular.TORSO_INFERIOR_ISQUIOTIBIALES,"Ejercicio de piernas, con maquina de prensa", 4,10);

        fullBody.guardarEjercicio(sentadilla);
        fullBody.guardarEjercicio(pressBanca);
        fullBody.guardarEjercicio(remoConMancuera);
        fullBody.guardarEjercicio(pesoMuertoRumano);
        fullBody.guardarEjercicio(pressMilitar);
        fullBody.guardarEjercicio(dominadas);

        upperLower.guardarEjercicio(pressBanca);
        upperLower.guardarEjercicio(pressMilitar);
        upperLower.guardarEjercicio(dominadas);
        upperLower.guardarEjercicio(remoConMancuera);
        upperLower.guardarEjercicio(sentadilla);
        upperLower.guardarEjercicio(RDL);
        upperLower.guardarEjercicio(hipThurst);
        upperLower.guardarEjercicio(gemelos);

        pushPullLegs.guardarEjercicio(pressBanca);
        pushPullLegs.guardarEjercicio(pressMilitar);
        pushPullLegs.guardarEjercicio(fondos);
        pushPullLegs.guardarEjercicio(laterales);
        pushPullLegs.guardarEjercicio(jalonTriceps);
        pushPullLegs.guardarEjercicio(dominadas);
        pushPullLegs.guardarEjercicio(remoConMancuera);
        pushPullLegs.guardarEjercicio(facePull);
        pushPullLegs.guardarEjercicio(curlBiceps);
        pushPullLegs.guardarEjercicio(sentadilla);
        pushPullLegs.guardarEjercicio(RDL);
        pushPullLegs.guardarEjercicio(hipThurst);
        pushPullLegs.guardarEjercicio(gemelos);
        pushPullLegs.guardarEjercicio(zancadasCaminando);

        fuerza.guardarEjercicio(sentadilla);
        fuerza.guardarEjercicio(pressBanca);
        fuerza.guardarEjercicio(remoConMancuera);
        fuerza.guardarEjercicio(pressMilitar);
        fuerza.guardarEjercicio(pesoMuerto);

        hipertrofiaClasica.guardarEjercicio(pressBanca);
        hipertrofiaClasica.guardarEjercicio(pressInclinado);
        hipertrofiaClasica.guardarEjercicio(apertura);
        hipertrofiaClasica.guardarEjercicio(fondos);
        hipertrofiaClasica.guardarEjercicio(dominadas);
        hipertrofiaClasica.guardarEjercicio(remoConMancuera);
        hipertrofiaClasica.guardarEjercicio(jalon);
        hipertrofiaClasica.guardarEjercicio(facePull);
        hipertrofiaClasica.guardarEjercicio(sentadilla);
        hipertrofiaClasica.guardarEjercicio(prensa);
        hipertrofiaClasica.guardarEjercicio(RDL);
        hipertrofiaClasica.guardarEjercicio(gemelos);
        hipertrofiaClasica.guardarEjercicio(pressMilitar);
        hipertrofiaClasica.guardarEjercicio(laterales);
        hipertrofiaClasica.guardarEjercicio(pajaros);
        hipertrofiaClasica.guardarEjercicio(encogimiento);
        hipertrofiaClasica.guardarEjercicio(curlBarra);
        hipertrofiaClasica.guardarEjercicio(curlInclinado);
        hipertrofiaClasica.guardarEjercicio(pressCerrado);
        hipertrofiaClasica.guardarEjercicio(jalonTriceps);

        rutinaEnCircuito.guardarEjercicio(burpees);
        rutinaEnCircuito.guardarEjercicio(dominadas);
        rutinaEnCircuito.guardarEjercicio(flexiones);
        rutinaEnCircuito.guardarEjercicio(sentadilla);
        rutinaEnCircuito.guardarEjercicio(planchas);
        rutinaEnCircuito.guardarEjercicio(zancadasCaminando);

        calistenia.guardarEjercicio(dominadas);
        calistenia.guardarEjercicio(fondos);
        calistenia.guardarEjercicio(flexionesPike);
        calistenia.guardarEjercicio(planchas);
        calistenia.guardarEjercicio(pistols);
        calistenia.guardarEjercicio(zancadasCaminando);
        calistenia.guardarEjercicio(hipThurst);
        calistenia.guardarEjercicio(hollowBody);

        fuerzaPotencia.guardarEjercicio(powerClean);
        fuerzaPotencia.guardarEjercicio(pushPress);
        fuerzaPotencia.guardarEjercicio(sentadilla);
        fuerzaPotencia.guardarEjercicio(dominadas);
        fuerzaPotencia.guardarEjercicio(pressInclinado);
        fuerzaPotencia.guardarEjercicio(gemelos);

        gestor.altaItem(fullBody);
        gestor.altaItem(upperLower);
        gestor.altaItem(pushPullLegs);
        gestor.altaItem(fuerza);
        gestor.altaItem(hipertrofiaClasica);
        gestor.altaItem(rutinaEnCircuito);
        gestor.altaItem(calistenia);
        gestor.altaItem(fuerzaPotencia);
    }



}