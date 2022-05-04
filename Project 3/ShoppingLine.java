import java.util.ArrayDeque;
import java.util.ArrayList;

public class ShoppingLine extends ArrayDeque<Customer> {
    ArrayList<Double> waitTimes = new ArrayList<>();
    int totalCustomers = 0;
    int maxSize = 0;
    @Override
    public boolean add(Customer e) {
        totalCustomers++;
        if (this.size()+1>maxSize) {
            maxSize = this.size()+1;
        }
        return super.add(e);
    }
    @Override
    public Customer remove() {
        Customer temp = super.peek();
        waitTimes.add(temp.getWaitTime());
        return super.remove();
    }

    public double getAverageWaitTime() {
        double total = 0;
        for (int i = 0; i < waitTimes.size(); i++) {
            total += waitTimes.get(i);
        }
        if (waitTimes.size() == 0) {
            return 0;
        }
        return total/waitTimes.size();
    }
    public int getTotalCustomers() {
        return totalCustomers;
    }
    public int getMaxSize() {
        return maxSize;
    }
}
