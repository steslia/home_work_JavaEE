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

    //Добавляет индексы валют в таблицу и добавляет курс валют на основе данных с приват банка
    public boolean add() {
        //Хранит список розпарсенных объектов из json приват банка
        List<CurrencyCursPrivateBank> listCurrencyCursPrivateBank = null;

        //Добавление Индесок валют, которые находяться в Enum классе
        for (NumberCurrency number : NumberCurrency.values()){
            if (number.getString() != null) {
                String s = number.getString();
                Currency currency = new Currency(s);
                addDB(currency);
            }
        }

        //Парсим json по url, получееные объекты ложим в список
        try {
            listCurrencyCursPrivateBank =
                    parser.parseJackson("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (listCurrencyCursPrivateBank != null) {
            //Получаем индексы валют с таблици
            Query query = em.createQuery("FROM Currency", Currency.class);
            List<Currency> listCurrency = query.getResultList();

            //Проходи по объектам полученным из json,
            // если их индексы валют совпадают, вносим данные  в таблицу Курс валют
            for (CurrencyCursPrivateBank currencyCursPrivateBank : listCurrencyCursPrivateBank) {
                String ccy = currencyCursPrivateBank.getFrom();

                for (Currency currency : listCurrency) {
                    //Добавлем в таблицу
                    if (currency.getName().equals(ccy)){
                        addDB(new CursCurrency(currency, currencyCursPrivateBank.getSale()));
                    }
                }
            }
            return true;
        }
        return false;
    }

    //Добавление в БД курс валют
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

    //Добавление в БД индек валют
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

    //Вернет список курса валют с бд
    public List<CursCurrency> getCursCurrencyInDB(){
        Query query = em.createQuery("FROM CursCurrency", CursCurrency.class);
        List<CursCurrency> resultList = query.getResultList();
        return resultList;
    }

    //Вернет список Валют с бд
    public List<Currency> getCurrencyInDB(){
        Query query = em.createQuery("FROM Currency", Currency.class);
        List<Currency> resultList = query.getResultList();
        return resultList;
    }

    //Вернет индекс валлюты по имени
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
