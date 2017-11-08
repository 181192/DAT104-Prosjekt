<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<div class="ui container">
	<form method="post" action="<%=LAGAKTIVITET_URL%>" class="ui form">
		<div class="field">
			<label>Tittel:</label> <input type="text" name="tittel"
				placeholder="Tittel">
		</div>
		<div class="field">
			<label>Status</label> <select name="status">
				<option value="planlagt">Planlagt</option>
				<option value="pagaende">Pågående</option>
				<option value="avsluttet">Avsluttet</option>
			</select>
		</div>
		<button class="ui button">Lag aktivitet</button>
	</form>
</div>

<jsp:include page="../../../partials/footer.jsp" />