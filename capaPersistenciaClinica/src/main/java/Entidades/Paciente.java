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

    private int idPaciente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String correoElectronico;
    private String contrasenia;
    private String telefono;
    private String calle;
    private String colonia;
    private String numero;

    public Paciente(int idPaciente, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String correoElectronico, String contrasenia, String telefono, String calle, String colonia, String numero) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
    }

    //CONSTRUCTOR SIN ID
    public Paciente(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String correoElectronico, String contrasenia, String telefono, String calle, String colonia, String numero) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
    }

    
    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Paciente{" + "idPaciente=" + idPaciente + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", correoElectronico=" + correoElectronico + ", contrasenia=" + contrasenia + ", telefono=" + telefono + ", calle=" + calle + ", colonia=" + colonia + ", numero=" + numero + '}';
    }
    
    
}
