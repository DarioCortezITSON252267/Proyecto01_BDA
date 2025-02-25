/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import Conexion.ConexionBD;
import Conexion.IConexionBD;
import DAO.ICitaDAO;
import DTO.CitaDTO;
import Exception.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chris
 */
public class CitaBO {

    private ICitaDAO citaDAO;

    public CitaBO(ICitaDAO citaDAO) {
        this.citaDAO = citaDAO;
    }

    public List<CitaDTO> obtenerHistorialCitasPaciente(int idPaciente) throws PersistenciaException {
        List<CitaDTO> citasDTO = new ArrayList<>();
        List<String> datosCitas = citaDAO.verHistorialCitas(idPaciente);

        for (String datos : datosCitas) {
            String[] partes = datos.split(", ");

            // Verificar que el array tiene el tamaño correcto
            if (partes.length < 6) {
                System.err.println("⚠️ Error: Datos incompletos en la consulta: " + datos);
                continue;
            }

            CitaDTO cita = new CitaDTO(
                    Integer.parseInt(partes[0].split(": ")[1]), // ID de la cita
                    partes[1].split(": ")[1], // Estado
                    LocalDateTime.parse(partes[2].split(": ")[1]), // Fecha y hora
                    partes[3].split(": ")[1], // Nota
                    Integer.parseInt(partes[4].split(": ")[1]), // ID médico
                    partes[5].split(": ")[1], // Especialidad
                    "", // Se elimina el nombre del médico
                    0, "", "", "" // Datos del paciente no incluidos
            );

            citasDTO.add(cita);
        }
        return citasDTO;
    }

    public List<CitaDTO> obtenerHistorialCitasMedico(int idMedico) throws PersistenciaException {
        List<CitaDTO> citasDTO = new ArrayList<>();
        List<String> datosCitas = citaDAO.verHistorialCitasMedico(idMedico);

        for (String datos : datosCitas) {
            String[] partes = datos.split(", ");
            CitaDTO cita = new CitaDTO(
                    Integer.parseInt(partes[0].split(": ")[1]), // ID de la cita
                    partes[1].split(": ")[1], // Estado
                    LocalDateTime.parse(partes[2].split(": ")[1]), // Fecha y hora
                    partes[3].split(": ")[1], // Nota
                    idMedico, // ID del médico
                    "", // Especialidad no incluida
                    "", // Médico no incluido
                    Integer.parseInt(partes[4].split(": ")[1]), // ID del paciente
                    partes[5].split(": ")[1], // Nombre paciente
                    partes[6].split(": ")[1], // Apellido paciente
                    partes[7].split(": ")[1] // Usuario paciente
            );
            citasDTO.add(cita);
        }
        return citasDTO;
    }

    public void agendarCita(int idPaciente, int idMedico, LocalDateTime fechaHora, String estado, String nota) throws PersistenciaException {
        // Validaciones antes de enviar los datos al DAO
        if (idPaciente <= 0 || idMedico <= 0) {
            throw new PersistenciaException("El ID del paciente o del médico no es válido.");
        }
        if (fechaHora == null || fechaHora.isBefore(LocalDateTime.now())) {
            throw new PersistenciaException("La fecha y hora de la cita deben ser futuras.");
        }
        if (estado == null || (!estado.equals("pendiente") && !estado.equals("confirmada") && !estado.equals("cancelada"))) {
            throw new PersistenciaException("El estado de la cita no es válido.");
        }

        // Llamar al DAO para insertar la cita en la base de datos
        citaDAO.agendarCita(idPaciente, idMedico, fechaHora, estado, nota);
    }

//    public int obtenerMedicoPorEspecialidad(String especialidad) throws PersistenciaException {
//        IConexionBD conexionBD = new ConexionBD(); // Crear instancia de conexión
//        Connection conn = null;
//        int idMedico = -1; // Valor por defecto si no se encuentra médico
//
//        try {
//            conn = conexionBD.crearConexion(); // Obtener conexión
//
//            if (conn != null) {
//                String sql = "SELECT id_medico FROM Medico WHERE especialidad = ? LIMIT 1";
//                PreparedStatement stmt = conn.prepareStatement(sql);
//                stmt.setString(1, especialidad);
//                ResultSet rs = stmt.executeQuery();
//
//                if (rs.next()) {
//                    idMedico = rs.getInt("id_medico");
//                }
//
//                // Cerrar recursos
//                rs.close();
//                stmt.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // Imprime el error en consola
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close(); // Cerrar conexión
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return idMedico; // Retorna el ID o -1 si no se encontró
//    }
    public int obtenerMedicoPorEspecialidad(String especialidad) throws PersistenciaException {
        return citaDAO.obtenerMedicoPorEspecialidad(especialidad);
    }

    public List<CitaDTO> verHistorialCitas(int idPaciente) throws PersistenciaException {
        List<CitaDTO> historial = new ArrayList<>();
        String sql = "CALL VerHistorialCitas(?)";

// Crear una instancia de ConexionBD
        IConexionBD conexion = new ConexionBD();

// Usar la instancia para llamar al método crearConexion()
        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, idPaciente);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                CitaDTO cita = new CitaDTO(
                        rs.getInt("id_cita"),
                        rs.getString("estado"),
                        rs.getTimestamp("fechahora").toLocalDateTime(),
                        rs.getString("nota") != null ? rs.getString("nota") : "Sin nota",
                        rs.getInt("id_medico"),
                        rs.getString("especialidad")
                );
                historial.add(cita);
            }
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al obtener el historial de citas", ex);
        }

        return historial;
    }

}
