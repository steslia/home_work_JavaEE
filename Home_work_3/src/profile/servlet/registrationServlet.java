package profile.servlet;

import profile.entity.Human;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Создаем сервлет, наследуяюсь от абстрактного класса и переопределения методов для нужных типов запросов
public class registrationServlet extends HttpServlet {

    //Вид html страници, что мы будем использовать для отображенния даных, при обработке запроса
    private static final String TEMPLATE = "<!DOCTYPE html><html lang = \"en\">" +
            "<head><title>Prog.kiev.ua</title></head>" +
            "<body><h1>%s</h1></body></html>";

    //Обрабатываем тип запросов Post
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        //Получаем значения из форм ввода. и преобразуем в нужный тип по необходимости
        //req это данные с запроса Post которые мы получаем(к нам приходят)
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

        //Делаем провекру ве ли поля заполнены и из-за этого отличаеться ввывод
        if (sex == null && country == null){
            String exception = "Вы не поставили галочки в обеих полях";
            //resp данные которые будут отправлены, когда мы обработаем запрос Post
            resp.getWriter().println(String.format(TEMPLATE, exception));
        } else if (sex == null){
            String exception = "Вы не указали ваш пол";
            resp.getWriter().println(String.format(TEMPLATE, exception));
        } else if (country == null){
            String exception = "Вы не указали ответ на вопрос про страну";
            resp.getWriter().println(String.format(TEMPLATE, exception));
        } else {
            //Создаем объект который принимает значения, что были введены в форме
            Human human = new Human(name,surname,age,sex,country);
            resp.getWriter().println(String.format(TEMPLATE, human));
        }
    }
}
