package models.database;

import gestores.GenericGestor;
import interfaces.Identificable;
import models.utils.Utilidades;
import org.json.JSONArray;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public abstract class ControlData{

    public static <T extends Identificable> void guardarData(GenericGestor<T> gestor, String nombreArchivo){
        File archivo = new File(nombreArchivo);
        String path = "src/data/".concat(nombreArchivo+".json");

        try {
            PrintWriter escribir = new PrintWriter(path);
            escribir.println("[");
            for(int i = 0; i < gestor.getInventario().size(); i++){
                escribir.println(Utilidades.ObjectToJSON(gestor.getInventario().get(i)));
                if(i < gestor.getInventario().size() -1 ){
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
        String path = "src/data/".concat(nombreArchivo+".json");
        try{
            String data = new String(Files.readAllBytes(Paths.get(path)));
            return new JSONArray(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
