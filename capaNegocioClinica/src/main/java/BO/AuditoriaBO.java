/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;
import Conexion.IConexionBD;
import DAO.AuditoriaDAO;
import DAO.IAuditoriaDAO;
import Exception.NegocioException;
import Exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author dario
 */
public class AuditoriaBO {

    private final IAuditoriaDAO auditoriaDAO;

    public AuditoriaBO(IConexionBD conexion) {
        this.auditoriaDAO = new AuditoriaDAO(conexion);
    }

    public List<String> verAuditoriaPorMedico(String cedula) throws NegocioException {
        if (cedula == null || cedula.isBlank()) {
            throw new NegocioException("La cédula es obligatoria.");
        }

        try {
            return auditoriaDAO.verAuditoriaPorMedico(cedula);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener auditoría: " + e.getMessage());
        }
    }
}