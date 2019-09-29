package servlet;

import database.*;
import entity.AccountsDB;
import entity.ActivityDB;
import entity.CurrencyDB;
import entity.ThisUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Activity", urlPatterns = "/activity")
public class ActivityServlet extends HttpServlet {
    private User thisUser = ThisUser.ourInstance.getUser();
    private CurrencyDB currencyDB = new CurrencyDB();
    private List<Currency> currencyList = currencyDB.getCurrencyInDB();
    private List<CursCurrency> cursCurrency = currencyDB.getCursCurrencyInDB();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean check = false;
        String strSenderAccount = req.getParameter("senderAccount");
        String strRecipientAccount = req.getParameter("recipientAccount");
        String strMoney = req.getParameter("money");

        if ((strSenderAccount != null && strRecipientAccount != null && strMoney != null)
                && (!strSenderAccount.isEmpty() && !strRecipientAccount.isEmpty() && !strMoney.isEmpty())) {

            Long idSenderAccount = Long.valueOf(strSenderAccount);
            Long idRecipientAccount = Long.valueOf(strRecipientAccount);
            double money = Double.parseDouble(strMoney);

            //получения счета по айди
            Accounts senderAccount = AccountsDB.getAccount(idSenderAccount);
            Accounts recipientAccount = AccountsDB.getAccount(idRecipientAccount);

            //получение суммы денег с счетов
            double senderMoney = senderAccount.getMoney();
            double recipientMoney = recipientAccount.getMoney();
            //Провекра чтобы с одного счета нельзя было одновреммено отправит на него же деньги
            if (senderAccount.getId() != recipientAccount.getId()) {
                //Проверка на подходящую сумму денег с счета отправителя
                if (senderMoney >= money) {
                    //вычитание денег с счета отправителя
                    senderAccount.setMoney(senderMoney - money);
                    //конвертация суммы по курсу
                    double calcMoney = calcMoney(senderAccount, recipientAccount, money);
                    //пополнение счета получателя
                    recipientAccount.setMoney(recipientMoney + calcMoney);
                    //обновления счетов получателя и отправителя в БД
                    check = AccountsDB.updateDatabase(senderAccount, recipientAccount);
                    //добавление данной транзакции в БД
                    if (check) {
                        ActivityDB.addDatabase(new Activity(senderAccount, money, recipientAccount));
                    }
                }
            }

            req.setAttribute("senderMoney", money);
        }

        req.setAttribute("check", check);

        dispatcher(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher(req, resp);
    }

    private void dispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Accounts> accountsList = AccountsDB.getListAccount(thisUser);
        req.setAttribute("accountsList", accountsList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/activity.jsp");
        dispatcher.forward(req, resp);
    }

    //Метод для привидение валюты к нужному курсу по индексу валют отправителя и получателя
    private double calcMoney(Accounts senderAccount, Accounts recipientAccount, double money) {
        double resultMoney = money;
        Currency senderCurrency = senderAccount.getCurrency();
        Currency recipientCurrency = recipientAccount.getCurrency();

        if (senderCurrency.equals(recipientCurrency)) {
            return resultMoney;
        }

        if (senderCurrency.getName().equals("UAH")) {
            for (CursCurrency cursCurrency : cursCurrency) {
                if (recipientAccount.getCurrency().equals(cursCurrency.getCurrency())) {
                    money /= cursCurrency.getCurs();
                    return money;
                }
            }
        }

        if (recipientCurrency.getName().equals("UAH")) {
            for (CursCurrency cursCurrency : cursCurrency) {
                if (senderAccount.getCurrency().equals(cursCurrency.getCurrency())) {
                    money *= cursCurrency.getCurs();
                    return money;
                }
            }
        }

        if (!senderCurrency.equals(recipientCurrency)) {
            for (CursCurrency cursCurrency : cursCurrency) {
                if (senderCurrency.equals(cursCurrency.getCurrency())) {
                    money *= cursCurrency.getCurs();
                }
            }

            for (CursCurrency cursCurrency : cursCurrency) {
                if (recipientCurrency.equals(cursCurrency.getCurrency())) {
                    money /= cursCurrency.getCurs();
                }
            }
            return money;
        }
        return money;
    }
}
