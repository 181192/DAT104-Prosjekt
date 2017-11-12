<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<jsp:include page="../../partials/header.jsp" />
<div class="ui container">
	<h1>Rediger bruker</h1>
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
	<form method="post" action="<%=REDIGERBRUKER_URL%>" class="ui form">
		<div class="field">
			<label>Fornavn:</label> <input type="text" name="fornavn"
				value="${empty redigerBrukerSkjema.fornavn ? bruker.fornavn : redigerBrukerSkjema.fornavn}"
				placeholder="Fornavn">
			<c:if test="${not empty redigerBrukerSkjema.fornavnFeilmelding}">
				<div class="ui negative  message">
					<div class="header">Beklager, noe gikk galt!</div>
					<p style="color: #B03060;">${redigerBrukerSkjema.fornavnFeilmelding}</p>
				</div>
				<c:remove var="brukerSkjema" scope="session" />
			</c:if>
		</div>
		<div class="field">
			<label>Etternavn:</label><input type="text" name="etternavn"
				value="${empty redigerBrukerSkjema.etternavn ? bruker.etternavn : redigerBrukerSkjema.etternavn}"
				placeholder="Etternavn">
			<c:if test="${not empty redigerBrukerSkjema.etternavnFeilmelding}">
				<div class="ui negative  message">
					<div class="header">Beklager, noe gikk galt!</div>
					<p style="color: #B03060;">${redigerBrukerSkjema.etternavnFeilmelding}</p>
				</div>
				<c:remove var="brukerSkjema" scope="session" />
			</c:if>
		</div>
		<div class="field">
			<label>Mail:</label><input type="text" name="mail"
				value="${empty redigerBrukerSkjema.mail ? bruker.mail : redigerBrukerSkjema.mail}"
				placeholder="Mailadresse">
			<c:if test="${not empty redigerBrukerSkjema.mailFeilmelding}">
				<div class="ui negative  message">
					<div class="header">Beklager, noe gikk galt!</div>
					<p style="color: #B03060;">${redigerBrukerSkjema.mailFeilmelding}</p>
				</div>
				<c:remove var="brukerSkjema" scope="session" />
			</c:if>
		</div>
		<div class="field">
			<label>Gammelt passord:</label><input type="password"
				name="gammeltpassord" placeholder="Passord">
		</div>
		<div class="field">
			<label>Nytt passord:</label><input type="password" name="nyttpassord"
				placeholder="Passord">
		</div>

		<button class="ui button">Oppdater bruker</button>
	</form>
</div>

<jsp:include page="../../partials/footer.jsp" />