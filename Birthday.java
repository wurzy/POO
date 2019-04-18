import java.util.Objects;

public class Birthday {
    private int day, month, year;

    public Birthday() {
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    public Birthday(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Birthday(Birthday bd) {
        this.day = bd.getDay();
        this.month = bd.getMonth();
        this.year = bd.getYear();
    }

    public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.day+ "-");
        sb.append(this.month + "-");
        sb.append(this.year);
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Birthday b = (Birthday) o;
        return (this.day == b.getDay() &&
                this.month == b.getMonth() &&
                this.year == b.getYear());
    }
    public Birthday clone(){
        return new Birthday(this);
    }
}
