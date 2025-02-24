/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Exception.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author chris
 */
public interface ICitaDAO {

    List<String> verHistorialCitas(int idPaciente) throws PersistenciaException;

    List<String> verHistorialCitasMedico(int idMedico) throws PersistenciaException;

    public void agendarCita(int idPaciente, int idMedico, LocalDateTime fechaHora, String estado, String nota) throws PersistenciaException;

    public int obtenerMedicoPorEspecialidad(String especialidad) throws PersistenciaException;
}
