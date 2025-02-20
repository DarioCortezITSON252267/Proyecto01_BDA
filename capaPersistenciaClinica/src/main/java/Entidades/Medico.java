/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author chris
 */
public class Medico {

    private int idMedico;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String especialidad;
    private String celuda;
    private String estado;
    private String contrasenia;

    public Medico(int idMedico, String nombre, String apellidoPaterno, String apellidoMaterno, String especialidad, String celuda, String estado, String contrasenia) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidad = especialidad;
        this.celuda = celuda;
        this.estado = estado;
        this.contrasenia = contrasenia;
    }

    //CONSTRUCOTR SIN ID
    public Medico(String nombre, String apellidoPaterno, String apellidoMaterno, String especialidad, String celuda, String estado, String contrasenia) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidad = especialidad;
        this.celuda = celuda;
        this.estado = estado;
        this.contrasenia = contrasenia;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCeluda() {
        return celuda;
    }

    public void setCeluda(String celuda) {
        this.celuda = celuda;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Medico{" + "idMedico=" + idMedico + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", especialidad=" + especialidad + ", celuda=" + celuda + ", estado=" + estado + ", contrasenia=" + contrasenia + '}';
    }
    
    
}
