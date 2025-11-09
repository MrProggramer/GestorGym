package models.users;


import models.rutinas.Rutina;

import java.util.Scanner;

public class Profesor extends User{
//    // Para idependizarlo de Staff - ya que no queremos que tenga todos los metodos que tiene staff
//    private Admin admin;
//    private Cliente cliente;
//
//    public Profesor(Admin admin, Cliente cliente) {
//        this.admin = admin;
//        this.cliente = cliente;
//    }
    public Profesor(){
    }
//
//    public Admin getAdmin() {
//        return admin;
//    }
//
//    public void setAdmin(Admin admin) {
//        this.admin = admin;
//    }
//
//    public Cliente getCliente() {
//        return cliente;
//    }
//
//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }

    @Override
    public String toString() {
        return "Profesor{" +
//                "admin=" + admin +
//                ", cliente=" + cliente +
                '}';
    }

    // METODO --> asignar rutina
    public void asignarRutina(Rutina rutina){
        Rutina rutinaAAsignar = null;

    }

    @Override
    public User crear(Scanner sc) {
        return null;
    }
}
