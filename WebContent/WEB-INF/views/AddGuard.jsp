<%@ taglib tagdir="/WEB-INF/tags" prefix="pr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<pr:Layout>
	<h1>Add Guard</h1>
	<div class="hero-unit">

		<form:form action="add" method="POST"
			modelAttribute="guard">
			<table>
				<tr>
					<td><form:label path="name">Name</form:label></td>
					<td><form:input path="name" /></td>
					<td><form:errors path="name" /></td>
				</tr>
				<tr>
					<td><form:label path="age">Age</form:label></td>
					<td><form:input path="age" /></td>
					<td><form:errors path="age" /></td>
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