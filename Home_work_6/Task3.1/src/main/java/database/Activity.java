package database;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sender_account")
    private Accounts sender;

    @Column(name = "Money")
    private double money;

    @ManyToOne
    @JoinColumn(name = "recipient_account")
    private Accounts recipient;

    public Activity() {
    }

    public Activity(Accounts sender, double money, Accounts recipient) {
        this.sender = sender;
        this.money = money;
        this.recipient = recipient;
    }

    public double getMoney() {
        return money;
    }

    public Accounts getRecipient() {
        return recipient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return id == activity.id &&
                Double.compare(activity.money, money) == 0 &&
                Objects.equals(sender, activity.sender) &&
                Objects.equals(recipient, activity.recipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, money, recipient);
    }
}
