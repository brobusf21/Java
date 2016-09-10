import java.util.ArrayList;

/**
 * @author Brandon Robinson
 * Description: This subclass is a thread safe version of DataManager that overrides the methods in the DataManager
 * class in order to use a custom multi-reader lock.  
 */
public class ThreadsafeDataManager extends DataManager{
	private final MultiReaderLock lock;
	
	
	/**
	 * Constructor.
	 */
	public ThreadsafeDataManager() {
		lock = new MultiReaderLock();
	}


	/**
	 * Description: This method adds the word, the text files path name, and the index of the word to the map line by line.
	 * If myMap does not contain the word that is being passed in, it will add it to the map with its text file and position.
	 * The same is for words that are already in the map. 
	 * @param String word: the word that is found in the text file. 
	 * @param String textFile: the text files pathname.
	 * @param int position: the list of locations of the words that are found in the text file.
	 */
	@Override
	public void addValuestoMap(String word, String textFile, int position) {
		lock.lockWrite();
		super.addValuestoMap(word, textFile, position);
		lock.unlockWrite();
	}
	
	
	@Override
	public void merge(DataManager localIndex) {
		lock.lockWrite();
		super.merge(localIndex);
		lock.unlockWrite();
	}
	
	/**
	 * Description: This method writes the map to a text file in the proper format. The first loop iterates through the keys
	 * of the map in order to print them out first. The second loop is then used to iterate through the inner map in order
	 * to print out the path file of where each word is found. The position of the word is also printed by iterating through 
	 * the inner map. 
	 * @param TreeMap<String, TreeMap<String, ArrayList>> myMap2: the map that was created by previous methods. 
	 */
	@Override
	public void writeToTextFile(DataManager dm, String outputFile) {
		lock.lockRead();
		super.writeToTextFile(dm, outputFile);
		lock.unlockRead();
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
	@Override
	public ArrayList<SearchResult> partialSearch(String[] queryWords) { 
		lock.lockRead();
		ArrayList<SearchResult> res = super.partialSearch(queryWords);
		lock.unlockRead();
		
		return res;
	}	
}
