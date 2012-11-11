<%@ taglib tagdir="/WEB-INF/tags" prefix="pr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<pr:Layout>
	<h1>Incidents</h1>
	<div class="hero-unit">
		<table>
			<tr>
				<th>Start</th>
				<th>End</th>
				<th>Location</th>
				<th>Description</th>
				<th>Count of involved guards</th>
				<th>Status</th>
			</tr>
			<c:forEach items="${incidents}" var="incident">
				<tr>
					<td>${incident.start}</td>
					<td>${incident.end}</td>
					<td>${incident.location}</td>
					<td>${incident.description}</td>
					<td>${incident.involvedGuardCount}</td>
					<td>${incident.status}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</pr:Layout>