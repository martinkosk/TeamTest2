package ee.itcollege.borderproject.dao;

import java.util.List;

import ee.itcollege.borderproject.model.Incident;

public interface IncidentDao {

	List<Incident> getIncidents();

	void saveIncident(Incident incident) ;
	
	void saveIncidents(List<Incident> incidents);
	
	Incident searchIncident(Integer id);
	
	void updateIncident(Incident incident);
	
	void deleteIncident(Integer id);
	
}
