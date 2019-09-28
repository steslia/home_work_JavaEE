package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Em {
    private static Em ourInstance = new Em();
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
    private EntityManager em = emf.createEntityManager();

    public static Em getInstance() {
        return ourInstance;
    }

    private Em() {
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
