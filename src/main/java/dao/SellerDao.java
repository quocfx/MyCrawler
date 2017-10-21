package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import model.Seller;

/**
 * Provide some functional to access data of seller table 
 * <p>
 * @version $Date$<br>
 *          $Revision$<br>
 *          $Author$<br>
 *          $HeadURL$
 */
public class SellerDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(SellerDao.class);
	
	
	private DataSource dataSource;
	
	private final String GET_ALL_SELLERS_SQL = "select * from seller";
	private final String INSERT_NEW_SELLER_SQL = "insert into seller (s_name, s_category, s_rate, s_size, s_location, s_time_on_lazada, s_shipped_on_time) "
			+ "values (?,?,?,?, ?,?,?)";
	private final String TRUNCATE_SQL = "truncate table review, seller;";
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * Retrieve all record of Seller table
	 * <p>
	 * @return list of seller
	 * @throws SQLException - Thrown if a data access error occurs
	 */	
	public List<Seller> getAllSeller() throws SQLException{
		ResultSet result = null;
		PreparedStatement pre = null;
		List<Seller> sellerList = new ArrayList<Seller>();
		try {
			pre = dataSource.getConnection().prepareStatement(GET_ALL_SELLERS_SQL);
			result = pre.executeQuery();
			
			while (result.next()) {
				Seller seller = new Seller();
				seller.setName(result.getString("s_name"));
				Map<String, String> categoryMap = new HashMap<String, String>();
				categoryMap.put("category", result.getString("s_category"));
				seller.setCategory(categoryMap);
				seller.setLocation(result.getString("s_location"));
				seller.setSize(result.getInt("s_size"));
				seller.setRate(result.getInt("s_rate"));
				Map<String, Integer> tempMap = new HashMap<String, Integer>();
				tempMap.put("months", new Integer(result.getInt("s_time_on_lazada")));
				seller.setTimeOnLazada(tempMap);
				tempMap = new HashMap<String, Integer>();
				tempMap.put("seller_rate", new Integer(result.getInt("s_shipped_on_time")));
				seller.setShippedOnTime(tempMap);
				sellerList.add(seller);
			}
			LOG.info("Execute query: " + GET_ALL_SELLERS_SQL);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (pre != null) {
				pre.close();
			}
		}
		return sellerList;
	}
	
	/**
	 * Insert new record into table Seller
	 * <p>
	 * @param seller - The Seller object which is retrieve from rest
	 * @return id - The Id of new record
	 * @throws SQLException - Thrown if a data access error occurs
	 */
	public long createNewSeller(Seller seller) throws SQLException {
		PreparedStatement pre = null;
		long sId = 0;
		try {
			pre = dataSource.getConnection().prepareStatement(INSERT_NEW_SELLER_SQL);
			pre.setString(1, seller.getName());
			pre.setString(2, seller.getCategory().get("name").toString());
			pre.setFloat(3, seller.getRate());
			pre.setInt(4, seller.getSize());
			pre.setString(5, seller.getLocation());
			pre.setInt(6, seller.getTimeOnLazada().get("months").intValue());
			pre.setInt(7, seller.getShippedOnTime().get("average_rate").intValue());
			pre.executeUpdate(); 
			ResultSet rs = pre.getGeneratedKeys();
			if (rs.next()) {
				sId = rs.getLong(1);
			}
			LOG.info("Insert new record to \"Seller\" successfully");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (pre != null) {
				pre.close();
			}
		}
		return sId;
	}
	
	/**
	 * Insert a list of seller into table Seller
	 * <p>
	 * @param sellerList - List of seller needs to be inserted
	 * @throws SQLException - Thrown if a data access error occurs
	 */
	public void importSellerList(List<Seller> sellerList) throws SQLException {
		PreparedStatement pre = null;
		try {
			pre = dataSource.getConnection().prepareStatement(INSERT_NEW_SELLER_SQL);
			for(Seller seller: sellerList) {
				pre.setString(1, seller.getName());
				pre.setString(2, seller.getCategory().get("name").toString());
				pre.setFloat(3, seller.getRate());
				pre.setInt(4, seller.getSize());
				pre.setString(5, seller.getLocation());
				pre.setInt(6, seller.getTimeOnLazada().get("months").intValue());
				pre.setInt(7, seller.getShippedOnTime().get("average_rate").intValue());
				pre.addBatch();
			}
			// Not sure if we should do it in a batch or not, as we may also need to insert data into Review table as the same time
			pre.executeBatch(); 
			LOG.info("Insert new records to \"Seller\" successfully");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (pre != null) {
				pre.close();
			}
		}
	}
	
	/**
	 * Truncate all table in database
	 * Really, it should NOT be here. It could be located somewhere in parent class
	 * <p>
	 * @throws SQLException - Thrown if a data access error occurs
	 */
	public void truncateDatable() throws SQLException {
		PreparedStatement pre = null;
		try {
			pre = dataSource.getConnection().prepareStatement(TRUNCATE_SQL);
			pre.executeUpdate(); 
			LOG.info("Truncate all tables successfully" );
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (pre != null) {
				pre.close();
			}
		}
	}
}
