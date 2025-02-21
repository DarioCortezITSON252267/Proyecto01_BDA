/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.IConexionBD;
import Entidades.Paciente;
import Exception.PersistenciaException;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Todos
 */
public class PacienteDAO implements IPacienteDAO {
    
    
    private IConexionBD conexion; // Atributo conexión que se usará en toda la clase
    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());

    public PacienteDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

   @Override
public Paciente registrarPaciente(Paciente paciente) throws PersistenciaException {
    String sql = "{CALL RegistrarPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"; // 12 parámetros

    try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {

        // Convertir fecha de LocalDate a java.sql.Date
        Date fechaNacimiento = Date.valueOf(paciente.getFecha_nacimiento());

        // Establecer parámetros en el procedimiento almacenado
        cs.setString(1, paciente.getUsuario().getUsuario()); // Usuario
        cs.setString(2, paciente.getUsuario().getContraseña()); // Contraseña
        cs.setString(3, paciente.getNombre()); // Nombre
        cs.setString(4, paciente.getApellido_paterno()); // Apellido Paterno
        cs.setString(5, paciente.getApellido_materno()); // Apellido Materno
        cs.setString(6, paciente.getTelefono()); // Teléfono
        cs.setString(7, paciente.getCorreo()); // Correo Electrónico
        cs.setDate(8, fechaNacimiento); // Fecha de nacimiento
        cs.setString(9, paciente.getDireccion().getCalle()); // Calle
        cs.setString(10, paciente.getDireccion().getNumero()); // Número de casa
        cs.setString(11, paciente.getDireccion().getColonia()); // Colonia
        cs.setInt(12, paciente.getDireccion().getCodigo_postal()); // Código postal

        cs.execute();
        logger.log(Level.INFO, "Paciente registrado exitosamente");

    } catch (SQLException ex) {
        logger.log(Level.SEVERE, "Error al registrar el paciente", ex);
        throw new PersistenciaException("Error al registrar el paciente", ex);
    }

    return paciente; // Retorna el paciente registrado
  }
}
