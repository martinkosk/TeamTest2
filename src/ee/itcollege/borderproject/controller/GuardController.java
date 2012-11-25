package ee.itcollege.borderproject.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ee.itcollege.borderproject.dao.GuardDao;
import ee.itcollege.borderproject.model.Guard;
import ee.itcollege.borderproject.util.GuardGenerator;

@Controller
public class GuardController {

	private static final String FEEDBACK_ATTRIBUTE = "feedbackMessage";
	private static final String GUARDS_ATTRIBUTE = "guards";
	
	private static final String LIST_GUARDS_VIEW = "ListGuards";
	private static final String ADD_GUARD_VIEW = "AddGuard";
	private static final String UPDATE_GUARD_VIEW = "UpdateGuard";
	
	private static final String REDIRECT_TO_GUARD_LISTING =  "redirect:/guard/view";

	
	@Resource
	private GuardDao guardDao;
	
	@RequestMapping(value = "/guard/view", method = RequestMethod.GET)
	public String viewGuards(Model model, 
			@RequestParam(required=false, value="name") String searchedName, 
			@RequestParam(required=false, value="age") Integer searchedAge) {
		
		List<Guard> searchedGuards;
		
		if (searchedName == null && searchedAge == null)
			searchedGuards = guardDao.getGuards();	
		else if (searchedName != null && searchedAge == null)
			searchedGuards =  guardDao.searchGuards(searchedName);		
		else if (searchedName == null && searchedAge != null)
			searchedGuards =  guardDao.searchGuards(searchedAge);		
		else
			searchedGuards = guardDao.searchGuards(searchedName, searchedAge);
		
		model.addAttribute(GUARDS_ATTRIBUTE, searchedGuards);
		return LIST_GUARDS_VIEW;
	}
	
	@RequestMapping(value = "/guard/add", method = RequestMethod.GET)
	public String addGuard(Model model) {
		model.addAttribute(new Guard());
		return ADD_GUARD_VIEW;
	}

	@RequestMapping(value = "/guard/add", method = RequestMethod.POST)
	public String receiveAddedGuard(Model model,
			@ModelAttribute @Valid Guard guard, BindingResult result) {		
		
		if (result.hasErrors())  
			return ADD_GUARD_VIEW;
		
		guardDao.saveGuard(guard);
		return REDIRECT_TO_GUARD_LISTING;
	}
	
	@RequestMapping(value = "/guard/update", method = RequestMethod.GET)
	public String updateGuard(Model model,
			@RequestParam(required=true, value="id") Integer guardId) {		
		
		Guard guard = guardDao.searchGuard(guardId);
		
		if (guard == null) 
			return REDIRECT_TO_GUARD_LISTING;

		model.addAttribute(guard);
		return UPDATE_GUARD_VIEW;
	}
	
	@RequestMapping(value = "/guard/update", method = RequestMethod.POST)
	public String receiveUpdatedGuard(Model model,
			@ModelAttribute @Valid Guard guard, BindingResult result) {				
		
		if(result.hasErrors())
			return UPDATE_GUARD_VIEW;
		
		guardDao.updateGuard(guard);		
		return REDIRECT_TO_GUARD_LISTING;
	}
	
	@RequestMapping(value = "/guard/delete", method = RequestMethod.GET)
	public String deleteGuard(Model model,
			@RequestParam(required=true, value="id") Integer guardId) {		
		
		guardDao.deleteGuard(guardId);	
		return REDIRECT_TO_GUARD_LISTING;
	}
	
	@RequestMapping(value = "/guard/generate", method = RequestMethod.GET)
	public String generateGuards(Model model, 
			@RequestParam(value="count") Integer generationCount) {
		
		List<Guard> generatedGuards = new GuardGenerator().generate(generationCount);	
		guardDao.saveGuards(generatedGuards);
		
		model.addAttribute(GUARDS_ATTRIBUTE, generatedGuards);
		model.addAttribute(FEEDBACK_ATTRIBUTE, "Generated and saved guards: ");	
		return LIST_GUARDS_VIEW;
	}
	
}
