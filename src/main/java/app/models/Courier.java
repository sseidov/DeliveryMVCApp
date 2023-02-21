package app.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Courier {
    private String phonenumber;
    private String password;
    private String firstname;
    private String lastname;
    private String deliverytype;

    public Courier(String phonenumber, String password,
                   String firstname, String lastname, String deliverytype) {
        this.phonenumber = phonenumber;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.deliverytype = deliverytype;
    }
}
