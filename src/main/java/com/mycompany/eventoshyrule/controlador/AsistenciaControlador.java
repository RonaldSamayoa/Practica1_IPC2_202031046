package com.mycompany.eventoshyrule.controlador;
import com.mycompany.eventoshyrule.dao.ActividadDAO;
import com.mycompany.eventoshyrule.dao.AsistenciaDAO;
import com.mycompany.eventoshyrule.dao.ParticipanteDAO;
import com.mycompany.eventoshyrule.modelo.Actividad;
import com.mycompany.eventoshyrule.modelo.Asistencia;
import com.mycompany.eventoshyrule.modelo.Participante;
import java.util.List;

/**
 *
 * @author ronald
 */
public class AsistenciaControlador {
    private AsistenciaDAO asistenciaDAO;
    private ParticipanteDAO participanteDAO;
    private ActividadDAO actividadDAO;
    
    public AsistenciaControlador(){
    this.asistenciaDAO = new AsistenciaDAO();
    this.participanteDAO = new ParticipanteDAO();
    this.actividadDAO = new ActividadDAO();
    }
    
    public boolean registrarAsistencia(String correoParticipante, String codigoActividad){
        //validar que hay un participante
        Participante participante = participanteDAO.buscarPorCorreo(correoParticipante);
        if (participante == null) {
            System.out.println("No existe un participante con el correo: " + correoParticipante);
            return false;
        }
        
        //validar actividad
        Actividad actividad = actividadDAO.buscarPorCodigo(codigoActividad);
        if (actividad == null) {
            System.out.println("No existe actividad con codigo: " + codigoActividad);
            return false;
        }
        
        //registrar asistencia
        Asistencia asistencia = new Asistencia(0, correoParticipante, codigoActividad);
        return asistenciaDAO.insertar(asistencia);
    }
    
    //listar asistencias
    public List<Asistencia> listarAsistencias(){
        return asistenciaDAO.listarTodos();
    }
    
    //busqueda por id
    public Asistencia buscarAsistencia(int idAsistencia){
        return asistenciaDAO.buscarPorId(idAsistencia);
    }
    
    //eliminar
    public boolean eliminarAsistencia(int idAsistencia){
        return asistenciaDAO.eliminar(idAsistencia);
    }
}
