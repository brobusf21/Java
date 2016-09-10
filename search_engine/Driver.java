import java.io.File;

/**
 * @author Brandon Robinson
 * ID# 20263268
 * CS212
 * Project 4
 * Description: This class starts the Inverted Index project. 
 */
public class Driver {

	/**
	 * In this class, we traverse the directory specified as a vlaue for -d flag and parse each text file in the directory, 
	 * in order to populate the inverted index with words. If -q is provided, the program will read the query file and perform
	 * the partial search for each query line from the query file. If -t is provided, the program will be ran with multiple threads.
	 * The user has the option of entering the number of threads with the -t value. If the number of threads is not specified, 
	 * it will run with its default value of 5. This driver also looks for -u flag to determine if it needs to crawl a webpage. 
	 * 
	 * @param String[] args
	 */
	public static void main(String[] args) {
		ArgumentParser dir = new ArgumentParser(args);
		ThreadsafeDataManager dm = new ThreadsafeDataManager();
		QueryDataManager is = new QueryDataManager();
		WorkQueue workers;
		
		int threadNumber = 1;
		String inputDirectory = dir.getValue("-d");
		if (dir.hasValue("-t")) {
			try {
				threadNumber = Integer.parseInt(dir.getValue("-t")); // Validating that the value is an integer
			} catch (NumberFormatException e) {
				System.out.println("Sorry but the the value of -t needs to be an integer" + e.getMessage());
				return;
			}
			if (threadNumber < 1) { // Validating that the number of threads entered is greater than or equal to 1
				System.out.println("Sorry, but the number of threads must be greater than or equal to 1");
				return;
			}
		} else {				
			if (dir.hasFlag("-t")) {	
				threadNumber = 5;	// Default number of threads if -t is provided with no value	
			}
		}
		
		if (threadNumber > 1) { // Run the multithreaded version 
			workers = new WorkQueue(threadNumber); 
			if (dir.hasFlag("-d") && (dir.hasValue("-d"))) {
				MultithreadedDirectoryFileTraverser traverser = new MultithreadedDirectoryFileTraverser(dm, is, workers);
				traverser.traverse(inputDirectory);
				traverser.finish();
				if (dir.hasFlag("-i")) {
						if (dir.hasValue("-i")) {
							dm.writeToTextFile(dm, dir.getValue("-i"));
						} else {
							dm.writeToTextFile(dm, "invertedindex.txt");
						}
				}
				// So this should call the URLTraverser (web crawler) class 
				// From there, it should call the HTML fetcher class 
			} else if (dir.hasFlag("-u") && (dir.hasValue("-u"))) { // -u flag is available that means we are going to do a web crawl 
				String seedUrl = dir.getValue("-u");
				if (seedUrl.endsWith(".html")) {
					System.out.println(seedUrl);
					//HTMLFetcher hf = new HTMLFetcher(seedUrl);
				} else {
					System.out.println("Sorry but the webpage needs to be an html page");
				}
			} else {
				System.out.println("Sorry, but a directory must be provided in the command line.");
				return;
			}
			
			if (dir.hasFlag("-q") && (dir.hasValue("-q"))) {
				MultithreadedQueryParser queries = new MultithreadedQueryParser(dm, is, workers); 
				String inputQueries = dir.getValue("-q");
				File file = new File(inputQueries);
				queries.gatherQueries(file);
				queries.finish();
				queries.shutdown();
				
					if (dir.hasFlag("-r")) {
						if (dir.hasValue("-r")) {
							is.writeQueryToTextFile(is, dir.getValue("-r"));
						} else 
							is.writeQueryToTextFile(is, "searchresults.txt");
					}
				}
		
		} else { // Run the single threaded version 
			QueryDataManager qm = new QueryDataManager();
			DataManager db = new DataManager();
			QueryParser qp = new QueryParser(db, qm);
			
			if (dir.hasFlag("-d") && (dir.hasValue("-d"))) {
				inputDirectory = dir.getValue("-d");
				DirectoryFileTraverser traverser = new DirectoryFileTraverser(db, qm);
				traverser.traverse(inputDirectory);
				if (dir.hasFlag("-i")) {
						if (dir.hasValue("-i")) {
							db.writeToTextFile(db, dir.getValue("-i"));
						} else {
							db.writeToTextFile(db, "invertedindex.txt");
						}
				}
			} else {
				System.out.println("Sorry, but a directory must be provided in the command line.");
				return;
			}
			
			if (dir.hasFlag("-q") && (dir.hasValue("-q"))) {
					String inputQueries = dir.getValue("-q");
					File file = new File(inputQueries);
					qp.gatherQueries(file);
					if (dir.hasFlag("-r")) {
						if (dir.hasValue("-r")) {
							qm.writeQueryToTextFile(qm, dir.getValue("-r"));
						} else {
							qm.writeQueryToTextFile(qm, "searchresults.txt");
						}
					}
			} else {
				if (!dir.hasFlag("-i")) {
					db.writeToTextFile(db, "invertedindex.txt");
				}
			}	
		}
	}
}