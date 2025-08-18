package com.mycompany.eventoshyrule.vista;
import com.mycompany.eventoshyrule.controlador.ParticipanteControlador;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ronald
 */
public class FrmParticipante extends JInternalFrame {
    private JTextField txtCorreo, txtNombreCompleto, txtInstitucion;
    private JComboBox<String> cbTipoParticipante;
    private JButton btnGuardar, btnCancelar;
    private ParticipanteControlador participanteCtrl;
    
    public FrmParticipante(){
        setTitle("Gestion de participantes");
        setSize(400,300);
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setLayout(new BorderLayout());
        
        participanteCtrl = new ParticipanteControlador();
        
        //panel principal
        JPanel panelForm = new JPanel(new GridLayout(5,2,10,10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        //campos
        panelForm.add(new JLabel ("*  Correo: "));
        txtCorreo = new JTextField();
        panelForm.add(txtCorreo);
        
        panelForm.add(new JLabel ("*  Nombre completo: "));
        txtNombreCompleto = new JTextField();
        panelForm.add(txtNombreCompleto);
        
        panelForm.add(new JLabel("*  Tipo de participante"));
        cbTipoParticipante = new JComboBox<>(new String[]{"ESTUDIANTE","PROFESIONAL","INVITADO"});
        panelForm.add(cbTipoParticipante);
        
        panelForm.add(new JLabel ("Institucion: "));
        txtInstitucion = new JTextField();
        panelForm.add(txtInstitucion);
        
        add (panelForm, BorderLayout.CENTER);
        
        //panel de botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        
        add(panelBotones, BorderLayout.SOUTH);
        
        btnGuardar.addActionListener(e -> guardarParticipante());
        btnCancelar.addActionListener(e -> limpiarCampos());
    }
    
    //guardar participante mediante el controlador
    private void guardarParticipante(){
        String correo = txtCorreo.getText().trim();
        String nombreCompleto = txtNombreCompleto.getText().trim();
        String tipoParticipante = (String) cbTipoParticipante.getSelectedItem();
        String institucion = txtInstitucion.getText().trim();
        
        if (correo.isEmpty() || nombreCompleto.isEmpty() || tipoParticipante == null) {
            JOptionPane.showMessageDialog(this, "Por favor, llene los campos obligatorios (*)", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean exito = participanteCtrl.crearParticipante(correo, nombreCompleto, tipoParticipante, institucion.isEmpty() ? null : institucion);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Participante registrado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else{
            JOptionPane.showMessageDialog(this, "No se pudo registrar el participante", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos(){
        txtCorreo.setText("");
        txtNombreCompleto.setText("");
        cbTipoParticipante.setSelectedIndex(0);
        txtInstitucion.setText("");
    }
}
