import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;

public class HTMLFetcher {
	private static final int PORT = 80;
	private URL url;
	private UrlTraverser urlTraverse; 
	private HashSet<String> master;
       

	public HTMLFetcher(String url) {
		this.master = new HashSet<String>();
		urlTraverse = new UrlTraverser();
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			fetch(this.url.getHost());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description: This method generates an HTTP request for the specified url
	 * @param urlDomain
	 * @return
	 */
	private String createRequest(String urlDomain) {
		String request;
		if (url.getFile().isEmpty()) {
			request = "GET " + "/" + " HTTP/1.1\n";
		} else {
			request = "GET " + url.getFile() + " HTTP/1.1\n";	
		}
	    request += "Host: " + url.getHost() + "\n";
	    request += "\r\n";
	    return request;
        }
	
	public void fetch(String urlDomain) throws Exception {

		// TODO: create a new socket here for a given urlDomain and a given PORT
		Socket socket = new Socket(urlDomain, PORT);
		
		// TODO: create PrintWriter for the socket's output stream
		PrintWriter writer = 
				new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		String request = createRequest(urlDomain);
		System.out.println(request);
		writer.write(request);
		writer.flush();
		
		StringBuilder string = new StringBuilder();
		boolean htmlFound = false;
		String line;
		while ((line = reader.readLine()) != null) {
			if (!htmlFound) {
				if (line.toLowerCase().startsWith("<html")) {
					htmlFound = true;
				} else {
					continue;
				}
			}
			string.append(line + "\n");
		}
		
		reader.close();
		writer.close();
		socket.close();
		
		System.out.println(string);
		String linksRead = string.toString();
		HTMLLinkParser lp = new HTMLLinkParser();
		ArrayList<String> links = lp.listLinks(linksRead);
		System.out.println(links);
		
		for (String link: links) { // test to see if link is relative or absolute
//			if (!master.contains(link))
//				master.add(link);
			//UrlTraverser urlTraverse = new UrlTraverser();
			URL absoluteUrl = new URL(url, link);
			System.out.println(absoluteUrl);
			//urlTraverse.traverse(absoluteUrl.toString()); // Trying to send the url to urlTraverser class to run multiple threads. 
			if (!master.contains(absoluteUrl.toString()))
				master.add(absoluteUrl.toString());
		}
		urlTraverse.traverse(master); 
	}
}
