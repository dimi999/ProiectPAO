package Models;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private static int count = 0;
    private int userId;
    private String name, email, address;
    private List<Products> boughtProducts;

    public Users() {
        setUserId(++count);
        name = email = address = "";
        boughtProducts = new ArrayList<Products>();
    }

    public Users(String name, String email, String address) {
        setUserId(++count);
        this.name = name;
        this.email = email;
        this.address = address;
        boughtProducts = new ArrayList<Products>();
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void insertItem(Products product) {
        boughtProducts.add(product);
    }

    public void countItems() {
        int ctLands, ctTickets, ctVehicles, ctRealEstates;
        ctLands = ctTickets = ctVehicles = ctRealEstates = 0;
        for(Products product : boughtProducts) {
            Class<? extends Products> productType = product.getClass();
            switch (productType.getName()) {
                case "Models.Tickets": ctTickets++;
                case "Models.Lands": ctLands++;
                case "Models.RealEstates": ctRealEstates++;
                case "Models.Vehicles": ctVehicles++;
            }
        }
        System.out.println("Tickets: " + ctTickets + ", Lands: " + ctLands + ", Real Estates: " + ctRealEstates + ", Vehicles: " + ctVehicles);
    }
}
