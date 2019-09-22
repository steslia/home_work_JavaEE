package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connect {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
    private static EntityManager em = emf.createEntityManager();

    private static Connect connect = new Connect();

    private Connect() {
    }

    public static EntityManager getEntityManager() {
        return em;
    }
}
