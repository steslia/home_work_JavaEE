package servlet;

import database.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Register", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
    private EntityManager em = emf.createEntityManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        boolean check = checkLogin(login,em);

        if (check){
            String password = req.getParameter("password");
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String phone = req.getParameter("phone");

            User user = new User(login, password, name, surname, phone);

            addDB(user);
        }

        req.setAttribute("check",check);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
        dispatcher.forward(req, resp);
    }

    private boolean checkLogin(String login, EntityManager em){

        if (login.isEmpty())
            return false;

        boolean check = true;

        Query query = em.createQuery("SELECT login FROM User", String.class);

        List<String> loginList = query.getResultList();

        for (String str : loginList){
            if (str.equals(login))
                check = false;
        }

        return check;
    }

    private void addDB(User user){
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
