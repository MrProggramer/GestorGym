package models.database;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControlData<T> {
    public void guardarData(String nombreArchivo, Map<Integer,T> data){
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter escribir = new PrintWriter(archivo);
            for(T dato : data.values()){
                escribir.println(dato);
            }
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
                datos.put(Integer.parseInt(linea.split(" ")[0]), (T) linea);
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
