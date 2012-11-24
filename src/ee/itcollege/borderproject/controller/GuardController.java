package ee.itcollege.borderproject.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@Resource
	private GuardDao guardDao;
	
	@RequestMapping(value = "/guard/generate", method = RequestMethod.GET)
	public String generateGuards(Model model, 
			@RequestParam(value="count") Integer generationCount) {
		List<Guard> generatedGuards = new GuardGenerator().generate(generationCount);	
		guardDao.saveGuards(generatedGuards);
		model.addAttribute(GUARDS_ATTRIBUTE, generatedGuards);
		model.addAttribute(FEEDBACK_ATTRIBUTE, "Generated and saved guards: ");
		
		return LIST_GUARDS_VIEW;
	}
	
	@RequestMapping(value = "/guard/search", method = RequestMethod.GET)
	public String searchGuards(Model model, 
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
	
	@RequestMapping(value = "/guard/update", method = RequestMethod.GET)
	public String updateGuard(Model model,
			@RequestParam(required=true, value="id") Integer guardId,
			@RequestParam(required=false, value="name") String updatedName, 
			@RequestParam(required=false, value="age") Integer updatedAge) {

		Guard updatedGuard = guardDao.searchGuard(guardId);
		
		if (updatedGuard != null) {
		
			if (updatedName != null)
				updatedGuard.setName(updatedName);
			if (updatedAge != null)
				updatedGuard.setAge(updatedAge);
			
			guardDao.updateGuard(updatedGuard);
			
			model.addAttribute(GUARDS_ATTRIBUTE, Arrays.asList(updatedGuard));
			model.addAttribute(FEEDBACK_ATTRIBUTE, "Updated guard: ");
		}
		else {
			model.addAttribute(FEEDBACK_ATTRIBUTE, String.format("Could not find guard with Id %s ", guardId));
		}
		
		return LIST_GUARDS_VIEW;
	}
	
}
