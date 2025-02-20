/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.IConexionBD;
import Entidades.ConsultaSinCita;
import Exception.PersistenciaException;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ConsultaSinCitaDAO implements IConsultaSinCitaDAO{
    
    IConexionBD conexion; // atributo conexion que se usara toda la clase. Recibiremos cualquer objeto que implemente IConexionBD

    public ConsultaSinCitaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }
    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());

    @Override
    public ConsultaSinCita agregarConsultaSinCita(ConsultaSinCita consultaSinCita) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
