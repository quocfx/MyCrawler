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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.core.JsonParser.Feature;

import myobject.Seller;

/**
 * TODO: 
 * <p>
 * @version $Date$<br>
 *          $Revision$<br>
 *          $Author$<br>
 *          $HeadURL$
 */
public class SpiderTest {	
//	public static void main(String[] args) {
//		
////		https://www.lazada.sg/verizon-e-store/
////		http://www.lazada.sg/value-market/
////		https://www.lazada.sg/starblink/
////		https://www.lazada.sg/ryan-rayla/
//		
//		Spider spider = new Spider();
//		spider.search("http://www.lazada.sg/value-market/", "Value Market");
//	}
	
	
	//We need a real browser user agent or Google will block our request with a 403 - Forbidden
	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36";

	public static void main(String[] args) throws Exception {
		
//		String templateURL = "http://seller-transparency-api.lazada.sg/v1/seller/transparency?platform=desktop&lang=en&seller_id=%s"; 
//
//		//Fetch the page
////		final Document doc = Jsoup.connect("https://google.com/search?q=test").userAgent(USER_AGENT).get();
//		
//		final Document htmlDocument = Jsoup.connect("http://www.lazada.sg/value-market/").userAgent(USER_AGENT).timeout(5000).get();
//		
//		Elements starRating = htmlDocument.getAllElements();
//		System.out.println("all element size is: " + starRating.size());
//		int count = 0;
//		
//		// get profile id of merchant
//		String rawData = starRating.get(0).data();		
//		String template = "sellerId: '";		
//		int index = rawData.indexOf(template);
//		if (index > 0) {
//			rawData = rawData.substring(index + template.length());
//			index = rawData.indexOf("'");
//			rawData = rawData.substring(0, index);
//		}
//		
//		System.out.println("Seller id=" + rawData);
//		
//		String profileURL = String.format(templateURL, rawData);
//		System.out.println(profileURL);
		
		String testUrl = "http://seller-transparency-api.lazada.sg/v1/seller/transparency?platform=desktop&lang=en&seller_id=18464";
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(testUrl);
		
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);
		
		if (response.getStatusLine().getStatusCode() != 200) {
		    throw new RuntimeException("Failed : HTTP error code : "
		             + response.getStatusLine().getStatusCode());
		}
		
		BufferedReader br = new BufferedReader(
			    new InputStreamReader( 
			        (response.getEntity().getContent())
			    )
			);
		
		StringBuilder content = new StringBuilder();
		String line;
		while (null != (line = br.readLine())) {
		    content.append(line);
		}
		
//		System.out.println(content.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		
		Map<String,Object> map = mapper.readValue(content.toString(), Map.class);
		
		Map<String,Object> sellerMap = (Map) map.get("seller");
//		System.out.println(sellerMap.get("name"));
		
//		String sellerInfo = map.get("seller").get("name").toString();
//		System.out.println(sellerInfo);
//		
//		String testValue = "{name=ValueMarket, logo_url=}";
//		
//		String jsonInString = "{\"name\":\"mkyong\",\"salary\":7500,\"skills\":[\"java\",\"python\"]}";
//		
//		Map<String,Object> sellerMap = mapper.readValue(jsonInString, Map.class);
//		
////		mapper.readVa
//		
		System.out.println("Seller name: " + sellerMap.get("name"));
		System.out.println("Seller location: " + sellerMap.get("location"));
		System.out.println("Seller rate: " + sellerMap.get("rate"));
		
//		Seller mySeller =  mapper.readValue(sellerInfo, Seller.class);
		
		
//		System.out.println(map);
		
//		JsonObject  jsonObject = new JSONObject(content.toString());
		

		//Traverse the results
//		for (Element result : starRating){
//
////			final String title = result.text();
////			final String url = result.attr("href");
//
//			//Now do something with the results (maybe something more useful than just printing to console)
//			count++;
//			System.out.println("------------ Element " + count + "---------------");
//			System.out.println(result.html());
//		}
	}

}
