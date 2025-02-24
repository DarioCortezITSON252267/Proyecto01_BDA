/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import Conexion.IConexionBD;
import DAO.IPacienteDAO;
import DAO.PacienteDAO;
import DTO.DireccionDTO;
import DTO.PacienteNuevoDTO;
import Entidades.Direccion;
import Entidades.Paciente;
import Entidades.Usuario;
import Exception.NegocioException;
import Exception.PersistenciaException;
import Mapper.PacienteMapper;
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author chris
 */
public class PacienteBO {

    private final IPacienteDAO pacienteDAO;
    private final PacienteMapper pacienteMapper;
    private static final Logger logger = Logger.getLogger(PacienteBO.class.getName());

    public PacienteBO(IConexionBD conexion) {
        this.pacienteDAO = new PacienteDAO(conexion);
        this.pacienteMapper = new PacienteMapper();
    }

    public boolean registrarPaciente(PacienteNuevoDTO paciNuevo) throws NegocioException {
        if (paciNuevo == null) {
            throw new NegocioException("El paciente no puede ser nulo.");
        }

        // **Validación de correo**
        String correo = paciNuevo.getCorreo();
        if (correo == null || correo.trim().isEmpty()) {
            throw new NegocioException("El correo no puede estar vacío.");
        }
        if (correo.length() > 150) {
            throw new NegocioException("No se permiten correos con más de 150 caracteres.");
        }
        if (!Pattern.matches("^[^@\\s]+@[^@\\s]+\\.com$", correo)) {
            throw new NegocioException("El correo ingresado no es válido.");
        }
        if (correo.split("@")[0].length() < 2) {
            throw new NegocioException("El correo debe tener al menos dos caracteres antes del '@'");
        }

        // **Validación de contraseña**
        String password = paciNuevo.getContrasenia();
        if (password == null || password.trim().isEmpty()) {
            throw new NegocioException("La contraseña no puede estar vacía.");
        }
        if (password.length() > 20) {
            throw new NegocioException("No se permiten contraseñas con más de 20 caracteres.");
        }
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            throw new NegocioException("La contraseña debe contener al menos una mayúscula, una minúscula y un número.");
        }
        if (password.matches(".*(.)\\1{2,}.*")) {
            throw new NegocioException("La contraseña no puede contener secuencias repetitivas.");
        }
        if (password.contains(" ")) {
            throw new NegocioException("La contraseña no debe contener espacios.");
        }
        if (password.equalsIgnoreCase(correo)) {
            throw new NegocioException("La contraseña no puede ser igual al correo.");
        }

        // **Validación de nombre y apellidos**
        String nombre = paciNuevo.getNombre();
        String apellidoP = paciNuevo.getApellidoPaterno();
        String apellidoM = paciNuevo.getApellidoMaterno();
        if (!validarNombreApellido(nombre, "nombre")
                || !validarNombreApellido(apellidoP, "apellido paterno")
                || !validarNombreApellido(apellidoM, "apellido materno")) {
            throw new NegocioException("Nombre o apellidos inválidos.");
        }

        // **Validación de teléfono**
        String telefono = paciNuevo.getTelefono();
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new NegocioException("El teléfono no puede estar vacío.");
        }
        if (!telefono.matches("^\\d{10}$")) {
            throw new NegocioException("El teléfono debe contener exactamente 10 dígitos numéricos.");
        }

        // **Validación de fecha de nacimiento**
        LocalDate fechaN = paciNuevo.getFechaNacimiento();
        if (fechaN == null) {
            throw new NegocioException("La fecha de nacimiento no puede estar vacía.");
        }
        if (fechaN.isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de nacimiento no puede estar después de la fecha actual.");
        }
        int edad = LocalDate.now().getYear() - fechaN.getYear();
        if (edad < 0 || edad > 120) {
            throw new NegocioException("La edad debe estar entre 0 y 120 años.");
        }

        // **Validación de dirección**
        Direccion direccion = paciNuevo.getDireccion();
        if (direccion == null) {
            throw new NegocioException("La dirección no puede estar vacía.");
        }
        if (!validarDireccion(direccion)) {
            throw new NegocioException("La dirección contiene datos inválidos.");
        }

        // **Encriptar contraseña**
        String contraseniaEncriptada = encriptarContrasenia(password);
        paciNuevo.setContrasenia(contraseniaEncriptada);
        logger.log(Level.INFO, "Contraseña encriptada correctamente.");

        // **Crear usuario**
        Usuario usuario = new Usuario(0, correo, contraseniaEncriptada, "Paciente");

        // **Convertir DTO a entidad y registrar**
        Paciente paciente = pacienteMapper.toEntityNuevo(paciNuevo, usuario);
        try {
            pacienteDAO.registrarPaciente(paciente);
            return true;
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al registrar paciente en la base de datos", ex);
            throw new NegocioException("Hubo un error al guardar en la base de datos", ex);
        }
    }

    private String encriptarContrasenia(String contrasenia) throws NegocioException {
        try {
            return BCrypt.withDefaults().hashToString(12, contrasenia.toCharArray());
        } catch (Exception e) {
            throw new NegocioException("Error al encriptar contraseña: " + e.getMessage());
        }
    }

    public boolean verificarContrasenia(String contraseniaIngresada, String contraseniaEncriptada) {
        return BCrypt.verifyer().verify(contraseniaIngresada.toCharArray(), contraseniaEncriptada).verified;
    }

    private boolean validarNombreApellido(String valor, String tipo) {
        if (valor == null || valor.trim().isEmpty()) {
            return false;
        }
        if (!valor.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            return false;
        }
        if (valor.length() < 2 || valor.length() > 50) {
            return false;
        }
        if (!valor.trim().equals(valor)) {
            return false;
        }
        return true;
    }

    private boolean validarDireccion(Direccion direccion) {
        if (direccion.getCalle() == null || direccion.getCalle().trim().isEmpty()
                || direccion.getNumero() == null || direccion.getNumero().trim().isEmpty()
                || direccion.getColonia() == null || direccion.getColonia().trim().isEmpty()
                || direccion.getCodigo_postal() == null || !direccion.getCodigo_postal().matches("^[0-9]{5}$")) {
            return false;
        }
        if (!direccion.getCalle().matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ #,-.]+$")
                || !direccion.getNumero().matches("^[0-9A-Za-z]+$")
                || !direccion.getColonia().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            return false;
        }
        return true;
    }

    public int obtenerIdPacientePorCredenciales(String correo, String contrasenia) throws NegocioException {
        try {
            // Obtener el paciente por su correo
            Paciente paciente = pacienteDAO.obtenerPacientePorCorreo(correo);

            // Si el paciente no existe, credenciales inválidas
            if (paciente == null || paciente.getUsuario() == null) {
                return -1; // Usuario no encontrado
            }

            // Obtener la contraseña encriptada almacenada
            String contraseniaEncriptada = paciente.getUsuario().getContraseña();

            // Verificar la contraseña usando BCrypt
            boolean credencialesValidas = BCrypt.verifyer().verify(contrasenia.toCharArray(), contraseniaEncriptada).verified;

            if (credencialesValidas) {
                return paciente.getId_paciente(); // Retornar el ID del paciente si las credenciales son correctas
            } else {
                return -1; // Credenciales incorrectas
            }

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al verificar credenciales: " + e.getMessage());
        }
    }

    public boolean editarPaciente(PacienteNuevoDTO pacienteNuevoDTO) throws NegocioException {
        if (pacienteNuevoDTO == null) {
            throw new NegocioException("El paciente no puede ser nulo.");
        }

        // **Validación de correo**
        String correo = pacienteNuevoDTO.getCorreo();
        if (correo == null || correo.trim().isEmpty()) {
            throw new NegocioException("El correo no puede estar vacío.");
        }
        if (correo.length() > 150) {
            throw new NegocioException("No se permiten correos con más de 150 caracteres.");
        }
        if (!Pattern.matches("^[^@\\s]+@[^@\\s]+\\.com$", correo)) {
            throw new NegocioException("El correo ingresado no es válido.");
        }
        if (correo.split("@")[0].length() < 2) {
            throw new NegocioException("El correo debe tener al menos dos caracteres antes del '@'");
        }

        // **Validación de nombre y apellidos**
        String nombre = pacienteNuevoDTO.getNombre();
        String apellidoP = pacienteNuevoDTO.getApellidoPaterno();
        String apellidoM = pacienteNuevoDTO.getApellidoMaterno();
        if (!validarNombreApellido(nombre, "nombre")
                || !validarNombreApellido(apellidoP, "apellido paterno")
                || !validarNombreApellido(apellidoM, "apellido materno")) {
            throw new NegocioException("Nombre o apellidos inválidos.");
        }

        // **Validación de teléfono**
        String telefono = pacienteNuevoDTO.getTelefono();
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new NegocioException("El teléfono no puede estar vacío.");
        }
        if (!telefono.matches("^\\d{10}$")) {
            throw new NegocioException("El teléfono debe contener exactamente 10 dígitos numéricos.");
        }

        // **Validación de fecha de nacimiento**
        LocalDate fechaN = pacienteNuevoDTO.getFechaNacimiento();
        if (fechaN == null) {
            throw new NegocioException("La fecha de nacimiento no puede estar vacía.");
        }
        if (fechaN.isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de nacimiento no puede estar después de la fecha actual.");
        }
        int edad = LocalDate.now().getYear() - fechaN.getYear();
        if (edad < 0 || edad > 120) {
            throw new NegocioException("La edad debe estar entre 0 y 120 años.");
        }

        // **Validación de dirección**
        Direccion direccion = pacienteNuevoDTO.getDireccion();
        if (direccion == null) {
            throw new NegocioException("La dirección no puede estar vacía.");
        }
        if (!validarDireccion(direccion)) {
            throw new NegocioException("La dirección contiene datos inválidos.");
        }

        // **Crear paciente para actualización**
        Paciente paciente = pacienteMapper.toEntityActualizacion(pacienteNuevoDTO);

        try {
            pacienteDAO.editarPaciente(paciente);
            return true;
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al actualizar paciente en la base de datos", ex);
            throw new NegocioException("Hubo un error al actualizar en la base de datos", ex);
        }
    }

}
