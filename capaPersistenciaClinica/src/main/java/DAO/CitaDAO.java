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

/**
 *
 * @author Usuario
 */
public class CitaDAO implements ICitaDAO {

    private IConexionBD conexion;

    public CitaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<String> verHistorialCitas(int idPaciente) throws PersistenciaException {
        List<String> historial = new ArrayList<>();
        String sql = "CALL VerHistorialCitas(?)";

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idPaciente);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                int idCita = rs.getInt("id_cita");
                String estado = rs.getString("estado");
                Timestamp fechahora = rs.getTimestamp("fechahora");
                String nota = rs.getString("nota");
                int idMedico = rs.getInt("id_medico");
                String especialidad = rs.getString("especialidad");
                String medico = rs.getString("medico");

                historial.add("Cita ID: " + idCita + ", Estado: " + estado + ", Fecha y Hora: " + fechahora + ", Nota: " + nota
                        + ", Médico ID: " + idMedico + ", Especialidad: " + especialidad + ", Médico: " + medico);
            }
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al obtener el historial de citas", ex);
        }
        return historial;
    }

    @Override
    public List<String> verHistorialCitasMedico(int idMedico) throws PersistenciaException {
        List<String> historial = new ArrayList<>();
        String sql = "CALL VerHistorialCitasMedico(?)";

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idMedico);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                int idCita = rs.getInt("id_cita");
                String estado = rs.getString("estado");
                Timestamp fechahora = rs.getTimestamp("fechahora");
                String nota = rs.getString("nota");
                int idPaciente = rs.getInt("id_paciente");
                String pacienteNombre = rs.getString("paciente_nombre");
                String pacienteApellido = rs.getString("paciente_apellido");
                String pacienteUsuario = rs.getString("paciente_usuario");

                historial.add("Cita ID: " + idCita + ", Estado: " + estado + ", Fecha y Hora: " + fechahora + ", Nota: " + nota
                        + ", Paciente ID: " + idPaciente + ", Nombre: " + pacienteNombre + " " + pacienteApellido + ", Usuario: " + pacienteUsuario);
            }
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al obtener el historial de citas del médico", ex);
        }
        return historial;
    }

}
