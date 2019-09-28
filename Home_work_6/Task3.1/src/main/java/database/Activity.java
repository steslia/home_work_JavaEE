package database;

import javax.persistence.*;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sender_user")
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

}
