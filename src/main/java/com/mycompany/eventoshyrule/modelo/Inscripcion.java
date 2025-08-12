package com.mycompany.eventoshyrule.modelo;

/**
 *
 * @author ronald
 */
public class Inscripcion {
    private int idInscripcion;
    private String correoParticipante;
    private String codigoEvento;
    private String tipoInscripcion; // asistente, conferencista, etc...
    private boolean validada;
    
    //Constructor vacio para crear un objeto y llenar uno a uno los atributos
    public Inscripcion () {}
    
    //Cpnstructor para cuando se tienen todos los datos listos
    public Inscripcion (int idInscripcion, String correoParticipante, String codigoEvento, String tipoInscripcion, boolean validada) {
        this.idInscripcion = idInscripcion;
        this.correoParticipante = correoParticipante;
        this.codigoEvento = codigoEvento;
        this.tipoInscripcion = tipoInscripcion;
        this.validada = validada;
    }
    
    public int getIdInscripcion(){
    return idInscripcion;
    }
    
    public void setIdInscripcion (int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }
    
    public String getCorreoParticipante(){
        return correoParticipante;
    }
    
    public void setCorreoParticipante(String correoParticipante){                
        this.correoParticipante = correoParticipante;
    }
    
    public String getCodigoEvento(){
        return codigoEvento;
    }
    
    public void setCodigoEvento(String codigoEvento){                
        this.codigoEvento = codigoEvento;
    }
    
    public String getTipoInscripcion(){
        return tipoInscripcion;
    }
    
    public void setTipoInscripcion(String tipoInscripcion){                
        this.tipoInscripcion = tipoInscripcion;
    }
    
    public boolean isValidada(){
        return validada;
    }
    
    public void setValidada(boolean validada){                
        this.validada = validada;
    }
}
