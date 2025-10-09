package gestores;

import interfaces.Identificable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GenericGestor<T extends Identificable>{    //al generico T, se le pone extends "Identificable" para que
    private final String type;                                //entienda que el T tiene un getId(); el cual esta en la interfaz identificable.
    Map<Integer, T> gestor;

    public GenericGestor() {
        this.gestor = new HashMap<>();
        this.type = getClass().getTypeName();
    }

    public Map<Integer, T> getGestor() {
        return gestor;
    }

    public void setGestor(Map<Integer, T> gestor) {
        this.gestor = gestor;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "gestores.GenericGestor{" +
                "gestor=" + gestor +
                '}';
    }

    public void altaItem(T item){
        gestor.put(item.getId(), item);
        //agregar un catch con error personalizado
    }
    public void bajaItem(int item_id){
        gestor.remove(item_id);
        //agregar un catch con error personalizado
    }
    public T buscarItem(int id_item){
        Iterator<Map.Entry<Integer, T>> it = gestor.entrySet().iterator();
        while(it.hasNext()){
            T item = it.next().getValue();
            if(item.getId() == id_item){
                return item;
            }
        }
        return null; // cambiar por un try catch con error personalizado
    }
}
