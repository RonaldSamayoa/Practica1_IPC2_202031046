package com.mycompany.eventoshyrule.dao;
import com.mycompany.eventoshyrule.modelo.DBConnection;
import com.mycompany.eventoshyrule.modelo.Inscripcion;
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
public class InscripcionDAO {
    private DBConnection dbConnection;
    
    public InscripcionDAO(){
        this.dbConnection = new DBConnection();
    }
    
    //insertar datos 
    public boolean insertar (Inscripcion inscripcion) {
        String sql = "INSERT INTO Inscripcion (correo_participante, codigo_evento, tipo_inscripcion, validada)"
                + "VALUES (?, ?, ?, ?)";
        try(Connection conn = dbConnection.getConnection ();
            PreparedStatement ps = conn.prepareStatement(sql)){
          
            ps.setString(1,inscripcion.getCorreoParticipante());
            ps.setString(2,inscripcion.getCodigoEvento());
            ps.setString(3,inscripcion.getTipoInscripcion());
            ps.setBoolean(4,inscripcion.isValidada());
            
            return ps.executeUpdate()>0;
        } catch (SQLException e){
            System.out.println("Error al registrar la inscripcion: " + e.getMessage());
            return false;
        }
    }
    
    //Listar datos (select * from)
    public List <Inscripcion> listarTodos(){
        List<Inscripcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Inscripcion";
        try (Connection conn = dbConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)){
            
            while (rs.next()){
                Inscripcion i = new Inscripcion(
                   rs.getInt("id_inscripcion"),
                   rs.getString("correo_participante"), 
                   rs.getString("codigo_evento"),
                   rs.getString("tipo_inscripcion"),
                   rs.getBoolean("validada")
                );
                lista.add(i);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar inscripciones: " + e.getMessage());
        }
        return lista;
    }
    
    //busqueda por id
    public Inscripcion buscarPorId(String id){
        String sql = "SELECT * FROM Inscripcion WHERE id_inscripcion = ?";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
                
            ps.setString(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return new Inscripcion(
                    rs.getInt("id_inscripcion"),
                    rs.getString("correo_participante"),
                    rs.getString("codigo_evento"),
                    rs.getString("tipo_inscripcion"),
                    rs.getBoolean("validada")
                    );}
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar inscripcion: " + e.getMessage());
        }
        return null;
    }
    
    //actualizar la inscripcion
    public boolean actualizar(Inscripcion inscripcion){
        String sql = "UPDATE Inscripcion SET correo_participante = ?, codigo_evento = ?, tipo_inscripcion = ?, validada = ? "
                + "WHERE id_inscripcion = ?";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, inscripcion.getCorreoParticipante());
            ps.setString(2, inscripcion.getCodigoEvento());
            ps.setString(3, inscripcion.getTipoInscripcion());
            ps.setBoolean(4, inscripcion.isValidada());
            ps.setInt(5,inscripcion.getIdInscripcion());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar inscripcion: " + e.getMessage());
            return false;
        } 
    }
    
    //eliminar inscripcion
    public boolean eliminar (String id){
        String sql = "DELETE FROM Inscripcion WHERE id_inscripcion = ? ";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, id);
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar inscripcion: " + e.getMessage());
            return false;
        }
    }
    
    public boolean validarInscripcion (int id){
        String sql = "UPDATE Inscripcion SET validada = TRUE WHERE id_inscripcion = ? ";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, id);
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error al validar inscripcion: " + e.getMessage());
            return false;
        }
    }
}
