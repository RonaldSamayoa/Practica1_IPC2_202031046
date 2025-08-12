package com.mycompany.eventoshyrule.modelo;
import java.sql.Time;

/**
 *
 * @author ronald
 */
public class Actividad {
    private String codigoActividad;
    private String codigoEvento;
    private String tipoActividad;
    private String tituloActividad;
    private String correoEncargado;
    private Time horaInicio;
    private Time horaFin;
    private int cupoMaximo;
    
    public Actividad () {}
    
    public Actividad (String codigoActividad, String codigoEvento, String tipoActividad, 
            String tituloActividad, String correoEncargado,Time horaInicio, Time horaFin, int cupoMaximo) {
        this.codigoActividad = codigoActividad;
        this.codigoEvento = codigoEvento;
        this.tipoActividad = tipoActividad;
        this.tituloActividad = tituloActividad;
        this.correoEncargado = correoEncargado;   
        this.horaInicio =  horaInicio;
        this.horaFin = horaFin;
        this.cupoMaximo = cupoMaximo;
    }
    
    public String getCodigoActividad(){
        return codigoActividad;
    }
    
    public void setCodigoActividad(String codigoActividad){
        this.codigoActividad = codigoActividad;
    }
    
    public String getCodigoEvento(){
        return codigoEvento;
    }
    
    public void setCodigoEvento(String codigoEvento){
        this.codigoEvento = codigoEvento;
    }
    
    
    public String getTipoActividad(){
        return tipoActividad;
    }
    
    public void setTipoActividad(String tipoActividad){
        this.tipoActividad = tipoActividad;
    }
    
    public String getTituloActividad(){
        return tituloActividad;
    }
    
    public void setTituloActividad(String tituloActividad){
        this.tituloActividad = tituloActividad;
    }
    
    public String getCorreoEncargado(){
        return correoEncargado;
    }
    
    public void setCorreoEncargado(String correoEncargado){
        this.correoEncargado = correoEncargado;   
    }
    
    
    public Time getHoraInicio(){
        return horaInicio;
    }
    
    public void setHoraInicio(Time horaInicio){
        this.horaInicio =  horaInicio;
    }
    
    public Time getHoraFin(){
        return horaFin;
    }
    
    public void setHoraFin(Time horaFin){
        this.horaFin = horaFin;
    }
    
    public int getCupoMaximo(){
        return cupoMaximo;
    }
    
    public void setCupoMaximo(int cupoMaximo){
        this.cupoMaximo = cupoMaximo;
    }
        
}
