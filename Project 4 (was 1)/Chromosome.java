import java.util.Random;
import java.util.ArrayList;
public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {
    private static Random rng = new Random();
    public static long dummy = 0;
    /*Used for random number generation*/
    
    public Chromosome() {}
    /*This no-arg constructor can be empty*/
    // Creates a copy while having all items not included by default.
    public Chromosome(ArrayList<Item> items, boolean allFalse) {
        for (Item i : items) {
            this.add(new Item(i));
        }
    }
    // Create chromosome for genetic algorithm
    public Chromosome(ArrayList<Item> items) {
        for (Item i : items) {
            this.add(new Item(i));
        }
        for (int i = 0; i < this.size(); i++) {
            this.get(i).setIncluded(Math.random() <= 1.0/this.size());
        }
    }
    /*Adds a copy of each of the items passed in to this Chromosome. Uses a random number to decide whether each
    item’s included field is set to true or false.*/
    public Chromosome crossover(Chromosome other) {
        Chromosome child = new Chromosome();
        for (int i = 0; i < this.size(); i++) {
            child.add(new Item(this.get(i)));
            if (rng.nextInt(10)<5) {
                child.get(i).setIncluded(this.get(i).isIncluded());
            } else {
                child.get(i).setIncluded(other.get(i).isIncluded());
            }
        }
        return child;
    }
    /*Creates and returns a new child chromosome by performing the crossover operation on this chromosome and the
    other one that is passed in (i.e. for each item, use a random number to decide which parent’s item should be copied and added to the child).*/
    public void mutate() {
        for (int i = 0; i < this.size(); i++) {
            if (rng.nextInt(10) == 1) {
                boolean flip = (this.get(i).isIncluded()) ? false : true;
                this.get(i).setIncluded(flip);
            }
        }
    }
    /*Performs the mutation operation on this chromosome (i.e. for each item in this chromosome, use a random number
    to decide whether or not to flip it’s included field from true to false or vice versa).*/
    public int getFitness() {
        dummy = 0;
        for (int i = 0; i < this.size()*1000; i++) {
            dummy += i;
        }
        int fitness = 0;
        double weight = 0;
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).isIncluded()) {
                fitness+= this.get(i).getValue();
                weight+= this.get(i).getWeight();
            }
        }
        return (weight < 10) ? fitness : 0;
    }
    /*Returns the fitness of this chromosome. If the sum of all of the included items’ weights are greater than 10,
    the fitness is zero. Otherwise, the fitness is equal to the sum of all of the included items’ values.*/
    public int compareTo(Chromosome other) {
        int compare = other.getFitness() - this.getFitness();
        return compare;
    }
    /*Returns -1 if this chromosome’s fitness is greater than the other’s fitness, +1 if this chromosome’s fitness is
    less than the other one’s, and 0 if their fitness is the same.*/	
    public String toString() {
        String result = "";
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).isIncluded()) {
                result+=this.get(i).toString()+"\n";
            }
        }
        result+="Fitness: "+this.getFitness()+"\n";
        return result;
    }
    /*Displays the name, weight and value of all items in this chromosome whose included value is true, followed by the fitness of this chromosome.*/
}
