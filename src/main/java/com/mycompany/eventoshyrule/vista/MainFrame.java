package com.mycompany.eventoshyrule.vista;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ronald
 */
public class MainFrame extends JFrame{
    private JDesktopPane desktopPane;
    
    public MainFrame(){
        setTitle("Eventos Hyrule");
        setExtendedState(JFrame.MAXIMIZED_BOTH); //pantalla completa
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //panel de escritorio para ventanas internas
        desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane, BorderLayout.CENTER);
        
        //barra de menu
        JMenuBar menuBar = new JMenuBar();
        
        //menu archivo
        JMenu menuArchivo = new JMenu ("Archivo");
        JMenuItem itemCargar = new JMenuItem("Cargar archivo");
        JMenuItem itemSalir = new JMenuItem("Salir");
        menuArchivo.add(itemCargar);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);
        
        //menu de participantes
        JMenu menuParticipantes = new JMenu ("Participantes");
        JMenuItem itemRegistrarP = new JMenuItem("Registrar participante");
        JMenuItem itemListarP = new JMenuItem("Listar participantes");
        menuParticipantes.add(itemRegistrarP);
        menuParticipantes.add(itemListarP);
        
        //menu eventos
        JMenu menuEventos = new JMenu ("Eventos");
        JMenuItem itemRegistrarE = new JMenuItem("Registrar evento");
        JMenuItem itemListarE = new JMenuItem("Listar eventos");
        menuEventos.add(itemRegistrarE);
        menuEventos.add(itemListarE);
        
        //menu inscripciones
        JMenu menuInscripciones = new JMenu ("Inscripciones");
        JMenuItem itemRegistrarI = new JMenuItem("Registrar inscripcion");
        JMenuItem itemValidarI = new JMenuItem("Validar inscripcion");
        menuInscripciones.add(itemRegistrarI);
        menuInscripciones.add(itemValidarI);
        
        // menu pago
        JMenu menuPagos = new JMenu ("Pagos");
        JMenuItem itemRegistrarPago = new JMenuItem("Registrar pago");
        menuPagos.add(itemRegistrarPago);
        
        // menu actividades
        JMenu menuActividades = new JMenu ("Actividades");
        JMenuItem itemRegistrarA = new JMenuItem("Registrar actividad");
        JMenuItem itemListarA = new JMenuItem("Listar actividades");
        menuActividades.add(itemRegistrarA);
        menuActividades.add(itemListarA);
        
        //menu asistencias
        JMenu menuAsistencias = new JMenu ("Asistencias");
        JMenuItem itemRegistrarAsis = new JMenuItem("Registrar asistencia");
        menuAsistencias.add(itemRegistrarAsis);
        
        //menu certificados
        JMenu menuCertificados = new JMenu ("Certificados");
        JMenuItem itemGenerarCert = new JMenuItem("Generar certificado");
        menuCertificados.add(itemGenerarCert);
        
        // menu reportes
        JMenu menuReportes = new JMenu ("Reportes");
        JMenuItem itemGenerarReporte = new JMenuItem("Generar reporte");
        menuReportes.add(itemGenerarReporte);
        
        //agregar menus a la barra
        menuBar.add(menuArchivo);
        menuBar.add(menuParticipantes);
        menuBar.add(menuEventos);
        menuBar.add(menuInscripciones);
        menuBar.add(menuPagos);
        menuBar.add(menuActividades);
        menuBar.add(menuAsistencias);
        menuBar.add(menuCertificados);
        menuBar.add(menuReportes);
        
        setJMenuBar(menuBar);
    }
    
    public JDesktopPane getDesktopPane(){
        return desktopPane;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            new MainFrame().setVisible(true);
        });
    }
}
