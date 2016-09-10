/* Brandon Robinson
 * CS155B Java Fundamentals
 * Project 3
 * 15 July 2016
 */
import java.util.Random;

/**Used to play Rock Paper Scissors between a user and a computer player.
 * @author Brandon Robinson
 */
public class RPSGame {
	private int computerWins;
	private int userWins;
	private int ties;
	private static final int ROCK = 1;
	private static final int PAPER = 2;
	private static final int SCISSORS = 3;
	
	/* Constructor */
	public RPSGame() {
	}

	/* Getters and Setters */
	public int getComputerWins() {
		return computerWins;
	}

	public void setComputerWins(int computerWins) {
		this.computerWins = computerWins;
	}

	public int getUserWins() {
		return userWins;
	}

	public void setUserWins(int userWins) {
		this.userWins = userWins;
	}

	public int getTies() {
		return ties;
	}

	public void setTies(int ties) {
		this.ties = ties;
	}
	
	/**Used to calculate a random integer or move for the computer.
	 * @return Random integer.
	 */
	public int generateComputerPlay() {
		Random r = new Random();
		return r.nextInt(3) + 1;
	}
	
	/**Used to determine a winner of the round based on the rules of 
	 * Rock Paper Scissors.
	 * @param userMove integer or move that was selected by button press.
	 * @param computerMove integer or move that was randomly generated.
	 * @return Sting that displays in the GUI.
	 */
	public String findWinner(int userMove, int computerMove) {
		String outcome = "";
		if (userMove == computerMove) {
			ties++;
			outcome+="Tie!";
			return outcome;
		}
		if (userMove == ROCK) {
			if (computerMove == PAPER) {
				computerWins++;
				outcome+="Computer Wins!";
			} else {
				userWins++;
				outcome+="User Wins!";
			}
		} else if (userMove == PAPER) {
			if (computerMove == SCISSORS) {
				computerWins++;
				outcome+="Computer Wins!";
			} else {
				userWins++;
				outcome+="User Wins!";
			}
		} else if (userMove == SCISSORS) {
			if (computerMove == ROCK) {
				computerWins++;
				outcome+="Computer Wins!";
			} else {
				userWins++;
				outcome+="User Wins!";
			}
		}
		return outcome;
	}
}
