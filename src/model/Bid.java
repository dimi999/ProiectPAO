package model;

public class Bid {
    private static int count = 0;
    private int bidId, amount;
    private int bidderId;
    private int productId;

    @Override
    public String toString() {
        String res = "Bid " + Integer.toString(bidId) + " amount " + Integer.toString(amount);
        return res;
    }

    public Bid() {
        setBidId(++count);
        amount = bidderId = productId = 0;
    }

    public Bid(int amount, int bidderId, int productId) {
        setBidId(++count);
        this.amount = amount;
        this.bidderId = bidderId;
        this.productId = productId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }
    public int getBidId() {
        return  bidId;
    }

    public int getProductId() { return productId; }

    public int getAmount() { return amount; }

    public int getBidderId() { return bidderId; }
}
