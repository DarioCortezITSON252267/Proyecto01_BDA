/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.ConexionBD;
import Conexion.IConexionBD;
import Entidades.Medico;
import Exception.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase DAO para la gestión de médicos en la base de datos.
 * Proporciona métodos para validar un médico y darlo de baja.
 */
public class MedicoDAO implements IMedicoDAO {

    private final IConexionBD conexion; // Instancia de conexión con la base de datos.
    private static final Logger logger = Logger.getLogger(MedicoDAO.class.getName());

    /**
     * Constructor de la clase MedicoDAO.
     * @param conexion Objeto que proporciona la conexión con la base de datos.
     */
    public MedicoDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }
   
    /**
     * Valida las credenciales de un médico en la base de datos.
     * Llama al procedimiento almacenado "ValidarMedico".
     * @param cedula Cédula del médico.
     * @param contraseña Contraseña del médico.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean validarMedico(String cedula, String contraseña) {
        String query = "CALL ValidarMedico(?, ?)"; // Llamar al procedimiento almacenado

        try (Connection conn = conexion.crearConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cedula);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();
            
            System.out.println("Cédula ingresada: " + cedula);
            System.out.println("Contraseña ingresada: " + contraseña);

            return rs.next(); // Devuelve true si hay un resultado

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al validar médico", e);
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error de persistencia", ex);
        }
        return false;
    }

    /**
     * Da de baja a un médico en la base de datos.
     * Llama al procedimiento almacenado "DarDeBajaMedico".
     * @param usuario Usuario que solicita la baja.
     * @param cedula Cédula del médico.
     * @param contraseña Contraseña del médico.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean darDeBajaMedico(String usuario, String cedula, String contraseña) {       
        String query = "CALL DarDeBajaMedico(?, ?, ?)";
        try (Connection conn = conexion.crearConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario);
            stmt.setString(2, cedula);
            stmt.setString(3, contraseña);
            int rowsAffected = stmt.executeUpdate();
            
            System.out.println("Usuario ingresado: " + usuario);
            System.out.println("Cédula ingresada: " + cedula);
            System.out.println("Contraseña ingresada: " + contraseña);

            return rowsAffected > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al dar de baja al medico", e);
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error de persistencia", ex);
        }
        return false;    
    }
}






