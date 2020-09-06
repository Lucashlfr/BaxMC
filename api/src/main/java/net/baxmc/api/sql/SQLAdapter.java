package net.baxmc.api.sql;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLAdapter {

    private SQLAuthentification sqlAuthentification;
    private Connection connection;

    public SQLAdapter(final SQLAuthentification sqlAuthentification){
        this.sqlAuthentification = sqlAuthentification;
        connect();
    }

    public void sendQuery(String query){
        if (isConnected()) {
            try {
                getConnection().createStatement().executeUpdate(query);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public ResultSet getResult(String query){
        throw new NotImplementedException();
    }

    private boolean connect() {
        try {
            if (!isConnected()) {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://" + sqlAuthentification.getAdress() + ":" + sqlAuthentification.getPort() + "/" + sqlAuthentification.getDatabase()
                                + "?autoReconnect=true", sqlAuthentification.getUsername(), sqlAuthentification.getPassword());
                System.out.println("[SQL] Verbindung hergestellt.");
                return true;
            }
        } catch (Exception ex) {
            System.out.println("[SQL] Konnte keine Verbindung zum SQL-Server herstellen!");
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                getConnection().close();
            } catch (Exception ex) {
                System.out.println("[SQL] Konnte SQL Verbindung nicht schließen.");
            }
        }
    }

    public boolean isConnected() {
        return connection != null;
    }

    public Connection getConnection() {
        return connection;
    }
}
