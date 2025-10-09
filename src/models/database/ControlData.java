package models.database;

import gestores.GenericGestor;
import interfaces.Identificable;
import interfaces.Registrable;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.utils.Utilidades;
import org.json.JSONArray;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

//MODIFICAR PARA UTILIZAR JSON

public abstract class ControlData{

    public static <T extends Identificable> void guardarData(GenericGestor<T> gestor, String nombreArchivo){
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter escribir = new PrintWriter(archivo);
            escribir.println("[");
            for(int i=0; i < gestor.getGestor().size(); i++){
                escribir.println(Utilidades.ObjectToJSON(gestor.getGestor().get(i)));
                if(i < gestor.getGestor().size() -1 ){
                    escribir.print(",");
                }
            }
            escribir.println("]");
            escribir.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static<T extends Identificable> Map<Integer, T> recuperarData(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        Map<Integer, T> datos = new HashMap<>();
        try {   
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String linea = entrada.readLine();
            while(linea != null){
                //datos.put(Integer.parseInt(linea.split(" ")[0]), (T) linea);
                //Hay que hacer un metodo que recree el string del obj al obj real para asi guardarlo en el map.
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datos;
    }

}
