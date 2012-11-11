package ee.itcollege.borderproject.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import ee.itcollege.borderproject.model.Incident;
import ee.itcollege.borderproject.service.IncidentService;

@Service
@Scope(value="singleton")
public class IncidentSimulatorServiceImpl implements IncidentService {

	@Override
	public List<Incident> getUnresolvedIncidents() {
		List<Incident> incidents = new ArrayList<Incident>();

		for (int i = 0; i < 10; i++) {
			Incident incident = new Incident();
			incident.setStart(new Date());
			incident.setDescription(getRandomDescription());
			incident.setInvolvedGuardCount(new Random().nextInt(50));
			incident.setLocation(getRandomLocation());
			incident.setStatus(getRandomStatus());
			incident.setEnd(new Date());		
			
			incidents.add(incident);
		}

		return incidents;
	}
	
	private String getRandomDescription() {
		List<String> descriptions = new ArrayList<String>();
		descriptions.add("Karud tulid üle piiri");
		descriptions.add("Sead chillivad");
		descriptions.add("Kisa");
			
		return descriptions.get(new Random().nextInt(descriptions.size()));
	}
	
	private String getRandomLocation() {
		List<String> locations = new ArrayList<String>();
		locations.add("Valga");
		locations.add("Narva jõgi");
		locations.add("Haapsalu sadam");
			
		return locations.get(new Random().nextInt(locations.size()));
	}
	
	private String getRandomStatus() {
		List<String> statuses = new ArrayList<String>();
		statuses.add("Lahendamata");
		statuses.add("Toimub praegu");
		statuses.add("Uurimises");
			
		return statuses.get(new Random().nextInt(statuses.size()));
	}	

}
