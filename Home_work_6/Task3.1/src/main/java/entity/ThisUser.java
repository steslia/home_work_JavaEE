package entity;

import database.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ThisUser {
    public static ThisUser ourInstance = new ThisUser();
    private EntityManager em = Em.getInstance().getEntityManager();
    private User user;

    private ThisUser(){

    }
    public void setThisUser(String login){
        Query query = em.createQuery("FROM User user WHERE user.login = :login");
        query.setParameter("login", login);
        List<User> list = query.getResultList();
        for (User users : list){
            this.user = users;
        }
    }

    //Получаем пользователя который залогинился
    public User getUser() {
        return user;
    }
}
