import java.io.File;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;


public class MultithreadedDirectoryTraverser {
	private final WorkQueue workers;
	private TreeSet<String> paths;
	private final MultiReaderLock lock;
	private int pending;
	
	public MultithreadedDirectoryTraverser() {
		workers = new WorkQueue();
		paths = new TreeSet<String>();
		lock = new MultiReaderLock();
		pending = 0;
	}
	
	public void traverseDirectory(Path dir) {
		try {
			String directory = dir.toString(); 
			File root = new File(directory);
			File[] list = root.listFiles();
			for (File file : list) { /* Loop traverses files */ 
				if (file.isFile() && ((file.getName().endsWith(".TXT")) || file.getName().endsWith(".txt"))) { 
					lock.lockWrite();
					updateSet(file);
					lock.unlockWrite();
				} else if (file.isDirectory()) {
					workers.execute(new DirectoryWorker(file));
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Sorry but the directory that was entered is not valid.");
		}
	}

	public synchronized Set getPaths() {
		finish();
		return paths;
	}

	public void finish() {
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
		workers.shutdown();
	}
	  
	private synchronized void updateSet(File file) {
		paths.add(file.getAbsolutePath());
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

	private class DirectoryWorker implements Runnable {

		private File directory;

		public DirectoryWorker(File file) {
			this.directory = file;
			incrementPending();
		}

		@Override
		public void run() {
			String file = directory.toString();
			File root = new File(file);
			File[] list = root.listFiles();
			for (File textFile : list) {
				if (textFile.isDirectory()) {
					workers.execute(new DirectoryWorker(textFile));
				} else if (textFile.isFile() && ((textFile.getName().endsWith(".TXT")) || textFile.getName().endsWith(".txt"))) {
					updateSet(textFile);
				}
			}
			decrementPending();
		}
	}
     
/*	public static void main(String[] args) {
		MultithreadedDirectoryTraverser demo = new MultithreadedDirectoryTraverser();
        demo.traverseDirectory(Paths.get("/Users/blrrobinsonblr/Documents/CS212Folder"));

        System.out.println(demo.getPaths() + " files");

        demo.shutdown();
	}*/
}