/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.ConsultaSinCita;
import Entidades.Paciente;
import Exception.PersistenciaException;

/**
 *
 * @author Usuario
 */
public interface IConsultaSinCitaDAO {
    
    void agendarCitaSinConsulta(String nota, int idPaciente) throws PersistenciaException;
     
}
