package com.mycompany.eventoshyrule.vista;
import com.mycompany.eventoshyrule.controlador.ActividadControlador;
import javax.swing.*;
import java.awt.*;
import java.sql.Time;
/**
 *
 * @author ronald
 */
public class FrmActividad extends JInternalFrame{
    private JTextField txtCodigoActividad;
    private JTextField txtCodigoEvento;
    private JComboBox<String> cbTipoActividad;
    private JTextField txtTituloActividad;
    private JTextField txtCorreoEncargado;
    private JTextField txtHoraInicio;
    private JTextField txtHoraFin;
    private JTextField txtCupoMaximo;
    
    private JButton btnGuardar;
    private JButton btnCancelar;
    
    private ActividadControlador actividadCtrl;
    
    public FrmActividad(){
        setTitle("Gestion de actividades");
        setSize(450,350);
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setLayout(new BorderLayout());
        
        actividadCtrl = new ActividadControlador();
        
        //panel de formulario 
        JPanel panelForm = new JPanel(new GridLayout(8, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelForm.add(new JLabel ("* Codigo de actividad "));
        txtCodigoActividad = new JTextField();
        panelForm.add(txtCodigoActividad); 
        
        panelForm.add(new JLabel ("* Codigo del evento: "));
        txtCodigoEvento = new JTextField();
        panelForm.add(txtCodigoEvento);
        
        panelForm.add(new JLabel ("* Tipo de actividad "));
        cbTipoActividad = new JComboBox<>(new String[]{"CHARLA", "TALLER", "DEBATE", "OTRA"});
        panelForm.add(cbTipoActividad);
        
        panelForm.add(new JLabel ("* Titulo de la actividad: "));
        txtTituloActividad = new JTextField();
        panelForm.add(txtTituloActividad);
        
        panelForm.add(new JLabel ("* Correo del encargado "));
        txtCorreoEncargado = new JTextField();
        panelForm.add(txtCorreoEncargado);
        
        panelForm.add(new JLabel ("* Hora de inicio (hrs:min) "));
        txtHoraInicio = new JTextField();
        panelForm.add(txtHoraInicio);
        
        panelForm.add(new JLabel ("* Hora fin (hrs:min) "));
        txtHoraFin = new JTextField();
        panelForm.add(txtHoraFin);
        
        panelForm.add(new JLabel ("* Cupo maximo "));
        txtCupoMaximo = new JTextField();
        panelForm.add(txtCupoMaximo);
        
        add(panelForm,BorderLayout.CENTER);
        
        //panel de botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        
        add(panelBotones, BorderLayout.SOUTH);
        
        btnGuardar.addActionListener(e -> guardarActividad());
        btnCancelar.addActionListener(e -> limpiarCampos());
    }
    
    //guardar inscripcion usando el controlador 
    private void guardarActividad(){
        try{
            String codigoActividad = txtCodigoActividad.getText().trim();
            String codigoEvento = txtCodigoEvento.getText().trim();
            String tipoActividad = (String) cbTipoActividad.getSelectedItem();
            String tituloActividad = txtTituloActividad.getText().trim();
            String correoEncargado = txtCorreoEncargado.getText().trim();
            
            // lee HH:mm y agrega :00 por el formato de TIME
            Time horaInicio = parseTimeHHmm(txtHoraInicio.getText().trim());
            Time horaFin = parseTimeHHmm(txtHoraFin.getText().trim());
            
            int cupoMaximo = Integer.parseInt(txtCupoMaximo.getText().trim());
            
            if (codigoActividad.isEmpty() || codigoEvento.isEmpty() || tituloActividad.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean exito = actividadCtrl.registrarActividad(codigoActividad, codigoEvento, tipoActividad, tituloActividad, correoEncargado, horaInicio, horaFin, cupoMaximo);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Actividad registrada con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else{
            JOptionPane.showMessageDialog(this, "No se pudo registrar la actividad", "Error", JOptionPane.ERROR_MESSAGE);
          }
        } catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Formato incorrecto en hora o en cupo", "Error", JOptionPane.ERROR_MESSAGE);}
    }
    
    private Time parseTimeHHmm(String valor){
        String v = valor == null ? "" : valor.trim();
        if (v.isEmpty()) throw new IllegalArgumentException("Hora vacia");
        if (v.length() == 5) v = v + ":00"; //de HH:mm a HH:mm:ss
        return Time.valueOf(v); //da IllegalArgumentException si no es valido
    }
    
    private void limpiarCampos(){
        txtCodigoActividad.setText("");
        txtCodigoEvento.setText("");
        cbTipoActividad.setSelectedIndex(0);
        txtTituloActividad.setText("");
        txtCorreoEncargado.setText("");
        txtHoraInicio.setText("");
        txtHoraFin.setText("");
        txtCupoMaximo.setText("");
    }
}
