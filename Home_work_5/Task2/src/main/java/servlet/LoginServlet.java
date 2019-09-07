package servlet;

import entity.OrdersDB;
import entity.PersonsDB;
import entity.ProductsDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Login", urlPatterns = {"/login"})

public class LoginServlet extends HttpServlet {
    private static final String LOGIN = "admin";
    private static final String PASS = "123";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (LOGIN.equals(login) && PASS.equals(password)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("user_login", login);

            PersonsDB personsDB = new PersonsDB();
            //При входе создаем таблицу клиентов
            try {
                personsDB.createTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ProductsDB productsDB = new ProductsDB();
            //При входе создаем таблицу товаров

            try {
                productsDB.createTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            OrdersDB ordersDB = new OrdersDB();
            try {
                ordersDB.createTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            resp.sendRedirect("adminPanel.html");
        } else {
            //Если что-то введено не верно возвращаемся на страницу входа
            resp.sendRedirect("index.html");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("param");

        HttpSession session = req.getSession(false);
        if ("exit".equals(param))
            session.removeAttribute("user_login");

        resp.sendRedirect("index.html");
    }
}
