import java.util.Objects;

public class Email {
    private String domain, user;  //user@domain

    public Email() {
        this.domain = "";
        this.user = "";
    }

    public Email(String user, String domain) {
        this.domain = domain;
        this.user = user;
    }

    public Email(Email e) {
        this.domain = e.getDomain();
        this.user = e.getUser();
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.user);
        sb.append("@");
        sb.append(this.domain);
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Email e = (Email) o;
        return (this.domain.equals(e.getDomain())&&
                this.user.equals(e.getUser()));
    }

    public Email clone() {
        return new Email(this);
    }
}
