/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */
public class Cita {
    
    private int idCita;
    private String estado; // 'pendiente', 'confirmada', 'cancelada'
    private LocalDateTime fechaHora;
    private String nota;
    private int idPaciente;
    private int idMedico;

    public Cita() {
    }

    public Cita(int idCita, String estado, LocalDateTime fechaHora, String nota, int idPaciente, int idMedico) {
        this.idCita = idCita;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.nota = nota;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
    }

    public Cita(String estado, LocalDateTime fechaHora, String nota, int idPaciente, int idMedico) {
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.nota = nota;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
    }

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

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    @Override
    public String toString() {
        return "Cita{" + "idCita=" + idCita + ", estado=" + estado + ", fechaHora=" + fechaHora + ", nota=" + nota + ", idPaciente=" + idPaciente + ", idMedico=" + idMedico + '}';
    }
    
}
