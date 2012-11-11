package ee.itcollege.borderproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ee.itcollege.borderproject.model.Incident;
import ee.itcollege.borderproject.service.IncidentService;

@Controller
public class IncidentController {
	
	private static final String INCIDENT_ATTRIBUTE = "incidents";
	
	@Resource
	private IncidentService incidentService;
	
	@RequestMapping(value = "/incident/listAll", method = RequestMethod.GET)
	public String listAllIncidents(Model model) {
		List<Incident> incidentList = incidentService.getUnresolvedIncidents();
		model.addAttribute(INCIDENT_ATTRIBUTE, incidentList);
		return "ListIncidents";
	}
	
	@RequestMapping(value="/incident/reportIncident", method=RequestMethod.GET) 
	 public String reportIncident() {
	  return "ReportIncident";
	 }
	 
	 @RequestMapping(value="/incident/reportIncident", method=RequestMethod.POST)
	 public String receiveReportIncident(@ModelAttribute Incident incident, Model model) {
	  List<Incident> incidents = new ArrayList<Incident>();
	  incidents.add(incident);
	  model.addAttribute(INCIDENT_ATTRIBUTE, incidents);
	  
	  return "ListIncidents";
	 }
	
}
