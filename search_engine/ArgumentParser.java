import java.util.HashMap;
import java.util.Map;

/**
 * @author Brandon Robinson
 *CS212 28 August 2013
 */
public class ArgumentParser {
	
	//Initializing a map
	private Map<String, String> myMap;

	// constructor
	public ArgumentParser(String[] args) {
		myMap = new HashMap<String, String>();
		parseArgs(args);
	}
	
	/**
	 * Parses a string array of arguments into flag and value pairs and stores them in a HashMap by key and value.
	 * @param args
	 */
	private void parseArgs(String[] args) {
		for(int i = 0; i < args.length - 1; i++) {
			if (isFlag(args[i]) && isValue(args[i+1])) {
				myMap.put(args[i], args[i+1]);
			} 
			else if (isFlag(args[i]) && isFlag(args[i+1])) {
				myMap.put(args[i], null);
			}
			else if (isFlag(args[args.length - 1])) {
				myMap.put(args[args.length - 1], null);
			}
		}
	}
	
	/**
	 * Determines whether not not the argument is a flag.
	 * @param arg
	 * @return true if argument is a flag. Returns false if argument is not a flag
	 */
	public static boolean isFlag(String arg) {
	    	if (arg.startsWith("-")) {
	    		return true;
	    	}
	    	return false;
	}

	/**
	 * Determines whether or not the argument is a value.
	 * @param arg
	 * @return true if argument is a value. Returns false if argument is not a value.
	 */
	public static boolean isValue(String arg) {
		if ((!arg.startsWith("-"))) {
			return true;
		}
		return false;
	}

	
	/**
	 * Determines if HashMap has a particular flag or not.
	 * @param flag
	 * @return true if flag is found. Returns false if flag is not found.
	 */
	public boolean hasFlag(String flag) {
		if (myMap.containsKey(flag)) {
			return true;
		}
	return false;
	}
	

	/**
	 * Determines if a particular flag exists with a value associated to it in the HashMap.
	 * @param flag
	 * @return true if flag and value exists. Returns false if it does not.
	 */
	public boolean hasValue(String flag) {
		String value = (myMap.get(flag));
		if (value != null) {
			return true;
		}
	return false;
	}
	

	/**
	 * Returns the value that is associated with a flag that is in HashMap.
	 * @param flag
	 * @return value that is associated with it's flag. Returns null if no value exists.
	 */
	public String getValue(String flag) {
		String value = myMap.get(flag);
		if (value != null) {
			return value;
		} else {
		return null;
		}
	}
	

	/**
	 * Retrieves the number of flags that were passed in as command-line arguments
	 * @return The number of flags that exist in the HashMap.
	 */
	public int numFlags() {
		int flagCounter = myMap.size();
		return flagCounter;
	}
	
	//retrieves the total number of command-line arguments 
	//passed in (do not count null values)
	/**
	 * Retrieves the total number of command-line arguments passed in.
	 * @return Total number of flags and values that are not null.
	 */
	public int numArguments() {
		int valueCounter = 0;
		int flagCounter = myMap.size();
		int sumOfArguments = 0;
		for (String value : myMap.values()) {
			if (value != null) {
				valueCounter+= 1;
			}
		}
		sumOfArguments = valueCounter + flagCounter;
		return sumOfArguments;
	}
	
}
