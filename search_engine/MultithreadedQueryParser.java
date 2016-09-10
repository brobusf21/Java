import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Brandon Robinson 
 * Description: This subclass is a threadsafe version of the QueryParser class. It uses custom write
 * locks to ensure that the threads are synchronized. This class also contains an inner class SearchWorker.
 */
public class MultithreadedQueryParser extends QueryParser {
	
	private final WorkQueue queue;
	private final MultiReaderLock lock;
	private int pending;
	
	public MultithreadedQueryParser(ThreadsafeDataManager dm, QueryDataManager is, WorkQueue threads) {
		super(dm, is);
		queue = threads;
		lock = new MultiReaderLock();
		pending = 0;
	}
	
	/**
	 * Description: This method uses BufferedReader and FileReader to read text line by line from a file. It then uses the custom 
	 * write lock to add the line and an empty ArrayList of search results to a LinkedHashMap in order to maintain order. It then 
	 * exectues a new thread for each line and sends it to the SearchWorker class to update the LinkedHashMap with the correct values. 
	 * 
	 * @param File file
	 */
	@Override 
	public void gatherQueries(File file) {
		try {
			BufferedReader textFile = new BufferedReader(new FileReader(file));
			String line;
			while ((line = textFile.readLine()) != null) { // This condition allows BufferedReader to read words line by line
					lock.lockWrite();
					super.addResult(line, new ArrayList<SearchResult>()); // Passed to IndexSearcher class
					lock.unlockWrite();
					queue.execute(new SearchWorker(line));
			}
			textFile.close(); // Closing the text file 	
		} catch (IOException e) {
			System.out.println("Not able to read lines from file");
		}
	}
	
	public synchronized void finish() {
		try {
			while (pending > 0) {
				this.wait();
			}
		}
		catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	public synchronized void shutdown() {
		finish();
		queue.shutdown();
	}  
         
	private synchronized void decrementPending() {
		pending--;
		if (pending <= 0) {
			this.notifyAll();
		}
	}
     
	private synchronized void incrementPending() {
		pending++;
	}
	
	/**
	 * @author Brandon Robinson 
	 * Descripton: This class receives lines of strings from the gatherQueries method and uses multiple
	 * threads to modify the strings to the correct format for the inverted index. As each line enters 
	 * this class, a queue is incremented to notify the thread pool that there is work to be done. After 
	 * the words are added to a LinkedHashMap in a different class, the pending variable is decremented. 
	 * This class also uses custom write locks.
	 */
	private class SearchWorker implements Runnable {

		private String queryLine;

		public SearchWorker(String line) {

			queryLine = line;
			incrementPending(); 
		}

		@Override
		public void run() {
			String[] queryWords = null; // Initializing String[] words outside of loop 
			if (queryLine.length() > 0) { // This condition eliminates blank lines from text file
				String actualLine = queryLine;
				queryLine = queryLine.toLowerCase().replaceAll("[^\\da-z ]", ""); // Regex used to convert each line
				queryWords = queryLine.split("\\s"); //This is splitting on white spaces 
				lock.lockWrite(); // Custom write lock
				addResult(actualLine, getResult(queryWords)); // Passed to IndexSearcher class
				lock.unlockWrite(); // Custom write unlock 
			}
			decrementPending();
		}
	}
}