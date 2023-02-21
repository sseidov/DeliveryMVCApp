package app.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class Order {
    private String data;
    private BigDecimal price;
    private String customers_phonenumber;
    private String couriers_phonenumber;

    public Order(String data, String customers_phonenumber, String couriers_phonenumber) {
        this.data = data;
        this.customers_phonenumber = customers_phonenumber;
        this.couriers_phonenumber = couriers_phonenumber;
    }
}
