package dao;

import java.sql.Connection;

import utils.PostgresUtils;

public class CrawlerDao {
	private PostgresUtils postgresUtils;
	
	private Connection connection;
	
	public CrawlerDao(Connection connection) {
		this.connection = connection;
	}
	
	public PostgresUtils getPostgresUtils() {
		return postgresUtils;
	}
	public void setPostgresUtils(PostgresUtils postgresUtils) {
		this.postgresUtils = postgresUtils;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
