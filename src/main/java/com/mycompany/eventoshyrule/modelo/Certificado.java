package com.mycompany.eventoshyrule.modelo;

/**
 *
 * @author ronald
 */
public class Certificado {
    private int idCertificado;
    private String correoParticipante;
    private String codigoEvento;
    private String rutaArchivo;
    
    public Certificado() {}

    public Certificado(int idCertificado, String correoParticipante, String codigoEvento, String rutaArchivo){
    this.idCertificado = idCertificado;
    this.correoParticipante = correoParticipante;
    this.codigoEvento = codigoEvento;
    this.rutaArchivo = rutaArchivo;
    }
    
    public int getIdCertificado () {
    return idCertificado;
    }
    
    public void setIdCertificado (int idCertificado){
        this.idCertificado = idCertificado;
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
    
    public String getRutaArchivo(){
        return rutaArchivo;
    }
    
    public void setRutaArchivo(String rutaArchivo){
        this.rutaArchivo = rutaArchivo;
    }
    
}
