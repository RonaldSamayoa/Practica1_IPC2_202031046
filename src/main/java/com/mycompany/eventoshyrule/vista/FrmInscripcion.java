package com.mycompany.eventoshyrule.vista;
import com.mycompany.eventoshyrule.controlador.InscripcionControlador;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ronald
 */
public class FrmInscripcion extends JInternalFrame {
    private JTextField txtCorreoParticipante;
    private JTextField txtCodigoEvento;
    private JComboBox<String> cbTipoInscripcion;
    private JButton btnGuardar;
    private JButton btnCancelar;
    
    private InscripcionControlador inscripcionCtrl;
    
    public FrmInscripcion(){
        setTitle("Gestion de inscripciones");
        setSize(450,350);
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setLayout(new BorderLayout());
        
        inscripcionCtrl = new InscripcionControlador();
        
        //panel de formulario 
        JPanel panelForm = new JPanel(new GridLayout(7, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelForm.add(new JLabel ("* Correo del participante: "));
        txtCorreoParticipante = new JTextField();
        panelForm.add(txtCorreoParticipante); 
        
        panelForm.add(new JLabel ("* Codigo del evento: "));
        txtCodigoEvento = new JTextField();
        panelForm.add(txtCodigoEvento);
        
        panelForm.add(new JLabel ("* Tipo de inscripcion: "));
        cbTipoInscripcion = new JComboBox<>(new String[]{"ASISTENTE", "CONFERENCISTA", "TALLERISTA", "OTRO"});
        panelForm.add(cbTipoInscripcion);
        
        add(panelForm,BorderLayout.CENTER);
        
        //panel de botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        
        add(panelBotones, BorderLayout.SOUTH);
        
        btnGuardar.addActionListener(e -> guardarInscripcion());
        btnCancelar.addActionListener(e -> limpiarCampos());
    }
    
    
    //guardar inscripcion usando el controlador 
    private void guardarInscripcion(){
            String correo = txtCorreoParticipante.getText().trim();
            String codigoEvento = txtCodigoEvento.getText().trim();
            String tipoInscripcion = (String) cbTipoInscripcion.getSelectedItem();
            
            if (correo.isEmpty() || codigoEvento.isEmpty() || tipoInscripcion == null) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean exito = inscripcionCtrl.crearInscripcion(correo, codigoEvento, tipoInscripcion);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Inscripcion registrado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else{
            JOptionPane.showMessageDialog(this, "No se pudo registrar la inscripcion", "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    
    private void limpiarCampos(){
        txtCorreoParticipante.setText("");
        txtCodigoEvento.setText("");
        cbTipoInscripcion.setSelectedIndex(0);
        
    }
}
