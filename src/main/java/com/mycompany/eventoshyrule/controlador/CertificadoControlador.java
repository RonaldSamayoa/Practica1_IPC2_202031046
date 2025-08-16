package com.mycompany.eventoshyrule.controlador;

import com.mycompany.eventoshyrule.dao.CertificadoDAO;
import com.mycompany.eventoshyrule.dao.EventoDAO;
import com.mycompany.eventoshyrule.dao.ParticipanteDAO;
import com.mycompany.eventoshyrule.modelo.Certificado;
import com.mycompany.eventoshyrule.modelo.Evento;
import com.mycompany.eventoshyrule.modelo.Participante;
import java.util.List;

/**
 *
 * @author ronald
 */
public class CertificadoControlador {
    private CertificadoDAO certificadoDAO;
    private ParticipanteDAO  participanteDAO;
    private EventoDAO eventoDAO;
    
    public CertificadoControlador(){
        this.certificadoDAO = new CertificadoDAO();
        this.participanteDAO = new ParticipanteDAO();
        this.eventoDAO = new EventoDAO();
    }
    
    public boolean registrarCertificado(String correoParticipante, String codigoEvento, String rutaArchivo){
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
        
        //registrar certificado
        Certificado certificado = new Certificado(0, correoParticipante, codigoEvento, rutaArchivo);
        return certificadoDAO.insertar(certificado);
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
