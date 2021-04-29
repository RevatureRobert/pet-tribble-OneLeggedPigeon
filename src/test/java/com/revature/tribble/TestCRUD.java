package com.revature.tribble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.tribble.dao.TribbleDao;
import com.revature.tribble.model.Tribble;

public class TestCRUD {
    TribbleDao td;
    Tribble t1;

    @BeforeEach
    void setUp() {
        td = new TribbleDao();
        // create some test objects
        TribbleDao td = new TribbleDao();
        t1 = new Tribble();
        t1.setName("fred");
        t1.setColor("green");
        t1.setMass(100);
        td.insert(t1);
    }

    @Test
    void testPost() {
        td.insert(t1);
    }

    //@Test
    void testGet() {
        System.out.println(
                td.getById(1).getName() + System.lineSeparator() +
                td.getList()
        );
    }
}
