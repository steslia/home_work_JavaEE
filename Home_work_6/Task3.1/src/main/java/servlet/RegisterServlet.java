package servlet;

import database.User;
import entity.Register;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Register", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private Register register = new Register();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean check = register.checkLogin(login, password);

        if (check) {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String phone = req.getParameter("phone");

            User user = new User(login, password, name, surname, phone);

            register.addDB(user);
        }

        req.setAttribute("check", check);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
        dispatcher.forward(req, resp);
    }
}
