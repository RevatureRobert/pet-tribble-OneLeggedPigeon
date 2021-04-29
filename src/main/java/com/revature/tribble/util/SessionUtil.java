package com.revature.tribble.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory() {
        Configuration config = new Configuration();
        config.configure();
        sessionFactory = config.buildSessionFactory();
        return sessionFactory;
    }
}
