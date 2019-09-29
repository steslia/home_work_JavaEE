package database;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts accounts = (Accounts) o;
        return id == accounts.id &&
                Double.compare(accounts.money, money) == 0 &&
                Objects.equals(user, accounts.user) &&
                Objects.equals(currency, accounts.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, currency, money);
    }
}
