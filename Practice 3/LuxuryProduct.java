public class LuxuryProduct extends Product {
    
    public LuxuryProduct(String name, double price) {
        super(name, price);
    }

    public double getTotalPrice() {
        return super.getPrice() * 1.0575;
    }
}