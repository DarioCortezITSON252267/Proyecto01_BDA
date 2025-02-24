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
        String sql = "CALL RegistrarPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // 12 parámetros

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
            cs.setString(12, paciente.getDireccion().getCodigo_postal()); // Código postal

            cs.execute();
            logger.log(Level.INFO, "Paciente registrado exitosamente");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al registrar el paciente", ex);
            throw new PersistenciaException("Error al registrar el paciente", ex);
        }

        return paciente; // Retorna el paciente registrado
    }

    @Override
    public Paciente editarPaciente(Paciente paciente) throws PersistenciaException {
        // Verifica que el paciente tenga un usuario asociado
        if (paciente.getUsuario() == null) {
            System.out.println("El paciente no tiene un usuario asignado.");
            return paciente;  // O realizar otra acción si lo consideras necesario
        }

        String sql = "CALL actualizarPacientePorId(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // Procedimiento almacenado para actualizar

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {
            // Convertir la fecha de LocalDate a java.sql.Date
            Date fechaNacimiento = Date.valueOf(paciente.getFecha_nacimiento());

            // Establecer los parámetros en el procedimiento almacenado
            cs.setInt(1, paciente.getId_paciente());  // idPaciente
            cs.setString(2, paciente.getUsuario().getContraseña());  // contraseniaNueva
            cs.setDate(3, Date.valueOf(paciente.getFecha_nacimiento()));  // fechaNacimientoNuevo
            cs.setString(4, paciente.getNombre());  // nombreNuevo
            cs.setString(5, paciente.getApellido_paterno());  // apellidoPaternoNuevo
            cs.setString(6, paciente.getApellido_materno());  // apellidoMaternoNuevo
            cs.setString(7, paciente.getTelefono());  // telefonoNuevo
            cs.setString(8, paciente.getCorreo());  // correoNuevo
            cs.setString(9, paciente.getDireccion().getCalle());  // calleNueva
            cs.setString(10, paciente.getDireccion().getNumero());  // numeroNuevo
            cs.setString(11, paciente.getDireccion().getColonia());  // coloniaNueva
            cs.setString(12, paciente.getDireccion().getCodigo_postal());  // codigoPostalNuevo
            // Ejecutar la consulta
            cs.execute();
            logger.log(Level.INFO, "Paciente actualizado exitosamente");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al actualizar el paciente", ex);
            throw new PersistenciaException("Error al actualizar el paciente", ex);
        }

        // Retorna el paciente actualizado después de la modificación
        return paciente;
    }

    //OBTENEMOS CONTRASEÑA ENCRIPATADA DEL SQL
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
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, "Error al obtener paciente por correo", ex);
            throw new PersistenciaException("Error al obtener paciente por correo", ex);
        }

        return null; // Si no encuentra el paciente, devuelve null
    }

}
