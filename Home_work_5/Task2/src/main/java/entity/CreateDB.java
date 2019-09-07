package entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
    private static ConnectDB db = new ConnectDB();
    private static Connection connection = db.connect();

    public void create() {

        try(Statement statement = connection.createStatement()) {
            statement.execute("DROP DATABASE IF EXISTS Store");
            statement.execute("CREATE DATABASE Store");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
