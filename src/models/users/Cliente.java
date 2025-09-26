package models.users;

import models.rutinas.Rutina;

public class Cliente extends User{
    private Rutina rut;

    public void setRut(Rutina rut) {
        this.rut = rut;
    }
}
