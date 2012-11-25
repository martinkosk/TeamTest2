package ee.itcollege.borderproject.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	private static final String LOGIN_VIEW = "Login";
	private static final String REDIRECT_TO_GUARD_LISTING =  "redirect:/guard/view";


	@RequestMapping("/")
	public String index() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		        return REDIRECT_TO_GUARD_LISTING;
		}
		
		return LOGIN_VIEW;
	}
	
	@RequestMapping("/login")
	public String login() {
		return LOGIN_VIEW;
	}

	@RequestMapping(value = "/loginfailed")
	public String loginerror(Model model) {
		return LOGIN_VIEW;

	}

}
