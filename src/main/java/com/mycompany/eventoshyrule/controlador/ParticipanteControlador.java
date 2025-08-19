package com.mycompany.eventoshyrule.controlador;
import com.mycompany.eventoshyrule.dao.ParticipanteDAO;
import com.mycompany.eventoshyrule.modelo.Participante;
import java.util.List;

/**
 *
 * @author ronald
 */
public class ParticipanteControlador {
    private ParticipanteDAO participanteDAO;
    
    public ParticipanteControlador (){
        this.participanteDAO = new ParticipanteDAO();
    }
    
    //crear participante
    public boolean crearParticipante(String nombreCompleto, String tipoParticipante, String institucion, String correo){
        if (correo==null || correo.isEmpty()) {
            System.out.println("El correo del participante no puede estar vacio");
            return false;
        }
        
        if (nombreCompleto==null || nombreCompleto.isEmpty()) {
            System.out.println("El nombre del participante no puede estar vacio");
            return false;
        }
        
        if (tipoParticipante==null || tipoParticipante.isEmpty()) {
            System.out.println("Debe indicar el tipo de participante");
            return false;
        }
        
        Participante participante = new Participante (nombreCompleto, tipoParticipante, institucion, correo);
        return participanteDAO.insertar(participante);
    }
    
    //listar a los participantes
    public List<Participante> listarEventos(){
        return participanteDAO.listarTodos();
    }
    
    //buscar a un participante por correo
    public Participante buscarParticipante(String correo){
        if (correo==null || correo.isEmpty()) {
            System.out.println("Debe ingresar un correo para buscar");
            return null;
        }
        return participanteDAO.buscarPorCorreo(correo);
    }
    
    //actualizacion de participantes
    public boolean actualizarParticipante(String nombreCompleto, String tipoParticipante, String institucion, String correo){
        if (buscarParticipante(correo)== null) {
            System.out.println("No se puede actualizar o el participante no esta registrado");
            return false;
        }
        Participante participante = new Participante (correo, nombreCompleto, tipoParticipante, institucion);
        return participanteDAO.actualizar(participante);
    }
    
    //elimunar a un participante
    public boolean eliminarEvento(String correo){
        if (buscarParticipante(correo)== null) {
            System.out.println("No se puede eliminar o el participante no esta registrado");
            return false;
        }
        return participanteDAO.eliminar(correo);
    }
}
