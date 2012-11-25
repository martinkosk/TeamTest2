package ee.itcollege.borderproject.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ee.itcollege.borderproject.dao.IncidentDao;
import ee.itcollege.borderproject.model.Incident;

@Controller
public class IncidentController {

	private static final String INCIDENTS_ATTRIBUTE = "incidents";
	
	private static final String LIST_INCIDENTS_VIEW = "ListIncidents";
	private static final String ADD_INCIDENT_VIEW = "AddIncident";
	private static final String UPDATE_INCIDENT_VIEW = "UpdateIncident";
	
	private static final String REDIRECT_TO_INCIDENT_LISTING =  "redirect:/incident/view";
	
	@Resource
	private IncidentDao incidentDao;

	@RequestMapping(value = "/incident/view", method = RequestMethod.GET)
	public String listAllIncidents(Model model) {
		model.addAttribute(INCIDENTS_ATTRIBUTE, incidentDao.getIncidents());
		return LIST_INCIDENTS_VIEW;
	}

	@RequestMapping(value = "/incident/add", method = RequestMethod.GET)
	public String reportIncident(Model model) {
		model.addAttribute(new Incident());
		return ADD_INCIDENT_VIEW;
	}

	@RequestMapping(value = "/incident/add", method = RequestMethod.POST)
	public String receiveReportIncident(Model model,
			@ModelAttribute @Valid Incident incident, BindingResult result) {		
		
		if (result.hasErrors())  
			return ADD_INCIDENT_VIEW;
		
		incidentDao.saveIncident(incident);
		return REDIRECT_TO_INCIDENT_LISTING;
	}
	
	@RequestMapping(value = "/incident/update", method = RequestMethod.GET)
	public String updateIncident(Model model,
			@RequestParam(required=true, value="id") Integer incidentId) {		
		
		Incident incident = incidentDao.searchIncident(incidentId);
		
		if (incident == null) 
			return REDIRECT_TO_INCIDENT_LISTING;

		model.addAttribute(incident);
		return UPDATE_INCIDENT_VIEW;
	}
	
	@RequestMapping(value = "/incident/update", method = RequestMethod.POST)
	public String receiveUpdatedIncident(Model model,
			@ModelAttribute @Valid Incident incident, BindingResult result) {				
		
		if(result.hasErrors())
			return UPDATE_INCIDENT_VIEW;
		
		incidentDao.updateIncident(incident);		
		return REDIRECT_TO_INCIDENT_LISTING;
	}
	
	@RequestMapping(value = "/incident/delete", method = RequestMethod.GET)
	public String deleteIncident(Model model,
			@RequestParam(required=true, value="id") Integer incidentId) {		
		
		incidentDao.deleteIncident(incidentId);	
		return REDIRECT_TO_INCIDENT_LISTING;
	}
	
}
