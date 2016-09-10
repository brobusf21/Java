/* Brandon Robinson
 * CS155B Java Fundamentals
 * Project 3 Extra Credit B
 * 15 July 2016
 */
import java.util.Scanner;

/**Intended for users to play Rock Paper Scissors with a computer
 * in the console shell.
 * Input:  1 - Rock
 *         2 - Paper
 *         3 - Scissors
 *        -1 - Quit
 * Output: Winner of game and the score of each player.
 * 
 * @author Brandon Robinson
 *
 */
public class Driver {

	public static void main(String[] args) {
		RPSGame game = new RPSGame();
		boolean stillPlaying = true;
		while (stillPlaying) {
			System.out.println("Please enter an int:\n" +
					" 1 (Rock)\n" +
					" 2 (Paper)\n" +
					" 3 (Scissors)\n" + 
					"-1 (Quit)");
			Scanner sc = new Scanner(System.in);
			int userMove = sc.nextInt();
			if (userMove == 1 || userMove == 2 || userMove == 3) {
				int compMove = game.generateComputerPlay();
				System.out.println("----" + game.findWinner(userMove, compMove) + "----");
				System.out.println("User Wins: " + game.getUserWins() +
						" Computer Wins: " + game.getComputerWins() + 
						" Ties: " + game.getTies());
			} else if (userMove < 0) {
				System.out.println("Goodbye");
				stillPlaying = false;
			} else {
				System.out.println("Not allowed! Please enter a selection.");
			}
		}
	}
}
