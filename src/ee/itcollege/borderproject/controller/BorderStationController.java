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

import ee.itcollege.borderproject.dao.BorderStationDao;
import ee.itcollege.borderproject.model.BorderStation;

@Controller
public class BorderStationController {

	private static final String BORDERSTATIONS_ATTRIBUTE = "borderStations";
	
	private static final String LIST_BORDERSTATIONS_VIEW = "ListBorderStations";
	private static final String ADD_BORDERSTATION_VIEW = "AddBorderStation";
	private static final String UPDATE_BORDERSTATION_VIEW = "UpdateBorderStation";
	
	private static final String REDIRECT_TO_BORDERSTATION_LISTING =  "redirect:/borderStation/view";
	
	@Resource
	private BorderStationDao borderStationDao;

	@RequestMapping(value = "/borderStation/view", method = RequestMethod.GET)
	public String listAllBorderStations(Model model) {
		model.addAttribute(BORDERSTATIONS_ATTRIBUTE, borderStationDao.getBorderStations());
		return LIST_BORDERSTATIONS_VIEW;
	}

	@RequestMapping(value = "/borderStation/add", method = RequestMethod.GET)
	public String reportBorderStation(Model model) {
		model.addAttribute(new BorderStation());
		return ADD_BORDERSTATION_VIEW;
	}

	@RequestMapping(value = "/borderStation/add", method = RequestMethod.POST)
	public String receiveReportBorderStation(Model model,
			@ModelAttribute @Valid BorderStation borderStation, BindingResult result) {		
		
		if (result.hasErrors())  
			return ADD_BORDERSTATION_VIEW;
		
		borderStationDao.saveBorderStation(borderStation);
		return REDIRECT_TO_BORDERSTATION_LISTING;
	}
	
	@RequestMapping(value = "/borderStation/update", method = RequestMethod.GET)
	public String updateBorderStation(Model model,
			@RequestParam(required=true, value="id") Integer borderStationId) {		
		
		BorderStation borderStation = borderStationDao.searchBorderStation(borderStationId);
		
		if (borderStation == null) 
			return REDIRECT_TO_BORDERSTATION_LISTING;

		model.addAttribute(borderStation);
		return UPDATE_BORDERSTATION_VIEW;
	}
	
	@RequestMapping(value = "/borderStation/update", method = RequestMethod.POST)
	public String receiveUpdatedBorderStation(Model model,
			@ModelAttribute @Valid BorderStation borderStation, BindingResult result) {				
		
		if(result.hasErrors())
			return UPDATE_BORDERSTATION_VIEW;
		
		borderStationDao.updateBorderStation(borderStation);		
		return REDIRECT_TO_BORDERSTATION_LISTING;
	}
	
	@RequestMapping(value = "/borderStation/delete", method = RequestMethod.GET)
	public String deleteBorderStation(Model model,
			@RequestParam(required=true, value="id") Integer borderStationId) {		
		
		borderStationDao.deleteBorderStation(borderStationId);	
		return REDIRECT_TO_BORDERSTATION_LISTING;
	}
	
}
