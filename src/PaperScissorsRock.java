
public class PaperScissorsRock {
	
	Player player1;
	Player player2;
	
	
	public PaperScissorsRock(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	
	public static String whoWon(String player1Move, String player2Move) {
		
		if (player1Move.equals(player2Move)) {
			return "Tie";
			
		} else {
			
			if (player1Move.equals("Rock")) {
				if (player2Move.equals("Scissors")) {
					return "Player 1";
				} else {
					return "Player 2";
				}
				
			} else if (player1Move.equals("Scissors")) {
				if (player2Move.equals("Rock")) {
					return "Player 2";
				} else {
					return "Player 1";
				}
				
			} else {
				if (player2Move.equals("Rock")) {
					return "Player 1";
				} else {
					return "Player 2";
				}
			}
		}
	}
	

	public static void main(String[] args) {

		StrategicPlayer player1 = new StrategicPlayer(0.3,0.6,0.1);
		Player player2 = new Player();
		
		int player1Wins = 0;
		
		for (int i=0; i<1000000; i++) {
			String player1Move = player1.go();
			String player2Move = player2.go();
			
			String winner = whoWon(player1Move, player2Move);
			
			if (winner.equals("Player 1")) {
				player1Wins++;
			}
		}

		System.out.println("Player 1 won " + player1Wins + " times");
	}
}
