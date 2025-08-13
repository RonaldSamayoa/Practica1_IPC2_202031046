package com.mycompany.eventoshyrule.dao;

import com.mycompany.eventoshyrule.modelo.Actividad;
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
public class ActividadDAO {
    private DBConnection dbConnection;

    public ActividadDAO() {
        this.dbConnection = new DBConnection();
    }
    
    //insertar datos 
    public boolean insertar (Actividad actividad) {
        String sql = "INSERT INTO Actividad (codigo_actividad, codigo_evento, tipo_actividad, titulo_actividad, correo_encargado, hora_inicio, hora_fin, cupo_maximo)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = dbConnection.getConnection ();
            PreparedStatement ps = conn.prepareStatement(sql)){
          
            ps.setString(1,actividad.getCodigoActividad());
            ps.setString(2,actividad.getCodigoEvento());
            ps.setString(3,actividad.getTipoActividad());
            ps.setString(4,actividad.getTituloActividad());
            ps.setString(5,actividad.getCorreoEncargado());
            ps.setTime(6, actividad.getHoraInicio());
            ps.setTime(7, actividad.getHoraFin());
            ps.setInt(8, actividad.getCupoMaximo());
            
            return ps.executeUpdate()>0;
        } catch (SQLException e){
            System.out.println("Error al registrar actividad: " + e.getMessage());
            return false;
        }
    }
    
    //Listar datos (select * from)
    public List <Actividad> listarTodos(){
        List<Actividad> lista = new ArrayList<>();
        String sql = "SELECT * FROM Actividad";
        try (Connection conn = dbConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)){
            
            while (rs.next()){
                Actividad a = new Actividad(
                   rs.getString("codigo_actividad"), 
                   rs.getString("codigo_evento"),
                   rs.getString("tipo_actividad"),
                   rs.getString("titulo_actividad"),
                   rs.getString("correo_encargado"), 
                   rs.getTime("hora_inicio"),
                   rs.getTime("hora_fin"),
                   rs.getInt("cupo_maximo")
                );
                lista.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar actividades: " + e.getMessage());
        }
        return lista;
    }
    
    //busqueda por codigo
    public Actividad buscarPorCodigo(String codigo){
        String sql = "SELECT * FROM Actividad WHERE codigo_actividad = ?";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
                
            ps.setString(1, codigo);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return new Actividad(
                    rs.getString("codigo_actividad"),
                    rs.getString("codigo_evento"),
                    rs.getString("tipo_actividad"),
                    rs.getString("titulo_actividad"),
                    rs.getString("correo_encargado"),
                    rs.getTime("hora_inicio"),
                    rs.getTime("hora_fin"),
                    rs.getInt("cupo_maximo")
                    );}
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar actividad: " + e.getMessage());
        }
        return null;
    }
    
    //actualizar actividad
    public boolean actualizar(Actividad actividad){
        String sql = "UPDATE Actividad SET codigo_evento = ?, tipo_actividad = ?, titulo_actividad = ?, correo_encargado = ?, hora_inicio = ?, hora_fin = ?, cupo_maximo = ? "
                + "WHERE codigo_actividad = ?";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, actividad.getCodigoEvento());
            ps.setString(2, actividad.getTipoActividad());
            ps.setString(3, actividad.getTituloActividad());
            ps.setString(4, actividad.getCorreoEncargado());
            ps.setTime(5, actividad.getHoraInicio());
            ps.setTime(6, actividad.getHoraFin());
            ps.setInt(7, actividad.getCupoMaximo());
            ps.setString(8, actividad.getCodigoActividad());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar actividades: " + e.getMessage());
            return false;
        } 
    }
    
    //eliminar actividad
    public boolean eliminar (String codigo){
        String sql = "DELETE FROM Actividad WHERE codigo_actividad = ? ";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, codigo);
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar actividad: " + e.getMessage());
            return false;
        }
    }
}
