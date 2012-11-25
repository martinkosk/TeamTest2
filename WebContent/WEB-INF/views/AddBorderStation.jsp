<%@ taglib tagdir="/WEB-INF/tags" prefix="pr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<pr:Layout>
	<h1>Add BorderStation</h1>
	<div class="hero-unit">

		<form:form action="add" method="POST" modelAttribute="borderStation">
			<table>
				<tr>
					<td><form:label path="name">Name</form:label></td>
					<td><form:input path="name" /></td>
					<td><form:errors path="name" /></td>
				</tr>
				<tr>
					<td><form:label path="address">Address</form:label></td>
					<td><form:input path="address" /></td>
					<td><form:errors path="address" /></td>
				</tr>
				<tr>
					<td><form:label path="guardCount">Guard count</form:label></td>
					<td><form:input path="guardCount" /></td>
					<td><form:errors path="guardCount" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Add BorderStation"
						class="btn btn-primary btn-larg" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</pr:Layout>