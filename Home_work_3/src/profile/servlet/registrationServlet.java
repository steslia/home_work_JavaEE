package profile.servlet;

import profile.entity.Human;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class registrationServlet extends HttpServlet {


    static final String TEMPLATE = "<!DOCTYPE html><html lang = \"en\">" +
            "<head><title>Prog.kiev.ua</title></head>" +
            "<body><h1>%s</h1></body></html>";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        Integer age = null;
        try {
            age = Integer.parseInt(req.getParameter("age"));
        } catch (NumberFormatException e) {
            age = 0;
        }
        String sex = req.getParameter("sex");
        String country = req.getParameter("country");



        if (sex == null && country == null){
            String exception = "Вы не поставили галочки в обеих полях";
            resp.getWriter().println(String.format(TEMPLATE, exception));
        } else if (sex == null){
            String exception = "Вы не указали ваш пол";
            resp.getWriter().println(String.format(TEMPLATE, exception));
        } else if (country == null){
            String exception = "Вы не указали ответ на вопрос про страну";
            resp.getWriter().println(String.format(TEMPLATE, exception));
        } else {
            Human human = new Human(name,surname,age,sex,country);
            resp.getWriter().println(String.format(TEMPLATE, human));
        }
    }
}
