package servlet;

import entity.ProductsDB;
import objectDB.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddProduct", urlPatterns = {"/addProduct"})

public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("addProduct.html"); }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int code = Integer.parseInt(req.getParameter("code"));
        String name = req.getParameter("name");
        int count = Integer.parseInt(req.getParameter("count"));
        double price = Double.parseDouble(req.getParameter("price"));

        Product product = new Product(code, name, count, price);
        ProductsDB productsDB = new ProductsDB();

        try {
            productsDB.addDB(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("addProduct.html");

    }
}
