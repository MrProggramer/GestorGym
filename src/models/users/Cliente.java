package models.users;

import interfaces.Identificable;
import models.rutinas.Rutina;

public class Cliente extends User implements Identificable {
    private String id;

    @Override
    public String getId() {
        return id;
    }

}
