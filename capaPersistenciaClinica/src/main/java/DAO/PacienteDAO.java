/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.IConexionBD;
import Entidades.Paciente;
import Exception.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class PacienteDAO implements IPacienteDAO {

    IConexionBD conexion; // atributo conexion que se usara toda la clase. Recibiremos cualquer objeto que implemente IConexionBD

    public PacienteDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }
    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());

//    @Override
//    public Paciente agendarCita(Paciente paciente) throws PersistenciaException {
//
//        String sentenciaSQL = "INSERT INTO Pacientes(fecha_nacimiento, id_usuario, id_direccion) VALUES (?,?,?)";
//        try (Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS)) {
//
//            ps.setDate(1, Date.valueOf(paciente.getFecha_nacimiento()));
//            ps.setInt(2, paciente.getId_usuario());
//            ps.setInt(3, paciente.getId_direccion());
//
//        } catch (SQLException ex) {
//            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//}
    @Override
    public Paciente registrarPaciente(Paciente paciente) throws PersistenciaException {
    String sql = "{CALL RegistrarPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

    try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {

        
        Date fechaNacimiento = Date.valueOf(paciente.getFecha_nacimiento());

        
        cs.setString(1, paciente.getNombre()); // Nombre
        cs.setString(2, paciente.getApellido_paterno()); // Apellido Paterno
        cs.setString(3, paciente.getApellido_materno()); // Apellido Materno
        cs.setString(4, paciente.getTelefono()); // Teléfono
        cs.setString(5, paciente.getCorreo()); // Correo Electrónico
        cs.setDate(6, fechaNacimiento); // Fecha de nacimiento
        cs.setString(7, paciente.getDireccion().getCalle()); // Calle
        cs.setString(8, paciente.getDireccion().getNumero()); // Número de casa
        cs.setString(9, paciente.getDireccion().getColonia()); // Colonia
        cs.setInt(10, paciente.getDireccion().getCodigo_postal()); // Código postal

        int filasAfectadas = cs.executeUpdate(); 

        if (filasAfectadas == 0) {
            throw new PersistenciaException("No se pudo registrar el paciente.");
        }

        System.out.println(" Paciente registrado exitosamente");

    } catch (SQLException ex) {
    System.out.println(" ERROR SQL: " + ex.getMessage());
    ex.printStackTrace(); 
    throw new PersistenciaException("Error al registrar el paciente", ex);
}

    return paciente;    

}}
