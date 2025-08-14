package com.mycompany.eventoshyrule.controlador;

import com.mycompany.eventoshyrule.dao.EventoDAO;
import com.mycompany.eventoshyrule.dao.InscripcionDAO;
import com.mycompany.eventoshyrule.dao.ParticipanteDAO;
import com.mycompany.eventoshyrule.modelo.Evento;
import com.mycompany.eventoshyrule.modelo.Inscripcion;
import com.mycompany.eventoshyrule.modelo.Participante;
import java.util.List;

/**
 *
 * @author ronald
 */
public class InscripcionControlador {
    private InscripcionDAO inscripcionDAO;
    private ParticipanteDAO participanteDAO;
    private EventoDAO eventoDAO;
    
    public InscripcionControlador(){
        this.inscripcionDAO =  new InscripcionDAO();
        this.participanteDAO = new ParticipanteDAO();
        this.eventoDAO = new EventoDAO();
    }
    
    // crear una inscripcion con validaciones
    public boolean crearInscripcion (String correoParticipante, String codigoEvento, String tipoInscripcion){
        //validar que exista el participante 
        Participante participante = participanteDAO.buscarPorCorreo(correoParticipante);
        if (participante == null) {
            System.out.println("No existe un participante con el correo: " + correoParticipante);
            return false;
        }
        
        //validar que exista el participante 
        Evento evento = eventoDAO.buscarPorCodigo(codigoEvento);
        if (evento == null) {
            System.out.println("No existe un evento con el codigo: " + codigoEvento);
            return false;
        }
        
        //validar que no este ya inscrito
        List<Inscripcion> inscripciones = inscripcionDAO.listarTodos();
        for (Inscripcion ins : inscripciones) {
            if (ins.getCorreoParticipante().equals(correoParticipante) && ins.getCodigoEvento().equals(codigoEvento)) {
                System.out.println("El participante ya esta inscrito en el evento");
                return false;
            }
        }
        
        //Crear la inscripcion al cumplir validaciones
        Inscripcion inscripcion = new Inscripcion(0, correoParticipante, codigoEvento, tipoInscripcion, false);
        return inscripcionDAO.insertar(inscripcion);
    }
    
    // listar todas las inscripciones
    public List<Inscripcion> listarInscripciones(){
        return inscripcionDAO.listarTodos();
    }
    
    //validar inscripcion (tras pago)
    public boolean validarInscripcion (int idInscripcion){
        Inscripcion inscripcion = inscripcionDAO.buscarPorId(idInscripcion);
        if (inscripcion == null) {
            System.out.println("No existe una inscripcion con el ID: " + idInscripcion);
            return false;
        }
        inscripcion.setValidada(true);
        return inscripcionDAO.actualizar(inscripcion);
    }
    
    //eliminar una inscripcion
    public boolean eliminarInscripcion(int idInscripcion){
        if (inscripcionDAO.buscarPorId(idInscripcion)==null) {
            System.out.println("Inscripcion no encontrada o no se puede eliminar");
            return false;
        }
        return inscripcionDAO.eliminar(idInscripcion);
    }
}
