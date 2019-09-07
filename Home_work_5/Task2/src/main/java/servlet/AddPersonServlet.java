package servlet;

import entity.PersonsDB;
import objectDB.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddPerson", urlPatterns = {"/addPerson"})
public class AddPersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("addPerson.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String phone = req.getParameter("phone");

        Person person = new Person(name, surname, phone);
        PersonsDB personsDB = new PersonsDB();

        try {
            personsDB.addDB(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("addPerson.html");

    }
}
