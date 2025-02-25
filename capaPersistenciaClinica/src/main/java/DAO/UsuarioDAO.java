/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.IConexionBD;
import Entidades.Usuario;
import Exception.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Todos
 */
public class UsuarioDAO implements IUsuarioDAO {

    private IConexionBD conexion; // Atributo conexión que se usará en toda la clase
    private static final Logger logger = Logger.getLogger(UsuarioDAO.class.getName());

        public UsuarioDAO(IConexionBD conexion) {
            this.conexion = conexion;
        }

    // Método para crear un usuario
    @Override
    public Usuario crearUsuario(Usuario usuario) throws PersistenciaException {
        String sql = "CALL crearUsuario(?, ?)"; // Procedimiento para crear un usuario

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, usuario.getUsuario()); // Usuario
            cs.setString(2, usuario.getContraseña()); // Contraseña

            cs.execute();
            logger.log(Level.INFO, "Usuario creado exitosamente");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al crear el usuario", ex);
            throw new PersistenciaException("Error al crear el usuario", ex);
        }

        return usuario; // Retorna el usuario creado
    }

    // Método para obtener un usuario por su nombre de usuario
    @Override
public Usuario obtenerUsuarioPorNombre(String nombreUsuario) throws PersistenciaException {
    String sql = "SELECT * FROM Usuarios WHERE usuario = ?";

    try (Connection con = conexion.crearConexion(); PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, nombreUsuario);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContraseña(rs.getString("contraseña"));  // Asegurar que es la versión actualizada
                return usuario;
            }
        }
    } catch (SQLException ex) {
        logger.log(Level.SEVERE, "Error al obtener usuario por nombre", ex);
        throw new PersistenciaException("Error al obtener usuario", ex);
    }

    return null;
}


    // Método para actualizar un usuario
    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws PersistenciaException {
        String sql = "CALL actualizarUsuario(?, ?)"; // Procedimiento para actualizar un usuario

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, usuario.getUsuario()); // Usuario
            cs.setString(2, usuario.getContraseña()); // Contraseña

            cs.execute();
            logger.log(Level.INFO, "Usuario actualizado exitosamente");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al actualizar el usuario", ex);
            throw new PersistenciaException("Error al actualizar el usuario", ex);
        }

        return usuario; // Retorna el usuario actualizado
    }
    // Método para obtener un usuario por su ID
   public Usuario obtenerUsuarioPorId(int idPaciente) throws PersistenciaException {
        Usuario usuario = null;
        String query = "SELECT * FROM usuarios WHERE id_usuario = ?";
        
        try (Connection connection = conexion.crearConexion();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, idPaciente);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario(
                        resultSet.getInt("id_usuario"),
                        resultSet.getString("usuario"),
                        resultSet.getString("contraseña"),
                        resultSet.getString("tipo_usuario")
                );
            }

        } catch (SQLException ex) {
            throw new PersistenciaException("Error al obtener el usuario por ID", ex);
        }

        return usuario;
    }
}
