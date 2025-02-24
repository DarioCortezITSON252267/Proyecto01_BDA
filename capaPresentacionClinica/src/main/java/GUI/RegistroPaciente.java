/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BO.PacienteBO;
import Conexion.ConexionBD;
import Conexion.IConexionBD;
import DTO.PacienteNuevoDTO;
import Entidades.Direccion;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author chris
 */
public class RegistroPaciente extends javax.swing.JFrame {

    /**
     * Creates new form InicioPaciente
     */
    public RegistroPaciente() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        CampoDeNombre = new javax.swing.JTextField();
        CampoDeApellidoP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        CampoDeApellidoM = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        CampoDeTelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        CampoDeCorreo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        CampoDeFechaNac = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        CampoDeContraseña = new javax.swing.JPasswordField();
        CampoDeCalle = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        CampoDeColonia = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        CampoDeCodifoPostal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        CampoDeNumero = new javax.swing.JTextField();
        BtnRegistrar1 = new javax.swing.JButton();

        jButton2.setBackground(new java.awt.Color(51, 153, 0));
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Confirmar");

        jPasswordField2.setBackground(new java.awt.Color(255, 255, 255));
        jPasswordField2.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel2.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Apellido Paterno");

        CampoDeNombre.setBackground(new java.awt.Color(255, 255, 255));
        CampoDeNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoDeNombreActionPerformed(evt);
            }
        });

        CampoDeApellidoP.setBackground(new java.awt.Color(255, 255, 255));
        CampoDeApellidoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoDeApellidoPActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nombre");

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jLabel3.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Registro");

        btnRegresar.setBackground(new java.awt.Color(204, 204, 204));
        btnRegresar.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegresar)
                .addGap(133, 133, 133)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(btnRegresar)
                .addGap(22, 22, 22))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Apellido Materno");

        CampoDeApellidoM.setBackground(new java.awt.Color(255, 255, 255));
        CampoDeApellidoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoDeApellidoMActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Telefono");

        CampoDeTelefono.setBackground(new java.awt.Color(255, 255, 255));
        CampoDeTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoDeTelefonoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Calle");

        jLabel8.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Contraseña");

        CampoDeCorreo.setBackground(new java.awt.Color(255, 255, 255));
        CampoDeCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoDeCorreoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Correo");

        CampoDeFechaNac.setBackground(new java.awt.Color(255, 255, 255));
        CampoDeFechaNac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoDeFechaNacActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Fecha nacimiento");

        CampoDeContraseña.setBackground(new java.awt.Color(255, 255, 255));
        CampoDeContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoDeContraseñaActionPerformed(evt);
            }
        });

        CampoDeCalle.setBackground(new java.awt.Color(255, 255, 255));
        CampoDeCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoDeCalleActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Numero");

        CampoDeColonia.setBackground(new java.awt.Color(255, 255, 255));
        CampoDeColonia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoDeColoniaActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Colonia");

        CampoDeCodifoPostal.setBackground(new java.awt.Color(255, 255, 255));
        CampoDeCodifoPostal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoDeCodifoPostalActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("C.P.");

        CampoDeNumero.setBackground(new java.awt.Color(255, 255, 255));
        CampoDeNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoDeNumeroActionPerformed(evt);
            }
        });

        BtnRegistrar1.setBackground(new java.awt.Color(204, 204, 204));
        BtnRegistrar1.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        BtnRegistrar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnRegistrar1.setText("Registrarse");
        BtnRegistrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegistrar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(CampoDeColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CampoDeCodifoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(CampoDeNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CampoDeNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(CampoDeApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CampoDeApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(CampoDeTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CampoDeFechaNac)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9)
                                    .addComponent(CampoDeCorreo)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(CampoDeContraseña)
                                    .addComponent(CampoDeCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(BtnRegistrar1)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CampoDeNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CampoDeApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CampoDeApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoDeContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CampoDeTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoDeCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CampoDeFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CampoDeCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel7)
                        .addGap(28, 28, 28)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CampoDeColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoDeNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CampoDeCodifoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnRegistrar1)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CampoDeApellidoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoDeApellidoPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoDeApellidoPActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed

        InicioPaciente nuevaVentana = new InicioPaciente();

        // Hacer visible la nueva ventana
        nuevaVentana.setVisible(true);

        // Cerrar la ventana actual (InicioSesion)
        this.dispose();    }//GEN-LAST:event_btnRegresarActionPerformed

    private void CampoDeApellidoMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoDeApellidoMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoDeApellidoMActionPerformed

    private void CampoDeTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoDeTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoDeTelefonoActionPerformed

    private void CampoDeCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoDeCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoDeCorreoActionPerformed

    private void CampoDeFechaNacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoDeFechaNacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoDeFechaNacActionPerformed

    private void CampoDeContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoDeContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoDeContraseñaActionPerformed

    private void CampoDeNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoDeNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoDeNombreActionPerformed

    private void CampoDeCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoDeCalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoDeCalleActionPerformed

    private void CampoDeColoniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoDeColoniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoDeColoniaActionPerformed

    private void CampoDeCodifoPostalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoDeCodifoPostalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoDeCodifoPostalActionPerformed

    private void CampoDeNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoDeNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoDeNumeroActionPerformed

    private void BtnRegistrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrar1ActionPerformed
        try {
            // Obtener datos de los campos de texto
            String nombre = CampoDeNombre.getText().trim();
            String apellidoPaterno = CampoDeApellidoP.getText().trim();
            String apellidoMaterno = CampoDeApellidoM.getText().trim();
            String telefono = CampoDeTelefono.getText().trim();
            String correo = CampoDeCorreo.getText().trim();
            String contrasenia = new String(CampoDeContraseña.getPassword()).trim();

            // Convertir la fecha ingresada
            LocalDate fechaNacimiento = LocalDate.parse(CampoDeFechaNac.getText().trim());

            // Obtener datos de la dirección
            String calle = CampoDeCalle.getText().trim();
            String numero = CampoDeNumero.getText().trim();
            String colonia = CampoDeColonia.getText().trim();
            String codigoPostal = CampoDeCodifoPostal.getText().trim();

            // Crear el objeto Dirección
            Direccion direccion = new Direccion(calle, numero, colonia, codigoPostal);

            //  Permitir apellido materno vacío o nulo
            if (apellidoMaterno.isEmpty()) {
                apellidoMaterno = null;
            }

            // Crear el DTO de paciente con los datos recopilados
            PacienteNuevoDTO pacienteNuevo = new PacienteNuevoDTO(
                    nombre,
                    apellidoPaterno,
                    apellidoMaterno, // Puede ser null ahora
                    telefono,
                    fechaNacimiento,
                    correo,
                    contrasenia,
                    direccion
            );

            // Crear la conexión y la capa de negocio
            IConexionBD conexion = new ConexionBD(); // Asegúrate de que esta clase esté implementada correctamente
            PacienteBO pacienteBO = new PacienteBO(conexion);

            // Llamar al método de negocio para registrar el paciente
            boolean registrado = pacienteBO.registrarPaciente(pacienteNuevo);

            if (registrado) {
                JOptionPane.showMessageDialog(this, "Paciente registrado exitosamente.");
                // Redirigir a la pantalla de inicio del paciente
                InicioPaciente inicio = new InicioPaciente();
                inicio.setVisible(true);
                this.dispose(); // Cierra la ventana actual solo si el registro fue exitoso
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar el paciente. Intenta de nuevo.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar el paciente: " + ex.getMessage());
            ex.printStackTrace();
        }
        // No cerramos la ventana para que el usuario pueda corregir sus datos
    }//GEN-LAST:event_BtnRegistrar1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnRegistrar1;
    private javax.swing.JTextField CampoDeApellidoM;
    private javax.swing.JTextField CampoDeApellidoP;
    private javax.swing.JTextField CampoDeCalle;
    private javax.swing.JTextField CampoDeCodifoPostal;
    private javax.swing.JTextField CampoDeColonia;
    private javax.swing.JPasswordField CampoDeContraseña;
    private javax.swing.JTextField CampoDeCorreo;
    private javax.swing.JTextField CampoDeFechaNac;
    private javax.swing.JTextField CampoDeNombre;
    private javax.swing.JTextField CampoDeNumero;
    private javax.swing.JTextField CampoDeTelefono;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField2;
    // End of variables declaration//GEN-END:variables
}
