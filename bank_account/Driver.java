/* Brandon Robinson
 * CS111B / Java Fundamentals
 * 19 June 2016
 * Project 1
 */

import java.util.Scanner;

/** Description: This Driver class is used to retrieve input from user.
 * @author Brandon Robinson
 */
public class Driver {

	public static void main(String[] args) {
		
		/* Initialization of the program */
		System.out.println("Welcome to Banking Online!");
		System.out.println("Please enter your name: ");
		Scanner sc = new Scanner(System.in);
		String accountHolderName = sc.nextLine();
		System.out.println("Please enter your ID #: ");
		String accountHolderID = sc.nextLine();
		BankAccount bm = new BankAccount(accountHolderName, accountHolderID);
		System.out.println("Hello " + accountHolderName + ". " + 
				"A deposit is required when creating an account.");
		bm.makeDeposit(); // Make initial deposit
		
		/* User can enter a single character at a time to modify bank account */
		boolean ok = true;
		while (ok) {
			System.out.println("\nPlease enter one of the following commands:\n" +
					"d = Make a Deposit\n" + 
					"w = Make a withdrawl\n" +
					"b = Check balance\n" +
					"i = Add Interest\n" +
					"q = Quit");
			char option = sc.nextLine().charAt(0);
			if (option == 'd') { // Make an additional deposit
				bm.makeDeposit();
			} else if (option == 'w') { // Make a withdrawal
				bm.makeWithdrawal();
			} else if (option == 'b') {	// Check account balance
				System.out.print("Your account balance = $");
				System.out.format("%.2f\n", bm.getAccountBalance());
			} else if (option == 'i') {	// Add interest to balance
				bm.addInterest();
			} else if (option == 'q') { // Quit
				ok = false;
			}
 		} // 'q' was selected
		System.out.println("Thank you for choosing to bank online");
		System.out.println(bm.toString());
	}

}
