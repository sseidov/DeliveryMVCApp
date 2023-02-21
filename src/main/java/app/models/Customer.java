package app.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Customer {
    private String phonenumber;
    private String password;
    private String firstname;
    private String lastname;
    private String email;

    public Customer(String phonenumber, String password, String firstname, String lastname, String email) {
        this.phonenumber = phonenumber;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}
