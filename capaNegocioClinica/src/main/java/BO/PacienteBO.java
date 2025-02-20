/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTO.PacienteNuevoDTO;
import Exception.NegocioException;
import java.time.LocalDate;

/**
 *
 * @author chris
 */
public class PacienteBO {
        private IPacienteDAO pacienteDAO;

    public PacienteBO(IConexionBD conexion) {
        this.pacienteDAO = new PacienteDAO(conexion);
    }

    public boolean registrarPaciente(PacienteNuevoDTO pacienteNuevo) throws NegocioException {

        if (pacienteNuevo == null) {
            throw new NegocioException("El paciente no puede ser nulo");
        }

        // Validaciones de negocio
        if (pacienteNuevo.getNombre() == null || pacienteNuevo.getNombre().isBlank()) {
            throw new NegocioException("El nombre es obligatorio");
        }

        if (pacienteNuevo.getTelefono() == null || pacienteNuevo.getTelefono().length() != 10) {
            throw new NegocioException("El teléfono debe tener 10 dígitos");
        }

        if (pacienteNuevo.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de nacimiento no puede ser futura");
        }

        // Convertir DTO a entidad
        PacienteMapper convertidor = new PacienteMapper();
        Paciente paciente = convertidor.toEntity(pacienteNuevo);

        try {
            pacienteDAO.agregarPaciente(paciente);
            return true;
        } catch (PersistenciaException ex) {
            Logger.getLogger(PacienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Hubo un error al guardar en la base de datos", ex);
        }
    }
}
