/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTO.PacienteNuevoDTO;
import Exception.NegocioException;
import Mapper.PacienteMapper;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author chris
 */
public class PacienteBO {

    IPacienteDAO pacienteDAO;

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
        
        if (pacienteNuevo.getApellidoPaterno() == null || pacienteNuevo.getApellidoPaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido paterno es obligatorio.");
        }

        if (pacienteNuevo.getTelefono() == null || pacienteNuevo.getTelefono().length() != 10) {
            throw new NegocioException("El teléfono debe tener 10 dígitos");
        }

        if (pacienteNuevo.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de nacimiento no puede ser futura");
        }

        //Validacion de contraseña menor a 20 caracteres
        if (pacienteNuevo.getContrasenia().length() > 20) {
            throw new NegocioException("La contraseña no debe tener mas de 20 caracteres");
        }

        //Validacion de que no se admiten caracteres especiales
        if (!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", pacienteNuevo.getNombre())) {
            throw new NegocioException("No se admiten caracteres especiales en el nombre");
        }


        // Convertir DTO a entidad
        PacienteMapper convertidor = new PacienteMapper();
        Paciente paciente = convertidor.toEntity(pacienteNuevo);

        try {
            pacienteDAO.registrarPaciente(paciente);
            return true;
        } catch (PersistenciaException ex) {
            Logger.getLogger(PacienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Hubo un error al guardar en la base de datos", ex);
        }
    }
}
