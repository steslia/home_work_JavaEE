package servlet;

import database.CursCurrency;
import entity.CurrencyDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Currency", urlPatterns = "/currency")
public class CurrencyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrencyDB curs = new CurrencyDB();
        //Добавляем индексы валют и курс валют в БД
        boolean check = curs.add();

        //Полученный список валют редиректим на страницу jsp для вывода
        if (check) {
            List<CursCurrency> listCurs = curs.getCursCurrencyInDB();
            req.setAttribute("listCurs", listCurs);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/currency.jsp");
            dispatcher.forward(req, resp);
        }

    }
}
