package crawler;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * TODO: 
 * <p>
 * @version $Date$<br>
 *          $Revision$<br>
 *          $Author$<br>
 *          $HeadURL$
 */
public class Spider {
	
//	private static final Logger LOG = LoggerFactory.getLogger(CollectionPage.class);
	// Fields
	private static final int MAX_PAGES_TO_SEARCH = 10;
	private Set<String> pagesVisited = new HashSet<String>();
	private List<String> pagesToVisit = new LinkedList<String>();
	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36";
	
	public Spider() {
	}
	
	
	/**
	 * Generate Lazada seller API link 
	 * 
	 * @param merchantUrl the Url of a merchant
	 *  e.g. http://www.lazada.sg/value-market/
	 *  
	 * @return the Url of the seller API
	 */
	public String generateSellerAPIUrl(String merchantUrl) {
		final String templateURL = "http://seller-transparency-api.lazada.sg/v1/seller/transparency?platform=desktop&lang=en&seller_id=%s";
		
		if (merchantUrl == null || StringUtils.isEmpty(merchantUrl)) {
			return "";
		}
		
		try {
			final Document htmlDocument = Jsoup.connect(merchantUrl).userAgent(USER_AGENT).timeout(5000).get();
		} catch (IOException e) {
//			System.out.println();
//			LOG.error("IOException: "+ e, e);
		}
		String result = "";
		
		return result;
		
	}

	/**
	 * Our main launching point for the Spider's functionality. Internally it
	 * creates spider legs that make an HTTP request and parse the response (the
	 * web page).
	 * 
	 * @param url
	 *            - The starting point of the spider
	 * @param searchWord
	 *            - The word or string that you are searching for
	 */
	public void search(String url, String searchWord) {
		while (this.pagesVisited.size() < MAX_PAGES_TO_SEARCH) {
			String currentUrl;
			SpiderLeg leg = new SpiderLeg();
			if (this.pagesToVisit.isEmpty()) {
				currentUrl = url;
				this.pagesVisited.add(url);
			} else {
				currentUrl = this.nextUrl();
			}
			leg.crawl(currentUrl); // Lots of stuff happening here. Look at the
									// crawl method in
									// SpiderLeg
			boolean success = leg.searchForWord(searchWord);
			if (success) {
				System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
				break;
			}
			this.pagesToVisit.addAll(leg.getLinks());
		}
		System.out.println("\n**Done** Visited " + this.pagesVisited.size() + " web page(s)");
	}

	/**
	 * Returns the next URL to visit (in the order that they were found). We
	 * also do a check to make sure this method doesn't return a URL that has
	 * already been visited.
	 * 
	 * @return
	 */
	private String nextUrl() {
		String nextUrl;
		do {
			nextUrl = this.pagesToVisit.remove(0);
		} while (this.pagesVisited.contains(nextUrl));
		this.pagesVisited.add(nextUrl);
		return nextUrl;
	}
}
