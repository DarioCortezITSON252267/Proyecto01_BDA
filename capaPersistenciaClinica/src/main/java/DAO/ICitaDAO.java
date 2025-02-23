/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface ICitaDAO {
    
      List<String> verHistorialCitas(int idPaciente) throws PersistenciaException;
      
      List<String> verHistorialCitasMedico(int idMedico) throws PersistenciaException;
}
