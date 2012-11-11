<%@ taglib tagdir="/WEB-INF/tags" prefix="pr"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<pr:Layout>
	<h1>Reported incident</h1>
	<div class="hero-unit">
		<table>
			<tr>
				<td>Asukoht: </td>
				<td><c:out value="${incident.location}"/></td>
			</tr>
			<tr>
				<td>Kirjeldus: </td>
				<td><c:out value="${incident.description}"/></td>
			</tr>
			<tr>
				<td>Valvureid: </td>
				<td><c:out value="${incident.involvedGuardCount}"/></td>
			</tr>
			<tr>
				<td>Staatus: </td>
				<td><c:out value="${incident.status}"/></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><a href='<c:url value="/incident/reportIncident"/>' class="btn btn-primary btn-larg">Lisa uus</a></td>
			</tr>
		</table>
	</div>
</pr:Layout>