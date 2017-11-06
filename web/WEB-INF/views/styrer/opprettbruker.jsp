<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<div class="ui container">
	<h1>Opprett en ny bruker</h1>
	<form method="post" action="<%=OPPRETTBRUKER_URL %>" class="ui form">
		 <div class="field"><label>Fornavn:</label> <input type="text" name="foravn" placeholder="Fornavn">
		 <p><font color="red">${fornavn_feil}</font></p></div>
	    <div class="field"><label>Eternavn:</label><input type="text" name="etternavn" id="datepicker" placeholder="Etternavn"></div>
	    <div class="field"> <label>Mail:</label><input class="timepicker" type="text" name="mail" placeholder="Mailadresse"></div>
      <div class="field"><label>Passord:</label><input type="password" class="timepicker" name="passord" placeholder="Passord"></div>
	  
	  <button class="ui button">Opprett bruker</button>
	</form>
</div>

<jsp:include page="../../partials/footer.jsp" />