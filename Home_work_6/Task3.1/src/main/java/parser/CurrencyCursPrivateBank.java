package parser;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyCursPrivateBank {
    @JsonProperty("ccy")
    private String from;
    @JsonProperty("base_ccy")
    private String to;
    @JsonProperty("buy")
    private Double buy;
    @JsonProperty("sale")
    private Double sale;

    public CurrencyCursPrivateBank(){

    }

    public CurrencyCursPrivateBank(String from, String to, Double buy, Double sale) {
        this.from = from;
        this.to = to;
        this.buy = buy;
        this.sale = sale;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Double getBuy() {
        return buy;
    }

    public Double getSale() {
        return sale;
    }

}
