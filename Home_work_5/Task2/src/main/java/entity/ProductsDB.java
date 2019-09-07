package entity;

import objectDB.Product;
import objectDB.interfaceObjectDB.ObjectDB;
import objectDB.interfaceObjectDB.ProductInterface;
import saveFromListToDB.SaveListBD;

import java.sql.*;

public class ProductsDB implements WorkDB{
    //Подсоеденяемся к базе данных
    private static ConnectDB db = new ConnectDB();
    private static Connection connection = db.connect();

    //Создаем таблицу products
    @Override
    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS products");
        statement.execute("CREATE TABLE products (code INT NOT NULL PRIMARY KEY, " +
                "name VARCHAR(20) NOT NULL, count INT NOT NULL, price DOUBLE NOT NULL)");
    }


    //Добавляем в базу данных
    @Override
    public void addDB(ObjectDB objectDB) throws SQLException {
        Product product = null;
        //Делаем привидение типа после с методами этого объекта делаем добавления в базу
        if (objectDB instanceof ProductInterface)
            product = (Product) objectDB;

        //Проверяем объект person
        assert product != null;

        //добавляем в таблицу
        try (PreparedStatement ps = connection.prepareStatement
                ("INSERT INTO products(code, name, count, price) VALUES(?,?,?,?)")) {

            ps.setInt(1,product.getCode());
            ps.setString(2, product.getName());
            ps.setInt(3, product.getCount());
            ps.setDouble(4, product.getPrice());
            ps.executeUpdate();
        }
    }

    //Из БД достаем данные и на их основе создаем объект после этот объект добавляем в список,
    // для дальнейших манипуляций с данными
    @Override
    public void addList() throws SQLException {
        SaveListBD saveListBD = SaveListBD.getInstance();

        int code = 0;
        String name = null;
        int count = 0;
        double price = 0;
        String columnName;

        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM products")) {

            try (ResultSet rs = ps.executeQuery()) {

                ResultSetMetaData md = rs.getMetaData();

                while (rs.next()) {

                    for (int i = 1; i <= md.getColumnCount(); i++) {

                        columnName = md.getColumnName(i);

                        if ("code".equals(columnName))
                            code = rs.getInt(i);

                        if ("name".equals(columnName))
                            name = rs.getString(i);

                        if ("count".equals(columnName))
                            count = rs.getInt(i);

                        if ("price".equals(columnName))
                            price = rs.getDouble(i);
                    }

                    //Добавляем в один список, клас которого являеться сингелтоном для хранения одного списка
                    saveListBD.addListProduct(new Product(code, name, count, price));

                }
            }
        }
    }
}
