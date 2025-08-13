package com.mycompany.eventoshyrule.dao;
import com.mycompany.eventoshyrule.modelo.DBConnection;
import com.mycompany.eventoshyrule.modelo.Participante;
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
public class ParticipanteDAO {
    private DBConnection dbConnection;
    
    public ParticipanteDAO(){
        this.dbConnection = new DBConnection();
    }
    
    //insertar datos 
    public boolean insertar (Participante participante) {
        String sql = "INSERT INTO Participante (correo, nombre_completo, tipo_participante, institucion)"
                + "VALUES (?, ?, ?, ?)";
        try(Connection conn = dbConnection.getConnection ();
            PreparedStatement ps = conn.prepareStatement(sql)){
          
            ps.setString(1,participante.getCorreo());
            ps.setString(2,participante.getNombreCompleto());
            ps.setString(3,participante.getTipoParticipante());
            ps.setString(4,participante.getInstitucion());
            
            return ps.executeUpdate()>0;
        } catch (SQLException e){
            System.out.println("Error al registrar participante: " + e.getMessage());
            return false;
        }
    }
    
    //Listar datos (select * from)
    public List <Participante> listarTodos(){
        List<Participante> lista = new ArrayList<>();
        String sql = "SELECT * FROM Participante";
        try (Connection conn = dbConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)){
            
            while (rs.next()){
                Participante p = new Participante(
                   rs.getString("correo"), 
                   rs.getString("nombre_completo"),
                   rs.getString("tipo_participante"),
                   rs.getString("institucion")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar participantes: " + e.getMessage());
        }
        return lista;
    }
    
    //busqueda por correo
    public Participante buscarPorCorreo(String correo){
        String sql = "SELECT * FROM Participante WHERE correo = ?";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
                
            ps.setString(1, correo);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return new Participante(
                    rs.getString("correo"),
                    rs.getString("nombre_completo"),
                    rs.getString("tipo_participante"),
                    rs.getString("institucion")
                    );}
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar participante: " + e.getMessage());
        }
        return null;
    }
    
    //actualizar al participante
    public boolean actualizar(Participante participante){
        String sql = "UPDATE Participante SET nombre_completo = ?, tipo_participante = ?, institucion = ? "
                + "WHERE correo = ?";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, participante.getNombreCompleto());
            ps.setString(2, participante.getTipoParticipante());
            ps.setString(3, participante.getInstitucion());
            ps.setString(4, participante.getCorreo());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar participante: " + e.getMessage());
            return false;
        } 
    }
    
    //eliminar participante
    public boolean eliminar (String correo){
        String sql = "DELETE FROM Participante WHERE correo = ? ";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, correo);
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar participante: " + e.getMessage());
            return false;
        }
    }
}
