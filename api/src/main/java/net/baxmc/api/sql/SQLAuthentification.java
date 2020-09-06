package net.baxmc.api.sql;

public class SQLAuthentification {

    private String adress, database, username, password;
    private int port;

    public SQLAuthentification(String adress, String database, String username, String password, int port) {
        this.adress = adress;
        this.database = database;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public String getAdress() {
        return adress;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }
}
