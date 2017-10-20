package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresUtils {
	private Connection connection = null;
	
	// DataSource properties
	public static final String POSTGRES_URL = "jdbc:postgresql://localhost:5432/crawler";
	public static final String POSTGRES_USER = "postgres";
	public static final String POSTGRES_PASSWORD = "root";
	
	// Queries
	public static final String QUERY_GET_ALL_SELLER = "select * from seller";
	public static final String QUERY_INSERT_NEW_SELLER = "insert into seller (s_name, s_rate, s_location, s_time_on_lazada, s_shipped_on_time) "
				+ "values (?, ?, ?, ?, ?)";
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Initializing connection to database
	 * <p>
	 * @param url - The URL of database
	 * @param username 
	 * @param password
	 * @throws SQLException - Thrown if a data access error occurs
	 */
	public void initialize(String url, String username, String password) throws SQLException {
		try {
			this.connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to \"" + url + "\" successfully");
		} catch (SQLException ex) {
			System.out.println("Connected to \"" + url + "\" failed!");
			ex.printStackTrace();
			return;
		}
	}
	
	/**
	 * Open connection to database
	 * <p>
	 * @throws SQLException - Thrown if a data access error occurs
	 */
	public void openConnection() throws SQLException{
		try {
			initialize(POSTGRES_URL, POSTGRES_USER, POSTGRES_PASSWORD);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
