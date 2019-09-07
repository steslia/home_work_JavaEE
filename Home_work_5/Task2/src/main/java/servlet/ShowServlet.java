package servlet;

import entity.OrdersDB;
import entity.PersonsDB;
import entity.ProductsDB;
import objectDB.Order;
import objectDB.Person;
import objectDB.Product;
import saveFromListToDB.SaveListBD;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Show", urlPatterns = {"/show"})
public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SaveListBD saveListBD = SaveListBD.getInstance();
        saveListBD.clearListPerson();
        saveListBD.clearListProduct();
        saveListBD.clearListOrder();


        PersonsDB personsDB = new PersonsDB();
        try {
            personsDB.addList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Person> personList = saveListBD.getListPerson();
        req.setAttribute("listPerson", personList);


        ProductsDB productsDB = new ProductsDB();
        try {
            productsDB.addList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Product> productList = saveListBD.getListProduct();

        req.setAttribute("listProduct", productList);

        //Добавляем заказы
        OrdersDB ordersDB = new OrdersDB();
        try {
            ordersDB.addList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Order> orderList = saveListBD.getListOrder();
        req.setAttribute("listOrder", orderList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/show.jsp");
        dispatcher.forward(req, resp);
    }
}
