package service;
import database.DatabaseConnection;
import model.Auction;
import model.Bid;
import model.RealEstate;

import java.sql.SQLException;
import java.util.List;

import static util.Audit.printAction;

public class AuctionService {
    private Auction auction;
    private DatabaseConnection dbconn;
    public AuctionService() throws SQLException {
        auction = new Auction();
        dbconn = DatabaseConnection.getInstance();
    }

    public AuctionService(Auction auction) throws SQLException {
        this.auction = auction;
        dbconn = DatabaseConnection.getInstance();
    }

    public void checkPrices() {
        System.out.println(auction.cheapest().getProductId());
        System.out.println(auction.mostExpensive().getProductId());
    }

    public void testDb() throws SQLException {
        Bid dummy = new Bid(25, 1, 3);
        auction.updateBidAmount(dummy, 170);
        auction.deleteSmallBids(120);
    }

    public void printBids() throws SQLException {
        auction.printBids();
    }

    public void checkEstates() {
        List<RealEstate> balcony = auction.balconyEstates();
        List<RealEstate> garden = auction.gardenEstates();

        for(RealEstate it : balcony){
            System.out.println(it.getProductId());
        }

        for(RealEstate it : garden){
            System.out.println(it.getProductId());
        }
    }

    public void checkBids() {
        System.out.println(auction.bidOTD().getAmount());
        System.out.println(auction.bidderOTD().getUserId());
    }

    public void endAuction() throws Exception {
        auction.setWinners();
    }
}
