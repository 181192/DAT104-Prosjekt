<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../../partials/header.jsp" />
<p>Rediger event</p>
<p>Her er din event ${eventId}</p>

<form class="ui form">

	<!-- Endre navn på event -->
	<div class="field">
		<label>Event</label> <input type="text" name="navn"
			value="${event.navn}">
	</div>

	<!-- Endre beskrivelse -->
	<div class="field">
		<label>Beskrivelse</label> <input type="text" name="beskrivelse"
			value="${event.beskrivelse}">
	</div>

	<!-- Endre status -->
	<div class="field">
		<label>Status</label> <input type="text" name="status"
			value="${event.status}">
	</div>

	<!-- Endre sted -->
	<div class="field">
		<label>Sted</label> <input type="text" name="sted"
			value="${event.sted}">
	</div>

	<button class="ui button" type="submit">Submit</button>
</form>

<jsp:include page="../../../partials/footer.jsp" />