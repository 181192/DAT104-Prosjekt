<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>

<div class="ui container">
	<h3>Brukere</h3>
	<p><font color="red">${error}</font></p>
	<p><font color="red">${success}</font></p>
	<form action="<%=ADMINISTRER_URL %>" method="POST" id="brukere">
	<input type="hidden" id="id" name="id" />
	<input type="hidden" id="rolle" name="rolle" />
	<table class="ui fixed single line celled table">
		<thead>
			<tr>
				<th>Navn</th>
				<th>Epost</th>
				<th>Rolle</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${brukere}" var="b">
				<tr>
					<td><c:out value="${b.etternavn}"></c:out>, <c:out value="${b.fornavn}"></c:out></td>
					<td><c:out value="${b.mail}"></c:out></td>
					<td>
						<div class="ui buttons">
							<input type="button" class="ui ${b.idRolle.id eq 1 ? 'active' : ''} button" value="Admin" onclick="postAD(${b.id});"></input>
							<input type="button" class="ui ${b.idRolle.id eq 2 ? 'active' : ''} button" value="Aktivitetsstyrer" onclick="postAS(${b.id});"></input>
							<input type="button" class="ui ${b.idRolle.id eq 3 ? 'active' : ''} button" value="Ikke godkjent" onclick="postIG(${b.id});"></input>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</form>
</div>
<script type="text/javascript">
function postAD(p) {
	document.getElementById('id').value=p;
	document.getElementById('rolle').value='1';
	document.getElementById('brukere').submit();
}

function postAS(p) {
	document.getElementById('id').value=p;
	document.getElementById('rolle').value='2';
	document.getElementById('brukere').submit();
}

function postIG(p) {
	document.getElementById('id').value=p;
	document.getElementById('rolle').value='3';
	document.getElementById('brukere').submit();
}
</script>
<jsp:include page="../../partials/footer.jsp" />