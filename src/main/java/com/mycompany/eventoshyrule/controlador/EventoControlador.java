package com.mycompany.eventoshyrule.controlador;

import com.mycompany.eventoshyrule.dao.EventoDAO;
import com.mycompany.eventoshyrule.modelo.Evento;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ronald
 */
public class EventoControlador {
    private EventoDAO eventoDAO;
    
    public EventoControlador(){
        this.eventoDAO = new EventoDAO();
    }
    
    public boolean crearEvento(String codigo, Date fecha, String tipoEvento, String titulo, String ubicacion, int cupoMaximo, BigDecimal costoInscripcion){
        if (codigo==null || codigo.isEmpty()) {
            System.out.println("El codigo del evento no puede estar vacio");
            return false;
        }
        
        if (fecha==null) {
            System.out.println("La fecha del evento no puede estar vacia");
            return false;
        }
        
        if (tipoEvento==null || tipoEvento.isEmpty()) {
            System.out.println("Debe indicarse el tipo de evento");
            return false;
        }
        
        if (titulo==null || titulo.isEmpty()) {
            System.out.println("El titulo del evento no puede estar vacio");
            return false;
        }
        
        if (cupoMaximo<=0) {
            System.out.println("El cupo maximo del evento debe ser mayor que cero");
            return false;
        }
        
        if (costoInscripcion.compareTo(BigDecimal.ZERO)< 0) {
            System.out.println("El costo de la inscripcion al evento no puede ser menor a cero");
            return false;
        }
        
        Evento evento = new Evento(codigo, fecha, tipoEvento, titulo, ubicacion, cupoMaximo, costoInscripcion);
        return eventoDAO.insertar(evento);
    }
    
    //listar a todos los eventos
    public List<Evento> listarEventos(){
        return eventoDAO.listarTodos();
    }
    
    //buscar un evento por codigo
    public Evento buscarEvento(String codigo){
        if (codigo==null || codigo.isEmpty()) {
            System.out.println("Debe ingresar un codigo para buscar");
            return null;
        }
        return eventoDAO.buscarPorCodigo(codigo);
    }
    
    //actualizacion de eventos
    public boolean actualizarEvento(String codigo, Date fecha, String tipoEvento, String titulo, String ubicacion, int cupoMaximo, BigDecimal costoInscripcion){
        if (buscarEvento(codigo)== null) {
            System.out.println("No se puede actualizar o el evento no existe");
            return false;
        }
        Evento evento = new Evento (codigo, fecha, tipoEvento, titulo, ubicacion, cupoMaximo, costoInscripcion);
        return eventoDAO.actualizar(evento);
    }
    
    //elimunar un evento
    public boolean eliminarEvento(String codigo){
        if (buscarEvento(codigo)== null) {
            System.out.println("No se puede eliminar o el evento no existe");
            return false;
        }
        return eventoDAO.eliminar(codigo);
    }    
}
