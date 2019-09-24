package entity;

import parser.CurrencyRate;
import parser.JacksonParser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class AddCurrency {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
    private EntityManager em = emf.createEntityManager();
    private JacksonParser parser = new JacksonParser();

    public boolean add() {
        List<CurrencyRate> listCurrency = null;

        try {
            listCurrency = parser.parseJackson("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (listCurrency != null){


        }

        return true;
    }

    private void addDB(CurrencyRate currencyRate){
        em.getTransaction().begin();

        try {
            em.persist(currencyRate);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }

        em.close();
        emf.close();
    }
}
