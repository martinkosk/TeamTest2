<%@ taglib tagdir="/WEB-INF/tags" prefix="pr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<pr:Layout>
	<h1>Report incident</h1>
	<div class="hero-unit">

		<form:form action="incident/reportIncident" method="POST"
			modelAttribute="incident">
			<table>
				<tr>
					<td><form:label path="start">Start</form:label></td>
					<td><form:input path="start" /></td>
					<td><form:errors path="start" /></td>
				</tr>
				<tr>
					<td><form:label path="end">End</form:label></td>
					<td><form:input path="end" /></td>
					<td><form:errors path="end" /></td>
				</tr>
				<tr>
					<td><form:label path="location">Location</form:label></td>
					<td><form:input path="location" /></td>
					<td><form:errors path="location" /></td>
				</tr>
				<tr>
					<td><form:label path="description">Description</form:label></td>
					<td><form:input path="description" /></td>
					<td><form:errors path="description" /></td>
				</tr>
				<tr>
					<td><form:label path="involvedGuardCount">Involved guard count</form:label></td>
					<td><form:input path="involvedGuardCount" /></td>
					<td><form:errors path="involvedGuardCount" /></td>
				</tr>
				<tr>
					<td><form:label path="status">Status</form:label></td>
					<td><form:input path="status" /></td>
					<td><form:errors path="status" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Add Incident"
						class="btn btn-primary btn-larg" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</pr:Layout>