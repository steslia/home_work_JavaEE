package entity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login {
    private EntityManager em = Em.getInstance().getEntityManager();

    //Проверка вводимого логина
    public boolean checkActivity(String login, String password) {

        if (login.isEmpty())
            return false;

        if (password.isEmpty())
            return false;

        Query queryLogin = em.createQuery("SELECT login FROM User", String.class);
        Query queryPassword = em.createQuery("SELECT password FROM User", String.class);

        List<String> loginList = queryLogin.getResultList();
        List<String> passwordList = queryPassword.getResultList();
        Map<String, String> mapUsers = new HashMap<>();

        for (int i = 0; i < loginList.size(); i++) {
            mapUsers.put(loginList.get(i), passwordList.get(i));
        }

        for (Map.Entry entry : mapUsers.entrySet()) {
            if (entry.getKey().equals(login) && entry.getValue().equals(password))
                return true;
        }

        return false;
    }


}
