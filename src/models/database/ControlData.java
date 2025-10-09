package models.database;

import gestores.GenericGestor;
import interfaces.Identificable;
import models.utils.Utilidades;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

//MODIFICAR PARA UTILIZAR JSON

public abstract class ControlData{

    public static <T extends Identificable> void guardarData(GenericGestor<T> gestor, String nombreArchivo){
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter escribir = new PrintWriter(archivo);
            for(T elem : gestor.getGestor().values()){
                escribir.println(Utilidades.ObjectToJSON(elem));
            }
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
