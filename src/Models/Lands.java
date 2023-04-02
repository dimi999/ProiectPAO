package Models;

public class Lands extends Products{
    private double area;
    private String location;
    private boolean urban;

    public Lands() {
        super();
        area = 0.;
        location = "";
        urban = false;
    }

    public Lands(int minPrice, Users seller, double area, String location, boolean urban) {
        super(minPrice, seller);
        this.area = area;
        this.location = location;
        this.urban = urban;
    }
}
