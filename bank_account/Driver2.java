/*
* BankAccount Program displays current balance, applies interest, accepts deposits, and makes withdrawals.
* Input: Name, Account ID, Balance, Deposit Amount, withdrawal Amount, and Interest Rate
* Output: Customer Info. and Current Balance
* Authors: Andrew Hu, Josiah Meyer, Brandon Robinson, Atakelti Wubneh
* Last modified: 06/20/2016
*
*/

import java.util.Scanner; //for the Scanner class
import java.text.DecimalFormat; // for the DecimalFormat class

// requires BankAccount
public class Driver2 {

   public static void main(String[] args) {   
      // Create a Scanner and DecimalFormat objects
      Scanner scan = new Scanner(System.in);
      DecimalFormat formatter = new DecimalFormat("#,###.##"); 
      
      // Initialize variables
      String customerName = "";  // to hold customer name
      String customerID = "";    // to hold customer ID
      double balance = 0;        // to hold initial balance
      double interestRate = 0;   // to hold interest rate  
      
      // greet customer
      System.out.println("\nWelcome to JAKB's bank!");
      
      // get custome name
      System.out.print("Enter your name: ");
      customerName = scan.nextLine();      
    
      do {      
         // validate customer name        
         if(customerName.length() == 0)  {
            System.out.print("Please enter a valid name: ");
            customerName = scan.nextLine();          
         }         
      } while (customerName.length() == 0);
      
      // setup customer ID
      System.out.print("Enter your customer ID: ");
      customerID = scan.nextLine();    
      
      do {      
         // validate customer ID       
         if(customerID.length() == 0) {
            System.out.print("Please enter a valid customer ID: ");
            customerID = scan.nextLine();
         }         
      } while (customerID.length() == 0);
     
      // get customer's initial balance
      do {
         System.out.print("Enter initial balance: ");    
         balance = Double.parseDouble(scan.nextLine()); 
      
         // ensure balance is valid 
         if(balance <= 0){
            System.out.println("\n\'Initial Balance\' must be greater than 0.");
         }
      } while (balance <= 0);       
      
      // get an interest rate
      do {
         System.out.print("Enter interest rate: ");
         interestRate = Double.parseDouble(scan.nextLine());
         
         // ensure interest rate is valid
         if(interestRate <= 0) {
            System.out.println("\n\'Interest Rate\' must be greater than 0.");
         }            
      } while (interestRate <= 0);      
      
      // create BankAccount object
      BankAccount2 JAKB = new BankAccount2(customerID, customerName, balance, interestRate);
            
      int loopController = 1;
      String customeChoice = "";    // to hold customer's choice 
      double depositAmount = 0.0;   // to hold deposit amount    
      double withdrawAmount = 0.0;  // to hold withdrawal amount

      // prompt user for their choice and continue until they choose to quit
       while ( loopController != 0 ) {
       
         // get customer's choice
         System.out.println("\n*** Choose one of the following to continue *** \n" 
                              + "\nEnter 'd' to make a deposit"
                              + "\nEnter 'w' to make a withdrawal" 
                              + "\nEnter 'b' to check your current balance" 
                              + "\nEnter 'i' to accumulate interest"
                              + "\nEnter 'q' to quit this session");
    	   customeChoice = scan.nextLine();  
          
    	   if(customeChoice.equalsIgnoreCase("d")) {            
            do {               
    	         // get deposit amount
               System.out.print("\nEnter deposit amount: ");
               depositAmount = Double.parseDouble(scan.nextLine());
               
               // validate deposit 
               if(depositAmount < 0) {
                  System.out.println("\'Deposit Amount\' must be greater than 0.");
               }               
            } while (depositAmount < 0);
      
    	      // make the deposit
    	      System.out.println("$" + depositAmount + " added to your balance");
    	      JAKB.makeDeposit(depositAmount); 
         }
         else if(customeChoice.equalsIgnoreCase("w")) {
                  
            // get withdrawal amount
            boolean positiveNum = false; 
                        
            do { 
               System.out.print("\nEnter withdraw amount: "); 
               withdrawAmount = Double.parseDouble(scan.nextLine());
               
               // validate withdrawal amount and check for sufficient funds
               if(withdrawAmount < 0) {
                  System.out.println("\'Withdrawal Amount\' must be greater than 0.");
                  positiveNum = false;
               } else if (withdrawAmount > JAKB.getAccountBalance()) {
                  System.out.println("\nInsufficient funds.");
               } else {             
                  // if valid withdrawal amount and customer have sufficient funds withdraw amount
                  System.out.println("$" + withdrawAmount + " withdrawn from your balance.");
                  JAKB.makeWithdraw(withdrawAmount); 
                  positiveNum = true;
               }               
            } while (positiveNum == false); 
              
         } else if(customeChoice.equalsIgnoreCase("i")) {

            // apply an interest to the balance and notify customer
            JAKB.addInterest(interestRate);
            System.out.println(interestRate + "% interest applied to your balance.");
    	   } else if(customeChoice.equalsIgnoreCase("b")) {
            
            // display customer information and balance
            System.out.println(JAKB.toString());
            
    	   } else if(customeChoice.equalsIgnoreCase("q")) {
         
            // notify customer that they are done.
            loopController = 0; 
    		   System.out.println("\nThank you for banking with us. Good bye."); 
    	   } else {
         
            // notify customer that they made an invalid choice 
    		   System.out.println("\'" + customeChoice + "\' is an invalid choice!"); 
    	   }        
      } // end of loop
      
      scan.close(); // close the scan utility 

   } // end of method main   
    
}// end of class