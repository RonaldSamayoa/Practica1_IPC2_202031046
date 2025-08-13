package com.mycompany.eventoshyrule.dao;

import com.mycompany.eventoshyrule.modelo.DBConnection;
import com.mycompany.eventoshyrule.modelo.Pago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronald
 */
public class PagoDAO {
    private DBConnection dbConnection;
    
    public PagoDAO(){
        this.dbConnection = new DBConnection();
    }
    
    public boolean insertar(Pago pago){
    String sql = "INSERT INTO Pago (correo_participante, codigo_evento, metodo_pago, monto)"
                + "VALUES (?, ?, ?, ?)";
        try(Connection conn = dbConnection.getConnection ();
            PreparedStatement ps = conn.prepareStatement(sql)){
          
            ps.setString(1,pago.getCorreoParticipante());
            ps.setString(2,pago.getCodigoEvento());
            ps.setString(3,pago.getMetodoPago());
            ps.setBigDecimal(4,pago.getMonto());
            
            return ps.executeUpdate()>0;
        } catch (SQLException e){
            System.out.println("Error al realizar pago: " + e.getMessage());
            return false;
        }
    }
    
    public List <Pago> listarTodos(){
        List<Pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pago";
        try (Connection conn = dbConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)){
            
            while (rs.next()){
                Pago p = new Pago(
                   rs.getInt("id_pago"), 
                   rs.getString("correo_participante"),
                   rs.getString("codigo_evento"),
                   rs.getString("metodo_pago"),
                   rs.getBigDecimal("monto")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar pagos: " + e.getMessage());
        }
        return lista;
    }
    
    //busqueda por id
    public Pago buscarPorId(int id){
        String sql = "SELECT * FROM Pago WHERE id_pago = ?";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
                
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return new Pago(
                        rs.getInt("id_pago"), 
                        rs.getString("correo_participante"),
                        rs.getString("codigo_evento"),
                        rs.getString("metodo_pago"),
                        rs.getBigDecimal("monto")
                    );}
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar pago: " + e.getMessage());
        }
        return null;
    }
    
    //actualizar pago
    public boolean actualizar(Pago pago){
        String sql = "UPDATE Pago SET correo_participante = ?, codigo_evento = ?, monto = ? "
                + "WHERE id_pago = ?";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pago.getCorreoParticipante());
            ps.setString(2, pago.getCodigoEvento());
            ps.setString(3, pago.getMetodoPago());
            ps.setBigDecimal(4, pago.getMonto());
            ps.setInt(5, pago.getIdPago());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar pago: " + e.getMessage());
            return false;
        } 
    }
    
    //eliminar pago
    public boolean eliminar (int id){
        String sql = "DELETE FROM Pago WHERE id_pago = ? ";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, id);
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar participante: " + e.getMessage());
            return false;
        }
    }
    
    
}
