package com.mycompany.eventoshyrule.vista;
import com.mycompany.eventoshyrule.controlador.EventoControlador;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ronald
 */
public class FrmEvento extends JInternalFrame{
    private JTextField txtCodigo;
    private JTextField txtFecha;
    private JComboBox<String> cbTipoEvento;
    private JTextField txtTitulo;
    private JTextField txtUbicacion;
    private JTextField txtCupoMaximo;
    private JTextField txtCostoInscripcion;
    private JButton btnGuardar;
    private JButton btnCancelar;
    
    private EventoControlador eventoCtrl;
    
    public FrmEvento(){
        setTitle("Gestion de eventos");
        setSize(450,350);
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setLayout(new BorderLayout());
        
        eventoCtrl = new EventoControlador();
        
        //panel de formulario
        JPanel panelForm = new JPanel(new GridLayout(7, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        //campos
        panelForm.add(new JLabel ("*  Codigo: "));
        txtCodigo = new JTextField();
        panelForm.add(txtCodigo);
    }
}
