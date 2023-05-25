package model;

public class Product {
    private static int count = 0;
    protected int productId;
    protected int minPrice;
    protected User seller;
    protected boolean sold;

    public int getMinPrice() {
        return minPrice;
    }

    public User getSeller() {
        return seller;
    }

    public boolean isSold() {
        return sold;
    }

    public Product() {
        setProductId(++count);
        minPrice = 0;
        sold = false;
        seller = new User();
    }

    public Product(int minPrice, User seller) {
        setProductId(++count);
        this.minPrice = minPrice;
        sold = false;
        this.seller = seller;
    }

    public void setSold() { this.sold = true; }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
}
