import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbApartments {
    private String url;
    private String user;
    private String password;
//    private Connection connection;
//
//    public Connection con (){
//        try {
//            connection = DriverManager.getConnection(url, user, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }

    public DbApartments() {
        //Получаем стрим через рефлексию

        Properties props = new Properties();

        try(InputStream is = new FileInputStream("src\\main\\resources\\db.properties")) {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = props.getProperty("db.url");
        user = props.getProperty("db.user");
        password = props.getProperty("db.password");

    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
