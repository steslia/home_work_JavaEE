package entity;

import database.Currency;
import database.CursCurrency;
import database.NumberCurrency;
import parser.CurrencyCursPrivateBank;
import parser.JacksonParser;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;

public class CurrencyDB {
    private EntityManager em = Em.getInstance().getEntityManager();
    private JacksonParser parser = new JacksonParser();

    public boolean add() {
        List<CurrencyCursPrivateBank> listCurrencyCursPrivateBank = null;

        for (NumberCurrency number : NumberCurrency.values()){
            if (number.getString() != null) {
                String s = number.getString();
                Currency currency = new Currency(s);
                addDB(currency);
            }
        }

        try {
            listCurrencyCursPrivateBank =
                    parser.parseJackson("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (listCurrencyCursPrivateBank != null) {
            Query query = em.createQuery("FROM Currency", Currency.class);
            List<Currency> listCurrency = query.getResultList();

            for (CurrencyCursPrivateBank currencyCursPrivateBank : listCurrencyCursPrivateBank) {
                String ccy = currencyCursPrivateBank.getFrom();

                for (Currency currency : listCurrency) {
                    if (currency.getName().equals(ccy)){
                        addDB(new CursCurrency(currency, currencyCursPrivateBank.getSale()));
                    }
                }
            }
            return true;
        }
        return false;
    }

    private void addDB(CursCurrency cursCurrency) {
        em.getTransaction().begin();

        //Ставить merge, когда уже есть данные в таблице
        try {
            em.merge(cursCurrency);
//            em.persist(cursCurrency);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    private void addDB(Currency currency) {
        em.getTransaction().begin();

        try {
            em.merge(currency);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public List<CursCurrency> getCursCurrencyInDB(){
        Query query = em.createQuery("FROM CursCurrency", CursCurrency.class);
        List<CursCurrency> resultList = query.getResultList();
        return resultList;
    }

    public List<Currency> getCurrencyInDB(){
        Query query = em.createQuery("FROM Currency", Currency.class);
        List<Currency> resultList = query.getResultList();
        return resultList;
    }


    public Currency getCurrencyInDB(String name){
        Currency currency = null;
        Query query = em.createQuery("SELECT c FROM Currency c WHERE c.name = :name ", Currency.class);
        query.setParameter("name", name);
        List<Currency> resultList = query.getResultList();
        for (Currency curr : resultList){
           currency = curr;
        }
        return currency;
    }
}
