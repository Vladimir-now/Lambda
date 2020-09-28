import java.util.Calendar;
import java.util.Date;

public class Companies {

    private String name;
    private String address;
    private String phoneNumber;
    private int inn;
    private int ogrn;
    private Calendar foundationDate;
    private Docs[] docs;


    public Companies() {
    }

    public Companies(String name, String address, String phoneNumber, int inn, int ogrn, Calendar foundationDate, Docs... docs) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.inn = inn;
        this.ogrn = ogrn;
        this.foundationDate = foundationDate;
        this.docs = docs;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getInn() {
        return inn;
    }

    public int getOgrn() {
        return ogrn;
    }

    public Calendar getFoundationDate() {
        return foundationDate;
    }

    public Docs[] getDocs() {
        return docs;
    }
}
