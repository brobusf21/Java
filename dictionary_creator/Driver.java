/** 
 * Lab 3 / CS 480
 * @author Brandon Robinson
 *
 * Description: This class starts the program by retreving the command line arguments which are two text files. 
 * It then calls on two other classes that are used to parse the text files and create a HashMap to store the dictionary. 
 * To run: It is expected that the user will enter two text files as followed:
 * Enter first text file that contains the hash values.
 * Then enter the included text file to populate the dictionary.  
 */
public class Driver {
	
	public static void main(String[] args) {
		WordManager dictionary = new WordManager(); // Calling the WordManager class
		TextFileReader reader= new TextFileReader(dictionary); // Calling TextFileReader class 
		
		if (args.length == 2) { 
			reader.readPasswordTextFile(args[1]); // These are passwords in a text file
			reader.readHashTextFile(args[0]); // These are the hash values
		} else {
			System.out.println("Please enter the text file that contains the hashes in the command line first\n" + 
					"Followed by the text file that is provided to populate the dictionary\n" +
					"i.e. /Users/brandonrobinson/Documents/10kCommonPasswords.txt /Users/brandonrobinson/Documents/MD5HashTest.txt");
		}
	}
}
