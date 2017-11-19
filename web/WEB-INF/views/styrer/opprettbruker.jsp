<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<div class="ui container">
	<h1>Opprett en ny bruker</h1>
	<c:if test="${flash == 'success'}">
		<div class="ui positive message">
			<div class="header">Suksess!</div>
			<p style="color: #016936;">${melding}</p>
		</div>
		<c:remove var="flash" scope="session" />
	</c:if>
	<c:if test="${flash == 'error'}">
		<div class="ui negative  message">
			<div class="header">Beklager, noe gikk galt!</div>
			<p style="color: #B03060;">${melding}</p>
		</div>
		<c:remove var="flash" scope="session" />
	</c:if>
	<form method="post" action="<%=OPPRETTBRUKER_URL%>" class="ui form">
		<div class="field">
			<label>Fornavn:</label> <input type="text" name="fornavn"
				value="${opprettBrukerSkjema.fornavn}" placeholder="Fornavn">
			<p>
				<font color="red">${opprettBrukerSkjema.fornavnFeilmelding}</font>
			</p>
		</div>
		<div class="field">
			<label>Etternavn:</label><input type="text" name="etternavn"
				value="${opprettBrukerSkjema.etternavn}" id="datepicker" placeholder="Etternavn">
		</div>
		<p>
			<font color="red">${opprettBrukerSkjema.etternavnFeilmelding}</font>
		</p>
		<div class="field">
			<label>Mail:</label><input class="timepicker" type="text" name="mail"
				value="${opprettBrukerSkjema.mail}" placeholder="Mailadresse">
		</div>
		<p>
			<font color="red">${opprettBrukerSkjema.mailFeilmelding}</font>
		</p>
		<div class="field">
			<label>Passord:</label><input type="password" class="timepicker"
				name="passord" value="${opprettBrukerSkjema.passord}" placeholder="Passord">
		</div>

		<button class="ui button">Opprett bruker</button>
	</form>
</div>

<jsp:include page="../../partials/footer.jsp" />