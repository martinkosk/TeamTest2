package ee.itcollege.borderproject.dao;

import java.sql.SQLException;
import java.util.List;

import ee.itcollege.borderproject.model.Guard;

public interface GuardDao {
	
	List<Guard> getGuards() throws SQLException;

	void saveGuard(Guard guard) throws SQLException;
	
	void saveGuards(List<Guard> guards) throws SQLException;
	
	List<Guard> searchGuards(String name, int age) throws SQLException;
	
	List<Guard> searchGuards(String name) throws SQLException;
	
	List<Guard> searchGuards(int name) throws SQLException;
	
	void updateGuard(int id, int age);

	void updateGuard(int id, String name);

	void updateGuard(int id, String name, int age);	
	
}
