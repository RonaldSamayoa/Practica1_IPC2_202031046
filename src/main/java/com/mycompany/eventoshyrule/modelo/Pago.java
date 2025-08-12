package com.mycompany.eventoshyrule.modelo;

import java.math.BigDecimal;

/**
 *
 * @author ronald
 */
public class Pago {
    private int idPago;
    private String correoParticipante;
    private String codigoEvento;
    private String metodoPago;
    private BigDecimal monto;
    
    public Pago () {}
    
    public Pago (int idPago, String correoParticipante, String codigoEvento, String metodoPago, BigDecimal monto){
        this.idPago = idPago;
        this.correoParticipante = correoParticipante;
        this.codigoEvento = codigoEvento;
        this.metodoPago = metodoPago;
        this.monto = monto;
    }
    
    public int getIdPago(){
        return idPago;
    }
    
    public void setIdPago(int idPago){
        this.idPago = idPago;
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
    
    public String getMetodoPago(){
        return metodoPago;
    }
    
    public void setMetodoPago(String metodoPago){
        this.metodoPago = metodoPago;
    }
    
    public BigDecimal getMonto(){
        return monto;
    }
    
    public void setMonto(BigDecimal monto){
        this.monto = monto;
    }
}
