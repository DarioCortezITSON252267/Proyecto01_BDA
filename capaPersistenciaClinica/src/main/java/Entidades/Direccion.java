/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Todos
 */
public class Direccion {

    private int id_direccion;
    private String calle;
    private String numero;
    private String colonia;
    private int codigo_postal;
    private int id_usuario;

    // Constructor vacío
    public Direccion() {
    }

    // Constructor con todos los atributos
    public Direccion(int id_direccion, String calle, String numero, String colonia, int codigo_postal, int id_usuario) {
        this.id_direccion = id_direccion;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigo_postal = codigo_postal;
        this.id_usuario = id_usuario;
    }

    // Getters y Setters
    public int getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(int id_direccion) {
        this.id_direccion = id_direccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public int getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(int codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "Direccion{" + "id_direccion=" + id_direccion + ", calle='" + calle + '\'' + ", numero='" + numero + '\'' +
        ", colonia='" + colonia + '\'' + ", codigo_postal=" + codigo_postal + ", id_usuario=" + id_usuario + '}';
    }
}


