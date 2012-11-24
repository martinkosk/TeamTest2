package ee.itcollege.borderproject.dao;

import java.util.List;

import ee.itcollege.borderproject.model.Guard;

public interface GuardDao {
	
	List<Guard> getGuards();

	void saveGuard(Guard guard) ;
	
	void saveGuards(List<Guard> guards);
	
	List<Guard> searchGuards(String name, Integer age);
	
	List<Guard> searchGuards(String name);
	
	List<Guard> searchGuards(Integer age);
	
	Guard searchGuard(Integer id);
	
	void updateGuard(Guard guard);
}
