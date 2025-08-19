package com.mycompany.eventoshyrule.vista;
import com.mycompany.eventoshyrule.controlador.PagoControlador;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
/**
 *
 * @author ronald
 */
public class FrmPago extends JInternalFrame {
    private JTextField txtCorreoParticipante;
    private JTextField txtCodigoEvento;
    private JComboBox<String> cbMetodoPago;
    private JTextField txtMonto;
    private JButton btnGuardar;
    private JButton btnCancelar;
    
    private PagoControlador pagoCtrl;
    
    public FrmPago(){
        setTitle("Gestion de pagos");
        setSize(450,350);
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setLayout(new BorderLayout());
        
        pagoCtrl = new PagoControlador();
        
        //panel de formulario 
        JPanel panelForm = new JPanel(new GridLayout(7, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelForm.add(new JLabel ("* Correo del participante: "));
        txtCorreoParticipante = new JTextField();
        panelForm.add(txtCorreoParticipante); 
        
        panelForm.add(new JLabel ("* Codigo del evento: "));
        txtCodigoEvento = new JTextField();
        panelForm.add(txtCodigoEvento);
        
        panelForm.add(new JLabel ("* Metodo de pago "));
        cbMetodoPago = new JComboBox<>(new String[]{"EFECTIVO", "TARJETA", "TRANSFERENCIA"});
        panelForm.add(cbMetodoPago);
        
        panelForm.add(new JLabel ("* Monto: "));
        txtMonto = new JTextField();
        panelForm.add(txtMonto);
        
        add(panelForm,BorderLayout.CENTER);
        
        //panel de botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Registrar pago");
        btnCancelar = new JButton("Cancelar");
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        
        add(panelBotones, BorderLayout.SOUTH);
        
        btnGuardar.addActionListener(e -> registrarPago());
        btnCancelar.addActionListener(e -> limpiarCampos());
    }
    
    //guardar inscripcion usando el controlador 
    private void registrarPago(){
        try {
            String correo = txtCorreoParticipante.getText().trim();
            String codigoEvento = txtCodigoEvento.getText().trim();
            String metodoPago = (String) cbMetodoPago.getSelectedItem();
            BigDecimal monto = new BigDecimal(txtMonto.getText().trim());
            
            if (correo.isEmpty() || codigoEvento.isEmpty() || metodoPago == null) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean exito = pagoCtrl.registrarPago(correo, codigoEvento, metodoPago, monto);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Pago registrado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else{
            JOptionPane.showMessageDialog(this, "No se pudo registrar el pago", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Error en el monto ingresado" , "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos(){
        txtCorreoParticipante.setText("");
        txtCodigoEvento.setText("");
        cbMetodoPago.setSelectedIndex(0);
        txtMonto.setText("");
    }
}
