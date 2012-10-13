package ee.itcollege.borderproject.parameters;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class ParameterUtil {
	
	/**
	 * Generates string output from request parameters
	 * @param request - {@code HttpServletRequest}
	 * @return formatted parameter 'name: value' pairs
	 */
	public String generateParameterOutput(HttpServletRequest request) {
		Enumeration<String> parameters = request.getParameterNames();
		StringBuffer stringBuffer = new StringBuffer();
		
		while(parameters.hasMoreElements()) {
			String name = parameters.nextElement();
			String value = request.getParameter(name);
			
			stringBuffer.append(String.format("%s: %s\n", name, value));
		}
		
		return stringBuffer.toString();
	}
	
}
