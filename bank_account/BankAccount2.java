import java.util.Scanner;

/** Description 
 * 
 * @author blrobinson
 *
 */
public class BankAccount2 {
	private String accountHolderName;
	private String accountID;
	private double accountBalance;
	private double interestRate;
	
	public BankAccount2(String customerID, String customerName, double customerBalance, double customerInterest) {
		accountID = customerID;
		accountHolderName = customerName;
		accountBalance = customerBalance;
		interestRate = customerInterest;
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
	
	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public void makeDeposit(double deposit) {
		accountBalance+=deposit;
	}
	
	public void makeWithdraw(double withdraw) {
		accountBalance-=withdraw;
	}

	public void addInterest(double amount) {
		amount/=100.00;
		amount+=1.00;
		accountBalance*=amount;
	}

	@Override
	public String toString() {
		return "Bank Account Holder: " + accountHolderName + "\n" +
			   "Account ID Number: " + accountID + "\n" +
			   "Account Balance: " + accountBalance + "\n";
	}
}
