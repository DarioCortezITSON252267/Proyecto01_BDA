/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Medico;
import Exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Angel
 */
public interface IMedicoDAO {

//    public Activista agregarActivista(Activista activista) throws PersistenciaException;
    boolean desactivarMedico(int idMedico) throws PersistenciaException;
    
    
//  ESTE METODO NO ESTA FUNCIONANDO POR EL MOMENTO  
//    List<String> verAgendaMedico(int idMedico) throws PersistenciaException;
}
