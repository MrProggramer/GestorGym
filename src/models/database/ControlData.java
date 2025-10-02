package models.database;

import interfaces.Identificable;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

//MODIFICAR PARA UTILIZAR JSON

public class ControlData<T extends Identificable> {
    public void guardarData(String nombreArchivo, Map<Integer,T> data){
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter escribir = new PrintWriter(archivo);
            for(Map.Entry<Integer, T> entry : data.entrySet()){
                escribir.println(entry.getKey()+"\t"+entry.getValue());
            }
            escribir.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, T> recuperarData(String nombreArchivo){
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
