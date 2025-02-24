/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.IConexionBD;
import Exception.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angel
 */
public class MedicoDAO implements IMedicoDAO {

    private IConexionBD conexion; // Atributo conexión que se usará en toda la clase
    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());

    public MedicoDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean desactivarMedico(int idMedico) throws PersistenciaException {
        String sql = "CALL DesactivarMedico(?)"; // 1 parámetro

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
    
    
//      ESTE METODO NO ESTA FUNCIONANDO TODAVIA
    
//    @Override
//    public List<String> verAgendaMedico(int idMedico) throws PersistenciaException {
//        List<String> agenda = new ArrayList<>();
//        String sql = "CALL VerAgendaFuturaMedico(?)";
//
//        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {
//            cs.setInt(1, idMedico);
//            ResultSet rs = cs.executeQuery();
//
//            while (rs.next()) {
//                int idCita = rs.getInt("id_cita");
//                String estado = rs.getString("estado");
//                Timestamp fechahora = rs.getTimestamp("fechahora");
//                String nota = rs.getString("nota");
//                int idPaciente = rs.getInt("id_paciente");
//                String pacienteNombre = rs.getString("paciente_nombre");
//                String pacienteApellido = rs.getString("paciente_apellido");
//                String pacienteUsuario = rs.getString("paciente_usuario");
//
//                agenda.add("Cita ID: " + idCita + ", Estado: " + estado + ", Fecha y Hora: " + fechahora + ", Nota: " + nota
//                        + ", Paciente ID: " + idPaciente + ", Nombre: " + pacienteNombre + " " + pacienteApellido + ", Usuario: " + pacienteUsuario);
//            }
//        } catch (SQLException ex) {
//            throw new PersistenciaException("Error al obtener la agenda futura del médico", ex);
//        }
//        return agenda;
//    }
}
