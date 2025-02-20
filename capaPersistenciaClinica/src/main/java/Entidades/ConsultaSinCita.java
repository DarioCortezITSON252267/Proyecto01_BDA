/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Usuario
 */
public class ConsultaSinCita {
//    
//     id_consin INT AUTO_INCREMENT PRIMARY KEY,
//    folio INT NOT NULL,
//    fecha DATE NOT NULL,
//    hora TIME NOT NULL,
//    id_consulta INT UNIQUE NOT NULL,
    private int id_consin;
    private int folio;
    private Date fecha;
    private Time hora;
    private int id_consulta;

    public ConsultaSinCita() {
    }

    public ConsultaSinCita(int id_consin, int folio, Date fecha, Time hora, int id_consulta) {
        this.id_consin = id_consin;
        this.folio = folio;
        this.fecha = fecha;
        this.hora = hora;
        this.id_consulta = id_consulta;
    }

    public ConsultaSinCita(int folio, Date fecha, Time hora, int id_consulta) {
        this.folio = folio;
        this.fecha = fecha;
        this.hora = hora;
        this.id_consulta = id_consulta;
    }

    public int getId_consin() {
        return id_consin;
    }

    public void setId_consin(int id_consin) {
        this.id_consin = id_consin;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    @Override
    public String toString() {
        return "ConsultaSinCita{" + "id_consin=" + id_consin + ", folio=" + folio + ", fecha=" + fecha + ", hora=" + hora + ", id_consulta=" + id_consulta + '}';
    }
    
}
