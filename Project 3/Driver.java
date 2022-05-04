import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./TXT/arrival.txt");
        File log = new File("./log.txt");
        ArrayList<Customer> result = initialize(file);
        simulate(result, log);
    }
    public static ArrayList<Customer> initialize(File filename) throws FileNotFoundException {
        // Read text from a document and output an array of customers!
        int customerNumber = 0;
        ArrayList<Customer> list = new ArrayList<>();
        Scanner in = new Scanner(filename);
        while(in.hasNext()) {
            list.add(new Customer(customerNumber, Double.parseDouble(in.next()), Integer.parseInt(in.next()), Double.parseDouble(in.next())));
            customerNumber++;
        }
        in.close();
        return list;
    }
    public static void simulate(ArrayList<Customer> shoppers, File output) throws FileNotFoundException {
        int totalShoppingLines = 12; // Choose total shopping lines for the simulation
        int expressShoppingLines = 3; // Choose amount of total shoppping lines are express (last lanes are express)
        int shopperAmount = 0;
        PrintWriter pw = new PrintWriter(output);
        // add all shoppers enter time to priority queue
        PriorityQueue<Customer> simQ = new PriorityQueue<>();
        for (Customer c : shoppers) {
            simQ.add(c);
            shopperAmount++;
        }
        ArrayList<ShoppingLine> lines = new ArrayList<>();
        for (int i = 0; i < totalShoppingLines; i++) {
            lines.add(new ShoppingLine());
        }
        ArrayList<Double> waitTimes = new ArrayList<>();
        double simClock = 0.0;
        while (simQ.size() > 0) {
            Customer event = simQ.poll();
            simClock = event.getNextEventTime(); // advance timer to first shopper
            switch (event.getConditionIndex()) {
                case 0 :
                pw.println(round(simClock,2)+": Arrival Customer "+event.getNumber());
                    // Arrival Condition
                    // add new event for when they are done shopping (items * browseTime)
                    event.setNextEventTime(simClock+event.getEndShopping()); // Set next event to occur Clock + (items * browseTime)
                    event.setConditionIndex(1); // Set next event to be recognized as End Shopping
                    simQ.add(event); // Add next event
                break;
                case 1 :
                pw.println(round(simClock,2)+": Finished Shopping Customer "+event.getNumber());
                    // End Shopping Condition
                    // Get valid shopping line
                    if (event.getItems() <= 12) {
                        ArrayList<Integer> availableExpressLanes = new ArrayList<>();
                        // Find shortest express lane
                        int expressLaneSmallest = 999999;
                        for (int i = lines.size()-expressShoppingLines; i < lines.size(); i++) {
                            if (lines.get(i).size() <= expressLaneSmallest) {
                                if (lines.get(i).size() < expressLaneSmallest) {
                                    availableExpressLanes.removeAll(availableExpressLanes);
                                }
                                expressLaneSmallest = lines.get(i).size();
                                availableExpressLanes.add(i);
                            }
                        }
                        ArrayList<Integer> availableRegularLanes = new ArrayList<>();
                        int regularLaneSmallest = 999999;
                        // Find shortest regular lane
                        for (int i = 0; i < lines.size()-expressShoppingLines; i++) {
                            if (lines.get(i).size() <= regularLaneSmallest) {
                                if (lines.get(i).size() < regularLaneSmallest) {
                                    availableRegularLanes.clear();
                                }
                                regularLaneSmallest = lines.get(i).size();
                                availableRegularLanes.add(i);
                            }
                        }
                        // compare express and regular lanes
                        if (regularLaneSmallest < expressLaneSmallest) {
                            int random = (int) (Math.random()*availableRegularLanes.size());
                            event.setShoppingLineIndex(availableRegularLanes.get(random));
                        } else {
                            int random = (int) (Math.random()*availableExpressLanes.size());
                            event.setShoppingLineIndex(availableExpressLanes.get(random));
                        }
                        event.setFoundInLine(lines.get(event.getShoppingLineIndex()).size());
                        pw.println("12 or fewer, chose Lane "+event.getShoppingLineIndex()+" ("+lines.get(event.getShoppingLineIndex()).size()+")");
                    } else {
                        ArrayList<Integer> availableRegularLanes = new ArrayList<>();
                        int regularLaneSmallest = 999999;
                        // Find shortest regular lane
                        for (int i = 0; i < lines.size()-expressShoppingLines; i++) {
                            if (lines.get(i).size() <= regularLaneSmallest) {
                                if (lines.get(i).size() < regularLaneSmallest) {
                                    availableRegularLanes.clear();
                                }
                                regularLaneSmallest = lines.get(i).size();
                                availableRegularLanes.add(i);
                            }
                        }
                        int random = (int) (Math.random()*availableRegularLanes.size());
                        event.setShoppingLineIndex(availableRegularLanes.get(random));
                        event.setFoundInLine(lines.get(event.getShoppingLineIndex()).size());
                        pw.println("More than 12, chose Lane "+event.getShoppingLineIndex()+" ("+lines.get(event.getShoppingLineIndex()).size()+")");
                    }
                    // add shopper to a checkout line
                    // if shopper is first in line, add finish time to events
                    lines.get(event.getShoppingLineIndex()).add(event);
                    event.setStartWaitTime(simClock);
                    event.setConditionIndex(2);
                    // Check if in express lane or regular lane, then stop wait time
                    if (lines.get(event.getShoppingLineIndex()).size()==1) {
                        if (event.getShoppingLineIndex() >= lines.size() - expressShoppingLines) {
                            event.setNextEventTime(simClock+event.getEndCheckoutExpress());
                        } else {
                            event.setNextEventTime(simClock+event.getEndCheckoutRegular());
                        }
                        event.setEndWaitTime(simClock);
                        simQ.add(event);
                    }
                break;
                case 2 :
                    // End Checkout Condition
                    // if there is a person behind them in line, add their finish time to events
                    event.setConditionIndex(3);
                    lines.get(event.getShoppingLineIndex()).remove();
                    // Check if customer behind is in express lane or regular lane, then stop wait time for them
                    if (lines.get(event.getShoppingLineIndex()).size() > 0) {
                        Customer temp = lines.get(event.getShoppingLineIndex()).peek();
                        if (temp.getShoppingLineIndex() >= lines.size() - expressShoppingLines) {
                            temp.setNextEventTime(simClock+temp.getEndCheckoutExpress());
                        } else {
                            temp.setNextEventTime(simClock+temp.getEndCheckoutRegular());
                        }
                        temp.setEndWaitTime(simClock);
                        simQ.add(temp);
                    }
                    waitTimes.add(event.getWaitTime());
                    pw.println(round(simClock,2)+": Finished Checkout Customer "+event.getNumber()+" on Lane "+event.getShoppingLineIndex()+" ("+lines.get(event.getShoppingLineIndex()).size()+") ("+round(event.getWaitTime(),2)+" minute wait, "+event.getFoundInLine()+" people in line -- finished shopping at "+round(event.getArrival()+event.getEndShopping(),2)+", got to front of line at "+round(event.getEndWaitTime(),2)+")");
                break;
            }
        }
        pw.println(shopperAmount+" shoppers simulated.");
        // calculate average wait time
        double temp = 0;
        for (int i = 0; i < waitTimes.size(); i++) {
            temp += waitTimes.get(i);
        }
        pw.println("Average wait time: "+round(temp/waitTimes.size(), 3));
        // print line information
        for (int i = 0; i < lines.size(); i++) {
            pw.println("Line "+i+" had "+lines.get(i).getTotalCustomers()+" total customers with an average wait time of "+round(lines.get(i).getAverageWaitTime(),3)+" and a max size of "+lines.get(i).getMaxSize());
        }
        pw.close();
        
    }
    // Fix errors of non exact double!
    public static double round(double x, int digits) {
        BigDecimal a = new BigDecimal(x); // Did some research on BigDecimal, very cool!
        a = a.setScale(digits, RoundingMode.HALF_UP);
        return a.doubleValue();
    }
}