<%@ taglib tagdir="/WEB-INF/tags" prefix="pr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<pr:Layout>
	<h1>Guards</h1>
	<div class="hero-unit">
		<p>${feedbackMessage}</p>
		<table>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Age</th>
			</tr>
			<c:forEach items="${guards}" var="guard">
				<tr>
					<td>${guard.id}</td>
					<td>${guard.name}</td>
					<td>${guard.age}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</pr:Layout>