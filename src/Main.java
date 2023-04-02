import Models.*;
import Services.AuctionService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        Set<Users> users = new HashSet<>();
        Users seller = new Users("user1", "user1@mail.com", "Bucuresti");
        Users buyer1 = new Users("user2", "user2@mail.com", "Ramnicu Valcea");
        users.add(buyer1);
        users.add(new Users("user3", "user3@mail.com", "Craiova"));
        Users buyer2 = new Users("user4", "user4@mail.com", "Vaslui");
        users.add(buyer2);
        List<Products> products = new ArrayList<>();
        RealEstates house = new RealEstates(150000, seller, 4, 2005, 65.5, true, false, false);
        products.add(house);
        products.add(new RealEstates(750000, seller, 2, 1995, 55, false, false, false));
        Vehicles vehicul = new Vehicles(6500, seller, "Skoda", "Octavia", 15000, 2009, 6.5);
        products.add(vehicul);
        products.add(new RealEstates(35000, seller, 1, 1977, 32, true, true, true));
        products.add(new RealEstates(65000, seller, 2, 1990, 63, false, false, true));
        List<Bids> bids = new ArrayList<>();
        Auctions auction = new Auctions(users, products, bids);
        auction.receiveBid(100, house, buyer1);
        auction.receiveBid(150, house, buyer2);
        auction.receiveBid(100, vehicul, buyer1);
        auction.receiveBid(110, vehicul, buyer2);
        auction.receiveBid(130, vehicul, buyer1);
        AuctionService menu = new AuctionService(auction);
        menu.checkPrices();
        menu.checkEstates();
        menu.checkBids();
        menu.endAuction();
        buyer1.countItems();
    }
}