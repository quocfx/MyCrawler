package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.SellerDao;
import model.Profile;
import model.Seller;

public class CrawlBusiness {
	
	@SuppressWarnings("unchecked")
	public void crawlDataIntoDB() throws SQLException {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/mycrawler-context.xml");
	    List<String> merchantUrlList = (List<String>) ctx.getBean("merchantList");
	    SellerDao sellerDao = (SellerDao) ctx.getBean("sellerDao");
	    ctx.close();
	    Spider spider = new Spider();
	    String profileUrl = "";
	    List<Seller> sellerList = new ArrayList<Seller>();
	    // for each provided merchant url, extract its data and add it into the list 
	    for (String merchantUrl: merchantUrlList) {
	    	profileUrl = spider.generateSellerAPIUrl(merchantUrl);
	    	Profile profile = spider.getProfileFromProfileUrl(profileUrl);
	    	if (profile != null) {
	    		sellerList.add(profile.getSeller());
	    	}
	    }
	    // truncate all old data, it could be done better with clearer requirement
		sellerDao.truncateDatable();
		// insert all collected data into Seller table
		sellerDao.importSellerList(sellerList);
		
	}

}
