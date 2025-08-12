package com.mycompany.eventoshyrule.modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author ronald
 */
public class DBConnection {
    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "eventos_hyrule";
    private static final String USER_NAME = "admin";
    private static final String PASSWORD = "Ronald01!";
    private static final String URL = "jdbc:mysql://" +
            IP + ":" + PUERTO + "/" + SCHEMA
            + "?useSSL=false&serverTimezone=UTC";
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
    
    
    //probar conexion manualmente
    public void connect() {
        System.out.println("URL de conexion: " + URL);
        
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);){
            System.out.println("Esquema: " + connection.getSchema());
            System.out.println("Catalogo: " + connection.getCatalog());
            
        } catch (SQLException e) {
            System.out.println("Error al conectar");
            e.printStackTrace(); 
        }
    }
}
