/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.capapersistenciaclinica;

import Conexion.ConexionBD;
import Conexion.IConexionBD;
import DAO.CitaDAO;
import DAO.IPacienteDAO;
import DAO.MedicoDAO;
import DAO.PacienteDAO;
import Entidades.Direccion;
import Entidades.Paciente;
import Entidades.Usuario;
import Exception.PersistenciaException;
import com.sun.tools.javac.Main;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Todos
 */
public class CapaPersistenciaClinica {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear la conexión a la base de datos
        IConexionBD conexion = new ConexionBD();
        IPacienteDAO pacienteDAO = new PacienteDAO(conexion);
        MedicoDAO medicoDAO = new MedicoDAO(conexion);

//        System.out.println("🔹 Ingresar los datos del paciente 🔹");
//
//        // Capturar datos del Usuario
//        System.out.print("Usuario: ");
//        String usuario = scanner.nextLine();
//        System.out.print("Contraseña: ");
//        String contraseña = scanner.nextLine();
//        System.out.print("Nombre: ");
//        String nombre = scanner.nextLine();
//        System.out.print("Apellido Paterno: ");
//        String apellidoPaterno = scanner.nextLine();
//        System.out.print("Apellido Materno: ");
//        String apellidoMaterno = scanner.nextLine();
//        System.out.print("Correo Electrónico: ");
//        String correo = scanner.nextLine();
//        System.out.print("Número de Teléfono: ");
//        String telefono = scanner.nextLine();
//
//        // Capturar fecha de nacimiento
//        System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
//        String fechaNacimientoStr = scanner.nextLine();
//        LocalDate fechaNacimiento = null;
//        try {
//            fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
//        } catch (Exception e) {
//            System.out.println("Error: Formato de fecha incorrecto. Use YYYY-MM-DD.");
//            return;
//        }
//
//        // Capturar datos de la Dirección
//        System.out.println("🔹 Ingrese los datos de la dirección 🔹");
//        System.out.print("Calle: ");
//        String calle = scanner.nextLine();
//        System.out.print("Número: ");
//        String numero = scanner.nextLine();
//        System.out.print("Colonia: ");
//        String colonia = scanner.nextLine();
//        System.out.print("Código Postal: ");
//        String codigoPostal;
//        try {
//            codigoPostal = scanner.nextLine();
//        } catch (NumberFormatException e) {
//            System.out.println(" Error: El código postal debe ser un número.");
//            return;
//        }
//
//        // Crear objetos Usuario y Dirección
//        Usuario usuarioObj = new Usuario();
//        usuarioObj.setUsuario(usuario);
//        usuarioObj.setContraseña(contraseña);
//    
//
//        Direccion direccion = new Direccion();
//        direccion.setCalle(calle);
//        direccion.setNumero(numero);
//        direccion.setColonia(colonia);
//        direccion.setCodigo_postal(codigoPostal);
//
//        // Crear objeto Paciente con Usuario y Dirección
//        Paciente paciente = new Paciente();
//        paciente.setFecha_nacimiento(fechaNacimiento);
//        paciente.setNombre(nombre);
//        paciente.setApellido_paterno(apellidoPaterno);
//        paciente.setApellido_materno(apellidoMaterno);
//        paciente.setTelefono(telefono);
//        paciente.setCorreo(correo);
//        paciente.setUsuario(usuarioObj);
//        paciente.setDireccion(direccion);
//
//        // Intentar registrar el paciente
//        try {
//            pacienteDAO.registrarPaciente(paciente);
//            System.out.println("Paciente registrado correctamente.");
//        } catch (PersistenciaException e) {
//            System.out.println("Error al registrar paciente: " + e.getMessage());
//        } finally {
//            scanner.close();
//        }
//    }
//      Aqui se prueba la actualizacion de datos del paciente 
//     System.out.println("Actualización de Datos del Paciente");
//
//        // Capturar ID del Paciente a Actualizar
//        System.out.print("Ingrese el ID del paciente a actualizar: ");
//        int idPaciente;
//        try {
//            idPaciente = Integer.parseInt(scanner.nextLine());
//        } catch (NumberFormatException e) {
//            System.out.println("Error: El ID debe ser un número.");
//            return;
//        }
//
//        // Capturar datos del Usuario
//        System.out.print("Nueva Contraseña: ");
//        String nuevaContrasenia = scanner.nextLine();
//        System.out.print("Nuevo Nombre: ");
//        String nuevoNombre = scanner.nextLine();
//        System.out.print("Nuevo Apellido Paterno: ");
//        String nuevoApellidoPaterno = scanner.nextLine();
//        System.out.print("Nuevo Apellido Materno: ");
//        String nuevoApellidoMaterno = scanner.nextLine();
//        System.out.print("Nuevo Correo Electrónico: ");
//        String nuevoCorreo = scanner.nextLine();
//        System.out.print("Nuevo Número de Teléfono: ");
//        String nuevoTelefono = scanner.nextLine();
//
//        // Capturar fecha de nacimiento
//        System.out.print("Nueva Fecha de Nacimiento (YYYY-MM-DD): ");
//        String nuevaFechaNacimientoStr = scanner.nextLine();
//        LocalDate nuevaFechaNacimiento;
//        try {
//            nuevaFechaNacimiento = LocalDate.parse(nuevaFechaNacimientoStr);
//        } catch (Exception e) {
//            System.out.println("Error: Formato de fecha incorrecto. Use YYYY-MM-DD.");
//            return;
//        }
//
//        // Capturar datos de la Dirección
//        System.out.println("Ingrese los nuevos datos de la dirección");
//        System.out.print("Nueva Calle: ");
//        String nuevaCalle = scanner.nextLine();
//        System.out.print("Nuevo Número: ");
//        String nuevoNumero = scanner.nextLine();
//        System.out.print("Nueva Colonia: ");
//        String nuevaColonia = scanner.nextLine();
//        System.out.print("Nuevo Código Postal: ");
//        String nuevoCodigoPostal;
//        try {
//            nuevoCodigoPostal = scanner.nextLine();
//        } catch (NumberFormatException e) {
//            System.out.println("Error: El código postal debe ser un número.");
//            return;
//        }
//
//        // Crear objetos Usuario y Dirección
//        Usuario usuarioObj = new Usuario();
//        usuarioObj.setContraseña(nuevaContrasenia);
//
//        Direccion direccionObj = new Direccion();
//        direccionObj.setCalle(nuevaCalle);
//        direccionObj.setNumero(nuevoNumero);
//        direccionObj.setColonia(nuevaColonia);
//        direccionObj.setCodigo_postal(nuevoCodigoPostal);
//
//        // Crear objeto Paciente con Usuario y Dirección
//        Paciente paciente = new Paciente();
//        paciente.setId_paciente(idPaciente);
//        paciente.setFecha_nacimiento(nuevaFechaNacimiento);
//        paciente.setNombre(nuevoNombre);
//        paciente.setApellido_paterno(nuevoApellidoPaterno);
//        paciente.setApellido_materno(nuevoApellidoMaterno);
//        paciente.setTelefono(nuevoTelefono);
//        paciente.setCorreo(nuevoCorreo);
//        paciente.setUsuario(usuarioObj);
//        paciente.setDireccion(direccionObj);
//
//        // Intentar actualizar el paciente
//        try {
//            Paciente pacienteActualizado = pacienteDAO.editarPaciente(paciente);
//            System.out.println("Paciente actualizado correctamente: " + pacienteActualizado);
//        } catch (PersistenciaException e) {
//            System.out.println("Error al actualizar paciente: " + e.getMessage());
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
//        } finally {
//            scanner.close();
//        }
//    }
////      aqui se prueba el metodo para que el medico se pueda dar de baja temporalmente
//        System.out.print("Ingrese su ID de médico para darse de baja: ");
//        int idMedico = scanner.nextInt();
//
//        try {
//            boolean resultado = medicoDAO.desactivarMedico(idMedico);
//            if (resultado) {
//                System.out.println("Te has dado de baja exitosamente.");
//            } else {
//                System.out.println("No se pudo completar la solicitud de baja.");
//            }
//        } catch (PersistenciaException e) {
//            System.err.println("Error: " + e.getMessage());
//        } finally {
//            scanner.close();
//        }
//    }
        CitaDAO citaDAO = new CitaDAO(conexion);

        System.out.print("Ingrese su ID de paciente para ver su historial de citas: ");
        int idPaciente = scanner.nextInt();

        try {
            List<String> historial = citaDAO.verHistorialCitas(idPaciente);
            if (historial.isEmpty()) {
                System.out.println("No se encontraron citas para el paciente.");
            } else {
                System.out.println("Historial de Citas:");
                for (String cita : historial) {
                    System.out.println(cita);
                }
            }
        } catch (PersistenciaException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
