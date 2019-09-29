package entity;

import database.Activity;
import database.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

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

    //Вернет список транзакций пользователя
    public static List<Activity> getListActivity(User user){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT act FROM Activity act WHERE act.sender.user = :user", Activity.class);
        query.setParameter("user", user);
        List<Activity> activityList = query.getResultList();

        em.close();
        emf.close();
        return activityList;
    }
}
