package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import myobject.Seller;
import utils.PostgresUtils;

public class SellerDao extends CrawlerDao {
	
	public SellerDao(Connection connection) {
		super(connection);
	}
	
	/**
	 * Retrieve all record of Seller table
	 * <p>
	 * @return result - ResultSet of Seller table
	 * @throws SQLException - Thrown if a data access error occurs
	 */
	public ResultSet getAll() throws SQLException{
		ResultSet result = null;
		String query = PostgresUtils.QUERY_GET_ALL_SELLER;
		PreparedStatement pre = null;
		try {
			pre = this.getConnection().prepareStatement(query);
			result = pre.executeQuery();
			System.out.println("Retrieve all record from table Seller");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (pre != null) {
				pre.close();
			}
		}
		return result;
	}
	
	/**
	 * Insert new record into table Seller
	 * <p>
	 * @param seller - The Seller object which is retrieve from rest
	 * @return id - The Id of new record
	 * @throws SQLException - Thrown if a data access error occurs
	 */
	public long createNewSeller(Seller seller) throws SQLException {
		String query = PostgresUtils.QUERY_INSERT_NEW_SELLER;
		PreparedStatement pre = null;
		long sId = 0;
		try {
			pre = this.getConnection().prepareStatement(query);
			pre.setString(1, seller.getSellerDetail().getName());
			pre.setInt(2, seller.getSellerDetail().getRate());
			pre.setString(3, seller.getSellerDetail().getLocation());
			pre.setInt(4, seller.getSellerDetail().getTimeOnLazada().get("months").intValue());
			pre.setInt(5, seller.getSellerDetail().getShippedOnTime().get("average_rate").intValue());
			pre.executeUpdate(); 
			ResultSet rs = pre.getGeneratedKeys();
			if (rs.next()) {
				sId = rs.getLong(1);
			}
			System.out.println("Insert new record to \"Seller\" successfully" );
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (pre != null) {
				pre.close();
			}
		}
		return sId;
	}
}
