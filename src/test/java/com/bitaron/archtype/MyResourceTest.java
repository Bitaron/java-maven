package com.bitaron.archtype;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class MyResourceTest {

    @Test
    public void testDbConnection() {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            assertEquals(session.isConnected(),true);
        } catch (Throwable ex) {
            System.out.println("Initial SessionFactory creation failed. " + ex);
            ex.printStackTrace();
            assertFalse("Initial SessionFactory creation failed. " + ex,true);
        }
    }

}
