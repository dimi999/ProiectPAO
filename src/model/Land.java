package model;

public class Land extends Product {
    private double area;
    private String location;
    private boolean urban;

    public Land() {
        super();
        area = 0.;
        location = "";
        urban = false;
    }

    public Land(int minPrice, User seller, double area, String location, boolean urban) {
        super(minPrice, seller);
        this.area = area;
        this.location = location;
        this.urban = urban;
    }
}
