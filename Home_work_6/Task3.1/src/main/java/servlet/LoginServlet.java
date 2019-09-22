package servlet;

import database.Accounts;
import database.Currency;
import database.NumberCurrency;
import database.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")

public class LoginServlet extends HttpServlet {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
    private EntityManager em = emf.createEntityManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Accounts accounts;
        User user;
        Currency currency = null;
        NumberCurrency[] numberCurrency = NumberCurrency.values();

        em.getTransaction().begin();

        user = new User("admin","123","Serg", "Teslay", "+380666170444");
        User user1 = new User("test","123","Ann", "Ostapshyk", "+380999870888");

        em.persist(user);
        em.persist(user1);

        for (NumberCurrency number : numberCurrency){
            currency = new Currency(number.getString());
            em.persist(currency);
        }

        accounts = new Accounts(user, currency, 341);

        em.persist(accounts);

        em.getTransaction().commit();

        em.close();
        emf.close();

        resp.sendRedirect("index.html");
    }
}
