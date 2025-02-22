/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.capanegocioclinica;

import BO.PacienteBO;
import Conexion.ConexionBD;
import Conexion.IConexionBD;
import DTO.PacienteNuevoDTO;
import Entidades.Direccion;
import Exception.NegocioException;
import com.sun.tools.javac.Main;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class CapaNegocioClinica {

    public static void main(String[] args) {
     try {
            // Crear una dirección
            Direccion direccion = new Direccion(0, "Calle Falsa", "123", "Colonia Centro", "12345", 0);
            
            // Crear un nuevo paciente DTO
            PacienteNuevoDTO pacienteNuevo = new PacienteNuevoDTO(
                "Juan", "Pérez", "Gómez", "5544332211", LocalDate.of(1990, 5, 20),
                "juan.perez@example.com", "Contraseña123", direccion
            );
            
            // Crear conexión simulada (debes implementar IConexionBD)
            IConexionBD conexion = new ConexionBD();
            
            // Crear la capa de negocio para registrar el paciente
            PacienteBO pacienteBO = new PacienteBO(conexion);
            
            // Intentar registrar al paciente
            boolean registrado = pacienteBO.registrarPaciente(pacienteNuevo);
            
            if (registrado) {
                System.out.println("Paciente registrado exitosamente.");
            } else {
                System.out.println("No se pudo registrar al paciente.");
            }
        } catch (NegocioException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error en el registro del paciente", ex);
        }
    }
}

