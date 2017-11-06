<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<div class="ui container">
	<h1>Opprett en ny bruker</h1>
	<form method="post" action="<%=OPPRETTBRUKER_URL %>" class="ui form">
		<div class="field"><label>Fornavn:</label> <input type="text" name="fornavn" value="${skjema.fornavn}" placeholder="Fornavn">
		<p><font color="red">${skjema.fornavnFeilmelding}</font></p></div>
	    <div class="field"><label>Eternavn:</label><input type="text" name="etternavn" value="${skjema.etternavn}" id="datepicker" placeholder="Etternavn"></div>
	    <p><font color="red">${skjema.etternavnFeilmelding}</font></p>
	    <div class="field"> <label>Mail:</label><input class="timepicker" type="text" name="mail" value="${skjema.mail}" placeholder="Mailadresse"></div>
      	<p><font color="red">${skjema.mailFeilmelding}</font></p>
      	<div class="field"><label>Passord:</label><input type="password" class="timepicker" name="passord" value="${skjema.passord}" placeholder="Passord"></div>
	  
	  <button class="ui button">Opprett bruker</button>
	</form>
</div>

<jsp:include page="../../partials/footer.jsp" />