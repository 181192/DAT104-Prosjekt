<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<div class="ui container">
	<form method="post" action="<%=LOGGINN_URL %>" class="ui form">
		<div class="field">
			<label>Mailadresse</label> <input type="text" name="mail" value="${mail}" placeholder="Mailadresse">
			<p><font color="red">${skjema.mailFeilmelding}</font></p>
			<label>Passord</label> <input type="password" name="passord" value="${passord}" placeholder="Passord">
			<p><font color="red">${skjema.passordFeilmelding}</font></p>
		</div>
		<button class="ui button">Logg inn</button>
	</form>
</div>

<jsp:include page="../partials/footer.jsp" />