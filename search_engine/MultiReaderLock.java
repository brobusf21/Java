/**
 * Brandon Robinson
 * blrobinson
 * Homework #2
 * A simple custom lock that allows simultaneously read operations, but
 * disallows simultaneously write and read/write operations.
 *
 * You do not need to implement any form or priority to read or write
 * operations. The first thread that acquires the appropriate lock should be
 * allowed to continue.
 *
 * @author CS 212 Software Development
 * @author University of San Francisco
 */
public class MultiReaderLock {
	// TODO: Add any necessary members here.
	private int readers;
	private int writers;

	/**
	 * Initializes a multi-reader (single-writer) lock.
	 */
	public MultiReaderLock() {
		// TODO: Initialize members.
		readers = 0;
		writers = 0;
	}

	/**
	 * Will wait until there are no active writers in the system, and then will
	 * increase the number of active readers.
	 */
	public synchronized void lockRead() {
		// TODO: Fill in. Do not modify method signature.
		while (writers > 0) {
			try {
				this.wait();
			} catch (InterruptedException ex ) {
				System.out.println("Sorry but this will not work" + ex.getMessage());
			}
		}
		readers++;
	}

	/**
	 * Will decrease the number of active readers, and notify any waiting
	 * threads if necessary. use notifyAll
	 */
	public synchronized void unlockRead() {
		readers--;
		this.notifyAll();
	}

	/**
	 * Will wait until there are no active readers or writers in the system, and
	 * then will increase the number of active writers.
	 */
	public synchronized void lockWrite() {
		// TODO: Fill in. Do not modify method signature.
		while (readers > 0 || writers > 0) {
			try {
				this.wait();
			} catch (InterruptedException ex) {
				System.out.println("Sorry but this will not work" + ex.getMessage());
			}
		}
		writers++;
	}

	/**
	 * Will decrease the number of active writers, and notify any waiting
	 * threads if necessary.
	 */
	public synchronized void unlockWrite() {
		// TODO: Fill in. Do not modify method signature.
		writers--;
		this.notifyAll();
	}
}