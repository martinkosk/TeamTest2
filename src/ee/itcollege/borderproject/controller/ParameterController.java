package ee.itcollege.borderproject.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ee.itcollege.borderproject.util.ParameterUtil;

@WebServlet("/request")
public class ParameterController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		ParameterUtil parameterUtil = new ParameterUtil();
		String parameterOutput = parameterUtil.generateParameterOutput(request);
		
		PrintWriter writer = response.getWriter();
		writer.write(parameterOutput);
		writer.close();
	}
}
