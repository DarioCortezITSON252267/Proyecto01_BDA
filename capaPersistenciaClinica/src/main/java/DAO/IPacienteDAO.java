/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Paciente;
import Exception.PersistenciaException;

/**
 *
 * @author Usuario
 */
public interface IPacienteDAO {
    
    //    public Activista agregarActivista(Activista activista) throws PersistenciaException;
    public Paciente registrarPaciente(Paciente paciente) throws PersistenciaException;
    

} 
