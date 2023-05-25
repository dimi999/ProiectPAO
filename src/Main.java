import model.*;
import service.AuctionService;
import service.ProductService;
import service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static util.Audit.initAudit;
import static util.Database.createTables;
import static util.Database.dropTables;

public class Main {
    public static void main(String[] args) throws Exception {
        initAudit();
        dropTables();
        createTables();

        Set<User> users = new HashSet();
        createUsers(users);
        List<Product> products = new ArrayList();
        addProducts(products);
        Auction auction = new Auction(users, products, new ArrayList());
        insertBids(auction);

        AuctionService menu = new AuctionService(auction);
        UserService userService = new UserService();

        menu.checkPrices();
        menu.checkEstates();
        menu.checkBids();
        menu.endAuction();
        auction.getUser(1).countItems();
        menu.printBids();
        menu.testDb();
        userService.banUser(users.iterator().next());
        User dummy = new User();
        dummy = new User("dummy", "dummy@info.ro", "Mihai Bravu 29");
        userService.insertUser(dummy);
        userService.updateAddress(dummy, "Timpuri Noi 47");
        userService.printUsers();
        testProducts(users);
    }

    private static void testProducts(Set<User> users) throws SQLException {
        ProductService productService = new ProductService();
        Ticket dummy = new Ticket(55, users.iterator().next(), "Dummyevent1", "23-05-2024", 48);
        productService.insertTicket(dummy);
        productService.deleteProductsBySeller(users.iterator().next().getUserId());
        productService.deleteExpensiveTickets(60);
        productService.updateTicketDate(dummy, "26-06-2024");
        Vehicle dummy2 = new Vehicle (12000, users.iterator().next(), "BMW", "X6", 25000, 2018, 6.5);
        productService.insertVehicle(dummy2);
        productService.deleteOlderVehicles(2017);
        productService.updateVehicleConsume(dummy2, 7.2);
    }

    private static void createUsers(Set<User> users) {
        User buyer1 = new User("user2", "user2@mail.com", "Ramnicu Valcea");
        users.add(buyer1);
        users.add(new User("user3", "user3@mail.com", "Craiova"));
        User buyer2 = new User("user4", "user4@mail.com", "Vaslui");
        users.add(buyer2);
    }

    private static void addProducts(List<Product> products) {
        User seller = new User("user1", "user1@mail.com", "Bucuresti");
        RealEstate house = new RealEstate(150000, seller, 4, 2005, 65.5, true, false, false);
        products.add(house);
        products.add(new RealEstate(750000, seller, 2, 1995, 55, false, false, false));
        Vehicle vehicle = new Vehicle(6500, seller, "Skoda", "Octavia", 15000, 2009, 6.5);
        products.add(vehicle);
        products.add(new RealEstate(35000, seller, 1, 1977, 32, true, true, true));
        products.add(new RealEstate(65000, seller, 2, 1990, 63, false, false, true));
    }

    private static void insertBids(Auction auction) throws Exception {
        auction.receiveBid(100, auction.getProducts().get(0), auction.getUser(1));
        auction.receiveBid(150, auction.getProducts().get(0), auction.getUser(2));
        auction.receiveBid(100, auction.getProducts().get(1), auction.getUser(1));
        auction.receiveBid(110, auction.getProducts().get(1), auction.getUser(2));
        auction.receiveBid(130, auction.getProducts().get(1), auction.getUser(1));
    }
}