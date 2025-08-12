package com.mycompany.eventoshyrule;

import com.mycompany.eventoshyrule.modelo.DBConnection;

/**
 *
 * @author ronald
 */
public class EventosHyrule {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        DBConnection connection = new DBConnection();
        connection.connect();
    }
}
