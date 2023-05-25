package model;

import java.util.ArrayList;
import java.util.List;

import static util.Audit.printAction;

public class User {
    private static int count = 0;
    private int userId;
    private String name;
    private String email;
    private String address;
    private List<Product> boughtProducts;

    public User() {
        setUserId(++count);
        name = email = address = "";
        boughtProducts = new ArrayList();
    }

    public User(String name, String email, String address) {
        setUserId(++count);
        this.name = name;
        this.email = email;
        this.address = address;
        boughtProducts = new ArrayList();
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void insertItem(Product product) {
        boughtProducts.add(product);
    }

    @Override
    public String toString() {
        String res = "User " + Integer.toString(userId) + " name " + name;
        return res;
    }
    public void countItems() {
        printAction("countItems");
        int ctLands = 0;
        int ctTickets = 0;
        int ctVehicles = 0;
        int ctRealEstates = 0;
        for(Product product : boughtProducts) {
            Class<? extends Product> productType = product.getClass();
            switch (productType.getName()) {
                case "model.Ticket":
                    ctTickets++;
                    break;
                case "model.Land":
                    ctLands++;
                    break;
                case "model.RealEstate":
                    ctRealEstates++;
                    break;
                case "model.Vehicle":
                    ctVehicles++;
                    break;
                default:
                    System.out.println("Unexpected type of object " + product);
            }
        }
        System.out.println("User " + this.name + " has bought: Tickets-> " + ctTickets + ", Lands-> " + ctLands + ", Real Estates-> " + ctRealEstates + ", Vehicles-> " + ctVehicles);
    }
}
