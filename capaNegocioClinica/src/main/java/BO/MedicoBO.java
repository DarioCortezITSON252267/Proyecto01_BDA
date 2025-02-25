/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import Conexion.IConexionBD;
import DAO.IMedicoDAO;
import DAO.MedicoDAO;
import DTO.CitaDTO;
import Exception.NegocioException;
import Exception.PersistenciaException;
import java.util.List;
import java.util.regex.Pattern;





/**
 *
 * @author dario
 */

public class MedicoBO {
    private final IMedicoDAO medicoDAO;

    public MedicoBO(IConexionBD conexion) {
        this.medicoDAO = new MedicoDAO(conexion);
    }

    public boolean autenticarMedico(String cedula, String contraseña) throws NegocioException {
        if (cedula == null || cedula.isBlank()) {
            throw new NegocioException("La cédula es obligatoria.");
        }

        if (contraseña == null || contraseña.isBlank()) {
            throw new NegocioException("La contraseña es obligatoria.");
        }

        if (contraseña.length() > 20) {
            throw new NegocioException("La contraseña no debe tener más de 20 caracteres.");
        }

        if (!Pattern.matches("\\d+", cedula)) {
            throw new NegocioException("La cédula debe contener solo números.");
        }

        return medicoDAO.validarMedico(cedula, contraseña);
    }
    
    public boolean darDeBajaMedico(String usuario, String cedula, String contraseña) throws NegocioException {
    if (usuario == null || usuario.isBlank()) {
        throw new NegocioException("El usuario es obligatorio.");
    }

    if (cedula == null || cedula.isBlank()) {
        throw new NegocioException("La cedula es obligatoria.");
    }

    if (contraseña == null || contraseña.isBlank()) {
        throw new NegocioException("La contraseña es obligatoria.");
    }

    if (contraseña.length() > 20) {
        throw new NegocioException("La contraseña no debe tener más de 20 caracteres.");
    }

    if (!Pattern.matches("\\d+", cedula)) {
        throw new NegocioException("La cédula debe contener solo números.");
    }

    // Llamar al método correcto de MedicoDAO
    return medicoDAO.darDeBajaMedico(usuario, cedula, contraseña);
    }
}    






