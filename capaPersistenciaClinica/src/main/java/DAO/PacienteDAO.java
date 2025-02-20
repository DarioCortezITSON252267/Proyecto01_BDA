/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.IConexionBD;
import Entidades.Paciente;
import Exception.PersistenciaException;
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

    @Override
    public Paciente agendarCita(Paciente paciente) throws PersistenciaException {

        String sentenciaSQL = "INSERT INTO Pacientes(fecha_nacimiento, id_usuario, id_direccion) VALUES (?,?,?)";
        try (Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, Date.valueOf(paciente.getFecha_nacimiento()));
            ps.setInt(2, paciente.getId_usuario());
            ps.setInt(3, paciente.getId_direccion());

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
    


}
