import java.util.ArrayList;
import java.util.Random;

class Stage extends ArrayList<Monster> {
    private Random rng = new Random();
    private int completionAmount;
    private int progress = 0;

    public Stage(int completionAmount) {
        this.completionAmount = completionAmount;
    }

    public Stage(ArrayList<Monster> list, int completionAmount) {
        super();
        for(Monster m : list) {
            this.add(new Monster(m));
            this.completionAmount = completionAmount;
        }
    }

    public Monster getNewMonster() {
        return new Monster(this.get(rng.nextInt(this.size())));
    }

    public int getCompletionAmount() {
        return completionAmount;
    }
    public void progressUp() {
        progress++;
    }
    public void progressReset() {
        progress = 0;
    }
    public int getProgress() {
        return progress;
    }
}