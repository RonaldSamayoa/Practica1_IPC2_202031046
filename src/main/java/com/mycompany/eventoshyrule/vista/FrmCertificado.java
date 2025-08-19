package com.mycompany.eventoshyrule.vista;
import com.mycompany.eventoshyrule.controlador.CertificadoControlador;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ronald
 */
public class FrmCertificado extends JInternalFrame {
    private JTextField txtCorreoParticipante;
    private JTextField txtCodigoEvento;
    private JTextField txtRutaArchivo;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JButton btnBuscarRuta;
    
    private CertificadoControlador certificadoCtrl;
    
    public FrmCertificado(){
        setTitle("Gestion de certificados");
        setSize(450,350);
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setLayout(new BorderLayout());
        
        certificadoCtrl = new CertificadoControlador();
        
        //panel de formulario 
        JPanel panelForm = new JPanel(new GridLayout(7, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelForm.add(new JLabel ("* Correo del participante: "));
        txtCorreoParticipante = new JTextField();
        panelForm.add(txtCorreoParticipante); 
        
        panelForm.add(new JLabel ("* Codigo del evento: "));
        txtCodigoEvento = new JTextField();
        panelForm.add(txtCodigoEvento);
        
        panelForm.add(new JLabel ("* Ruta del certificado "));
        JPanel panelRuta = new JPanel(new BorderLayout());
        txtRutaArchivo = new JTextField();
        btnBuscarRuta = new JButton("...");
        panelRuta.add(txtRutaArchivo,BorderLayout.CENTER);
        panelRuta.add(btnBuscarRuta,BorderLayout.EAST);
        panelForm.add(panelRuta);
        
        add(panelForm,BorderLayout.CENTER);
        
        //panel de botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        
        add(panelBotones, BorderLayout.SOUTH);
        
        btnGuardar.addActionListener(e -> guardarCertificado());
        btnCancelar.addActionListener(e -> limpiarCampos());
        btnBuscarRuta.addActionListener(e -> seleccionarRutaArchivo());
    }
    //guardar inscripcion usando el controlador 
    private void guardarCertificado(){
        try {
            String correoParticipante = txtCorreoParticipante.getText().trim(); 
            String codigoEvento = txtCodigoEvento.getText().trim();
            String rutaArchivo = txtRutaArchivo.getText().trim();
            
            if (correoParticipante.isEmpty() || codigoEvento.isEmpty() || rutaArchivo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean exito = certificadoCtrl.registrarCertificado(correoParticipante, codigoEvento, rutaArchivo);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Certificado registrado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else{
            JOptionPane.showMessageDialog(this, "No se pudo registrar el certificado", "Error", JOptionPane.ERROR_MESSAGE);
          } 
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error al registrar certificado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //seleccionar donde se guarda el archivo de certificado
    private void seleccionarRutaArchivo(){    
        JFileChooser fileChooser = new JFileChooser();
        int opcion = fileChooser.showSaveDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            txtRutaArchivo.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }
        
    private void limpiarCampos(){
        txtCorreoParticipante.setText("");
        txtCodigoEvento.setText("");
    }
}
