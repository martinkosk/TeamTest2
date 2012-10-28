package ee.itcollege.borderproject.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ee.itcollege.borderproject.dao.GuardDao;
import ee.itcollege.borderproject.model.Guard;

public class GuardDaoJdbc implements GuardDao {
	private static final String CONNECTION_STRING = "jdbc:hsqldb:C:/hsql/BorderDB";
	private static final String DRIVER_NAME = "org.hsqldb.jdbcDriver";
	private static final String INSERT_STATEMENT = "INSERT INTO guard (name, age) VALUES (?, ?)";
	
	private static final String SELECT_GUARDS = "SELECT id, name, age FROM guard";
	private static final String SELECT_GUARDS_BY_AGE_NAME = "SELECT id, name, age FROM guard WHERE LOWER(name) = ? AND age = ?";
	private static final String SELECT_GUARDS_BY_AGE = "SELECT id, name, age FROM guard WHERE age = ?";
	private static final String SELECT_GUARDS_BY_NAME = "SELECT id, name, age FROM guard WHERE LOWER(name) = ?";
	
	private static final String UPDATE_STATEMENT_SET_AGE = "UPDATE guard SET age = ? WHERE id = ?";
	private static final String UPDATE_STATEMENT_SET_NAME = "UPDATE guard SET name = ? WHERE id = ?";
	private static final String UPDATE_STATEMENT_SET_NAME_AGE = "UPDATE guard SET name = ?, age = ? WHERE id = ?";
	
	@Override
	public List<Guard> getGuards() throws SQLException {
		List<Guard> guards = new ArrayList<Guard>();
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(SELECT_GUARDS);
		
		try {
			while (resultSet.next()) {
				Guard guard = new Guard();
				guard.setId(resultSet.getInt(1));
				guard.setName(resultSet.getString(2));
				guard.setAge(resultSet.getInt(3));
				guards.add(guard);
			}
		}
		finally {
			resultSet.close();
			statement.close();
			connection.close();
		}

		return guards;
	}
	
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
	
	@Override
	public List<Guard> searchGuards(String name, int age) throws SQLException {
		List<Guard> guards = new ArrayList<Guard>();
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(SELECT_GUARDS_BY_AGE_NAME);
		
		try {
			statement.setString(1, name.toLowerCase());
			statement.setInt(2, age);
			guards.addAll(findGuards(statement));
		}
		finally {
			statement.close();
			connection.close();
		}
		
		return guards;
	}
	
	@Override
	public List<Guard> searchGuards(String name) throws SQLException {
		List<Guard> guards = new ArrayList<Guard>();
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(SELECT_GUARDS_BY_NAME);
		
		try {
			statement.setString(1, name.toLowerCase());
			guards.addAll(findGuards(statement));
		}
		finally {
			statement.close();
			connection.close();
		}
		
		return guards;
	}
	
	@Override
	public List<Guard> searchGuards(int age) throws SQLException {
		List<Guard> guards = new ArrayList<Guard>();
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(SELECT_GUARDS_BY_AGE);
		
		try {
			statement.setInt(1, age);
			guards.addAll(findGuards(statement));
		} 
		finally {
			statement.close();
			connection.close();
		}
		
		return guards;
	}
	
	@Override
	public void updateGuard(int id, String name, int age) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(UPDATE_STATEMENT_SET_NAME_AGE);
		
		try {
			statement.setString(1, name);
			statement.setInt(2, age);
			statement.setInt(3, id);
			statement.execute();
		} 
		finally {
			statement.close();
			connection.close();
		} 
	}
	
	@Override
	public void updateGuard(int id, int age) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(UPDATE_STATEMENT_SET_AGE);
		
		try {
			statement.setInt(1, age);
			statement.setInt(2, id);
			statement.execute();
		} 
		finally {
			statement.close();
			connection.close();
		}
	}
	
	@Override
	public void updateGuard(int id, String name) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(UPDATE_STATEMENT_SET_NAME);
		
		try {
			statement.setString(1, name);
			statement.setInt(2, id);
			statement.execute();
		} 
		finally {
			statement.close();
			connection.close();
		}
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
	
	private List<Guard> findGuards(PreparedStatement statement) {
		List<Guard> guards = new ArrayList<Guard>();

		try {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Guard guard = new Guard();
				guard.setId(resultSet.getInt(1));
				guard.setName(resultSet.getString(2));
				guard.setAge(resultSet.getInt(3));
				guards.add(guard);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 

		return guards;
	}
}

