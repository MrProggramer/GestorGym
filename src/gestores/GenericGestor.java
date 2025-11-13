package gestores;

import Exceptions.InvalidTypeException;
import interfaces.Identificable;
import models.database.ControlData;
import models.rutinas.Rutina;
import models.users.User;
import models.utils.Utilidades;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class GenericGestor<T extends Identificable>{
    private final String type;
    List<T> inventario;

    public GenericGestor() {
        this.inventario = new ArrayList<>();
        this.type = getClass().getTypeName();
    }
    public GenericGestor(List<Rutina> rutinas){
        this.type = getClass().getTypeName();
        this.inventario = new ArrayList<>();
    }

    public List<T> getInventario() {
        return inventario;
    }

    public void setInventario(List<T> inventario) {
        this.inventario = inventario;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "gestores.GenericGestor{" +
                "gestor=" + inventario +
                '}';
    }

    public void altaItem(T item){
        inventario.add(item);
    }
    public void bajaItem(int item_id){
        for(T elem : inventario){
            if(elem.getId() == item_id && elem instanceof User u){
                    u.setActive(false);
            }
        }
    }
    public T buscarItem(int id_item){
        for(T e : inventario){
            if(e.getId() == id_item){
                return e;
            }
        }
        return null;
    }

    public void borrarItem(int item_id) {
        ListIterator<T> it = inventario.listIterator();

        while (it.hasNext()) {
            if(it.next().getId() == item_id){
                it.remove();
            }
        }
    }

    public void actualizarGestor(String archivo) throws InvalidTypeException { //ESTÁ MAL HECHA, CAMBIAR A FUTURO
        JSONArray data = ControlData.recuperarData(archivo); //VERIFICAR QUE LLEGUE LA INFO
        this.inventario.clear();
        for(Object e : data){
            if(e instanceof JSONObject obj){
                switch (archivo.toUpperCase()){
                    case "EJERCICIOS" -> this.altaItem((T) Utilidades.crearEjercicioFromJSON(obj));
                    case "RUTINAS" -> this.altaItem((T) Utilidades.crearRutinaFromJSON(obj));
                    case "USERS" -> this.altaItem((T) Utilidades.crearUserFromJSON(obj));
                    default -> throw new InvalidTypeException("tipo invalido "+ archivo);
                }
            }
        }
    }

}


