package dao;

import java.sql.Connection;
import java.sql.SQLException;

import crawler.Spider;
import myobject.Seller;
import utils.PostgresUtils;

public class Test {

	public static void main(String[] args) {
		Spider spider = new Spider();
		String profileUrl = "http://seller-transparency-api.lazada.sg/v1/seller/transparency?platform=desktop&lang=en&seller_id=18464";
		Seller seller = spider.getSellerFromProfileUrl(profileUrl);
		try {
			PostgresUtils pos = new PostgresUtils();
			pos.openConnection();
			Connection connection = pos.getConnection();
			SellerDao sellerDao = new SellerDao(connection);
			sellerDao.createNewSeller(seller);
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		

	}

}
