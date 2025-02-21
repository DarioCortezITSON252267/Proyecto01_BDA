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
public class Paciente extends Usuario{
   private int id_paciente;
    private LocalDate fecha_nacimiento;
    private Direccion direccion;

    // Constructor vacío
    public Paciente() {}

    // Constructor completo
    public Paciente(int id_paciente, String nombre, String apellidoPaterno, String apellidoMaterno,
                    String telefono, String correo, LocalDate fechaNacimiento, Direccion direccion) {
        super(nombre, apellidoPaterno, apellidoMaterno, telefono, correo); // Llamada al constructor de Usuario
        this.id_paciente = id_paciente;
        this.fecha_nacimiento = fechaNacimiento;
        this.direccion = direccion;
    }

    // Constructor sin ID (para nuevos registros)
    public Paciente(String nombre, String apellidoPaterno, String apellidoMaterno,
                    String telefono, String correo, LocalDate fechaNacimiento, Direccion direccion) {
        super(nombre, apellidoPaterno, apellidoMaterno, telefono, correo); // Llamada al constructor de Usuario
        this.fecha_nacimiento = fechaNacimiento;
        this.direccion = direccion;
    }

    // Getters y Setters
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

    @Override
    public String toString() {
        return "Paciente{" +
               "id_paciente=" + id_paciente +
               ", fecha_nacimiento=" + fecha_nacimiento +
               ", direccion=" + direccion +
               ", " + super.toString() +  // Llama al toString() de Usuario
               '}';

}}
