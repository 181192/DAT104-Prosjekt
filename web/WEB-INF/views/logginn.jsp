<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<div class="ui container">
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
	<p>
		<font color="#B03060">${ikkeGodkjent}</font>
	</p>
	<form method="post" action="<%=LOGGINN_URL%>" class="ui form">
		<div class="field">
			<label>Mailadresse</label> <input type="text" name="mail"
				value="${brukerSkjema.mail}" placeholder="Mailadresse">
			<p>
				<font color="#B03060">${brukerSkjema.mailFeilmelding}</font>
			</p>
		</div>
		<div class="field">
			<label>Passord</label> <input type="password" name="passord"
				placeholder="Passord">
			<p>
				<font color="#B03060">${brukerSkjema.passordFeilmelding}</font>
			</p>
		</div>
		<button class="ui button">Logg inn</button>
	</form>
</div>

<jsp:include page="../partials/footer.jsp" />