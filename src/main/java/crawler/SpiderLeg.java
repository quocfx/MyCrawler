//*******************************************************************
//*******************************************************************
// (c) Copyright DXC Technology 2017, all rights reserved.          *
// This module contains source code for software that is part       *
// of the DXC Technology Background Technology Library. DXC         *
// Technology retains any and all rights in such software and       *
// grants <Client> a license for use as defined in the Contractual  *
// Agreement between <Client> and DXC Technology. This code was     *
// developed using the methodology in place at the time of          *
// development. Since this source code is used for multiple         *
// development projects, no attempt was made to modify the code to  *
// adhere to coding standards employed on a particular project.     *
//*******************************************************************
//*******************************************************************
package crawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * TODO: 
 * <p>
 * @version $Date$<br>
 *          $Revision$<br>
 *          $Author$<br>
 *          $HeadURL$
 */
public class SpiderLeg {
	
	private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36";
	
	private List<String> links = new LinkedList<String>(); // Just a list of
															// URLs
	private Document htmlDocument; // This is our web page, or in other words,
									// our document

//	doc = Jsoup.connect("https://www.facebook.com/")
//    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
//    .referrer("http://www.google.com")
//    .get();
	
	/**
	 * This performs all the work. It makes an HTTP request, checks the
	 * response, and then gathers up all the links on the page. Perform a
	 * searchForWord after the successful crawl
	 * 
	 * @param url
	 *            - The URL to visit
	 * @return whether or not the crawl was successful
	 */
	public boolean crawl(String url) {
		try {
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			Document htmlDocument = connection.get();
			this.htmlDocument = htmlDocument;
			if (connection.response().statusCode() == 200) {
				System.out.println("\n**Visiting** Received web page at " + url);
			}
			if (!connection.response().contentType().contains("text/html")) {
				System.out.println("**Failure** Retrieved something other than HTML");
				return false;
			}
//			Elements linksOnPage = htmlDocument.select("a[href]");
			
			
//			Elements starRating = htmlDocument.select("div.gkpes");
			
//			System.out.println("Found (" + starRating.size() + ") star rating element(s)");
//			for (Element rating : starRating) {
//				System.out.println("Rating: " + rating.html());
//			}
			
			
//			System.out.println("Found (" + linksOnPage.size() + ") links");
//			for (Element link : linksOnPage) {
//				this.links.add(link.absUrl("href"));
//			}
			return true;
		} catch (IOException ioe) {
			// We were not successful in our HTTP request
			return false;
		}
	}

	/**
	 * Performs a search on the body of on the HTML document that is retrieved.
	 * This method should only be called after a successful crawl.
	 * 
	 * @param searchWord
	 *            - The word or string to look for
	 * @return whether or not the word was found
	 */
	public boolean searchForWord(String searchWord) {
		// Defensive coding. This method should only be used after a successful
		// crawl.
		if (this.htmlDocument == null) {
			System.out.println("ERROR! Call crawl() before performing analysis on the document");
			return false;
		}
		System.out.println("Searching for the word " + searchWord + "...");
		String bodyText = this.htmlDocument.body().text();
		return bodyText.toLowerCase().contains(searchWord.toLowerCase());
	}

	public List<String> getLinks() {
		return this.links;
	}
}
