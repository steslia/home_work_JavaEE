package objectDB;

import objectDB.interfaceObjectDB.PersonInterface;

public class Person implements PersonInterface {
    private int id;
    private String name;
    private String surname;
    private String phone;

    //Конструктор для добавления объекта в БД
    public Person(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    //Для добавления объкта в список
    public Person(int id, String name, String surname, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getPhone() {
        return phone;
    }
}
