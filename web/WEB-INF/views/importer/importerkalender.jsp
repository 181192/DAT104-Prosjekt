<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>

<div class="ui centered grid container">
	<div style="margin-top: 2em;" class="ui segment column">
		<h1 class="header">Importer kalender</h1>
		<form action="<%=IMPORTERKALENDER_URL%>" method="post">
			<div class="ui form warning">
				<div class="field">
					<label>Link fra timeedit</label> <input type="text" placeholder="Url"
						name="timeediturl">
				</div>
				<c:if test="${flash == 'error'}">
					<div class="ui negative  message">
						<div class="header">Beklager, noe gikk galt!</div>
						<p style="color: red;">${melding}</p>
					</div>
					<c:remove var="flash" scope="session" />
				</c:if>
				<c:if test="${flash=='Success'}">
					<div style="margin-bottom: 2em;" class="ui info message">${melding}</div>
					<c:remove var="flash" scope="session" />
				</c:if>
				<input type="submit" class="ui teal button" value="Legg til kalender">
			</div>
		</form>
	</div>
</div>

<jsp:include page="../../partials/footer.jsp" />