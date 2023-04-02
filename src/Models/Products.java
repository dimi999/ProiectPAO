package Models;

public class Products {
    private static int count = 0;
    protected int productId, minPrice;
    protected Users seller;
    protected boolean sold;

    public Products() {
        setProductId(++count);
        minPrice = 0;
        sold = false;
        seller = new Users();
    }

    public Products(int minPrice, Users seller) {
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
