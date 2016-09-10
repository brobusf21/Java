import java.io.File;

/**
 * @author Brandon Robinson
 * Description: This subclass is a thread safe version of DirectoryFileTraverser. It uses a 
 * thread pool that contained in WorkQueue. A pending variable is used to distinguish when work
 * is needed to be done by a thread. 
 *
 */
public class MultithreadedDirectoryFileTraverser extends DirectoryFileTraverser {

	private final WorkQueue queue;
	private int pending;
	private ThreadsafeDataManager index;
	
	/**
	 * Constructor.  
	 * @param is 
	 * @param Datamanager dm. 
	 */
	public MultithreadedDirectoryFileTraverser(ThreadsafeDataManager dm, QueryDataManager is, WorkQueue threads) {
		super(dm, is);
		index = dm;
		queue = threads;
		pending = 0;
	}
	
	
	/**
	 * Description: This method recursively traverses directories and sub-directories in order to find files. 
	 * Once the files are found, they are sent over to the fileParser class.
	 * A NullPointerException will be caught if a directory is found not valid.  
	 * @param String directory
	 */
	public void traverse(String directory) {
		try {
			File root = new File(directory);
			File[] list = root.listFiles();
			for (File file : list) { /* Loop traverses files */ 
				if (file.isFile() && ((file.getName().endsWith(".TXT")) || file.getName().endsWith(".txt"))) { 
					queue.execute(new FileWorker(file, index));
			    } else if (file.isDirectory()){ /* Condition: If file is a directory */
			    	traverse(file.toString());
			    }
			}
		} catch (NullPointerException e) {
			System.out.println("Sorry but the directory that was entered is not valid.");
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
	 * Description: This class implements Runnable in order to use multi threads. Each thread parses each file by 
	 * calling the FileParser class in order to create a local inverted index for efficiency. The local index is then 
	 * merged with the larger inverted index by calling the merge method. 
	 */
	private class FileWorker implements Runnable {

		private File file;
		private ThreadsafeDataManager index; // This will be the main invertedindex that contains locks 
		private DataManager localIndex; // Not threaded local index 
		
		public FileWorker(File file, ThreadsafeDataManager index) {

			this.file = file;
			this.index = index;
			localIndex = new DataManager(); // Not threaded 
			incrementPending();
		}

		@Override
		public void run() {
			FileParser parser = new FileParser(localIndex); //creates a FileParser and passes in ThreadedDataManager index
			parser.parseFile(file); // Sending each file to the parseFile method in FileParser
			index.merge(localIndex); // Merging the local index with the larger inverted index 
			decrementPending();
		}
	}
}