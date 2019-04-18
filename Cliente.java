
import java.util.List;
import java.util.ArrayList;

public class Cliente {

    private Email email;
    private String name;
    private String password;
    private Address address;
    private Birthday birthday; // Day-Mth-Yr

    public Cliente() {
        this.email = new Email();
        this.name = "";
        this.password = "";
        this.address = new Address();
        this.birthday = new Birthday();
    }
/*
    public Cliente(String email, String name, String password, String address, Birthday birthday) {
        this.email =
        this.name =
        this.password =
        this.address =
        this.birthday =
    }

    public Cliente(Cliente c) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
        this.birthday = birthday;
    }


    public String toString() {

    }
    */
}
