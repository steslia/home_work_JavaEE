package servlet;

import database.Accounts;
import database.User;
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

@WebServlet(name = "ReplenishAccount", urlPatterns = "/replenishAccount")
public class ReplenishAccountServlet extends HttpServlet {
    private User thisUser = ThisUser.ourInstance.getUser();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean check = false;
        String idStr = req.getParameter("id");
        String moneyStr = req.getParameter("money");

        //Проверка на непустые поля в форме
        if ( (!(idStr == null) && !(moneyStr == null)) && ( !idStr.isEmpty() ) && !moneyStr.isEmpty()){

            Long id = Long.valueOf(idStr);
            Double money = Double.valueOf(moneyStr);

            //Получаем счет по айди, айди приходит с формы
            Accounts accounts = AccountsDB.getAccount(id);
            //Устанавливаем новое значение денег, и обновляем объет в базе
            accounts.setMoney(accounts.getMoney() + money);
            check = AccountsDB.updateDatabase(accounts);
        }

        req.setAttribute("check", check);
        dispatcher(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher(req, resp);
    }

    //Метод чтобы редиректить список счетов данного клиента на страниц jsp
    private void dispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Accounts> accountsList = AccountsDB.getListAccount(thisUser);
        req.setAttribute("accountsList", accountsList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/replenishAccount.jsp");
        dispatcher.forward(req, resp);
    }
}
