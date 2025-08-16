package com.mycompany.eventoshyrule.controlador;
import com.mycompany.eventoshyrule.dao.ActividadDAO;
import com.mycompany.eventoshyrule.dao.EventoDAO;
import com.mycompany.eventoshyrule.dao.InscripcionDAO;
import com.mycompany.eventoshyrule.dao.ParticipanteDAO;
import com.mycompany.eventoshyrule.modelo.Actividad;
import com.mycompany.eventoshyrule.modelo.Evento;
import com.mycompany.eventoshyrule.modelo.Inscripcion;
import com.mycompany.eventoshyrule.modelo.Participante;
import java.sql.Time;
import java.util.List;
/**
 *
 * @author ronald
 */
public class ActividadControlador {
    private ActividadDAO actividadDAO;
    private EventoDAO eventoDAO;
    private ParticipanteDAO participanteDAO;
    private InscripcionDAO inscripcionDAO;
    
    public ActividadControlador(){
        this.actividadDAO = new ActividadDAO();
        this.eventoDAO = new EventoDAO();
        this.participanteDAO = new ParticipanteDAO();
        this.inscripcionDAO = new InscripcionDAO();
    }
    
    public boolean registrarActividad (String codigoActividad, String codigoEvento, String tipoActividad, String tituloActividad,
                                    String correoEncargado, Time horaInicio, Time horaFin, int cupoMaximo){
        
        //comprobar que el evento existe
        Evento evento = eventoDAO.buscarPorCodigo(codigoEvento);
        if (evento == null) {
            System.out.println("No existe un evento con el codigo: " + codigoEvento);
            return false;
        }
        
        //validar que el participante encargado exista
        Participante encargado = participanteDAO.buscarPorCorreo(correoEncargado);
        if (encargado == null) {
            System.out.println("No existe un participante con el correo: " + correoEncargado);
            return false;
        }
        
        // validar la inscripcion del encargado en el evento
        Inscripcion inscripcionEncargado = inscripcionDAO.buscarPorCorreoYEvento(correoEncargado, codigoEvento);
        if (inscripcionEncargado == null) {
            System.out.println("El encargado no esta inscrito");
            return false;
        }
        
        // el encargado no puede ser asistente
        if ("ASISTENTE".equalsIgnoreCase(inscripcionEncargado.getTipoInscripcion())) {
            System.out.println("El encargado no puede ser asistente");
            return false;
        }
        
        //registrar la actividad
        Actividad actividad = new Actividad(codigoActividad, codigoEvento, tipoActividad, tituloActividad,
                                            correoEncargado, horaInicio,horaFin, cupoMaximo);
            return actividadDAO.insertar(actividad);
    }
    
    //listar todas las actividades
    public List<Actividad> listarActividades(){
        return actividadDAO.listarTodos();
    }
    
    // buscar actividad por codigo
    public Actividad buscarActividad(String codigoActividad){
        return actividadDAO.buscarPorCodigo(codigoActividad);
    }
    
     //actualizacion de actividad
    public boolean actualizarActividad(Actividad actividad){
        return actividadDAO.actualizar(actividad);
    }
    
    //elimunar a un participante
    public boolean eliminarActividad(String codigoActividad){
        return actividadDAO.eliminar(codigoActividad);
    }
}

