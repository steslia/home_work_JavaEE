package database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class CursCurrency implements Serializable {

    @Id
    @OneToOne
    private Currency currency;

    @Column(name = "curs")
    private Double curs;

    public CursCurrency() {
    }

    public CursCurrency(Currency currency, Double curs) {
        this.currency = currency;
        this.curs = curs;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getNameCurrency() {
        return currency.getName();
    }

    public Double getCurs() {
        return curs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursCurrency that = (CursCurrency) o;
        return Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency);
    }
}
