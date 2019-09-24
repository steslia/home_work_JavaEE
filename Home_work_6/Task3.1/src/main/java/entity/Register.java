package entity;

import database.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Register {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
    private EntityManager em = emf.createEntityManager();

    public boolean checkLogin(String login, String password) {

        if (login.isEmpty())
            return false;

        if (password.isEmpty())
            return false;

        boolean check = true;

        Query query = em.createQuery("SELECT login FROM User", String.class);

        List<String> loginList = query.getResultList();

        for (String str : loginList) {
            if (str.equals(login))
                check = false;
        }

        return check;
    }

    public void addDB(User user) {
        em.getTransaction().begin();

        try {
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }

        em.close();
        emf.close();
    }
}
