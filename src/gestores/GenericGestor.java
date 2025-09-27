package gestores;

import interfaces.Identificable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GenericGestor<T extends Identificable>{    //al generico T, se le pone extends "Identificable" para que
                                                        //entenda que el T tiene un getId(); el cual esta en la interfaz identificable.
    Map<String, T> gestor;

    public GenericGestor(Map<String, T> gestor) {
        this.gestor = new HashMap<>();
    }

    public Map<String, T> getGestor() {
        return gestor;
    }

    public void setGestor(Map<String, T> gestor) {
        this.gestor = gestor;
    }

    @Override
    public String toString() {
        return "gestores.GenericGestor{" +
                "gestor=" + gestor +
                '}';
    }

    public void altaItem(String id_item, T item){
        gestor.put(id_item, item);
        //agregar un catch con error personalizado
    }
    public void bajaItem(String item_id){
        gestor.remove(item_id);
        //agregar un catch con error personalizado
    }
    public T buscarItem(String id_item){
        Iterator<Map.Entry<String, T>> it = gestor.entrySet().iterator();
        while(it.hasNext()){
            T item = it.next().getValue();
            //if(item.)  --- como hago para hacerle saber que el generico tiene un getId() ... ?
            //ya lo averigue.. mirar el comentario de mas arriba
            if(item.getId().equalsIgnoreCase(id_item)){
                return item;
            }
        }
        return null; // cambiar por un try catch con error personalizado
    }
}
