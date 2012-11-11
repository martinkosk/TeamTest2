<%@ taglib tagdir="/WEB-INF/tags" prefix="pr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<pr:Layout>
	<h1>Report incident</h1>
	<div class="hero-unit">
		<form method="post" action="reportIncident">
			<table>
				<tr>
					<td><label>Start</label></td>
					<td><input type="text" name="start"></td>
				</tr>
				<tr>
					<td><label>End</label></td>
					<td><input type="text" name="end"></td>
				</tr>
				<tr>
					<td><label>Location</label></td>
					<td><input type="text" name="location"></td>
				</tr>
				<tr>
					<td><label>Description</label></td>
					<td><input type="text" name="description"></td>
				</tr>
				<tr>
					<td><label>Count of involved guards</label></td>
					<td><input type="text" name="involvedGuardCount"></td>
				</tr>
				<tr>
					<td><label>Status</label></td>
					<td><input type="text" name="status"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Add Incident"
						class="btn btn-primary btn-larg" /></td>
				</tr>
			</table>
		</form>
	</div>
</pr:Layout>