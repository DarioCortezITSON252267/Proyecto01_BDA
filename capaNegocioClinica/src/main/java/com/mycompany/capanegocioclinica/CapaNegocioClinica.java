/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.capanegocioclinica;

import BO.CitaBO;
import BO.PacienteBO;
import Conexion.ConexionBD;
import Conexion.IConexionBD;
import DAO.CitaDAO;
import DTO.CitaDTO;
import DTO.PacienteNuevoDTO;
import Entidades.Direccion;
import Exception.NegocioException;
import Exception.PersistenciaException;
import com.sun.tools.javac.Main;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class CapaNegocioClinica {

    public static void main(String[] args) {
//     try {
//            // Crear una dirección
//            Direccion direccion = new Direccion(0, "Calle Falsa", "123", "Colonia Centro", "12345", 0);
//            
//            // Crear un nuevo paciente DTO
//            PacienteNuevoDTO pacienteNuevo = new PacienteNuevoDTO(
//                "Juan", "Pérez", "Gómez", "5544332211", LocalDate.of(1990, 5, 20),
//                "juan.perez@example.com", "Contraseña123", direccion
//            );
//            
//            // Crear conexión simulada (debes implementar IConexionBD)
//            IConexionBD conexion = new ConexionBD();
//            
//            // Crear la capa de negocio para registrar el paciente
//            PacienteBO pacienteBO = new PacienteBO(conexion);
//            
//            // Intentar registrar al paciente
//            boolean registrado = pacienteBO.registrarPaciente(pacienteNuevo);
//            
//            if (registrado) {
//                System.out.println("Paciente registrado exitosamente.");
//            } else {
//                System.out.println("No se pudo registrar al paciente.");
//            }
//        } catch (NegocioException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error en el registro del paciente", ex);
//        }

//        IConexionBD conexion = new ConexionBD();
//        CitaDAO citaDAO = new CitaDAO(conexion);
//        CitaBO citaBO = new CitaBO(citaDAO);
//
//        int idPaciente = 1; // Cambia a un ID sin citas en la BD
//
//        try {
//            List<CitaDTO> citas = citaBO.obtenerHistorialCitasPaciente(idPaciente);
//            if (citas.isEmpty()) {
//                System.out.println("No hay citas registradas.");
//            } else {
//                for (CitaDTO cita : citas) {
//                    System.out.println("Cita ID: " + cita.getIdCita()
//                            + " | Estado: " + cita.getEstado()
//                            + " | Fecha: " + cita.getFechaHora()
//                            + " | Nota: " + (cita.getNota() != null ? cita.getNota() : "Sin nota")
//                            + " | Especialidad: " + cita.getEspecialidad());
//                }
//            }
//        } catch (PersistenciaException e) {
//            System.err.println("Error al obtener historial: " + e.getMessage());
//        }
        try {
            // Crear la implementación de la conexión a la base de datos
            IConexionBD conexionBD = new ConexionBD();

            // Crear el objeto BO con la conexión inyectada
            PacienteBO pacienteBO = new PacienteBO(conexionBD);

            // Crear el objeto PacienteNuevoDTO con datos de prueba
            PacienteNuevoDTO pacienteNuevoDTO = new PacienteNuevoDTO();
            pacienteNuevoDTO.setIdPaciente(1);  // Asegúrate de que este ID exista en la base de datos
            pacienteNuevoDTO.setCorreo("juan.perez@example.com");
            pacienteNuevoDTO.setNombre("Juan");
            pacienteNuevoDTO.setApellidoPaterno("Perez");
            pacienteNuevoDTO.setApellidoMaterno("Gomez");
            pacienteNuevoDTO.setTelefono("1234567890");
            pacienteNuevoDTO.setFechaNacimiento(LocalDate.of(1990, 5, 15));
            pacienteNuevoDTO.setDireccion(new Direccion("Calle Falsa 123", "Ciudad", "Estado", "12345"));

            // Llamar al método editarPaciente con el DTO
            boolean resultado = pacienteBO.editarPaciente(pacienteNuevoDTO);

            // Mostrar el resultado
            if (resultado) {
                System.out.println("Paciente editado correctamente.");
            }

        } catch (NegocioException e) {
            // Manejo de excepciones
            System.out.println("Error: " + e.getMessage());
        }
    }
}
