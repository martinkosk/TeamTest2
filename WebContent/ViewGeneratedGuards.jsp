<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generated guards</title>
</head>
<body>
	<p>Generated and saved guards: <br /> </p>
	<table>
		<c:forEach items="${generatedGuards}" var="guard" varStatus="i">
			<tr>
				<td>${i.count} &nbsp;</td>
				<td>Name: ${guard.name} &nbsp; &nbsp;</td>
				<td>Age: ${guard.age}</td>
			</tr> 
		</c:forEach>
	</table>
</body>
</html>