package Models;

import static java.lang.Math.max;

public class Bids {
    private static int count = 0;
    private int bidId, amount;
    private int bidderId, productId;

    public Bids() {
        setBidId(++count);
        amount = bidderId = productId = 0;
    }

    public Bids(int amount, int bidderId, int productId) {
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
