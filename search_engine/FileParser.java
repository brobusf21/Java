import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * @author Brandon Robinson 
 * Description: This class contains a method that reads a file line by line and parses the words into the correct format to be placed into a map.
 * The parseFile method discovers the positions of the words in each file after the white spaces, special characters, and blank lines are removed from the text file.
 * 
 *
 */
public class FileParser {
	
	private DataManager data;
	
	/**
	 * Constructor.
	 * @param DataManager dm
	 */
	public FileParser(DataManager dm) {
		data = dm;
	}

	
	/**
	 * Description: This method uses BufferedReader in order to read each file and then parses each word line by line.
	 * Using regular expressions, each line is modified to meet the standards of the assignment. After the lines are trimmed 
	 * of white spaces and the special characters are removed, each word is located and its position is recorded. The word, file path and position 
	 * are sent over to the DataManager class in order to be placed into a TreeMap.  
	 * @param File file: this file is passed in from the DirectoryFileTraverser class.
	 */
	public void parseFile(File file) {
		try {
			BufferedReader textFile = new BufferedReader(new FileReader(file));
			String[] words = null; // Initializing String[] words outside of loop
			String lines;
			int position = 0; // Initializing position in order to count the location of words
			while ((lines = textFile.readLine()) != null) { /* This condition allows BufferedReader to read words line by line */
				if (lines.length() > 0) { // This condition eliminates blank lines from text file 
					lines = lines.toLowerCase().replaceAll("[^\\da-z ]", "");  /* These conditions convert upper cases characters to lower case characters and  special characters */
					words = lines.split("\\s"); // This is splitting on white spaces
					for (String word: words) { /* This for loop iterates through the words in order to discover their positions */ 
						if (word.length() > 0) {
							position++;
							data.addValuestoMap(word, file.getAbsolutePath(), position); /* The keys and values of the map are then created by adding the word, file path, and the ArrayList to the HashMap by calling the addValuestoMap method */ 
						}
					}
				}
			}
			textFile.close(); // Closing the text file
		} catch (FileNotFoundException e) {
			System.out.println("Can not locate file that is being inputted" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Not able to read lines from file" + e.getMessage());
		}
	}
}