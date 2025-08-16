package com.mycompany.eventoshyrule.controlador;
import com.mycompany.eventoshyrule.dao.EventoDAO;
import com.mycompany.eventoshyrule.dao.InscripcionDAO;
import com.mycompany.eventoshyrule.dao.PagoDAO;
import com.mycompany.eventoshyrule.dao.ParticipanteDAO;
import com.mycompany.eventoshyrule.modelo.Evento;
import com.mycompany.eventoshyrule.modelo.Inscripcion;
import com.mycompany.eventoshyrule.modelo.Pago;
import com.mycompany.eventoshyrule.modelo.Participante;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ronald
 */
public class PagoControlador {
    private PagoDAO pagoDAO;
    private ParticipanteDAO participanteDAO;
    private EventoDAO eventoDAO;
    private InscripcionDAO inscripcionDAO;
    
    public PagoControlador(){
        this.pagoDAO = new PagoDAO();
        this.participanteDAO = new ParticipanteDAO();
        this.eventoDAO = new EventoDAO();
        this.inscripcionDAO = new InscripcionDAO();
    }
    
    // realizar el pago (y validar la inscripcion)
    public boolean registrarPago(String correoParticipante, String codigoEvento, String metodoPago, BigDecimal monto){
        //verificar que existe el participante
        Participante participante = participanteDAO.buscarPorCorreo(correoParticipante);
        if (participante==null) {
            System.out.println("No existe un participante con el correo: " + correoParticipante);
            return false;
        }
        
        // validar existencia de evento
        Evento evento = eventoDAO.buscarPorCodigo(codigoEvento);
        if (evento==null) {
            System.out.println("No existe un evento con el codigo: " + codigoEvento);
            return false;
        }
        
        // validar existencia de inscripcion previa
        Inscripcion inscripcion = null;
        //busqueda manual en memoria con un bucle
        for (Inscripcion i : inscripcionDAO.listarTodos()) {
            if (i.getCorreoParticipante().equals(correoParticipante) 
                    && i.getCodigoEvento().equals(codigoEvento)) {
                inscripcion = i;
                break;
            }
        }
        if (inscripcion==null) {
            System.out.println("No existe un participante con el correo: " + correoParticipante);
            return false;
        }
        //registrar el pago
        Pago pago = new Pago(0, correoParticipante, codigoEvento, metodoPago, monto);
        return pagoDAO.insertar(pago);        
    }
    
    //validar inscripcion
    public boolean validarInscripcion (String correoParticipante, String codigoEvento){
        Inscripcion inscripcion = null;
        for (Inscripcion i : inscripcionDAO.listarTodos()) {
            if (i.getCorreoParticipante().equals(correoParticipante)
                    && i.getCodigoEvento().equals(codigoEvento)) {
                inscripcion = i;
                break;
            }
        }
        if (inscripcion == null) {
            System.out.println("No existe una inscripcion para validar");
            return false;
        }
        
        if (inscripcion.isValidada()) {
            System.out.println("La inscripcion ya fue validada");
            return false;
        }
        
        inscripcion.setValidada(true);
        return inscripcionDAO.actualizar(inscripcion);
    }
    
    //listar todos los pagos
    public List<Pago> listarPagos(){
        return pagoDAO.listarTodos();
    }
    
    //buscar pago por id
    public Pago buscarPago(int idPago){
        return pagoDAO.buscarPorId(idPago);
    }
    
    //eliminar pago (opcional ante cualquier error)
    public boolean eliminarPago(int idPago){
        if (pagoDAO.buscarPorId(idPago)==null) {
            System.out.println("No se encontro el pago o no existe");
            return false;
        }
        return pagoDAO.eliminar(idPago);
    }
}
