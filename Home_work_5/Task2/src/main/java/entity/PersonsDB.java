package entity;

import objectDB.Person;
import objectDB.interfaceObjectDB.ObjectDB;
import objectDB.interfaceObjectDB.PersonInterface;
import saveFromListToDB.SaveListBD;

import java.sql.*;

public class PersonsDB implements WorkDB {
    private static ConnectDB db = new ConnectDB();
    private static Connection connection = db.connect();


    @Override
    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS persons");
        statement.execute("CREATE TABLE persons (id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(20) NOT NULL, surname VARCHAR(20) NOT NULL, phone VARCHAR(20) NOT NULL)");

    }

    @Override
    public void addDB(ObjectDB objectDB) throws SQLException {
        Person person = null;
        if (objectDB instanceof PersonInterface)
            person = (Person) objectDB;

        //Проверяем объект person
        assert person != null;

        try (PreparedStatement ps = connection.prepareStatement
                ("INSERT INTO persons(name, surname, phone) VALUES(?,?,?)")) {

            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setString(3, person.getPhone());
            ps.executeUpdate();
        }
    }

    @Override
    public void addList() throws SQLException {
        SaveListBD saveListBD = SaveListBD.getInstance();

        int id = 0;
        String name = null;
        String surname = null;
        String phone = null;
        String columnName;

        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM persons")) {
            //Получаем набор результатов базы данных указывает на -1 строку в таблице в самом начале,
            //работает как итератор после первой строки переходит ко 2
            //executeQuery выполняет запрос и возвращает ResultSet
            try (ResultSet rs = ps.executeQuery()) {

                //Возращает информацию о полученом результате и дальше мы можем узнать именна колонок, количество итд...
                ResultSetMetaData md = rs.getMetaData();

                //Указатель на строку, и из этой строки вытаскиваем данные ко всем колонкам
                while (rs.next()) {
                    //Достаем все параметры
                    for (int i = 1; i <= md.getColumnCount(); i++) {

                        columnName = md.getColumnName(i);

                        if ("id".equals(columnName))
                            id = rs.getInt(i);

                        if ("name".equals(columnName))
                            name = rs.getString(i);

                        if ("surname".equals(columnName))
                            surname = rs.getString(i);

                        if ("phone".equals(columnName))
                            phone = rs.getString(i);
                    }

                    saveListBD.addListPerson(new Person(id, name, surname, phone));

                }
            }
        }
    }

}
