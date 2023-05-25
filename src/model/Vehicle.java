package model;

public class Vehicle extends Product {

    private String brand;
    private String model;
    private int km;
    private int year;
    private double consume;
    public Vehicle() {
        super();
        brand = model = "";
        km = year = 0;
        consume = 0.;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getKm() {
        return km;
    }

    public int getYear() {
        return year;
    }

    public double getConsume() {
        return consume;
    }

    public Vehicle(int minPrice, User seller, String brand, String model, int km, int year, double consume) {
        super(minPrice, seller);
        this.brand = brand;
        this.model = model;
        this.km = km;
        this.year = year;
        this.consume = consume;
    }
}
