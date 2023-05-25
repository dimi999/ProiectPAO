package model;

public class RealEstate extends Product {
    private int rooms;
    private int year;
    private double area;
    private boolean balcony;
    private boolean garden;
    private  boolean parkingSlot;

    public RealEstate() {
        super();
        rooms = year = 0;
        area = 0.;
        balcony = garden = parkingSlot = false;
    }

    public RealEstate(int minPrice, User seller, int rooms, int year, double area, boolean balcony, boolean garden, boolean parkingSlot) {
        super(minPrice, seller);
        this.rooms = rooms;
        this.year = year;
        this.area = area;
        this.balcony = balcony;
        this.garden = garden;
        this.parkingSlot = parkingSlot;
    }

    public boolean getBalcony() {
        return balcony;
    }

    public boolean getGarden() {
        return garden;
    }
}
