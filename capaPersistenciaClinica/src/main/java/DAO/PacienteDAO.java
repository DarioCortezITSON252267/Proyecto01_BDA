/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.IConexionBD;
import Entidades.Direccion;
import Entidades.Paciente;
import Entidades.Usuario;
import Exception.PersistenciaException;
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase de acceso a datos para la entidad Paciente.
 * Implementa las operaciones CRUD utilizando procedimientos almacenados.
 */
public class PacienteDAO implements IPacienteDAO {

    private IConexionBD conexion; // Conexión a la base de datos
    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());

    /**
     * Constructor que inicializa la conexión con la base de datos.
     * 
     * @param conexion Objeto de conexión a la base de datos
     */
    public PacienteDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    /**
     * Registra un nuevo paciente en la base de datos.
     * 
     * @param paciente Objeto Paciente a registrar
     * @return Paciente registrado
     * @throws PersistenciaException Si ocurre un error al registrar el paciente
     */
    @Override
    public Paciente registrarPaciente(Paciente paciente) throws PersistenciaException {
        String sql = "CALL RegistrarPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {

            Date fechaNacimiento = Date.valueOf(paciente.getFecha_nacimiento());
            
            cs.setString(1, paciente.getUsuario().getUsuario());
            cs.setString(2, paciente.getUsuario().getContraseña());
            cs.setString(3, paciente.getNombre());
            cs.setString(4, paciente.getApellido_paterno());
            cs.setString(5, paciente.getApellido_materno());
            cs.setString(6, paciente.getTelefono());
            cs.setString(7, paciente.getCorreo());
            cs.setDate(8, fechaNacimiento);
            cs.setString(9, paciente.getDireccion().getCalle());
            cs.setString(10, paciente.getDireccion().getNumero());
            cs.setString(11, paciente.getDireccion().getColonia());
            cs.setString(12, paciente.getDireccion().getCodigo_postal());
            
            cs.execute();
            logger.log(Level.INFO, "Paciente registrado exitosamente");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al registrar el paciente", ex);
            throw new PersistenciaException("Error al registrar el paciente", ex);
        }
        
        return paciente;
    }

    /**
     * Edita la información de un paciente existente en la base de datos.
     * 
     * @param paciente Objeto Paciente con los datos actualizados
     * @return Paciente actualizado
     * @throws PersistenciaException Si ocurre un error al actualizar el paciente
     */
    @Override
    public Paciente editarPaciente(Paciente paciente) throws PersistenciaException {
        if (paciente.getUsuario() == null) {
            System.out.println("El paciente no tiene un usuario asignado.");
            return paciente;
        }

        String sql = "CALL actualizarPacientePorId(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {
            Date fechaNacimiento = Date.valueOf(paciente.getFecha_nacimiento());
            
            cs.setInt(1, paciente.getId_paciente());
            cs.setDate(2, fechaNacimiento);
            cs.setString(3, paciente.getNombre());
            cs.setString(4, paciente.getApellido_paterno());
            cs.setString(5, paciente.getApellido_materno());
            cs.setString(6, paciente.getTelefono());
            cs.setString(7, paciente.getDireccion().getCalle());
            cs.setString(8, paciente.getDireccion().getNumero());
            cs.setString(9, paciente.getDireccion().getColonia());
            cs.setString(10, paciente.getDireccion().getCodigo_postal());

            cs.execute();
            logger.log(Level.INFO, "Paciente actualizado exitosamente");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al actualizar el paciente", ex);
            throw new PersistenciaException("Error al actualizar el paciente", ex);
        }

        return paciente;
    }

    /**
     * Obtiene un paciente por su correo electrónico.
     * 
     * @param correo Correo electrónico del paciente
     * @return Paciente encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error al obtener el paciente
     */
    public Paciente obtenerPacientePorCorreo(String correo) throws PersistenciaException {
        String sql = "SELECT p.id_paciente, u.contraseña FROM Usuarios u "
                + "JOIN Pacientes p ON u.id_usuario = p.id_usuario "
                + "WHERE u.usuario = ?";

        try (Connection con = conexion.crearConexion(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, correo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setContraseña(rs.getString("contraseña"));

                    Paciente paciente = new Paciente();
                    paciente.setId_paciente(rs.getInt("id_paciente"));
                    paciente.setUsuario(usuario);
                    return paciente;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al obtener paciente por correo", ex);
            throw new PersistenciaException("Error al obtener paciente por correo", ex);
        }
        return null;
    }
}
