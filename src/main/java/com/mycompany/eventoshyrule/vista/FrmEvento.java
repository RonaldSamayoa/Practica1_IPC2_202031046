package com.mycompany.eventoshyrule.vista;
import com.mycompany.eventoshyrule.controlador.EventoControlador;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ronald
 */
public class FrmEvento extends JInternalFrame{
    private JTextField txtCodigo;
    private JDateChooser txtFecha;
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
        
        panelForm.add(new JLabel ("* Fecha: "));
        txtFecha = new JDateChooser(); //calendario
        txtFecha.setDateFormatString("dd/MM/yyyy"); //formato
        panelForm.add(txtCodigo);
        
        panelForm.add(new JLabel("*  Tipo de evento: "));
        cbTipoEvento = new JComboBox<>(new String[]{"CHARLA", "CONGRESO","TALLER", "DEBATE"});
        panelForm.add(cbTipoEvento);
        
        panelForm.add(new JLabel (" * Titulo: "));
        txtTitulo = new JTextField();
        panelForm.add(txtTitulo);
        
        panelForm.add(new JLabel("Ubicacion"));
        txtUbicacion = new JTextField();
        panelForm.add(txtUbicacion);
        
        panelForm.add(new JLabel("* Cupo maximo"));
        txtCupoMaximo = new JTextField();
        panelForm.add(txtCupoMaximo);
        
        panelForm.add(new JLabel("* Costo inscripcion"));
        txtCostoInscripcion = new JTextField();
        panelForm.add(txtCostoInscripcion);
        
        add(panelForm, BorderLayout.CENTER);
        
        //panel de botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        
        add(panelBotones, BorderLayout.SOUTH);
        
        btnGuardar.addActionListener(e -> guardarEvento());
        btnCancelar.addActionListener(e -> limpiarCampos());
    }
    
    //guardar evento usando el controlador 
    private void guardarEvento(){
        try {
            String codigo = txtCodigo.getText().trim();
            java.util.Date fechaUtil = txtFecha.getDate(); //obtener fecha del jDateChooser
            if (fechaUtil == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Date fecha = new Date(fechaUtil.getTime()); //convertir a java.sql.Date
            
            String tipoEvento = (String) cbTipoEvento.getSelectedItem();
            String titulo = txtTitulo.getText().trim();
            String ubicacion = txtUbicacion.getText().trim();
            int cupoMaximo = Integer.parseInt(txtCupoMaximo.getText().trim());
            BigDecimal costoInscripcion = new BigDecimal(txtCostoInscripcion.getText().trim());
            
            if (codigo.isEmpty() || titulo.isEmpty() || tipoEvento == null) {
                JOptionPane.showMessageDialog(this, "Por favor, llene los campos obligatorios (*)", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean exito = eventoCtrl.crearEvento(codigo, fecha, tipoEvento, titulo, ubicacion.isEmpty() ? null : ubicacion, cupoMaximo, costoInscripcion);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Evento registrado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else{
            JOptionPane.showMessageDialog(this, "No se pudo registrar el evento", "Error", JOptionPane.ERROR_MESSAGE);
        }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Formato incorrecto en campos numericos", "Error", JOptionPane.ERROR_MESSAGE);        
        }
    }
    
    private void limpiarCampos(){
        txtCodigo.setText("");
        txtFecha.setDate(null);
        cbTipoEvento.setSelectedIndex(0);
        txtTitulo.setText("");
        txtUbicacion.setText("");
        txtCupoMaximo.setText("");
        txtCostoInscripcion.setText("");
    }
}
