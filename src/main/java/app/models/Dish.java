package app.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class Dish {
    private String name;
    private String ingredients;
    private BigDecimal price;
    private int weight;

    public Dish(String name, String ingredients, BigDecimal price, int weight) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.weight = weight;
    }

}
