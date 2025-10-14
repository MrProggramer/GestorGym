package gestores;

import exceptions.Exc_ItemExistente;
import exceptions.Exc_ItemNoEncontrado;
import interfaces.Identificable;
import java.util.HashMap;
import java.util.Map;

public class    GenericGestor<T extends Identificable>{
    private final String type;
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

    public void altaItem(T item) throws Exc_ItemExistente {
        if (gestor.containsKey(item.getId())) {
            throw new Exc_ItemExistente("Ya existe un item con ese ID");
        }
        gestor.put(item.getId(), item);
    }

    public void bajaItem(int item_id) throws Exc_ItemNoEncontrado {
        if (!gestor.containsKey(item_id)) {
            throw new Exc_ItemNoEncontrado("No existe un item con ese ID");
        }
        gestor.remove(item_id);
    }

    public T buscarItem(int id_item) throws Exc_ItemNoEncontrado {
        T item = gestor.get(id_item);
        if (item == null) {
            throw new Exc_ItemNoEncontrado("No se encontró ningún item con ese ID");
        }
        return item;
    }

    public void modificarItem(T itemModificado) throws Exc_ItemNoEncontrado {
        int id = itemModificado.getId();
        if (!gestor.containsKey(id)) {
            throw new Exc_ItemNoEncontrado("No se encontró el item para modificar");
        }
        gestor.put(id, itemModificado);
    }
}

