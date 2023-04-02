package Models;

public class Tickets extends Products {
    private String eventName, eventDate;
    private int boughtPrice;

    public Tickets() {
        super();
        eventName = eventDate = "";
        boughtPrice = 0;
    }

    public Tickets(int minPrice, Users seller, String eventName, String eventDate, int boughtPrice) {
        super(minPrice, seller);
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.boughtPrice = boughtPrice;
    }
}
