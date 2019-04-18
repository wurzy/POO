public class Ator {
    private String email; //key
    private String name;
    private String password;
    private Address address;
    private Birthday birthday; // Day-Mth-Yr

    public Ator() {
        this.email = "";
        this.name = "";
        this.password = "";
        this.address = new Address();
        this.birthday = new Birthday();
    }

    public Ator(String email, String name, String password, Address address, Birthday birthday) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
        this.birthday = birthday;
    }
}
