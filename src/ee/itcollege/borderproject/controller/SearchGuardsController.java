package ee.itcollege.borderproject.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ee.itcollege.borderproject.dao.GuardDao;
import ee.itcollege.borderproject.dao.impl.GuardDaoJdbc;
import ee.itcollege.borderproject.model.Guard;

@WebServlet("/searchGuard")
public class SearchGuardsController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String GUARD_NAME_PARAMETER = "name";
	private static final String GUARD_AGE_PARAMETER = "age";
	
	private static final String GUARDS_PARAMETER = "guards";
	private static final String GUARDS_VIEW = "/ViewGuards.jsp";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		List<Guard> guards = getSearchedGuards(
				request.getParameter(GUARD_NAME_PARAMETER), 
				request.getParameter(GUARD_AGE_PARAMETER));
		
		request.setAttribute(GUARDS_PARAMETER, guards);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(GUARDS_VIEW);
		dispatcher.forward(request, response); 
	}
	
	private List<Guard> getSearchedGuards(String name, String ageString) {
		GuardDao guardDao = new GuardDaoJdbc();
		int age;
		
		try {
			age = Integer.parseInt(ageString);
		} catch (NumberFormatException e) {
			age = 0;
		}	
		
		try {
			if (name == null && ageString == null)
				return guardDao.getGuards();
			
			else if (name != null && ageString == null)
				return guardDao.searchGuards(name);
			
			else if (name == null && ageString != null)
				return guardDao.searchGuards(age);
			
			else
				return guardDao.searchGuards(name, age);
		}
		catch(SQLException e) {
			e.printStackTrace();
			return new ArrayList<Guard>();
		}
	}
}
