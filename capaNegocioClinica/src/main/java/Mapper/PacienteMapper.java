/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

/**
 *
 * @author chris
 */
public class PacienteMapper {
       public Paciente toEntity(PacienteNuevoDTO pacienteNuevo) {
        return new Paciente(
            pacienteNuevo.getNombre(),
            pacienteNuevo.getApellidoPaterno(),
            pacienteNuevo.getApellidoMaterno(),
            pacienteNuevo.getTelefono(),
            pacienteNuevo.getFechaNacimiento(),
            pacienteNuevo.getCorreoElectronico(),
            pacienteNuevo.getDireccion() // Asumiendo que la dirección ya es un objeto Direccion
        );
    }

    public PacienteNuevoDTO toDTO(Paciente paciente) {
        return new PacienteNuevoDTO(
            paciente.getNombre(),
            paciente.getApellidoPaterno(),
            paciente.getApellidoMaterno(),
            paciente.getTelefono(),
            paciente.getFechaNacimiento(),
            paciente.getCorreoElectronico(),
            paciente.getDireccion() // Si la dirección es un objeto, asegúrate de incluir su mapeo en DTO
        );
    } 
}
