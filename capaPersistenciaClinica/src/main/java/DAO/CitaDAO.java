/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.IConexionBD;
import Exception.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
            Timestamp timestamp = rs.getTimestamp("fechahora");
            LocalDateTime fechaHora = timestamp.toLocalDateTime();
            String nota = rs.getString("nota");
            int idMedico = rs.getInt("id_medico");
            String especialidad = rs.getString("especialidad");

            historial.add(idCita + ", " + estado + ", " + fechaHora + ", " + (nota != null ? nota : "Sin nota") + ", " + idMedico + ", " + especialidad);
        }
    } catch (SQLException ex) {
        throw new PersistenciaException("Error al obtener historial de citas", ex);
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

    @Override
    public void agendarCita(int idPaciente, int idMedico, LocalDateTime fechaHora, String estado, String nota) throws PersistenciaException {
        String sql = "CALL AgendarCita(?, ?, ?, ?, ?)";

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {

            // Asignar parámetros
            cs.setInt(1, idPaciente);
            cs.setInt(2, idMedico);
            cs.setTimestamp(3, Timestamp.valueOf(fechaHora));
            cs.setString(4, estado);
            cs.setString(5, nota);

            // Ejecutar el procedimiento almacenado
            cs.execute();

            System.out.println("Cita agendada correctamente.");

        } catch (SQLException ex) {
            throw new PersistenciaException("Error al agendar la cita: " + ex.getMessage(), ex);
        }

    }

    @Override
    public int obtenerMedicoPorEspecialidad(String especialidad) throws PersistenciaException {
        String sql = "SELECT id_medico FROM Medicos WHERE especialidad = ? ORDER BY RAND() LIMIT 1";

        try (Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, especialidad);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_medico");
            } else {
                return -1; // No hay médicos disponibles
            }

        } catch (SQLException ex) {
            throw new PersistenciaException("Error al obtener médico por especialidad: " + ex.getMessage(), ex);
        }
    }

}
