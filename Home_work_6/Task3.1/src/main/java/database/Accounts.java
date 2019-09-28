package database;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
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

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getNameCurrency() {
        return currency.getName();
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
