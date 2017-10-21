package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Profile;
/**
 * TODO: 
 * <p>
 * @version $Date$<br>
 *          $Revision$<br>
 *          $Author$<br>
 *          $HeadURL$
 */
public class Spider {
	
	private static final Logger LOG = LoggerFactory.getLogger(Spider.class);
	//We need a real browser user agent or Google will block our request with a 403 - Forbidden
	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36";	
	
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
			Elements elements = htmlDocument.getAllElements();
			// get profile id of merchant
			String rawData = elements.get(0).data();		
			final String template = "sellerId: '";
			
			int index = rawData.indexOf(template);
			// retrieve data between template (sellerId)
			if (index > 0) {
				rawData = rawData.substring(index + template.length());
				index = rawData.indexOf("'");
				rawData = rawData.substring(0, index);
			}
			LOG.info("Seller Id=" + rawData);
			return String.format(templateURL, rawData);
		} catch (IOException e) {
			LOG.error("IOException: "+ e, e);
		}
		return "";
	}
	
	/**
	 * Extract data from Json using Lazada profile URL 
	 * 
	 * @param merchantUrl the Url of a merchant
	 *  e.g. http://seller-transparency-api.lazada.sg/v1/seller/transparency?platform=desktop&lang=en&seller_id=18464
	 *  
	 * @return the Url of the seller API
	 */
	public Profile getProfileFromProfileUrl(final String profileUrl) {		
		if (profileUrl == null || StringUtils.isEmpty(profileUrl)) {
			return null;
		}		
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(profileUrl);
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response;
		try {
			response = client.execute(request);
			if (response.getStatusLine().getStatusCode() != 200) {
			    throw new RuntimeException("Failed : HTTP error code : "
			             + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			StringBuilder content = new StringBuilder();
			String line;
			while (null != (line = br.readLine())) {
				content.append(line);
			}			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			return  mapper.readValue(content.toString(), Profile.class);
		} catch (ClientProtocolException e1) {
			LOG.error("ClientProtocolException: "+ e1, e1);
		} catch (UnsupportedOperationException e) {
			LOG.error("UnsupportedOperationException: "+ e, e);
		} catch (JsonParseException e) {
			LOG.error("JsonParseException: "+ e, e);
		} catch (JsonMappingException e) {
			LOG.error("JsonMappingException: "+ e, e);
		} catch (IOException e1) {
			LOG.error("IOException: "+ e1, e1);
		}
		return null;
	}
}
