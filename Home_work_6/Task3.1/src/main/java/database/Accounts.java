package database;

import javax.persistence.*;

@Entity
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_currency")
    private Currency currency;

    @Column(name = "Money")
    private double money;

    public Accounts() {
    }

    public Accounts(User user, Currency currency, double money) {
        this.user = user;
        this.currency = currency;
        this.money = money;
    }
}
