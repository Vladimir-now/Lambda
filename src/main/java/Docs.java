import java.util.Calendar;

public class Docs {

    private int id;
    private Calendar date;
    private String currency;

    public Docs() {
    }

    public Docs(int id, Calendar date, String currency) {
        this.id = id;
        this.date = date;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public Calendar getDate() {
        return date;
    }

    public String getCurrency() {
        return currency;
    }
}
