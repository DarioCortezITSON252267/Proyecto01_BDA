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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ConsultaSinCitaDAO implements IConsultaSinCitaDAO {

    IConexionBD conexion; // atributo conexion que se usara toda la clase. Recibiremos cualquer objeto que implemente IConexionBD

    public ConsultaSinCitaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }
    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());

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
