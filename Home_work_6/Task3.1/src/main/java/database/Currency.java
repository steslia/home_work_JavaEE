package database;

import javax.persistence.*;

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Name")
    private String name;

    public Currency() {
    }

    public Currency(String name) {
        this.name = name;
    }
}
