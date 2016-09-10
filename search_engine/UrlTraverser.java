import java.net.URL;
import java.util.HashSet;


public class UrlTraverser {
	private final WorkQueue queue;
	private int pending;
	private HashSet<String> master;
	
	/**
	 * Constructor.   
	 */
	public UrlTraverser() {
		queue = new WorkQueue();
		pending = 0;
		this.master = new HashSet<String>();
	}
	
	
	/**
	 * Description: This method recursively traverses directories and sub-directories in order to find files. 
	 * Once the files are found, they are sent over to the fileParser class.
	 * A NullPointerException will be caught if a directory is found not valid.  
	 * @param String directory
	 */
	public void traverse(HashSet<String> links) {
		//System.out.println(links);
		for (String link : links) {
			queue.execute(new LinkWorker(link));
		}
		//master.add(master2); // Trying to add to hashset 
		//queue.execute(new LinkWorker(String);
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
		System.out.println("Shutdown!");
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
	 * Description: This class implements Runnable in order to use multi threads. Each thread parses each link by 
	 * calling the LinkParser class in order to iterate through the webpages to generate the inverted index.
	 */
	private class LinkWorker implements Runnable {

		private String link;
		
		public LinkWorker(String link2) {

			this.link = link2;
			incrementPending();
		}

		@Override
		public void run() {
			HTMLCleaner clean = new HTMLCleaner();
			clean.fetchWords(link);
			decrementPending();
		}
	}
}
