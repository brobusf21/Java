import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Description: This class contains a method that obtains the hash values of the passwords 
 * in order to populate the dictionary. It also contains a method that obtains the results 
 * of the has values which are the passwords. 
 * A HashMap data structure is used to store the hash values and their passwords. 
 *
 */
public class WordManager {
	private HashMap<String, String> passwords; // We want to place all passwords and their hash codes in this map 
	
	public WordManager() {
		passwords = new HashMap<String, String>();
	}
	
	/** 
	 * This method obtains the hash values of the passwords that are provided in the text file.
	 * It then populates the HashMap accordingly. 
	 * 
	 * @param password
	 */
	public void getHashes(String password) {
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		m.reset();
		m.update(password.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
		passwords.put(hashtext, password); 
	}
	
	/**
	 * This method obtains the passwords of the hash values by seeing if the hash values provided match
	 * with that the HashMap has saved. 
	 * @param hash
	 */
	public void getPassword(String hash) {
		if (passwords.containsKey(hash)) 
			System.out.println("Hash = " + hash + " Password = " + passwords.get(hash));
		else
			System.out.println("Sorry, but the password for the hash " + hash + "can not be found. Please try a differnt one.");
	}
}
