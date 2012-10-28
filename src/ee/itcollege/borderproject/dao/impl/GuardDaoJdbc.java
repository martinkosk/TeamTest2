package ee.itcollege.borderproject.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ee.itcollege.borderproject.dao.GuardDao;
import ee.itcollege.borderproject.model.Guard;

public class GuardDaoJdbc implements GuardDao {

	private static final String CONNECTION_STRING = "jdbc:hsqldb:C:/hsql/BorderDB";
	private static final String DRIVER_NAME = "org.hsqldb.jdbcDriver";
	private static final String INSERT_STATEMENT = "INSERT INTO guard (name, age) VALUES (?, ?)";
	
	@Override
	public void saveGuard(Guard guard) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(INSERT_STATEMENT);
		
		try {
			statement.setString(1, guard.getName());
			statement.setInt(2, guard.getAge());
			statement.executeUpdate();
		} 
		finally {
			statement.close();
			connection.close();		
		}
	}

	@Override
	public void saveGuards(List<Guard> guards) throws SQLException {
		for (Guard guard : guards)
			saveGuard(guard);
	}
	
	private Connection getConnection() {
		try {
			Class.forName(DRIVER_NAME);
			return DriverManager.getConnection(CONNECTION_STRING);
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}

