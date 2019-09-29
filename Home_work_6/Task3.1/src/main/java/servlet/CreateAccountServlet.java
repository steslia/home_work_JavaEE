package servlet;

import database.Accounts;
import database.Currency;
import database.User;
import entity.CurrencyDB;
import entity.AccountsDB;
import entity.ThisUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "createAccount", urlPatterns = "/createAccount")
public class CreateAccountServlet extends HttpServlet {
    private ThisUser thisUser = ThisUser.ourInstance;
    private CurrencyDB getCurrency = new CurrencyDB();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean check = false;
        String nameCurrency = req.getParameter("currency");
        String moneyString = req.getParameter("money");

        //Проверка на вводимые значения
        if ((!(nameCurrency == null) && !(moneyString == null)) && (!nameCurrency.isEmpty() && !moneyString.isEmpty()) ) {

            //Метод для получения объекта Currency по выбраному полю в форме
            Currency currency = getCurrency.getCurrencyInDB(nameCurrency);

            if (!(currency == null)) {
                double money = Double.parseDouble(moneyString);
                //Получае данного пользователя, что в программе
                User user = thisUser.getUser();
                //Создание счета
                Accounts accounts = new Accounts(user, currency, money);
                check = AccountsDB.addDatabase(accounts);
            }
        }

        List<Currency> currencyList = getCurrency.getCurrencyInDB();
        req.setAttribute("currencyList", currencyList);

        req.setAttribute("check", check);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/createAccount.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Currency> currencyList = getCurrency.getCurrencyInDB();
        req.setAttribute("currencyList", currencyList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/createAccount.jsp");
        dispatcher.forward(req, resp);
    }
}
