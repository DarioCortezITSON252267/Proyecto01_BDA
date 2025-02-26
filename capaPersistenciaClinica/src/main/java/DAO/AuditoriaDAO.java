/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.IConexionBD;
import Exception.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que maneja la auditoría de las acciones realizadas por los médicos.
 * Se conecta a la base de datos para obtener información de auditoría específica.
 */
public class AuditoriaDAO implements IAuditoriaDAO {

    private final IConexionBD conexion; // Instancia de conexión a la base de datos
    private static final Logger logger = Logger.getLogger(AuditoriaDAO.class.getName());

    /**
     * Constructor de la clase AuditoriaDAO.
     * @param conexion Instancia de conexión a la base de datos.
     */
    public AuditoriaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    /**
     * Obtiene el historial de auditoría de un médico a partir de su cédula.
     * 
     * @param cedula Cédula del médico cuya auditoría se desea consultar.
     * @return Lista de registros de auditoría en formato de cadena.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    public List<String> verAuditoriaPorMedico(String cedula) throws PersistenciaException {
        List<String> auditorias = new ArrayList<>();
        String query = "CALL VerAuditoriaPorMedico(?)"; // Llamada al procedimiento almacenado

        try (Connection conn = conexion.crearConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String hora = rs.getTimestamp("hora").toLocalDateTime().toString();
                int idPaciente = rs.getInt("id_paciente");
                String estado = rs.getString("estado");
                String tipo = rs.getString("tipo");

                // Formateo del registro de auditoría
                String auditoria = "Hora: " + hora + ", ID Paciente: " + idPaciente +
                                  ", Estado: " + estado + ", Tipo: " + tipo;
                auditorias.add(auditoria);
            }

            System.out.println("Cédula ingresada: " + cedula);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener auditoría por médico", e);
            throw new PersistenciaException("Error al obtener auditoría por médico", e);
        }

        return auditorias;
    }
}