package ee.itcollege.borderproject.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ee.itcollege.borderproject.dao.GuardDao;
import ee.itcollege.borderproject.dao.impl.GuardDaoJdbc;

@WebServlet("/updateGuard")
public class UpdateGuardController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String GUARD_ID_PARAMETER = "id";
	private static final String GUARD_NAME_PARAMETER = "name";
	private static final String GUARD_AGE_PARAMETER = "age";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		updateGuard(
			request.getParameter(GUARD_ID_PARAMETER),
			request.getParameter(GUARD_NAME_PARAMETER),
			request.getParameter(GUARD_AGE_PARAMETER));
	}
	
	private void updateGuard(String idString, String name, String ageString) {
		int age;
		int id;
		
		try {
			id = Integer.parseInt(idString);
		} 
		catch (NumberFormatException e) {
			return;
		}
		try {
			age = Integer.parseInt(ageString);
		} 
		catch (NumberFormatException e) {
			age = -1;
		}
		
		GuardDao guardDao = new GuardDaoJdbc();
		try{
			if (name == null && ageString == null)
				return;
			else if (name != null && ageString == null)
				guardDao.updateGuard(id, name);
			else if (name == null && ageString != null){
				if(age != -1) 
					guardDao.updateGuard(id, age);
			}
			else if (name != null && ageString != null){
				if(age != -1)
					guardDao.updateGuard(id, name, age);
			}
		}
		catch(SQLException e){
			return;
		}
	}

}