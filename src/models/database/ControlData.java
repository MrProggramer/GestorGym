package models.database;

import interfaces.Identificable;

import java.io.File;

public class ControlData<T> {
    private String name;
    private final File archivo;

    public ControlData(String name) {
        this.name = name;
        this.archivo = new File(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getArchivo() {
        return archivo;
    }

    @Override
    public String toString() {
        return "ControlData{" +
                "name='" + name + '\'' +
                ", archivo=" + archivo +
                '}';
    }
}
