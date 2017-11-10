<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../../partials/header.jsp" />
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
	<form method="post" action="<%=LAGAKTIVITET_URL%>" class="ui form">
		<div class="field">
			<label>Tittel <span style="color:#B03060">${skjema.tittelFeilmelding}</span></label> <input type="text" name="tittel" value="${skjema.tittel}"
				placeholder="Tittel">
		</div>
		<div class="field">
			<label>Status <span style="color:#B03060">${skjema.statusFeilmelding}</span></label> <select name="status">
				<option value="planlagt">Planlagt</option>
				<option value="pagaende">Pågående</option>
				<option value="avsluttet">Avsluttet</option>
			</select>
		</div>
		<button class="ui button">Lag aktivitet</button>
	</form>
</div>

<jsp:include page="../../../partials/footer.jsp" />