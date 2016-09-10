
/**
 * @author Brandon Robinson
 * 
 * Desciption: This class is a container class that stores Search Results by filename. 
 * It contains 1 method for updating the Search Result and another method that overrides 
 * the compareTo method. 
 *
 */
public class SearchResult implements Comparable<SearchResult> {
	private String location;
	public int frequency; 
	private int position;

	/**
	 * CONSTRUCTOR 
	 * 
	 * @param  String location
	 * @param int frequency
	 * @param int position
	 */
	public SearchResult(String location, int frequency, int position) {
		this.location = location;
		this.frequency = frequency;
		this.position = position; 
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getFrequency() {
		return frequency;
	}


	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}


	public int getPosition() {
		return position;
	}


	public void setPosition(int position) {
		this.position = position;
	}

	
	/**
	 * Description: This method is called by the partialSearch method that updates the Search Result based on 
	 * whether or not the multi-worded query is found in the same file. If so, this method will update the  
	 * frequency and position of the Search Result. 
	 * 
	 * @param int anotherFrequency
	 * @param int anotherPosition
	 */
	public void update(int anotherFrequency, int anotherPosition) {
		int result = getFrequency() + anotherFrequency; /* Adding the frequency the previoud one */ 
		setFrequency(result); /* Setting the new frequency for the result */ 
		if (getPosition() > anotherPosition) { /* If the new position less than the old position */
			setPosition(anotherPosition); /* Set the earlier position as the result */ 
		}
	}
	
	
	
	/* Description: This method overrides the compareTo method in order to sort the Search Results 
	 * based on frequency, position, and the location of each result. 
	 * 
	 */
	@Override
	public int compareTo(SearchResult otherSearch) {
		if (this.frequency < otherSearch.frequency) { /* Frequency comparison: Less than */ 
			return 1; 
		} else if (this.frequency > otherSearch.frequency) { /* Frequency comparison: Greater than */ 
			return -1;
		} else { /* Equal */ 
			if (this.position < otherSearch.position) { /* Position comparison: Less than */ 
				return -1;
			} else if (this.position > otherSearch.position) { /* Position comparison: Greater than */ 
				return 1;
			} else { /* Equal */ 
				if (!this.location.equalsIgnoreCase(otherSearch.location)) {
					return this.location.compareTo(otherSearch.location);
				}
			}
		}
	return 0;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}