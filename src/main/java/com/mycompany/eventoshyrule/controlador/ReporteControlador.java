package com.mycompany.eventoshyrule.controlador;

import com.mycompany.eventoshyrule.dao.ActividadDAO;
import com.mycompany.eventoshyrule.dao.AsistenciaDAO;
import com.mycompany.eventoshyrule.dao.EventoDAO;
import com.mycompany.eventoshyrule.dao.InscripcionDAO;
import com.mycompany.eventoshyrule.dao.ParticipanteDAO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author ronald
 */
public class ReporteControlador {
    private String RUTA_REPORTES = "reportes";
    
    private ParticipanteDAO participanteDAO;
    private EventoDAO eventoDAO;
    private ActividadDAO actividadDAO;
    private AsistenciaDAO asistenciaDAO;
    private InscripcionDAO inscripcionDAO;
    
    public ReporteControlador(){
    this.participanteDAO = new ParticipanteDAO();          
    this.eventoDAO = new EventoDAO();
    this.actividadDAO = new ActividadDAO();
    this.asistenciaDAO = new AsistenciaDAO();
    this.inscripcionDAO = new InscripcionDAO();
        
    try {
      //crear carpetas si no existen
      Files.createDirectories(Paths.get(RUTA_REPORTES, "participantes"));
      Files.createDirectories(Paths.get(RUTA_REPORTES, "actividades"));
      Files.createDirectories(Paths.get(RUTA_REPORTES, "eventos"));
      
    } catch (IOException e) {
        System.out.println("Error creando carpetas de reportes: " + e.getMessage());
      }
    }
    
    public void generarReporteParticipantes(String codigoEvento, String tipoParticipante, String institucion){
        String nombreArchivo = RUTA_REPORTES + "/participantes/reporte_participantes_"+ codigoEvento + ".html";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))){
            writer.write("<html><head><tittle>Reporte de Participantes</tittle></head><body>");
            writer.write("<h1>Reporte de participantes del evento " + codigoEvento + "</h1>");
            
            writer.write("</body></html>");
            System.out.println("Reporte generado: " + nombreArchivo);            
        } catch (IOException e) {
            System.out.println("Error al generar reporte" + e.getMessage());
        }
    }
    
    public void generarReporteActividades(String codigoEvento, String tipoActividad, String correoEncargado){
        String nombreArchivo = RUTA_REPORTES + "/actividades/reporte_actividades_"+ codigoEvento + ".html";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))){
            writer.write("<html><head><tittle>Reporte de Actividades</tittle></head><body>");
            writer.write("<h1>Reporte de actividades del evento " + codigoEvento + "</h1>");
            
            writer.write("</body></html>");
            System.out.println("Reporte generado: " + nombreArchivo);            
        } catch (IOException e) {
            System.out.println("Error al generar reporte" + e.getMessage());
        }
    }
    
    public void generarReporteEventos(String tipoEvento, String fechaInicio, String fechaFin, String cupoMin, String cupoMax){
        String nombreArchivo = RUTA_REPORTES + "/eventos/reporte_eventos.html";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))){
            writer.write("<html><head><tittle>Reporte de Eventos</tittle></head><body>");
            writer.write("<h1>Reporte de eventos </h1>");
            
            writer.write("</body></html>");
            System.out.println("Reporte generado: " + nombreArchivo);            
        } catch (IOException e) {
            System.out.println("Error al generar reporte" + e.getMessage());
        }
    }
}
