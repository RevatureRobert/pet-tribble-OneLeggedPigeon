package com.revature.tribble.service;

import java.sql.ResultSet;

public abstract class PrimaryKeyService {

    private static int newId(String query) {
        int id = -1;
        try {
            ResultSet rs = SQLQueryService.query(query);
            if (rs != null) {
                rs.next();
                //Retrieve by column name
                id = rs.getInt("max") + 1;
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }//Handle other errors

        if (id == -1) {
            throw new IndexOutOfBoundsException("Couldn't get the new Primary Key for some reason");
        }
        return id;
    }

    public static int newTribbleId(){
        return newId("SELECT MAX(id) FROM tribble");
    }

    public static int newLabId() {
        return newId("SELECT MAX(id) FROM lab");
    }
}
