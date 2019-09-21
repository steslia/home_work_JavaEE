package database;

import javax.persistence.*;

@Entity
public class CursCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "id_currency")
    private Currency currency;

    @Column(name = "curs")
    private Double curs;

    public CursCurrency() {
    }

    public CursCurrency(Currency currency, Double curs) {
        this.currency = currency;
        this.curs = curs;
    }
}
