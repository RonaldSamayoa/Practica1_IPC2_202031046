package com.mycompany.eventoshyrule.modelo;
import java.sql.Date;

/**
 *
 * @author ronald
 */
public class Evento {
    private String codigoEvento;
    private Date fecha; //para compatibilidad con la jdbc
    private String tipoEvento;
    private String titulo;
    private String ubicacion;
    private int cupoMaximo;
    
    public Evento (){
    }
    
    public Evento (String codigoEvento, Date fecha, String tipoEvento, String titulo, String ubicacion, int cupoMaximo){
    this.codigoEvento = codigoEvento;
    this.fecha = fecha;
    this.tipoEvento = tipoEvento;
    this.titulo = titulo;
    this.ubicacion = ubicacion;
    this.cupoMaximo = cupoMaximo;
    }
    
    public String getCodigoEvento(){
        return codigoEvento;
    }
    
    public void setCodigoEvento(String codigoEvento){
        this.codigoEvento = codigoEvento;
    }
    
    public Date getFecha(){
        return fecha;
    }
    
    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
    
    public String getTipoEvento(){
        return tipoEvento;
    }
    
    public void setTipoEvento(String tipoEvento){
        this.tipoEvento = tipoEvento;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public String getUbicacion(){
        return ubicacion;
    }
    
    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }
    
    public int getCupoMaximo (){
        return cupoMaximo;
    }
    
    public void setCupoMaximo (int cupoMaximo){
        this.cupoMaximo = cupoMaximo;
    }
}
