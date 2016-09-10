import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Brandon Robinson
 * Description: This class contains methods that add the words, file path name, and word locations to a Treemap. 
 * The TreeMap is initialized with a nested TreeMap called myMap. 
 */
public class DataManager {
	private TreeMap<String, TreeMap<String, ArrayList<Integer>>> myMap;
	
	
	/**
	 * Constructor.
	 */
	public DataManager() {
		myMap = new TreeMap<String, TreeMap<String, ArrayList<Integer>>>();
	}


	/**
	 * Description: This method adds the word, the text files path name, and the index of the word to the map line by line.
	 * If myMap does not contain the word that is being passed in, it will add it to the map with its text file and position.
	 * The same is for words that are already in the map. 
	 * @param String word: the word that is found in the text file. 
	 * @param String textFile: the text files pathname.
	 * @param int position: the list of locations of the words that are found in the text file.
	 */
	public void addValuestoMap(String word, String textFile, int position) {
		if (!myMap.containsKey(word)) 
			myMap.put(word, new TreeMap<String, ArrayList<Integer>>());
		if (!myMap.get(word).containsKey(textFile))
			myMap.get(word).put(textFile, new ArrayList<Integer>());
		myMap.get(word).get(textFile).add(Integer.valueOf(position));
	}
	
	
	/**
	 * Description: This method writes the map to a text file in the proper format. The first loop iterates through the keys
	 * of the map in order to print them out first. The second loop is then used to iterate through the inner map in order
	 * to print out the path file of where each word is found. The position of the word is also printed by iterating through 
	 * the inner map. 
	 * @param TreeMap<String, TreeMap<String, ArrayList>> myMap2: the map that was created by previous methods. 
	 */
	public void writeToTextFile(DataManager dm, String outputFile) {
		File invertedIndex = new File(outputFile);
		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileWriter(invertedIndex));
			for (String key: myMap.keySet()) {
				writer.println(key);
				Map<String, ArrayList<Integer>> innerMap2 = myMap.get(key);
				Set<String> innerMapKey = innerMap2.keySet();
				for (String path: innerMapKey) {
					String noBrackets = innerMap2.get(path).toString().replace('[', ' ');
					noBrackets = noBrackets.replace(']', ' ');
					writer.println('"' + path + '"' + "," + noBrackets);
				}
				writer.println();
			}
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			System.out.println("File already exists");
		}
	}
	

	/**
	 * Description: This method iterates over the inverted index and compares each key with the given query word.
	 * A tailMap is populated by keys in the inverted index that start with the query word in order to avoid searching the 
	 * entire inverted index for a match. If a given key starts with the query word, then partialSearch will create a set of 
	 * SearchResults for this query. Only one file path is given a search result. If a multi word query appears in the same file, 
	 * the frequency and position will be updated by calling a method in the SearchResult class. The frequency and position of each
	 * query word is calculated by pulling data from the inner map of the inverted index. This method returns an ArrayList of SearchResults. 
	 * @param String[] queryWords
	 * @return ArrayList<SearchResult> 
	 */
	public ArrayList<SearchResult> partialSearch(String[] queryWords) { 
		//ArrayList<SearchResult> mainList = new ArrayList<SearchResult>(); // Instantiating the main ArrayList that will contain the SearchResults
		HashMap<String, SearchResult> subMap = new HashMap<String, SearchResult>();
		for (int i = 0; i < queryWords.length; i++) { // Looping through the ArrayList of strings
			SortedMap tailMapElements = ((TreeMap) myMap).tailMap(queryWords[i]); // Using tailMap for efficiency
			for (String keys : (Set<String>) tailMapElements.keySet()) {
				if (keys.startsWith(queryWords[i])) { // If the keys in the map start with the query word
					Set<String> fileName = myMap.get(keys).keySet(); 
						for (String eachFile : fileName) { // Looping through the set of filenames 
							int frequency = myMap.get(keys).get(eachFile).size(); // Obtaining the frequency from myMap 
							int firstPosition = myMap.get(keys).get(eachFile).get(0); // Obtaining the first position of each word in myMap
							if (!subMap.containsKey(eachFile)) { // subMap does not contain the location of file 
								SearchResult search = new SearchResult(eachFile, frequency, firstPosition);
								subMap.put(eachFile, search);
							} else { // subMap contains the location of file
								SearchResult r = subMap.get(eachFile);
								r.update(frequency, firstPosition); // Passes the current frequency and position to update method
							}
						}
				} else { // The keys do not start with the query word(s) 
					break;
				}
			}
		}
		ArrayList<SearchResult> mainList = new ArrayList<SearchResult>(subMap.values());
		Collections.sort(mainList);
		return mainList; // Returning the main Arraylist that contains the SearchResults 
	}

	/**
	 * Description: This method adds the local inverted index to the main inverted index.  
	 * @param DataManager localindex
	 */
	public void merge(DataManager localIndex) {
		Set<String> localIndexKeys = localIndex.myMap.keySet(); //trying to get the keys of the localIndex
		for (String key : localIndexKeys) {
			if (!myMap.containsKey(key)) 
				myMap.put(key, localIndex.myMap.get(key)); 
			else
				myMap.get(key).putAll(localIndex.myMap.get(key));
		}
		
		
	}
}