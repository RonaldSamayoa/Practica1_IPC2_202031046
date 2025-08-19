package com.mycompany.eventoshyrule.vista;
import com.mycompany.eventoshyrule.controlador.AsistenciaControlador;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ronald
 */
public class FrmAsistencia extends JInternalFrame{
    private JTextField txtCorreoParticipante;
    private JTextField txtCodigoActividad;
    private JButton btnGuardar;
    private JButton btnCancelar;
    
    private AsistenciaControlador asistenciaCtrl; 
    
    public FrmAsistencia(){
        setTitle("Gestion de asistencias");
        setSize(450,350);
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setLayout(new BorderLayout());
        
        asistenciaCtrl = new AsistenciaControlador();
        
        //panel de formulario 
        JPanel panelForm = new JPanel(new GridLayout(7, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelForm.add(new JLabel ("* Correo del participante: "));
        txtCorreoParticipante = new JTextField();
        panelForm.add(txtCorreoParticipante); 
        
        panelForm.add(new JLabel ("* Codigo del actividad: "));
        txtCodigoActividad = new JTextField();
        panelForm.add(txtCodigoActividad);
        
        add(panelForm,BorderLayout.CENTER);
        
        //panel de botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Registrar asistencia");
        btnCancelar = new JButton("Cancelar");
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        
        add(panelBotones, BorderLayout.SOUTH);
        
        btnGuardar.addActionListener(e -> guardarAsistencia());
        btnCancelar.addActionListener(e -> limpiarCampos());
        
    }
    
    //guardar inscripcion usando el controlador 
    private void guardarAsistencia(){
        try {
            String correoParticipante = txtCorreoParticipante.getText().trim(); 
            String codigoActividad = txtCodigoActividad.getText().trim();
            
            if (correoParticipante.isEmpty() || codigoActividad.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean exito = asistenciaCtrl.registrarAsistencia(correoParticipante, codigoActividad);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Inscripcion registrado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else{
            JOptionPane.showMessageDialog(this, "No se pudo registrar la inscripcion", "Error", JOptionPane.ERROR_MESSAGE);
          } 
        } catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Error al registrar asistencia", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos(){
        txtCorreoParticipante.setText("");
        txtCodigoActividad.setText("");
    }
}
