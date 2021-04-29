package com.revature.tribble.util;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory implements Closeable {

    public static final int MAX_CONNECTIONS = 4;
    private final Connection[] connectionPool = new Connection[MAX_CONNECTIONS];

    private static ConnectionFactory instance;

    private ConnectionFactory() {
        for(int i = 0; i< MAX_CONNECTIONS; i++){
            connectionPool[i] = createConnection();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }


    @SuppressWarnings("SameParameterValue")
    private Connection createConnection() {

        try {
            String url = "jdbc:postgresql://revature-project.cu9tcaqykiil.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=tribble";
            Properties props = new Properties();
            props.setProperty("user","OneLeggedPigeon");
            props.setProperty("password","password");
            return DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Connection[] getConnectionPool() {
        return connectionPool;
    }

    @Override
    public void close(){
        for(Connection con: connectionPool){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
