package ee.itcollege.borderproject.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import ee.itcollege.borderproject.util.GuardGenerator;

@WebServlet("/generateGuards")
public class GenerateGuardsController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String GUARD_COUNT_PARAMETER = "count";
	private static final String GENERATED_GUARDS_ATTRIBUTE = "generatedGuards";
	private static final String GENERATED_GUARDS_VIEW = "/ViewGeneratedGuards.jsp";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		try {
			int guardCount = Integer.parseInt(request.getParameter(GUARD_COUNT_PARAMETER));
            List<Guard> generatedGuards = new GuardGenerator().generate(guardCount);  
            
            GuardDao guardDao = new GuardDaoJdbc();
            try {
                guardDao.saveGuards(generatedGuards);
            } catch (SQLException e) {
            	return;
            }
            
            request.setAttribute(GENERATED_GUARDS_ATTRIBUTE, generatedGuards);
            
            RequestDispatcher dispatcher = 
            		  getServletContext().getRequestDispatcher(GENERATED_GUARDS_VIEW);
            dispatcher.forward(request, response);           
		} 
		catch (NumberFormatException e) {
			
		}
	}
		
}
