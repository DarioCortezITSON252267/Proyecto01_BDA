/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Medico;
import Exception.PersistenciaException;
import java.util.List;



/**
 *
 * @author Angel
 */
public interface IMedicoDAO {
    boolean validarMedico(String cedula, String contraseña);
    boolean darDeBajaMedico(String usuario, String cedula, String contraseña);
}

