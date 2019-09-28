package servlet;

import database.Accounts;
import database.CursCurrency;
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

@WebServlet(name = "showAccount", urlPatterns = "/showAccount")
public class ShowAccountServlet extends HttpServlet {
    //    private boolean check = true;
    private User user = ThisUser.ourInstance.getUser();
    private CurrencyDB currency = new CurrencyDB();
    private List<CursCurrency> cursCurrency = currency.getCursCurrencyInDB();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Accounts> accountsList = AccountsDB.getListAccount(user);

        for (Accounts account : accountsList) {

            for (CursCurrency curs : cursCurrency) {

                if (account.getCurrency().equals(curs.getCurrency())) {

                    double count = account.getMoney() * curs.getCurs();
                    account.setMoney(count);
                }
            }

        }


        req.setAttribute("accountsList", accountsList);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/showAccount.jsp");
        dispatcher.forward(req, resp);
    }
}
