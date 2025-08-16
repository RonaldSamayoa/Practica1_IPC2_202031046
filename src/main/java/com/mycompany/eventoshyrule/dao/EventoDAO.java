package com.mycompany.eventoshyrule.dao;
import com.mycompany.eventoshyrule.modelo.DBConnection;
import com.mycompany.eventoshyrule.modelo.Evento;
import java.math.BigDecimal;
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
public class EventoDAO {
    private DBConnection dbConnection;

    public EventoDAO() {
        this.dbConnection = new DBConnection();
    }

    // insertar datos del evento
    public boolean insertar(Evento evento) {
        String sql = "INSERT INTO Evento (codigo_evento, fecha, tipo_evento, titulo, ubicacion, cupo_maximo, costo_inscripcion) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, evento.getCodigoEvento());
            ps.setDate(2, evento.getFecha());
            ps.setString(3, evento.getTipoEvento());
            ps.setString(4, evento.getTitulo());
            ps.setString(5, evento.getUbicacion());
            ps.setInt(6, evento.getCupoMaximo());
            ps.setBigDecimal(7, evento.getCostoInscripcion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar evento: " + e.getMessage());
            return false;
        }
    }

    // listar a todos los eventos
    public List<Evento> listarTodos() {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Evento";
        try (Connection conn = dbConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Evento e = new Evento(
                    rs.getString("codigo_evento"),
                    rs.getDate("fecha"),
                    rs.getString("tipo_evento"),
                    rs.getString("titulo"),
                    rs.getString("ubicacion"),
                    rs.getInt("cupo_maximo"),
                    rs.getBigDecimal("costo_inscripcion")
                );
                lista.add(e);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar eventos: " + e.getMessage());
        }
        return lista;
    }

    // busqueda por codigo 
    public Evento buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM Evento WHERE codigo_evento = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Evento(
                        rs.getString("codigo_evento"),
                        rs.getDate("fecha"),
                        rs.getString("tipo_evento"),
                        rs.getString("titulo"),
                        rs.getString("ubicacion"),
                        rs.getInt("cupo_maximo"),
                        rs.getBigDecimal("costo_inscripcion")    
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar evento: " + e.getMessage());
        }
        return null;
    }

    // actualizaciones 
    public boolean actualizar(Evento evento) {
        String sql = "UPDATE Evento SET fecha = ?, tipo_evento = ?, titulo = ?, ubicacion = ?, cupo_maximo = ?, costo_inscripcion = ? "
                   + "WHERE codigo_evento = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, evento.getFecha());
            ps.setString(2, evento.getTipoEvento());
            ps.setString(3, evento.getTitulo());
            ps.setString(4, evento.getUbicacion());
            ps.setInt(5, evento.getCupoMaximo());
            ps.setBigDecimal(6, evento.getCostoInscripcion());
            ps.setString(7, evento.getCodigoEvento());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar evento: " + e.getMessage());
            return false;
        }
    }

    // eliminar por codigo
    public boolean eliminar(String codigo) {
        String sql = "DELETE FROM Evento WHERE codigo_evento = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar evento: " + e.getMessage());
            return false;
        }
    }
}
