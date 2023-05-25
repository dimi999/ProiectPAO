package model;

public class Ticket extends Product {
    private String eventName;
    private String eventDate;
    private int boughtPrice;

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public int getBoughtPrice() {
        return boughtPrice;
    }

    public Ticket() {
        super();
        eventName = eventDate = "";
        boughtPrice = 0;
    }

    public Ticket(int minPrice, User seller, String eventName, String eventDate, int boughtPrice) {
        super(minPrice, seller);
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.boughtPrice = boughtPrice;
    }
}
