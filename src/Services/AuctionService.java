package Services;
import Models.Auctions;
import Models.RealEstates;

import java.util.List;

public class AuctionService {
    Auctions auction;
    public AuctionService() { auction = new Auctions(); }

    public AuctionService(Auctions auction) {
        this.auction = auction;
    }

    public void checkPrices() {
        System.out.println(auction.cheapest().getProductId());
        System.out.println(auction.mostExpensive().getProductId());
    }

    public void checkEstates() {
        List<RealEstates> balcony = auction.balconyEstates();
        List<RealEstates> garden = auction.gardenEstates();

        for(RealEstates it : balcony){
            System.out.println(it.getProductId());
        }

        for(RealEstates it : garden){
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
