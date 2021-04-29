package com.revature.tribble.service;


import com.revature.tribble.util.ConnectionSession;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class SQLQueryService {
    public static ResultSet query(String statement){
        try (ConnectionSession ses = new ConnectionSession()) {
            Connection conn = ses.getActiveConnection();
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
     * returns: first value in the column, or -1 if unsuccessful.
    */
    public static int intQuery(String statement, String columnLabel){
        ResultSet rs = SQLQueryService.query(statement);
        int result = -1;
        try {
            assert rs != null;
            if(rs.next()) {
                result = rs.getInt(columnLabel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int idQuery(String username){
        return intQuery("select user_id from login where username = '" +username+"'","user_id");
    }
}
