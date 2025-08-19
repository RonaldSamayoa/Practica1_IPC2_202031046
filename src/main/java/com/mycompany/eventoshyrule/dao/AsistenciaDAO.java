package com.mycompany.eventoshyrule.dao;

import com.mycompany.eventoshyrule.modelo.Asistencia;
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
public class AsistenciaDAO {
   private DBConnection dbConnection;

    public AsistenciaDAO() {
        this.dbConnection = new DBConnection();
    }
    
    //insertar datos 
    public boolean insertar (Asistencia asistencia) {
        String sql = "INSERT INTO Asistencia (correo_participante, codigo_actividad)"
                + "VALUES (?, ?)";
        try(Connection conn = dbConnection.getConnection ();
            PreparedStatement ps = conn.prepareStatement(sql)){ //para ejecutar los parametros query de entrada
            // prepara los parametros con los valores del objeto
            ps.setString(1,asistencia.getCorreoParticipante());
            ps.setString(2,asistencia.getCodigoActividad());
            
            return ps.executeUpdate()>0; //ejecuta la insercion y retorna true si fue exitosa
        } catch (SQLException e){
            System.out.println("Error al registrar asistencia: " + e.getMessage());
            return false;
        }
    }
    
    //Listar datos (select * from)
    public List <Asistencia> listarTodos(){
        List<Asistencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM Asistencia";
        try (Connection conn = dbConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)){ //ejecuta query y obtiene resultados
            
            while (rs.next()){
                Asistencia a = new Asistencia(
                   rs.getInt("id_asistencia"), 
                   rs.getString("correo_participante"),
                   rs.getString("codigo_actividad")
                );
                lista.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar asistencias: " + e.getMessage());
        }
        return lista;
    }
    
    //busqueda por id
    public Asistencia buscarPorId(int id){
        String sql = "SELECT * FROM Asistencia WHERE id_asistencia = ?";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
                
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return new Asistencia(
                    rs.getInt("id_asistencia"),
                    rs.getString("correo_participante"),
                    rs.getString("codigo_actividad")
                    );}
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar asistencia: " + e.getMessage());
        }
        return null;
    }
    
    //eliminar asistencia
    public boolean eliminar (int id){
        String sql = "DELETE FROM Asistencia WHERE id_asistencia = ? ";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, id);
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar asistencia: " + e.getMessage());
            return false;
        }
    }
}
