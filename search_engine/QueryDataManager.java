import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * @author Brandon Robinson
 * 
 * Description: This class contains a method that adds keys and values to the final result which is the queryMap. 
 * This map is a LinkedHashMap for efficiency that contains a String for its key, and an ArrayList of SearchResult 
 * objects for its value. The second method in this class writes the queryMap to a text file. 
 *
 */
public class QueryDataManager {
	private LinkedHashMap<String, ArrayList<SearchResult>> queryMap; //change to linked hashmap 
	
	/**
	 * CONSTRUCTOR
	 */
	public QueryDataManager() {
		queryMap = new LinkedHashMap<String, ArrayList<SearchResult>>();
	}
	
	
	/**
	 * Description: This method adds keys and values to the queryMap. 
	 * @param String string
	 * @param ArrayList<SearchResult> valuesString
	 */
	public void addResultsToMap(String string, ArrayList<SearchResult> valuesString ) {
		
		queryMap.put(string, valuesString);
	}
	
	
	/**
	 * Description: This method 
	 * @param QueryDataManager is
	 * @param String outputFile
	 */
	public void writeQueryToTextFile(QueryDataManager is, String outputFile) {
		File queryResults = new File(outputFile);
		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileWriter(queryResults));
			for (String key: queryMap.keySet()) {
				writer.println(key);
				ArrayList<SearchResult> innerValue = queryMap.get(key);
				for (SearchResult path : innerValue) {
					writer.println('"' + path.getLocation() + '"' + ", " + path.getFrequency() + ", " + path.getPosition());
				}
				writer.println();
			}
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			System.out.println("File already exists");
		}
	}	
}
