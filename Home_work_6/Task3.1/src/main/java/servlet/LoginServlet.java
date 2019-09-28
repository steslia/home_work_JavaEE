package servlet;

import entity.Login;
import entity.ThisUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")

public class LoginServlet extends HttpServlet {
    private Login loginCheck = new Login();
    private ThisUser thisUser = ThisUser.ourInstance;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean checkActivity = loginCheck.checkActivity(login, password);

        if (checkActivity){
            //
            thisUser.setThisUser(login);
            HttpSession session = req.getSession(true);
            session.setAttribute("user_login", login);

            req.setAttribute("login", login);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profile.jsp");
            dispatcher.forward(req, resp);
        }

        if (!checkActivity) {
            req.setAttribute("check", checkActivity);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logOut = req.getParameter("log");
        HttpSession session = req.getSession(false);

        if ("exit".equals(logOut) && (session != null))
            session.removeAttribute("user_login");

        resp.sendRedirect("index.html");
    }

}
