import java.util.ArrayList;

public class ComboFinder {
    private ArrayList<Item> totalItemList = new ArrayList<>();
    private ArrayList<Chromosome> allCombos =  new ArrayList<>();

    public ComboFinder(ArrayList<Item> items) throws InvalidArguementException{
        totalItemList = items;
        if (totalItemList.size() >= 10) {
            throw new InvalidArguementException();
        }
    }
    // Create a recursive function to calculate all possible combinations
    // recursive function should expand all possible combination array each recursion
    private ArrayList<Item> calculateAllCombos(ArrayList<Item> itemList) {
        int itemIndex = itemList.size()-1;
        // lowest possible step
        if (itemList.size()==1) {
            allCombos.add(new Chromosome(totalItemList, false));
            allCombos.add(new Chromosome(totalItemList, false));
            allCombos.get(0).get(itemIndex).setIncluded(true);
            return itemList;
        }
        // call own function with 1 less item
        ArrayList<Item> smallerList = new ArrayList<>(itemList);
        smallerList.remove(smallerList.size()-1);
        calculateAllCombos(smallerList);
        // calculate output & return
        int temp = allCombos.size();
        for (int i = 0; i < temp; i++) {
            allCombos.add(new Chromosome(allCombos.get(i), false));
        }
        for (int i = 0; i < allCombos.size()/2; i++) {
            allCombos.get(i).get(itemIndex).setIncluded(true);
        }
        return itemList;
    }
    // create a get best function (simply sort the output from our recursive function)
    public ArrayList<Chromosome> getAllCombos() {
        allCombos.clear();
        calculateAllCombos(totalItemList);
        return allCombos;
    }
}
