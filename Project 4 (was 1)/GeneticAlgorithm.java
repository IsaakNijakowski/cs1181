/* THREAD AVERAGES AFTER 10 RUNS
 * POP_SIZE = 10
 * NUM_EPOCHS = 20
 * THREADS AVERAGE FITNESS
 * 1 = 3060
 * 2 = 3160
 * 3 = 3300
 * 4 = 3340
 * THREADS AVERAGE TIME
 * 1 = 91.2 ms
 * 2 = 95.5 ms
 * 3 = 100.9 ms
 * 4 = 111.9 ms
 */

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class GeneticAlgorithm {
    public static final String FILE_PATH = "items.txt";
    public static final int POP_SIZE = 10;
    public static final int NUM_EPOCHS = 20;
    public static final int NUM_THREADS = 4;

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

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        long start = System.currentTimeMillis();
        ArrayList<Item> itemSet = new ArrayList<>(readData(FILE_PATH));
        ArrayList<GeneticThread> threadLine = new ArrayList<>();
        ArrayList<Chromosome> threadResults = new ArrayList<>();

        for (int i = 0; i < NUM_THREADS; i++) {
            threadLine.add(new GeneticThread(itemSet, POP_SIZE, NUM_EPOCHS));
            threadLine.get(i).start();
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            threadLine.get(i).join();
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            threadResults.add(threadLine.get(i).getBest());
        }
        Collections.sort(threadResults);
        System.out.printf("Ran %d generations with a population size of %d on %d threads. This was the best creature: %n", NUM_EPOCHS, POP_SIZE, NUM_THREADS);
        System.out.println(threadResults.get(0));
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");
        
    }
}
