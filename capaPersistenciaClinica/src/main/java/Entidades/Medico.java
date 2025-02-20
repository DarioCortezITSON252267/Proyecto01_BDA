/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Angel
 */
public class Medico {
    
//    
//    id_medico INT AUTO_INCREMENT PRIMARY KEY,
//    especialidad VARCHAR(100) NOT NULL,
//    cedula VARCHAR(50) UNIQUE NOT NULL,
//    id_usuario INT UNIQUE NOT NULL,
    
    private int id_medico;
    private String especialidad;
    private String cedula;
    private int id_usuario;

    public Medico() {
    }

    public Medico(int id_medico, String especialidad, String cedula, int id_usuario) {
        this.id_medico = id_medico;
        this.especialidad = especialidad;
        this.cedula = cedula;
        this.id_usuario = id_usuario;
    }

    public Medico(String especialidad, String cedula, int id_usuario) {
        this.especialidad = especialidad;
        this.cedula = cedula;
        this.id_usuario = id_usuario;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "Medico{" + "id_medico=" + id_medico + ", especialidad=" + especialidad + ", cedula=" + cedula + ", id_usuario=" + id_usuario + '}';
    }
    
}
