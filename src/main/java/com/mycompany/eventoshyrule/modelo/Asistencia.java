package com.mycompany.eventoshyrule.modelo;

/**
 *
 * @author ronald
 */
public class Asistencia {
    private int idAsistencia;
    private String correoParticipante;
    private String codigoActividad;
    
    public Asistencia(){}
    
    public Asistencia(int idAsistencia, String correoParticipante, String codigoActividad){
        this.idAsistencia = idAsistencia;
        this.correoParticipante = correoParticipante;
        this.codigoActividad = codigoActividad;
    }
    
    public int getIdAsistencia(){
        return idAsistencia;
    }
    
    public void setIdAsistencia(int idAsistencia){
        this.idAsistencia = idAsistencia;
    } 
    
    public String getCorreoParticipante(){
        return correoParticipante;
    }    
    
    public void setCorreoParticipante(String correoParticipante){
        this.correoParticipante = correoParticipante;
    }
    
    public String getCodigoActividad(){
        return codigoActividad;
    }    
    
    public void setCodigoActividad(String codigoActividad){
        this.codigoActividad = codigoActividad;
    }
    
}
