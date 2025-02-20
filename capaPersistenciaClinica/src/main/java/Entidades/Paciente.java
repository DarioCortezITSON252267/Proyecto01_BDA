/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;

/**
 *
 * @author Angel
 */
public class Paciente {
//    
//       id_paciente INT AUTO_INCREMENT PRIMARY KEY,
//    fecha_nacimiento DATE NOT NULL,
//    id_usuario INT UNIQUE NOT NULL,
//    id_direccion INT NOT NULL,
    private int id_paciente;
    private LocalDate fecha_nacimiento;
    private int id_usuario;
    private int id_direccion;

    public Paciente() {
    }

    public Paciente(int id_paciente, LocalDate fecha_nacimiento, int id_usuario, int id_direccion) {
        this.id_paciente = id_paciente;
        this.fecha_nacimiento = fecha_nacimiento;
        this.id_usuario = id_usuario;
        this.id_direccion = id_direccion;
    }

    public Paciente(LocalDate fecha_nacimiento, int id_usuario, int id_direccion) {
        this.fecha_nacimiento = fecha_nacimiento;
        this.id_usuario = id_usuario;
        this.id_direccion = id_direccion;
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

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(int id_direccion) {
        this.id_direccion = id_direccion;
    }

    @Override
    public String toString() {
        return "Paciente{" + "id_paciente=" + id_paciente + ", fecha_nacimiento=" + fecha_nacimiento + ", id_usuario=" + id_usuario + ", id_direccion=" + id_direccion + '}';
    }
    
}
