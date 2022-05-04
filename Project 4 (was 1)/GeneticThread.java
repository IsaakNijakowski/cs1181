import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class GeneticThread extends Thread {
    private Random rng = new Random();
    private ArrayList<Chromosome> currentGen = new ArrayList<>();
    private ArrayList<Chromosome> nextGen = new ArrayList<>();
    private ArrayList<Integer> mutateSet = new ArrayList<>();
    private int populationSize;
    private int epochs;
    
    public GeneticThread(ArrayList<Item> items, int populationSize, int generationSize) {
        currentGen = initializePopulation(items, populationSize);
        this.populationSize = populationSize;
        this.epochs = generationSize;
    }

    /*Reads in a data file with the format shown below and creates and returns an ArrayList of Item objects.
    item1_label, item1_weight, item1_value
    item2_label, item2_weight, item2_value
    ...*/
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize)  {
        ArrayList<Chromosome> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(new Chromosome(items));
        }
        return population;
    };
    /*Creates and returns an ArrayList of populationSize Chromosome objects that each contain the items,
    with their included field randomly set to true or false.*/
    public void run() {
        // 1. Create a set of ten random individuals to serve as the initial population
        for (int e = 0; e < epochs; e++) {
            // 2.Add each of the individuals in the current population to the next generation
            for (Chromosome r : currentGen) {
                nextGen.add(r);
            }
            // 3.Randomly pair off the individuals and perform crossover to create a child and add it to the next generation as well
            Collections.shuffle(nextGen);
            for (int i = 0; i < currentGen.size()/2; i++) {
                nextGen.add(currentGen.get(i).crossover(currentGen.get(i+1)));
            }
            // 4.Randomly choose ten percent of the individuals in the next generation and expose them to mutation
            for (int i = 0; i < nextGen.size()/10; i++) {
                int value;
                while (true) {
                value = rng.nextInt(nextGen.size());
                if (!mutateSet.contains(value)) {
                    mutateSet.add(value);
                    break;
                }
            }
            nextGen.get(value).mutate();
            }
            // 5.Sort the individuals in the next generation according to their fitness
            Collections.sort(nextGen);
            // 6.Clear out the current population and add the top ten of the next generation back into the population
            currentGen.clear();
            mutateSet.clear();
            for (int i = 0; i < populationSize; i++) {
                currentGen.add(nextGen.get(i));
            }
            // 7.repeat steps 2 through 6 twenty times
            nextGen.clear();
        }
    }
    // 8. Sort the population and return the best
    public Chromosome getBest() {
        
        return currentGen.get(0);
    }
}
