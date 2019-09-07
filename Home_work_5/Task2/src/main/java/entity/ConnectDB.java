package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private String url = "jdbc:mysql://localhost:3306/store?serverTimezone=UTC";
    private String user = "root";
    private String password = "admin";
    private Connection connection;


    public Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
