<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="borderStation" class="ee.itcollege.borderproject.model.BorderStation" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Piiripunkti lisamine</title>
	</head>
	<body>
		<form method="post" action="DisplayBorderStation.jsp">
		<h2>Lisa piiripunkt</h2>
			<table>
				<tr>
					<td>Nimetus:</td>
					<td><input type="text" name="name" value="${borderStation.name}"/></td>
				</tr>
				<tr>
					<td>Aadress:</td>
					<td><input type="text" name="address" value="${borderStation.address}"/></td>
				</tr>
				<tr>
					<td>Valvurite arv:</td>
					<td><input type="text" name="guardCount" value="${borderStation.guardCount}"/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><button type="submit">Lisa piiripunkt</button></td>
				</tr>
			</table>
		</form>	
	</body>
</html>