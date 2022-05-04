import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class BruteForce {
    public static ArrayList<Item> readData(String filename)throws FileNotFoundException {
        File file = new File(filename);
        Scanner read = new Scanner(file);
        ArrayList<Item> itemsFile = new ArrayList<>();
        
        while (read.hasNextLine()) {
            String line = read.nextLine();
            String[] tokens = line.split(", ");
            String name = tokens[0];
            double weight = Double.parseDouble(tokens[1]);
            int value = Integer.parseInt(tokens[2]);
            //Splits the line of each file into tokens of seperate datatypes
            itemsFile.add(new Item(name, weight, value));
        }
        read.close();
        return itemsFile;
    }
    public static ArrayList<Item> getOptimalSet(ArrayList<Item> items) throws InvalidArguementException{
        ComboFinder calculate = new ComboFinder(items);
        ArrayList<Chromosome> combos = calculate.getAllCombos();
        Collections.sort(combos);
        Chromosome result = combos.get(0);
        return result;
    }
    public static void main(String[] args) throws FileNotFoundException, InvalidArguementException{
        String path = "items.txt";
        ArrayList<Item> itemSet = readData(path);
        System.out.println("Calculated "+(int) Math.pow(2, itemSet.size())+" different possibilites.");
        System.out.println(getOptimalSet(itemSet));
    }
}
