package com.mycompany.eventoshyrule.controlador;
import com.mycompany.eventoshyrule.dao.ActividadDAO;
import com.mycompany.eventoshyrule.dao.AsistenciaDAO;
import com.mycompany.eventoshyrule.dao.CertificadoDAO;
import com.mycompany.eventoshyrule.dao.EventoDAO;
import com.mycompany.eventoshyrule.dao.ParticipanteDAO;
import com.mycompany.eventoshyrule.modelo.Actividad;
import com.mycompany.eventoshyrule.modelo.Asistencia;
import com.mycompany.eventoshyrule.modelo.Certificado;
import com.mycompany.eventoshyrule.modelo.Evento;
import com.mycompany.eventoshyrule.modelo.Participante;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

/**
 *
 * @author ronald
 */
public class CertificadoControlador {
    private CertificadoDAO certificadoDAO;
    private ParticipanteDAO  participanteDAO;
    private EventoDAO eventoDAO;
    private AsistenciaDAO asistenciaDAO;
    private ActividadDAO actividadDAO;
    
    private final String RUTA_CERTIFICADOS = "reportes/certificados";
    
    public CertificadoControlador(){
        this.certificadoDAO = new CertificadoDAO();
        this.participanteDAO = new ParticipanteDAO();
        this.eventoDAO = new EventoDAO();
        this.asistenciaDAO = new AsistenciaDAO();
        this.actividadDAO = new ActividadDAO();
    }
    
    public boolean registrarCertificado(String correoParticipante, String codigoEvento){
        //validar que hay un participante
        Participante participante = participanteDAO.buscarPorCorreo(correoParticipante);
        if (participante == null) {
            System.out.println("No existe un participante con el correo: " + correoParticipante);
            return false;
        }
        
        //comprobar que el evento existe
        Evento evento = eventoDAO.buscarPorCodigo(codigoEvento);
        if (evento == null) {
            System.out.println("No existe un evento con el codigo: " + codigoEvento);
            return false;
        }
        
        //validar que haya asistido a una actividad
        List<Asistencia> asistencias = asistenciaDAO.listarTodos();
        boolean asistio = false;
        
        for (Asistencia a : asistencias) {
            if (a.getCorreoParticipante().equals(correoParticipante)) {
                Actividad actividad = actividadDAO.buscarPorCodigo(a.getCodigoActividad());
                if (actividad != null && actividad.getCodigoEvento().equals(codigoEvento)) {
                    asistio = true;
                    break;
                }
            }
        }
        
        if (!asistio) {
            System.out.println("El participante no asistio a ninguna actividad");
            return false;
        }
        
        try {
            //crear carpeta si no existe
            Path dir = Paths.get(RUTA_CERTIFICADOS);
            Files.createDirectories(dir);
                    
            //construir nombre de archivo        
            String correoSafe = correoParticipante.replaceAll("[^a-zA-Z0-9._-]", "_");
            String eventoSafe = codigoEvento.replaceAll("[^a-zA-Z0-9._-]", "_");
            String nombreArchivo = "certificado_"+correoSafe + "_" + eventoSafe+".html";
            Path destino = dir.resolve(nombreArchivo);
                    
            //contenido html
            String html = "<html><body style='font-family:sans-serif;text-align:center;'>"
                    +"<h1>Certificado de Participacion</h1>"
                    +"<p>Se certifica que <b>"+ participante.getNombreCompleto() +" </b></p>" 
                    + "<p>ha participado en el evento <b>"+ evento.getTitulo()+"</b></p>"
                    + "<p>Fecha: "+evento.getFecha()+"</p>"
                    +"<p>Emitido por Eventos Hyrule</p>"
                    +"</body></html>";
            //guardar archivo
            Files.writeString(destino, html, StandardCharsets.UTF_8);
        //registrar certificado
        Certificado certificado = new Certificado(0, correoParticipante, codigoEvento, destino.toAbsolutePath().toString());
            return certificadoDAO.insertar(certificado);
                    
        } catch (IOException e) {
            System.out.println("Error generando certificado: "+ e.getMessage());
            return false;
        }
        
    }
    
    // listar certificados
    public List<Certificado> listarCertificados(){
        return certificadoDAO.listarTodos();
    }
    
    //buscar un certificado
    public Certificado buscarCertificado(int idCertificado){
        return certificadoDAO.buscarPorId(idCertificado);
    }
    
    // actualizar certificados
    public boolean actualizarCertificado(Certificado certificado){
        return certificadoDAO.actualizar(certificado);
    }
    
    public boolean eliminarCertificado(int idCertificado){
        return certificadoDAO.eliminar(idCertificado);
    }
}
