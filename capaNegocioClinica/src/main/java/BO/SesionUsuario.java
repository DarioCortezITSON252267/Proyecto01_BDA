/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

/**
 *
 * @author chris
 */
public class SesionUsuario {

    public static int idPaciente = -1;

    public static void iniciarSesion(int id) {
        idPaciente = id;
    }

    public static void cerrarSesion() {
        idPaciente = -1;
    }
}
