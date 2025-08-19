package com.mycompany.eventoshyrule.vista;

import com.mycompany.eventoshyrule.controlador.ReporteControlador;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author ronald
 */
public class FrmReportes extends JInternalFrame{
    private ReporteControlador reporteCtrl;
    private JComboBox<String> cbTipoReporte;
    private JButton btnGenerar;
    private JTextField txtEvento, txtFiltro1, txtFiltro2, txtExtra1,txtExtra2;
    
    public FrmReportes(){
        setTitle("Gestion de reportes");
        setSize(400,300);
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setLayout(new BorderLayout());
        
        reporteCtrl = new ReporteControlador();
        
        JPanel panelForm = new JPanel(new GridLayout(5,2,10,10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Selecciona el reporte"));
        
        cbTipoReporte = new JComboBox<>(new String[]{
            "Participantes","Actividades","Eventos"
        });
        
        /*panelTop.add(new JLabel("Tipo de reporte: "));
        panelTop.add(cbTipoReporte);*/
        
        
        
    }
    
    private void generarReporte(){
        String tipo = (String) cbTipoReporte.getSelectedItem();
        try {
            if ("Participantes".equals(tipo)) {
                reporteCtrl.generarReporteParticipantes(txtEvento.getText().trim(), txtFiltro1.getText().trim(), txtFiltro2.getText().trim());
                JOptionPane.showMessageDialog(this, "Reporte generado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } else if ("Actividades".equals(tipo)) {
                reporteCtrl.generarReporteActividades(txtEvento.getText().trim(), txtFiltro1.getText().trim(), txtFiltro2.getText().trim());
                JOptionPane.showMessageDialog(this, "Reporte generado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } else if ("Eventos".equals(tipo)) {
                reporteCtrl.generarReporteEventos(txtFiltro1.getText().trim(), txtFiltro2.getText().trim(), txtExtra1.getText().trim(), "", txtExtra2.getText().trim());
                JOptionPane.showMessageDialog(this, "Reporte generado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
