/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;

/**
 *
 * @author chris
 */
public class Paciente {

//        id_paciente INT AUTO_INCREMENT PRIMARY KEY,
//    fecha_nacimiento DATE NOT NULL,
//    id_usuario INT UNIQUE NOT NULL,
//    id_direccion INT NOT NULL,
    private int id_paciente;
    private LocalDate fecha_nacimiento;
    private Direccion direccion;
    private Usuario usuario;

    // constructor vacio
    public Paciente() {
    }

    // constructor con todo
    public Paciente(int id_paciente, LocalDate fecha_nacimiento, Direccion direccion, Usuario usuario) {
        this.id_paciente = id_paciente;
        this.fecha_nacimiento = fecha_nacimiento;
        this.direccion = direccion;
        this.usuario = usuario;
    }

    public Paciente(LocalDate fecha_nacimiento, Direccion direccion, Usuario usuario) {
        this.fecha_nacimiento = fecha_nacimiento;
        this.direccion = direccion;
        this.usuario = usuario;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Paciente{" + "id_paciente=" + id_paciente + ", fecha_nacimiento=" + fecha_nacimiento + ", direccion=" + direccion + ", usuario=" + usuario + '}';
    }

}
