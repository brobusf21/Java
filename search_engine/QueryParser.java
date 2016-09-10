import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Brandon Robinson
 * Description: This class contains a method that gathers different queries from a file and sends them off in an ArrayList
 * to the DataManager class.  
 * DataManager class. 
 *
 */
public class QueryParser {
	private DataManager queryData;
	private QueryDataManager addData;
	
	/**
	 * CONSTRUCTOR
	 * @param DataManager dm
	 * @param IndexSearcher is
	 */
	public QueryParser(DataManager dm, QueryDataManager is) {
		queryData = dm;
		addData = is;
	}
	
	public ArrayList<SearchResult> getResult(String[] queries) {
		return queryData.partialSearch(queries);
	}
	
	public void addResult(String line, ArrayList<SearchResult> values) {
		addData.addResultsToMap(line, values);
	}
	
	/**
	 * Description: This method uses BufferedReader and FileReader to read text line by line from a file. After extracting
	 * the query words, it uses regular expressions to modify the words so they can by compared to the keys in the 
	 * Inverted Index map. These words are then added to an ArrayList of strings and passed over to the partialSearch method 
	 * in the DataManager class. An ArrayList of SearchResults is returned from partialSearch and then is passed to the 
	 * addValuesToMap method in IndexSearcher. 
	 * 
	 * @param File file
	 */
	public void gatherQueries(File file) {
		try {
			BufferedReader textFile = new BufferedReader(new FileReader(file));
			String[] queryWords = null; // Initializing String[] words outside of loop 
			String line;
			while ((line = textFile.readLine()) != null) { // This condition allows BufferedReader to read words line by line
				//queue.executre(new SearchWorker(queryLine, index);
				if (line.length() > 0) { // This condition eliminates blank lines from text file
					String actualLine = line;
					line = line.toLowerCase().replaceAll("[^\\da-z ]", ""); // Regex used to convert each line
					queryWords = line.split("\\s"); //This is splitting on white spaces 
					ArrayList<SearchResult> values = queryData.partialSearch(queryWords); // Passed to DataManager class 
					addData.addResultsToMap(actualLine, values); // Passed to IndexSearcher class
				}
			}
			textFile.close(); // Closing the text file 	
		} catch (IOException e) {
			System.out.println("Not able to read lines from file");
		}
	}
}