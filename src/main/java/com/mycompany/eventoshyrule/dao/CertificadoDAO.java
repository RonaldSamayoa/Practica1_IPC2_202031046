package com.mycompany.eventoshyrule.dao;

import com.mycompany.eventoshyrule.modelo.Certificado;
import com.mycompany.eventoshyrule.modelo.DBConnection;
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
public class CertificadoDAO {
    private DBConnection dbConnection;

    public CertificadoDAO() {
        this.dbConnection = new DBConnection();
    }
    
    //insertar datos 
    public boolean insertar (Certificado certificado) {
        String sql = "INSERT INTO Certificado (correo_participante, codigo_evento, ruta_archivo)"
                + "VALUES (?, ?, ?)";
        try(Connection conn = dbConnection.getConnection ();
            PreparedStatement ps = conn.prepareStatement(sql)){
          
            ps.setString(1,certificado.getCorreoParticipante());
            ps.setString(2,certificado.getCodigoEvento());
            ps.setString(3,certificado.getRutaArchivo());
            
            return ps.executeUpdate()>0;
        } catch (SQLException e){
            System.out.println("Error al registrar certificado: " + e.getMessage());
            return false;
        }
    }
    
    //Listar datos (select * from)
    public List <Certificado> listarTodos(){
        List<Certificado> lista = new ArrayList<>();
        String sql = "SELECT * FROM Certificado";
        try (Connection conn = dbConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)){
            
            while (rs.next()){
                Certificado c = new Certificado(
                   rs.getInt("id_certificado"), 
                   rs.getString("correo_participante"),
                   rs.getString("codigo_evento"),
                   rs.getString("titulo_actividad")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar certificados: " + e.getMessage());
        }
        return lista;
    }
    
    //busqueda por id
    public Certificado buscarPorId(int id){
        String sql = "SELECT * FROM Certificado WHERE id_certificado = ?";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
                
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return new Certificado(
                    rs.getInt("id_certificado"),
                    rs.getString("correo_participante"),
                    rs.getString("codigo_evento"),
                    rs.getString("ruta_archivo")
                    );}
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar certificado: " + e.getMessage());
        }
        return null;
    }
    
    //actualizar certificado
    public boolean actualizar(Certificado certificado){
        String sql = "UPDATE Certificado SET correo_participante = ?, codigo_evento = ?, ruta_archivo = ?"
                + "WHERE id_certificado = ?";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, certificado.getCorreoParticipante());
            ps.setString(2, certificado.getCodigoEvento());
            ps.setString(3, certificado.getRutaArchivo());
            ps.setInt(4, certificado.getIdCertificado());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar certificado: " + e.getMessage());
            return false;
        } 
    }
    
    //eliminar certificado
    public boolean eliminar (int id){
        String sql = "DELETE FROM Certificado WHERE id_certificado = ? ";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, id);
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar certificado: " + e.getMessage());
            return false;
        }
    }
}
