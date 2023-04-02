package Models;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Auctions {
    private static int count = 0;
    private int auctionId;
    private List<Products> products;
    private List<Bids> bids;
    private Set<Users> users;


    public Auctions() {
        setAuctionId(++count);
        products = new ArrayList<Products>();
        bids = new ArrayList<Bids>();
        users = new HashSet<Users> ();
    }

    public Auctions(Set <Users> users, List<Products> products, List <Bids> bids) {
        this.users = users;
        this.products = products;
        this.bids = bids;
    }

    public void receiveBid(int amount, Products product, Users user) {
        Bids bid = new Bids(amount, user.getUserId(), product.getProductId());
        bids.add(bid);
    }

    public void setWinners() throws Exception {
        for(Products product : products) {
            int userId = 0, crtMax = 0;
            for(Bids bid : bids) {
                if(product.getProductId() == bid.getProductId()) {
                    if(bid.getAmount() > crtMax) {
                        crtMax = bid.getAmount();
                        userId = bid.getBidderId();
                    }
                }
            }

            if(userId != 0) {
                Users user = getUser(userId);
                product.setSold();
                user.insertItem(product);
            }
        }
    }

    public Bids bidOTD() {
        int maxi = 0;
        Bids ret = null;
        for(Bids bid : bids) {
            if(bid.getAmount() > maxi) {
                maxi = bid.getAmount();
                ret = bid;
            }
        }
        return ret;
    }

    public Users bidderOTD() {
        int maxi = 0;
        Users bidder = null;
        for(Users user : users) {
            int ct = 0;
            for(Bids bid : bids) {
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

    public Products mostExpensive() {
        Map <Integer, Integer> bidAmounts = new HashMap<Integer, Integer>();

        for(Bids bid : bids) {
            bidAmounts.merge(bid.getProductId(), bid.getAmount(), Math::max);
        }

        int maxi = 0, idx = 0;
        for(Integer it : bidAmounts.keySet()) {
            if(maxi < bidAmounts.get(it)) {
                maxi = bidAmounts.get(it);
                idx = it;
            }
        }

        for(Products product : products) {
            if(product.getProductId() == idx)
                return product;
        }
        return null;
    }

    public Products cheapest() {
        Map <Integer, Integer> bidAmounts = new HashMap<Integer, Integer>();

        for(Bids bid : bids) {
            bidAmounts.merge(bid.getProductId(), bid.getAmount(), Math::max);
        }

        int mini = 1000000, idx = 0;
        for(Integer it : bidAmounts.keySet()) {
            if(mini > bidAmounts.get(it)) {
                mini = bidAmounts.get(it);
                idx = it;
            }
        }

        for(Products product : products) {
            if(product.getProductId() == idx)
                return product;
        }
        return null;
    }

    public Users getUser(int userId) throws Exception {
        for(Users user : users) {
            if(user.getUserId() == userId)
                return user;
        }
        throw new Exception("User not registered for this auction!");
    }

    public List<RealEstates> balconyEstates() {
        List<RealEstates> ret = new ArrayList<RealEstates>();
        for(Products product : products) {
            if(product.getClass().getName().equals("Models.RealEstates")) {
                RealEstates crt = (RealEstates)product;
                if(crt.getBalcony()) {
                    ret.add(crt);
                }
            }
        }

        return ret;
    }

    public List<RealEstates> gardenEstates() {
        List<RealEstates> ret = new ArrayList<RealEstates>();
        for(Products product : products) {
            if(product.getClass().getName().equals("Models.RealEstates")) {
                RealEstates crt = (RealEstates)product;
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
