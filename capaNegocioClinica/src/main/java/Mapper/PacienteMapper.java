/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.PacienteNuevoDTO;
import Entidades.Direccion;
import Entidades.Paciente;
import Entidades.Usuario;

/**
 *
 * @author chris
 */
public class PacienteMapper {

    // Mapea de PacienteNuevoDTO a Paciente para el registro de un nuevo paciente
    public Paciente toEntityNuevo(PacienteNuevoDTO pacienteNuevo, Usuario usuario) {
        return new Paciente(
                0, // ID inicializado en 0 hasta que se registre en la BD
                pacienteNuevo.getFechaNacimiento(),
                pacienteNuevo.getNombre(),
                pacienteNuevo.getApellidoPaterno(),
                pacienteNuevo.getApellidoMaterno(),
                pacienteNuevo.getTelefono(),
                pacienteNuevo.getCorreo(),
                usuario, // Recibe un usuario ya creado
                pacienteNuevo.getDireccion() // Se añade la dirección
        );
    }

    // Mapea de PacienteNuevoDTO a Paciente para la actualización de un paciente existente
    public Paciente toEntityActualizacion(PacienteNuevoDTO pacienteNuevo) {
        return new Paciente(
                pacienteNuevo.getIdPaciente(), // Usamos el idPaciente para la actualización
                pacienteNuevo.getFechaNacimiento(),
                pacienteNuevo.getNombre(),
                pacienteNuevo.getApellidoPaterno(),
                pacienteNuevo.getApellidoMaterno(),
                pacienteNuevo.getTelefono(),
                pacienteNuevo.getCorreo(),
                null, // Aquí no se necesita el usuario para la actualización
                pacienteNuevo.getDireccion() // Se mantiene la dirección para la actualización
        );
    }

    // Convierte la entidad Paciente a PacienteNuevoDTO
    public PacienteNuevoDTO toDTO(Paciente paciente) {
        return new PacienteNuevoDTO(
                paciente.getId_paciente(),
                paciente.getNombre(),
                paciente.getApellido_paterno(),
                paciente.getApellido_materno(),
                paciente.getTelefono(),
                paciente.getFecha_nacimiento(),
                paciente.getCorreo(),
                paciente.getUsuario().getContraseña(),
                paciente.getDireccion() // Se agrega la dirección
        );
    }
}

