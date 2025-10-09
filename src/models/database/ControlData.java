package models.database;

import gestores.GenericGestor;
import interfaces.Identificable;
import interfaces.Registrable;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.utils.Utilidades;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public static JSONArray recuperarData(String nombreArchivo){
        String path = "src/data/".concat(nombreArchivo);
        try{
            String data = new String(Files.readAllBytes(Paths.get(path)));
            return new JSONArray(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
