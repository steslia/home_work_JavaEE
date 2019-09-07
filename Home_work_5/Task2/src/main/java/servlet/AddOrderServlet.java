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

@WebServlet(name = "AddOrder", urlPatterns = {"/addOrder"})
public class AddOrderServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SaveListBD saveListBD = SaveListBD.getInstance();
        saveListBD.clearListPerson();
        saveListBD.clearListProduct();

        ProductsDB productsDB = new ProductsDB();
        try {
            productsDB.addList();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        List<Product> productList = saveListBD.getListProduct();

        req.setAttribute("ordersListProduct", productList);


        PersonsDB personsDB = new PersonsDB();
        try {
            personsDB.addList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Person> personList = saveListBD.getListPerson();
        req.setAttribute("ordersListPerson", personList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addOrder.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idPerson = Integer.parseInt(req.getParameter("idPerson"));
        int idCode = Integer.parseInt(req.getParameter("idCode"));
        int count = Integer.parseInt(req.getParameter("count"));

        Order order = new Order(idPerson, idCode, count);
        OrdersDB ordersDB = new OrdersDB();

        try {
            ordersDB.addDB(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("adminPanel.html");

    }
}
