package Models;

public class RealEstates extends Products{
    private int rooms, year;
    private double area;
    private boolean balcony, garden, parkingSlot;

    public RealEstates() {
        super();
        rooms = year = 0;
        area = 0.;
        balcony = garden = parkingSlot = false;
    }

    public RealEstates(int minPrice, Users seller, int rooms, int year, double area, boolean balcony, boolean garden, boolean parkingSlot) {
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
