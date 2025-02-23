/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.IConexionBD;
import Exception.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angel
 */
public class MedicoDAO implements IMedicoDAO{

    private IConexionBD conexion; // Atributo conexión que se usará en toda la clase
    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());

    public MedicoDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

   @Override
    public boolean desactivarMedico(int idMedico) throws PersistenciaException {
        String sql = "{CALL DesactivarMedico(?)}"; // 1 parámetro

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {
            
            // Establecer parámetros en el procedimiento almacenado
            cs.setInt(1, idMedico); // ID del médico a desactivar

            cs.execute();
            logger.log(Level.INFO, "Médico desactivado exitosamente");
            return true;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al desactivar el médico", ex);
            throw new PersistenciaException("Error al desactivar el médico", ex);
        }
    }

}
