import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        DbApartments db = new DbApartments();
        connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
        initDB();

        while (true){
            System.out.println("1: Сгенерировать БД");
            System.out.println("2: Вывести всю БД");
            System.out.println("3: Выборка по параметрам");
            System.out.print("-> ");

            String menu = scanner.nextLine();

            switch (menu){
                case "1":
                    addApartment();
                    System.out.println();
                    break;
                case "2":
                    viewApartment();
                    System.out.println();
                    break;
                case "3":
                    viewApartmentParam(scanner);
                    System.out.println();
                    break;
            }

        }

    }

    private static void initDB() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.execute("DROP TABLE IF EXISTS apartment");
            statement.execute("CREATE TABLE apartment(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, area VARCHAR (60)," +
                    " address VARCHAR (60) NOT NULL, square DOUBLE, number_rooms INT, price INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Генерируем автоматически
    private static void addApartment() throws SQLException {

        //Задаем шаблон по которому будем делать добавление
        try (PreparedStatement ps = connection.prepareStatement
                ("INSERT INTO apartment(area, address, square, number_rooms, price) VALUES(?,?,?,?,?)")) {
            for (int i = 1; i < 11; i++) {
                if (i < 6) {
                    ps.setString(1, "Kirov");
                    ps.setString(2, "Adress");
                    ps.setDouble(3, i * 10.0);
                    ps.setInt(4, i);
                    ps.setInt(5, 1000 * i);
                } else {
                    ps.setString(1, "Darnitza");
                    ps.setString(2, "New Kiew");
                    ps.setDouble(3, i * 10.0);
                    ps.setInt(4, i);
                    ps.setInt(5, 1000 * i);
                }

                //Добавления данных в таблицу уже
                ps.executeUpdate();
            }
        }

    }

    private static void viewApartmentParam(Scanner scanner) {
        System.out.println("1: Поиск по району");
        System.out.println("2: Поиск по адресу");
        System.out.print("-> ");
        String menu = scanner.nextLine();

        String value = "";
        String colum = "";

        switch (menu) {
            case "1":
                value = "area";
                System.out.println("Введите район:");
                colum = scanner.nextLine();
                break;
            case "2":
                value = "address";
                System.out.println("Введите адрес:");
                colum = scanner.nextLine();
                break;
        }

        StringBuilder url = new StringBuilder();
        url.append("SELECT * FROM apartment WHERE ").append(value).append(" = ").append('"').append(colum).append('"');
        try(PreparedStatement ps = connection.prepareStatement(String.valueOf(url))){
            System.out.println(value);
            try (ResultSet rs = ps.executeQuery()){
                //Возращает информацию о полученом результате и дальше мы можем узнать иенна колонок, количество итд...
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.printf("%-14s ", md.getColumnName(i));
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.printf("%-14s ", (String) rs.getString(i));
                    }
                    System.out.println();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void viewApartment() throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM apartment")) {
            //Получаем набор результатов базы данных указывает на -1 строку в таблице в самом начале,
            //работает как итератор после первой строки переходит ко 2
            //executeQuery выполняет запрос и возвращает ResultSet
            try (ResultSet rs = ps.executeQuery()) {

                //Возращает информацию о полученом результате и дальше мы можем узнать иенна колонок, количество итд...
                ResultSetMetaData md = rs.getMetaData();

                //Выводим названия колонок
                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.printf("%-13s ", md.getColumnName(i));
                System.out.println();

                //Указатель на строку, и из этой строки вытаскиваем данные ко всем колонкам
                while (rs.next()) {
                    //Достаем все параметры
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.printf("%-13s ", (String) rs.getString(i));
                    }
                    System.out.println();
                }
            }
        }
    }
}
