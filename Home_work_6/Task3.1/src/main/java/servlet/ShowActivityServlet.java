package servlet;

import database.Activity;
import database.User;
import entity.ActivityDB;
import entity.ThisUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "showActivity", urlPatterns = "/showActivity")
public class ShowActivityServlet extends HttpServlet {
    private User thisUser = ThisUser.ourInstance.getUser();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Получаем список транзакций данного пользователя и отправляем на страницу jsp
        List<Activity> activityList = ActivityDB.getListActivity(thisUser);

        req.setAttribute("activityList", activityList);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/showActivity.jsp");
        dispatcher.forward(req, resp);
    }
}
