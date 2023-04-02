package Models;

public class Vehicles extends Products {

    public String brand, model;
    public int km, year;
    double consume;
    public Vehicles() {
        super();
        brand = model = "";
        km = year = 0;
        consume = 0.;
    }

    public Vehicles(int minPrice, Users seller, String brand, String model, int km, int year, double consume) {
        super(minPrice, seller);
        this.brand = brand;
        this.model = model;
        this.km = km;
        this.year = year;
        this.consume = consume;
    }
}
