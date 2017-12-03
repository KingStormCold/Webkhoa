package core.common.utils;

import org.hibernate.SessionFactory;

/**
 * Created by TuanKul on 11/7/2017.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.out.printf("Không tạo được!");
            throw new ExceptionInInitializerError(e);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
