public class Customer implements Comparable<Customer> {
    private int customerNumber; // Contains the number in the order in which it was added from the file
    private double entryTime; // Contains the time from the file this customer will enter the store
    private int items; // Contains the amount items from the file this customer will grab
    private double browseTime; // Contains the time from the file it takes for this customer to find one itme
    private double nextEventTime; // Contains the time this customer will do their next action
    private int conditionIndex = 0; // 0 = Arrival condition, 1 = End Shopping condition, 2 = End Checkout condition, 3 = left the store
    private double startWaitTime; //Start of waiting in line, used for calculating average wait times
    private double endWaitTime; //End of waiting in line, used for calculating average wait times
    private int shoppingLineIndex; // Allows customer to remember what shopping line they were in
    private int foundInLine; // Displays the amount of people in line when entering the line

    public Customer(int customerNumber, Double entryTime, int items, Double browseTime) {
        this.customerNumber = customerNumber;
        this.entryTime = entryTime;
        this.items = items;
        this.browseTime = browseTime;
        nextEventTime = entryTime;
    }
    public int getNumber() {
        return customerNumber;
    }
    public double getArrival() {
        return entryTime;
    }
    public int getItems() {
        return items;
    }
    public double getEndShopping() {
        return items * browseTime;
    }
    public double getEndCheckoutRegular() {
        return 0.05 * items + 2.0;
    }
    public double getEndCheckoutExpress() {
        return 0.10 * items + 1.0;
    }
    public double getNextEventTime() {
        return nextEventTime;
    }
    public int getConditionIndex() {
        return conditionIndex;
    }
    public double getEndWaitTime() {
        return endWaitTime;
    }
    public double getWaitTime() {
        return endWaitTime - startWaitTime;
    }
    public int getShoppingLineIndex() {
        return shoppingLineIndex;
    }
    public int getFoundInLine() {
        return foundInLine;
    }


    public void setNextEventTime(double value) {
        nextEventTime = value;
    }
    public void setConditionIndex(int value) {
        conditionIndex = value;
    }
    public void setStartWaitTime(double value) {
        startWaitTime = value;
    }
    public void setEndWaitTime(double value) {
        endWaitTime = value;
    }
    public void setShoppingLineIndex(int value) {
        shoppingLineIndex = value;
    }
    public void setFoundInLine(int value) {
        foundInLine = value;
    }
    
    public int compareTo(Customer c) {
        if (nextEventTime - c.getNextEventTime() > 0) {
            return 1;
        } else if (nextEventTime - c.getNextEventTime() < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}