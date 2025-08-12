package com.mycompany.eventoshyrule.modelo;

/**
 *
 * @author ronald
 */
public class Participante {
    private String correo;
    private String nombreCompleto;
    private String tipoParticipante;
    private String institucion;
    
    public Participante(){}
    
    public Participante(String correo, String nombreCompleto, String tipoParticipante, String institucion){
        this.correo = correo;
        this.nombreCompleto = nombreCompleto;
        this.tipoParticipante = tipoParticipante;
        this.institucion = institucion;
    }
    
    public String getCorreo(){
        return correo;
    }
    
    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    public String getNombreCompleto(){
        return nombreCompleto;
    }
    
    public void setNombreCompleto(String nombreCompleto){
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getTipoParticipante(){
        return tipoParticipante;
    }
    
    public void setTipoParticipante(String tipoParticipante){
        this.tipoParticipante = tipoParticipante;
    }
    
    public String getInstitucion(){
        return institucion;
    }
    
    public void setInstitucion(String institucion){
        this.institucion = institucion;
    }
    
}
