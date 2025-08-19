package com.mycompany.eventoshyrule.vista;
import com.mycompany.eventoshyrule.modelo.Participante;
import com.mycompany.eventoshyrule.controlador.ParticipanteControlador;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
/**
 *
 * @author ronald
 */
public class FrmListarParticipantes extends JInternalFrame{
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private ParticipanteControlador participanteCtrl;
    
    public FrmListarParticipantes(){
        setTitle("Listado de participantes");
        setSize(400,300);
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setLayout(new BorderLayout());
        
        participanteCtrl = new ParticipanteControlador();
        
        //definir columnas
        modeloTabla = new DefaultTableModel(
            new Object[]{"Correo", "Nombre Completo", "Tipo Participante", "Institucion"}, 0);
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
        List<Participante> lista = participanteCtrl.listarParticipantes();
        for (Participante p : lista) {
            modeloTabla.addRow(new Object[]{
                p.getCorreo(),
                p.getNombreCompleto(),
                p.getTipoParticipante(),
                p.getInstitucion()
            });
        }
    }
}
