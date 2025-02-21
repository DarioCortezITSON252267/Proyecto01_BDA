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

    public Paciente toEntityNuevo(PacienteNuevoDTO pacienteNuevo, Usuario usuario) {
        return new Paciente(
                0, // ID inicializado en 0 hasta que se registre en la BD
                pacienteNuevo.getFechaNacimiento(),
                pacienteNuevo.getNombre(),
                pacienteNuevo.getApellidoPaterno(),
                pacienteNuevo.getApellidoMaterno(),
                pacienteNuevo.getTelefono(),
                pacienteNuevo.getCorreo(),
                usuario, // Ahora recibe un usuario ya creado
                pacienteNuevo.getDireccion() // Se añade la dirección
        );
    }

    public PacienteNuevoDTO toDTO(Paciente paciente) {
        return new PacienteNuevoDTO(
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
