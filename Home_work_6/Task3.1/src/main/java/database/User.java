package database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
//    @NotNull
    @Column(name="id")
    private String login;

    @Column(name = "Password")
    private String password;

    @Column(name="Name")
    private String name;

    @Column(name="Surname")
    private String surname;

    @Column(name="Phone")
    private String phone;

    public User() {
    }

    public User(String login, String password, String name, String surname, String phone) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }
}
