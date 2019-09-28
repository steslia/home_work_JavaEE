package entity;

import database.Activity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ActivityDB {
    //Метод для добавления счета в дб
    public static boolean addDatabase(Activity activity){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        try {
            em.persist(activity);
            em.getTransaction().commit();
            em.close();
            emf.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
