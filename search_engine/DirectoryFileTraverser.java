import java.io.File;
import java.util.Set;
import java.util.TreeSet;


/**
 * @author Brandon Robinson
 * Description: This class recursively traverses the directories and sub-directories that are passed 
 * in as arguments in order to find files. Once the files are found, they are then passed to the fileParser 
 * class so the words can be parsed. 
 */
import java.io.File;
/**
 * @author Brandon Robinson
 * Description: This class recursively traverses the directories and sub-directories that are passed 
 * in as arguments in order to find files. Once the files are found, they are then passed to the fileParser 
 * class so the words can be parsed. 
 */
public class DirectoryFileTraverser {
	
	private FileParser fp; 

	
	/**
	 * Constructor.  
	 * @param is 
	 * @param Datamanager dm. 
	 */
	public DirectoryFileTraverser(DataManager dm, QueryDataManager is) {
		fp = new FileParser(dm);
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
					/* Condition: File needs to be a file, the file name has to have a .txt or .TXT extension,  */
					fp.parseFile(file); /* Calling FileParser method to begin iterating words in the text file */
			    } else if (file.isDirectory()){ /* Condition: If file is a directory */
			        traverse(file.getAbsolutePath()); /* Recursively calls traverse method in order to only seek files */
			    }
			}
		} catch (NullPointerException e) {
			System.out.println("Sorry but the directory that was entered is not valid.");
		}
	}
}