
public class Player {

	public String go() {
		
		double random = Math.random();
		
		if (random < .33) {
			return "Paper";
		} else if (random < .66) {
			return "Scissors";
		} else {
			return "Rock";
		}
	}
}
