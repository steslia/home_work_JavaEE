package entity;

import database.Accounts;
import database.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class AccountsDB {

    //Метод для добавления счета в дб
    public static boolean addDatabase(Accounts accounts){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        try {
            em.persist(accounts);
            em.getTransaction().commit();
            em.close();
            emf.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean updateDatabase(Accounts accounts){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        try {
            em.merge(accounts);
            em.getTransaction().commit();
            em.close();
            emf.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean updateDatabase(Accounts senderAccount, Accounts recipientAccount){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        try {
            em.merge(senderAccount);
            em.merge(recipientAccount);
            em.getTransaction().commit();
            em.close();
            emf.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Вернет список счетов пользователя
    public static List<Accounts> getListAccount(User user){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT acc FROM Accounts acc WHERE acc.user = :user", Accounts.class);
        query.setParameter("user", user);
        List<Accounts> accountsList = query.getResultList();

        em.close();
        emf.close();
        return accountsList;
    }

    //Возвращает один счет по id
    public static Accounts getAccount(Long id){
        Accounts accounts = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT acc FROM Accounts acc WHERE acc.id = :id", Accounts.class);
        query.setParameter("id", id);
        List<Accounts> accountsList = query.getResultList();

        for (Accounts account : accountsList){
            accounts = account;
        }

        em.close();
        emf.close();
        return accounts;
    }

}
