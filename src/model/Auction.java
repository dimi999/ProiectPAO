package model;

import database.DatabaseConnection;

import java.sql.SQLException;
import java.util.*;

import static util.Audit.printAction;

public class Auction {
    private static int count = 0;
    private int auctionId;
    private List<Product> products;
    private List<Bid> bids;
    private Set<User> users;

    private static DatabaseConnection dbconn;

    static {
        try {
            dbconn = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Auction() {
        setAuctionId(++count);
        products = new ArrayList();
        bids = new ArrayList();
        users = new HashSet();
    }

    public Auction(Set <User> users, List<Product> products, List <Bid> bids) {
        this.users = users;
        this.products = products;
        this.bids = bids;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void printBids() throws SQLException {
        printAction("printBids");
        dbconn.getBids().stream().map(Bid::toString).forEach(System.out::println);
    }

    public void receiveBid(int amount, Product product, User user) throws SQLException {
        printAction("receiveBid");
        Bid bid = new Bid(amount, user.getUserId(), product.getProductId());
        dbconn.insertBid(bid);
        bids.add(bid);
    }

    public void updateBidAmount(Bid bid, int newAmount) throws SQLException {
        printAction("updateBidAmount");
        dbconn.updateAmount(bid.getBidId(), newAmount);
    }

    public void deleteSmallBids(int amount) throws SQLException {
        printAction("deleteSmallBids");
        dbconn.deleteSmallerAmount(amount);
    }

    public void setWinners() throws Exception {
        printAction("setWinners");
        for(Product product : products) {
            int userId = 0;
            int crtMax = 0;
            for(Bid bid : bids) {
                if(product.getProductId() == bid.getProductId() && bid.getAmount() > crtMax) {
                        crtMax = bid.getAmount();
                        userId = bid.getBidderId();
                }
            }

            if(userId != 0) {
                User user = getUser(userId);
                product.setSold();
                dbconn.updateSold(product.getProductId(), true);
                user.insertItem(product);
            }
        }
    }

    public Bid bidOTD() {
        printAction("bidOTD");
        int maxi = 0;
        Bid ret = null;
        for(Bid bid : bids) {
            if(bid.getAmount() > maxi) {
                maxi = bid.getAmount();
                ret = bid;
            }
        }
        return ret;
    }

    public User bidderOTD() {
        printAction("bidderOTD");
        int maxi = 0;
        User bidder = null;
        for(User user : users) {
            int ct = 0;
            for(Bid bid : bids) {
                if(user.getUserId() == bid.getBidderId()) {
                    ct += 1;
                }
            }

            if(ct > maxi) {
                maxi = ct;
                bidder = user;
            }
        }

        return bidder;
    }

    public Product mostExpensive() {
        printAction("mostExpensive");
        Map <Integer, Integer> bidAmounts = new HashMap<Integer, Integer>();

        for(Bid bid : bids) {
            bidAmounts.merge(bid.getProductId(), bid.getAmount(), Math::max);
        }

        int maxi = 0;
        int idx = 0;
        for(Map.Entry<Integer, Integer> it : bidAmounts.entrySet()) {
            if(maxi < it.getValue()) {
                maxi = it.getValue();
                idx = it.getKey();
            }
        }

        for(Product product : products) {
            if(product.getProductId() == idx)
                return product;
        }
        return null;
    }

    public Product cheapest() {
        printAction("cheapest");
        Map <Integer, Integer> bidAmounts = new HashMap<Integer, Integer>();

        for(Bid bid : bids) {
            bidAmounts.merge(bid.getProductId(), bid.getAmount(), Math::max);
        }

        int mini = 1000000;
        int idx = 0;
        for(Integer it : bidAmounts.keySet()) {
            if(mini > bidAmounts.get(it)) {
                mini = bidAmounts.get(it);
                idx = it;
            }
        }

        for(Product product : products) {
            if(product.getProductId() == idx)
                return product;
        }
        return null;
    }

    public User getUser(int userId) throws Exception {
        printAction("getUser");
        for(User user : users) {
            if(user.getUserId() == userId)
                return user;
        }
        throw new Exception("User not registered for this auction!");
    }

    public List<RealEstate> balconyEstates() {
        printAction("balconyEstates");
        List<RealEstate> ret = new ArrayList();
        for(Product product : products) {
            if(product.getClass().getName().equals("Models.RealEstates")) {
                RealEstate crt = (RealEstate)product;
                if(crt.getBalcony()) {
                    ret.add(crt);
                }
            }
        }

        return ret;
    }

    public List<RealEstate> gardenEstates() {
        printAction("gardenEstates");
        List<RealEstate> ret = new ArrayList();
        for(Product product : products) {
            if(product.getClass().getName().equals("Models.RealEstates")) {
                RealEstate crt = (RealEstate)product;
                if(crt.getGarden()) {
                    ret.add(crt);
                }
            }
        }

        return ret;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }
}
