import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Description: This class has two seperate methods that read both textfiles
 * line by line. 
 *
 */
public class TextFileReader {
	private WordManager data; 
	
	public TextFileReader(WordManager wm) {
		data = wm; 
	}
	
	/**
	 * Description: This method reads the text file that containts the passwords and reads
	 * them line by line. It then sends each line (password value) to the WordManager class to obtain 
	 * their hash values and add them to the HashMap. 
	 * @param password
	 */
	public void readPasswordTextFile(String password) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(password));
			String line = null; 
			while ((line = reader.readLine()) != null) {
				data.getHashes(line);	
			}
			reader.close(); // Closing the text file
		} catch (IOException e) {
			System.out.println("Can not locate file that is being inputted" + e.getMessage());
		}
	}
	
	/**
	 * Description: This method reads the text file that containts the hash values and reads
	 * them line by line. It then sends each line (hash value) to the WordManager class to add 
	 * to the HashMap. 
	 * @param hashFile
	 */
	public void readHashTextFile(String hashFile) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(hashFile));
			String hash = null; 
			while ((hash = reader.readLine()) != null) {
				data.getPassword(hash);	
			}
			reader.close(); // Closing the text file
		} catch (IOException e) {
			System.out.println("Can not locate file that is being inputted" + e.getMessage());
		}
	}
}
