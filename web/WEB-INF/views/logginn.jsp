<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<div class="ui container">
	<form method="post" action="<%=LOGGINN_URL %>" class="ui form">
		<div class="field">
			<label>Mailadresse</label> <input type="text" name="mail"
				placeholder="Mailadresse">
			<label>Mailadresse</label> <input type="password" name="passord"
				placeholder="Passord">
		</div>
		<button class="ui button">Lag aktivitet</button>
	</form>
</div>

<jsp:include page="../partials/footer.jsp" />