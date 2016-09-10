import java.util.Scanner;

/** Description 
 * 
 * @author blrobinson
 *
 */
public class BankAccount {
	private String accountHolderName;
	private String accountID;
	private double accountBalance;
	private double interestTotal = 0;
	
	public BankAccount(String name, String id) {
		accountHolderName = name;
		accountID = id;
	}
	
	/* Getters and setters */
	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public void makeDeposit() {
		System.out.println("Please enter deposit amount: ");
		Scanner sc = new Scanner(System.in);
		boolean validated = true;
		while (validated) {
			String deposit = sc.nextLine();
			if (Double.parseDouble(deposit) > 0 && deposit.matches("([0-9]+)(.)([0-9]{2})")) {
				accountBalance+=Double.parseDouble(deposit);
				validated = false;
			}
			else {
				System.out.println("Sorry, your deposit needs to be greater than 0 " +
						"and in the following format: 000.00");
			}
		}
//		System.out.print("Your account balance = $");
//		System.out.format("%.2f\n", accountBalance);
	}
	
	public void makeWithdrawal() {
		System.out.println("Please enter withdrawal amount: ");
		Scanner sc = new Scanner(System.in);
		boolean validated = true;
		while (validated) {
			String withdrawal = sc.nextLine();
			System.out.println(withdrawal);
			if (Double.parseDouble(withdrawal) > 0 && withdrawal.matches("([0-9]+)(.)([0-9]{2})")) {
				double checkForOverdraw = accountBalance-Double.parseDouble(withdrawal);
				if (checkForOverdraw >= 0) {
					accountBalance-=Double.parseDouble(withdrawal);
					validated = false;
				} else {
					System.out.println("Sorry, you do not have enough funds in your account. " +
							"Would you still like to try again? (y/n)");
					char decision = sc.nextLine().charAt(0);
					if (decision == 'n') {
						validated = false;
					} else {
						System.out.print("Please re-enter a withdrawal amount that is less than");
						System.out.format("%.2f\n", accountBalance);
					}
				}
			} else {
				System.out.println("Sorry, your withdrawal needs to be greater than 0 " +
						"and/or in the following format: 000.00\n" + 
						"Please re-enter the withdrawal amount:");
			}
		}
//		System.out.print("Your account balance = $");
//		System.out.format("%.2f\n", accountBalance);
	}

	public void addInterest() {
		System.out.println("Please enter an interest rate value: ");
		Scanner sc = new Scanner(System.in);
		double amount = 0;
		boolean validated = true;
		while (validated) {
			amount = Double.parseDouble(sc.nextLine());
			if (amount > 0) {
				validated = false;
			} else {
				System.out.println("Sorry, please re-enter a valid interest rate value without the % sign: ");
			}
		}
		amount/=100.00;
		amount+=1.00;
		interestTotal+=amount;
		accountBalance*=amount;
	}

	@Override
	public String toString() {
		return "Bank Account Holder: " + accountHolderName +
			   "Account ID Number: " + accountID +
			   "Account Balance: " + accountBalance +
			   "Interest Accured: " + interestTotal;
	}
}
