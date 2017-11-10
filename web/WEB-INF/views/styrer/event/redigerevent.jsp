<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<jsp:include page="../../../partials/header.jsp" />

<fmt:parseDate value="${event.tidFra}" var="datoFra"
	pattern="yyyy-MM-dd HH:mm:ss" />
<fmt:formatDate value="${datoFra}" var="parsedDate" pattern="dd-MM-yyyy" />
<fmt:formatDate value="${datoFra}" var="parsedTimeFra" pattern="HH-mm" />

<fmt:parseDate value="${event.tidTil}" var="datoTil"
	pattern="yyyy-MM-dd HH:mm:ss" />
<fmt:formatDate value="${datoTil}" var="parsedTimeTil" pattern="HH-mm" />

<div class="ui container">
	<h3>Rediger event</h3>
	<p>Her er ditt event ${event.navn}</p>
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


	<form class="ui form" method="post" action="<%=REDIGEREVENT_URL%>">

		<!-- Ta vare på event ID -->
		<input type="hidden" name="id" value="${event.id}">

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
		<!-- Endre tid og dato -->

		<div class="field" id="datoen">
			<label>Dato:</label><input type="text" name="dato" id="datepicker"
				value="${parsedDate}">
		</div>
		<div class="two fields">
			<div class="field">
				<label>Fra:</label><input class="timepicker" type="text" name="fra"
					value="${parsedTimeFra}" placeholder="hh:mm">
			</div>
			<div class="field">
				<label>Til:</label><input class="timepicker" type="text" name="til"
					value="${parsedTimeTil}" placeholder="hh:mm">
			</div>
		</div>

		<!-- Endre status -->
		<div class="field">
			<label>Status</label> <select name="status">
				<option ${event.status eq 'pagaende' ? 'selected="selected"' : ''}
					value="pagaende">Pågående</option>
				<option ${event.status eq 'planlagt' ? 'selected="selected"' : ''}
					value="planlagt">Planlagt</option>
				<option ${event.status eq 'avsluttet' ? 'selected="selected"' : ''}
					value="avsluttet">Avsluttet</option>
			</select>
		</div>

		<!-- Endre sted -->
		<div class="field">
			<label>Sted</label> <input type="text" name="sted"
				value="${event.sted}">
		</div>

		<button class="ui button" type="submit">Submit</button>
	</form>
</div>
<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : "dd.mm.yy"
		});
		$('.timepicker').timepicker({
			'scrollDefault' : 'now',
			'timeFormat' : 'H:i',
			'step' : 15
		});
		$('.aktivitet').dropdown();
	});
</script>
<jsp:include page="../../../partials/footer.jsp" />