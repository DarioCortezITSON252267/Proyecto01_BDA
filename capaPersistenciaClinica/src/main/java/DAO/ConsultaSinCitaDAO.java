/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.IConexionBD;
import Entidades.ConsultaSinCita;
import Exception.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Clase que implementa la interfaz IConsultaSinCitaDAO para gestionar las
 * consultas sin cita en la base de datos.
 * 
 * Proporciona métodos para agendar consultas sin cita asociadas a un paciente.
 */
public class ConsultaSinCitaDAO implements IConsultaSinCitaDAO {

    /**
     * Objeto de conexión a la base de datos.
     * Se recibe cualquier implementación de IConexionBD.
     */
    private final IConexionBD conexion;
    
    /**
     * Logger para registrar eventos y errores de la clase.
     */
    private static final Logger logger = Logger.getLogger(ConsultaSinCitaDAO.class.getName());

    /**
     * Constructor de la clase ConsultaSinCitaDAO.
     * 
     * @param conexion Objeto que implementa IConexionBD y proporciona acceso a la base de datos.
     */
    public ConsultaSinCitaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    /**
     * Método para agendar una consulta sin cita para un paciente.
     * 
     * Llama al procedimiento almacenado "AgendarCitaSinConsulta" en la base de datos.
     * 
     * @param nota Nota o descripción de la consulta.
     * @param idPaciente Identificador del paciente asociado a la consulta.
     * @throws PersistenciaException Si ocurre un error al ejecutar la operación en la base de datos.
     */
    @Override
    public void agendarCitaSinConsulta(String nota, int idPaciente) throws PersistenciaException {
        String sqlAgendarConsulta = "CALL AgendarCitaSinConsulta(?, ?)";

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sqlAgendarConsulta)) {

            // Asignar parámetros al procedimiento almacenado
            cs.setString(1, nota);
            cs.setInt(2, idPaciente);

            // Ejecutar el procedimiento almacenado
            cs.execute();

            System.out.println("Consulta sin cita registrada exitosamente con un médico de Medicina General.");

        } catch (SQLException ex) {
            throw new PersistenciaException("Error al agendar la consulta sin cita: " + ex.getMessage(), ex);
        }
    }
}

