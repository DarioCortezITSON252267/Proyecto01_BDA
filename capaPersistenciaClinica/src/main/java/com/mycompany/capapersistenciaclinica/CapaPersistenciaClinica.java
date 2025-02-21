/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.capapersistenciaclinica;

import Conexion.ConexionBD;
import Conexion.IConexionBD;
import DAO.IPacienteDAO;
import DAO.PacienteDAO;
import Entidades.Direccion;
import Entidades.Paciente;
import Entidades.Usuario;
import Exception.PersistenciaException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author Angel
 */
public class CapaPersistenciaClinica {

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        IConexionBD conexion = new ConexionBD();
        IPacienteDAO pacienteDAO = new PacienteDAO(conexion);

        System.out.println("🔹 Ingresar los datos del paciente 🔹");

        // Capturar datos del Usuario
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido Paterno: ");
        String apellidoPaterno = scanner.nextLine();
        System.out.print("Apellido Materno: ");
        String apellidoMaterno = scanner.nextLine();
        System.out.print("Correo Electrónico: ");
        String correo = scanner.nextLine();
        System.out.print("Número de Teléfono: ");
        String telefono = scanner.nextLine();

        // Capturar fecha de nacimiento
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate fechaNacimiento = null;
        while (fechaNacimiento == null) {
            System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
            String fechaNacimientoStr = scanner.nextLine();
            try {
                fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println(" Error: Formato de fecha incorrecto. Use exactamente YYYY-MM-DD.");
            }
        }

        // Capturar datos de la Dirección
        System.out.println("🔹 Ingrese los datos de la dirección 🔹");
        System.out.print("Calle: ");
        String calle = scanner.nextLine();
        System.out.print("Número: ");
        String numero = scanner.nextLine();
        System.out.print("Colonia: ");
        String colonia = scanner.nextLine();
        System.out.print("Código Postal: ");
        int codigoPostal;
        try {
            codigoPostal = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" Error: El código postal debe ser un número.");
            return;
        }

        // Crear objetos Usuario y Dirección
        Usuario usuario = new Usuario(nombre, apellidoPaterno, apellidoMaterno, telefono, correo);
        Direccion direccion = new Direccion(calle, numero, colonia, codigoPostal);

        // Crear objeto Paciente
Paciente paciente = new Paciente(
    nombre, apellidoPaterno, apellidoMaterno, telefono, correo,
    fechaNacimiento, direccion
);

        // Intentar registrar el paciente
        try {
            pacienteDAO.registrarPaciente(paciente);
            System.out.println("✅ Paciente registrado correctamente.");
        } catch (PersistenciaException e) {
            System.out.println("❌ Error al registrar paciente: " + e.getMessage());
        }

        scanner.close();
    }
    }

