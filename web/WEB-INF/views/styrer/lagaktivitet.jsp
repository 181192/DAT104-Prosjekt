<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../partials/header.jsp" />

<div class="ui container">
	<form method="post" action="/" class="ui form">
		<div class="field">
			<label>Tittel:</label> <input type="text" name="tittel"
				placeholder="Tittel">
		</div>
		<button class="ui button">Lag aktivitet</button>
	</form>
</div>

<jsp:include page="../../partials/footer.jsp" />