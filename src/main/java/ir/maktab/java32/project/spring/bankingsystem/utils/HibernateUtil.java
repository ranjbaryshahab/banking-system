package ir.maktab.java32.project.spring.bankingsystem.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static Session session;

    static {
        SessionFactory firstSessionFactory = new Configuration().configure().buildSessionFactory();
        session = firstSessionFactory.openSession();
    }

    public static Session getSession() {
        return session;
    }
}
