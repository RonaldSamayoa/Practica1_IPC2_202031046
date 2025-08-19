package com.mycompany.eventoshyrule.vista;
import com.mycompany.eventoshyrule.controlador.EventoControlador;
import com.mycompany.eventoshyrule.modelo.Evento;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author ronald
 */
public class FrmListarEventos extends JInternalFrame{
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private EventoControlador eventoCtrl;
    
    public FrmListarEventos(){
        setTitle("Listado de eventos");
        setSize(400,300);
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setLayout(new BorderLayout());
        
        eventoCtrl = new EventoControlador();
        
        //definir columnas
        modeloTabla = new DefaultTableModel(
            new Object[]{"Codigo Evento", "Fecha", "Tipo Evento", "Titulo",
                        "Ubicacion", "Cupo Maximo", "Costo inscripcion"}, 0);
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
        List<Evento> lista = eventoCtrl.listarEventos();
        for (Evento e : lista) {
            modeloTabla.addRow(new Object[]{
                e.getCodigoEvento(),
                e.getFecha(),
                e.getTipoEvento(),
                e.getTitulo(),
                e.getUbicacion(),
                e.getCupoMaximo(),
                e.getCostoInscripcion()
            });
        }
    }
}
