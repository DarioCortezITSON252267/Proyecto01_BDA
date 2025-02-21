/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;

/**
 *
 * @author Todos
 */
public class Paciente {
    
    private int id_paciente;
    private LocalDate fecha_nacimiento;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String telefono;
    private String correo;
    private Usuario usuario;
    private Direccion direccion;
    
 // Constructor vacío
    public Paciente() {
    }

    // Constructor con todos los atributos
    public Paciente(int id_paciente, LocalDate fecha_nacimiento, String nombre, String apellido_paterno, String apellido_materno, String telefono, String correo, Usuario usuario, Direccion direccion) {
        this.id_paciente = id_paciente;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.telefono = telefono;
        this.correo = correo;
        this.usuario = usuario;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    @Override
    public String toString() {
        return "Paciente{" + "id_paciente=" + id_paciente + ", fecha_nacimiento=" + fecha_nacimiento + ", nombre='" + nombre + '\'' +
        ", apellido_paterno='" + apellido_paterno + '\'' + ", apellido_materno='" + apellido_materno + '\'' + ", telefono='" + telefono + '\'' +
        ", correo='" + correo + '\'' +", usuario=" + usuario + ", direccion=" + direccion + '}';
    }
}

