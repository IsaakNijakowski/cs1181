/*
 * Isaak Nijakowski
 * CS1181L-06
 * Practice Problem 3
 * 2/1/2022
 */
public class ShoppingTripStartingCode {

    public static void main(String[] args) {

        Product[] products = new Product[4];

        Product p1 = new NecessaryProduct("Water", 3.25);
        Product p2 = new LuxuryProduct("Gas", 2.84);
        Product p3 = new NecessaryProduct("Medicine", 1.75);
        Product p4 = new LuxuryProduct("Snacks", 5.43);

        products[0] = p1;
        products[1] = p2;
        products[2] = p3;
        products[3] = p4;

        double total = 0;
        for (Product p : products) {
            System.out.println(p.toString());
            total += p.getTotalPrice();
        }

        System.out.printf("%nThe total cost is $%.2f", total);
    }

}
