package app.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Orders_dishes {
    private int quantity;

    public Orders_dishes(int quantity) {
        this.quantity = quantity;
    }
}
