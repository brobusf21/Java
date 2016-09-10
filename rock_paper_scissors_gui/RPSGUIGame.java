/* Brandon Robinson
 * CS155B Java Fundamentals
 * Project 3
 * 15 July 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**Used to manage the GUI that is played by the user and a computer.
 * Calls the RPSGame class. 
 * @author Brandon Robinson
 */
public class RPSGUIGame extends JFrame {

	// buttons for the user to enter their move
	private JButton rockButton, paperButton, scissorsButton;

	// labels to display the number of wins/losses/ties
	private JLabel statusC, statusU, statusT;

	// images and labels to display the computer and user's moves and the outcome of a match-up
	private ImageIcon rockImage, paperImage, scissorsImage;
	private JLabel compPlay, userPlay;
	private JLabel outcome;
	
	// the game object
	private RPSGame game;

	public RPSGUIGame() {

		// initializes the window
		super("Rock, Paper, Scissors, Go!");
		setSize(400, 300);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.gray);
		setResizable(false);

		// creates the game object
		// 	NOTE: IF COMPLETING THE EXTRA CREDIT, YOU WILL NEED TO ADD A PARAMETER TO REPRESENT THE BET AMOUNT
		game = new RPSGame();


		// creates the labels for displaying the computer and user's move;
		// the images for the moves and the outcome of a match-up will be displayed
		// in a single panel
		rockImage = new ImageIcon("/Users/blrobinson/Documents/cs11b/RockPaperScissors/img/rock.jpg");
		paperImage = new ImageIcon("/Users/blrobinson/Documents/cs11b/RockPaperScissors/img/paper.jpg");
		scissorsImage = new ImageIcon("/Users/blrobinson/Documents/cs11b/RockPaperScissors/img/scissors.jpg");

		compPlay = new JLabel();
		compPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
		compPlay.setHorizontalTextPosition(SwingConstants.CENTER);
		compPlay.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
		compPlay.setForeground(Color.cyan);
		userPlay = new JLabel();
		userPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
		userPlay.setHorizontalTextPosition(SwingConstants.CENTER);
		userPlay.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
		userPlay.setForeground(Color.cyan);
		
		outcome = new JLabel();
		outcome.setHorizontalAlignment(SwingConstants.CENTER);
		outcome.setForeground(Color.white);
		
		JPanel imageOutcomePanel = new JPanel();
		imageOutcomePanel.setBackground(Color.gray);
		imageOutcomePanel.setLayout(new BorderLayout());
		imageOutcomePanel.add(userPlay, BorderLayout.WEST);
		imageOutcomePanel.add(compPlay, BorderLayout.EAST);
		imageOutcomePanel.add(outcome, BorderLayout.NORTH);
		
		// creates the labels for the status of the game (number of wins, losses, and ties);
		// the status labels will be displayed in a single panel
		statusC = new JLabel("Computer Wins: " + game.getComputerWins()); // Not showing reflected values!
		statusU = new JLabel("User Wins: " + game.getUserWins());
		statusT = new JLabel("Ties: " + game.getTies());
		statusC.setForeground(Color.white);
		statusU.setForeground(Color.white);
		statusT.setForeground(Color.white);
		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(Color.black);
		statusPanel.add(statusC);
		statusPanel.add(statusU);
		statusPanel.add(statusT);

		// the play and status panels are nested in a single panel
		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(250, 250));
		gamePanel.setBackground(Color.gray);
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(statusPanel, BorderLayout.NORTH);
		gamePanel.add(imageOutcomePanel, BorderLayout.CENTER);
		
		// creates the buttons and displays them in a control panel;
		// one listener is used for all three buttons
		rockButton = new JButton("Play Rock");
		rockButton.addActionListener(new GameListener());
		paperButton = new JButton("Play Paper");
		paperButton.addActionListener(new GameListener());
		scissorsButton = new JButton("Play Scissors");
		scissorsButton.addActionListener(new GameListener());

		JPanel controlPanel = new JPanel();
		controlPanel.add(rockButton);
		controlPanel.add(paperButton);
		controlPanel.add(scissorsButton);
		controlPanel.setBackground(Color.black);

		// the gaming and control panel are added to the window
		contentPane.add(gamePanel, BorderLayout.CENTER);
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	/* determines which button was clicked and updates the game accordingly */
	private class GameListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// Obtains user's move and computer's move
			int uMove = getUserMove(event);
			int cMove = getCompMove();
			outcome.setText(game.findWinner(uMove, cMove));
			statusC.setText("Computer Wins: " + game.getComputerWins());
			statusU.setText("User Wins: " + game.getUserWins());
			statusT.setText("Ties: " + game.getTies());
		}
		
		/**Obtains user's move by determining which button is selected in the GUI.
		 * This method also sets the images based on selection.
		 * @param event Which button is selected in the GUI.
		 * @return Rock = 1, paper = 2, scissors = 3
		 */
		private int getUserMove(ActionEvent event) {
			int move = 0;
			if (event.getSource() == rockButton) {
				System.out.println("Rock button pressed!");
				userPlay.setIcon(rockImage);
				move = 1;
			} else if (event.getSource() == paperButton) {
				System.out.println("Paper button pressed!");
				userPlay.setIcon(paperImage);
				move = 2;
			} else if (event.getSource() == scissorsButton) {
				System.out.println("Scissors button pressed!");
				userPlay.setIcon(scissorsImage);
				move = 3;
			}
			return move;
		}
		
		/**Obtains computer's move by calling a method in the RPSGame class.
		 * This method also sets the images based on selection.
		 * @return Rock = 1, paper = 2, scissors = 3
		 */
		private int getCompMove() {
			int move = 0;
			move = game.generateComputerPlay();
			if (move == 1) {
				System.out.println("Computer Rock button pressed!");
				compPlay.setIcon(rockImage);
			} else if (move == 2) {
				System.out.println("Computer Paper button pressed!");
				compPlay.setIcon(paperImage);
			} else if (move == 3) {
				System.out.println("Computer Scissors button pressed!");
				compPlay.setIcon(scissorsImage);
			}
			return move;
		}
	}
	
	public static void main(String args[]) {
		// create an object of your class
		RPSGUIGame frame = new RPSGUIGame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
