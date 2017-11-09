<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<div class="ui container">
	<p><font color="red">${ikkeGodkjent}</font></p>
	<form method="post" action="<%=LOGGINN_URL %>" class="ui form">
		<div class="field"><label>Mailadresse</label> <input type="text" name="mail" value="${skjema.mail}" placeholder="Mailadresse">
		<p><font color="red">${skjema.mailFeilmelding}</font></p></div>
		<div class="field"><label>Passord</label> <input type="password" name="passord" placeholder="Passord">
		<p><font color="red">${skjema.passordFeilmelding}</font></p></div>
		<button class="ui button">Logg inn</button>
	</form>
</div>

<jsp:include page="../partials/footer.jsp" />