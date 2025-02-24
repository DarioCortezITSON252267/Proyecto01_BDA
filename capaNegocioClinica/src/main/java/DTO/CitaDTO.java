/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author chris
 */
public class CitaDTO {

    private int idCita;
    private String estado;
    private LocalDateTime fechaHora;
    private String nota;
    private int idMedico;
    private String especialidad;
    private String medico;
    private int idPaciente;
    private String pacienteNombre;
    private String pacienteApellido;
    private String pacienteUsuario;

    public CitaDTO(int idCita, String estado, LocalDateTime fechaHora, String nota,
            int idMedico, String especialidad, String medico,
            int idPaciente, String pacienteNombre, String pacienteApellido, String pacienteUsuario) {
        this.idCita = idCita;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.nota = nota;
        this.idMedico = idMedico;
        this.especialidad = especialidad;
        this.medico = medico;
        this.idPaciente = idPaciente;
        this.pacienteNombre = pacienteNombre;
        this.pacienteApellido = pacienteApellido;
        this.pacienteUsuario = pacienteUsuario;
    }

    // Getters y Setters
    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public String getPacienteApellido() {
        return pacienteApellido;
    }

    public void setPacienteApellido(String pacienteApellido) {
        this.pacienteApellido = pacienteApellido;
    }

    public String getPacienteUsuario() {
        return pacienteUsuario;
    }

    public void setPacienteUsuario(String pacienteUsuario) {
        this.pacienteUsuario = pacienteUsuario;
    }
}
