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
		
		String merchantUrl = "http://www.lazada.sg/value-market/";
//		String merchantUrl = "https://www.lazada.sg/verizon-e-store/";
		
		Spider spider = new Spider();
//		String profileUrl = spider.generateSellerAPIUrl(merchantUrl);
		String profileUrl = "http://seller-transparency-api.lazada.sg/v1/seller/transparency?platform=desktop&lang=en&seller_id=18464";
		Seller currentSeller = spider.getSellerFromProfileUrl(profileUrl);
		System.out.println("Seller name: " + currentSeller.getSellerDetail().getName());
		System.out.println("Seller location: " + currentSeller.getSellerDetail().getLocation());
		System.out.println("Seller rate: " + currentSeller.getSellerDetail().getRate());		
	}

}
