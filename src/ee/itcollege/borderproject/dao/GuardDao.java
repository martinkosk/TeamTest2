package ee.itcollege.borderproject.dao;

import java.sql.SQLException;
import java.util.List;

import ee.itcollege.borderproject.model.Guard;

public interface GuardDao {

	void saveGuard(Guard guard) throws SQLException;
	
	void saveGuards(List<Guard> guards) throws SQLException;
}
