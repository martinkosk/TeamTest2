package ee.itcollege.borderproject.service;

import java.util.List;

import ee.itcollege.borderproject.model.Incident;

public interface IncidentService {

	List<Incident> getUnresolvedIncidents();
	
}
