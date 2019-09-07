package entity;

import objectDB.Order;
import objectDB.interfaceObjectDB.ObjectDB;
import objectDB.interfaceObjectDB.OrderInterface;
import saveFromListToDB.SaveListBD;

import java.sql.*;

public class OrdersDB implements WorkDB {

    private static ConnectDB db = new ConnectDB();
    private static Connection connection = db.connect();

    @Override
    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS orders");
        statement.execute("CREATE TABLE orders (id INT AUTO_INCREMENT PRIMARY KEY, idPerson INT NOT NULL," +
                "idCode INT NOT NULL, FOREIGN KEY(idPerson) REFERENCES persons(id), count INT NOT NULL, FOREIGN KEY(idCode) REFERENCES products(code))");

    }

    @Override
    public void addDB(ObjectDB objectDB) throws SQLException {
        Order order = null;
        if (objectDB instanceof OrderInterface)
            order = (Order) objectDB;

        //Проверяем объект person
        assert order != null;

        try (PreparedStatement ps = connection.prepareStatement
                ("INSERT INTO orders(idPerson, idCode, count) VALUES(?,?,?)")) {

            ps.setInt(1, order.getIdPerson());
            ps.setInt(2, order.getIdCode());
            ps.setInt(3, order.getCount());
            ps.executeUpdate();
        }
    }

    @Override
    public void addList() throws SQLException {
        SaveListBD saveListBD = SaveListBD.getInstance();

        int idPerson = 0;
        int idCode = 0;
        int count = 0;
        String columnName;

        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders")) {

            try (ResultSet rs = ps.executeQuery()) {

                ResultSetMetaData md = rs.getMetaData();

                while (rs.next()) {

                    for (int i = 1; i <= md.getColumnCount(); i++) {

                        columnName = md.getColumnName(i);

                        if ("idPerson".equals(columnName))
                            idPerson = rs.getInt(i);

                        if ("idCode".equals(columnName))
                            idCode = rs.getInt(i);

                        if ("count".equals(columnName))
                            count = rs.getInt(i);

                    }

                    saveListBD.addListOrder(new Order(idPerson, idCode, count));

                }
            }
        }
    }

}
