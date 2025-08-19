package com.mycompany.eventoshyrule.vista;
import com.mycompany.eventoshyrule.controlador.ActividadControlador;
import com.mycompany.eventoshyrule.modelo.Actividad;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author ronald
 */
public class FrmListaActividades extends JInternalFrame{
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private ActividadControlador actividadCtrl;
    
    public FrmListaActividades(){
        setTitle("Listado de actividades");
        setSize(400,300);
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setLayout(new BorderLayout());
        
        actividadCtrl = new ActividadControlador();
        
        //definir columnas
        modeloTabla = new DefaultTableModel(
            new Object[]{"Codigo Actividad", "Codigo Evento", "Tipo Actividad", "Titulo",
                        "Correo Encargado", "Hora inicio", "Hora fin", "Cupo Maximo"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);
        
        JButton btnActualizar = new JButton("Actualizar lista");
        btnActualizar.addActionListener(e -> cargarDatos());
        add(btnActualizar,BorderLayout.SOUTH);
        
        //cargar al abrir
        cargarDatos();
    }
    
    private void cargarDatos(){
        modeloTabla.setRowCount(0); //limpiar tabla
        List<Actividad> lista = actividadCtrl.listarActividades();
        for (Actividad a : lista) {
            modeloTabla.addRow(new Object[]{
                a.getCodigoActividad(),
                a.getCodigoEvento(),
                a.getTipoActividad(),
                a.getTituloActividad(),
                a.getCorreoEncargado(),
                a.getHoraInicio(),
                a.getHoraFin(),
                a.getCupoMaximo()
            });
        }
    }
}
