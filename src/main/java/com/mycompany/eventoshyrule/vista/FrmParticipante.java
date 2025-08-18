package com.mycompany.eventoshyrule.vista;
import com.mycompany.eventoshyrule.controlador.ParticipanteControlador;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ronald
 */
public class FrmParticipante extends JInternalFrame {
    private JTextField txtCorreo, txtxNombreCompleto, txtInstitucion;
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
        panelForm.add(new JLabel ("Correo: "));
        txtCorreo = new JTextField();
        panelForm.add(txtCorreo);
    }
}
