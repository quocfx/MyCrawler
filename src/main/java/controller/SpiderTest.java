package controller;
//*******************************************************************
//*******************************************************************
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

/**
 * TODO: 
 * <p>
 * @version $Date$<br>
 *          $Revision$<br>
 *          $Author$<br>
 *          $HeadURL$
 */
public class SpiderTest {	
	
	public static void main(String[] args) throws Exception {
//		// open/read the application context file
//	    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/mycrawler-context.xml");
//	    List<String> merchantUrlList = (List<String>) ctx.getBean("merchantList");
//	    SellerDao sellerDao = (SellerDao) ctx.getBean("sellerDao");
//	    ctx.close();
//	    Spider spider = new Spider();
//	    String profileUrl = "";
//	    List<SellerDetail> sellerDetailList = new ArrayList<SellerDetail>(); 
//	    for (String merchantUrl: merchantUrlList) {
//	    	profileUrl = spider.generateSellerAPIUrl(merchantUrl);
//	    	Seller tempSeller = spider.getSellerFromProfileUrl(profileUrl);
//	    	if (tempSeller != null) {
//	    		sellerDetailList.add(tempSeller.getSellerDetail());
//	    	}
//	    }
//		sellerDao.truncateDatable();
//		sellerDao.importSellerDetailList(sellerDetailList);
		CrawlBusiness myCrawler = new CrawlBusiness();
		myCrawler.crawlDataIntoDB();
		System.out.println("OK!");
	}

}
