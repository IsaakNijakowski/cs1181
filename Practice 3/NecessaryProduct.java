public class NecessaryProduct extends Product {

    public NecessaryProduct(String name, double price) {
        super(name, price);
    }

    public double getTotalPrice() {
        return super.getPrice();
    }
}