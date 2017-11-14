<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<jsp:include page="../../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<%@ page import="static no.hvl.dat104.controller.Attributter.*"%>
<%@ page import="static no.hvl.dat104.util.DatoUtil.*" %>
<%@ page import="static no.hvl.dat104.model.Status.*" %>
<jsp:useBean id="dato" class="no.hvl.dat104.util.DatoUtil" />
<c:set var="PAAGANDE" value="<%=PAAGANDE%>"></c:set>
<c:set var="PLANLAGT" value="<%=PLANLAGT%>"></c:set>

<h2 style="text-align:center; color:teal;">Pågående eventer: </h2>
<p style="text-align:center; color:teal;">Her kan du gå til live visningen av pågående eventer for å se real-time statistikk, eller starte eventer.</p>
<div class="ui container segment delta" style="width: 60%; margin-top: 50px;">
	
	
	<!-- Gå til livevisning av et startet event -->
	<c:choose>
	<c:when test="${visPaagande}">
	<h3 style="text-align:center;  color:teal;">Pågående eventer: </h3>
	<table class="ui fixed single line celled table">
		<thead>
			<tr>
				<th>Aktivitet</th>
				<th>Event</th>
				<th>Dato</th>
				<th>Tidspunkt (faktisk start)</th>
				<th>Live visning</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${eventer}" var="event" varStatus="counter">
				<c:if test="${event.status eq PAAGANDE}">
				<tr>
					<td><c:out value="${event.idAktivitet.navn}" /></td>
					<td><c:out value="${event.navn}" /></td>
					<td><c:out value="${fn:substring(dato.fraEngTilNorskDatov2(event.faktiskStart), 0, 10)}" /></td>
					<td
						class="positive"><c:out
							value="${fn:substring(event.faktiskStart, 10, 16)}" /></td>
					<td>
						<form action="<%=LIVE_EVENT_URL%>" method="get">
							<button class="fluid ui button" type="submit">Gå til live visning</button>
							<input type="hidden" name="liveeventid" value="${event.id}">
						</form>
					</td>
				</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	</c:when>
	<c:otherwise>
	<c:if test="${visPlanlagt}">	
		<h3 style="text-align:center;  color:teal;">Du har ingen pågående eventer, du kan starte en her: </h3>
	</c:if>
	</c:otherwise>
	</c:choose>
	
	<!-- Starte eventer -->
	<c:choose>
	<c:when test="${visPlanlagt}">
	<c:if test="${visPaagande}">
	<h3 style="text-align:center;  color:teal;">Planlagte eventer: </h3>
	</c:if>
	<table class="ui fixed single line celled table">
		<thead>
			<tr>
				<th>Aktivitet</th>
				<th>Event</th>
				<th>Dato</th>
				<th>Tidspunkt (planlagt)</th>
				<th>Tving eventet i gang</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${eventer}" var="event">
			<c:if test="${event.status eq PLANLAGT}">
				<tr>
					<td><c:out value="${event.idAktivitet.navn}" /></td>
					<td><c:out value="${event.navn}" /></td>
					<td><c:out value="${fn:substring(dato.fraEngTilNorskDatov2(event.tidFra), 0, 10)}" /></td>
					<td
						class="warning"><c:out
							value="${fn:substring(event.tidFra, 10, 16)}" /></td>
					<td>
						<form action="<%=PRE_LIVE_EVENT_URL%>" method="post">
							<button class="fluid ui button" type="submit">Start event</button>
							<input type="hidden" name="liveeventid" value="${event.id}">
						</form>
					</td>
				</tr>
			</c:if>
			</c:forEach>
		</tbody>
	</table>
	</c:when>
	<c:otherwise>
	<c:choose>
		<c:when test="${harAvsluttet and visPaagande eq false}">
			<h3 style="text-align:center;  color:teal;">Alle eventene dine er avsluttet, lag en ny for å måle tilbakemeldinger.</h3>
		</c:when>
		<c:otherwise>
		<c:choose>
			<c:when test="${visPaagande}">
				<h3 style="text-align:center;  color:teal;">Alle eventene dine er i gang!</h3>
			</c:when>
			<c:otherwise>
				<h3 style="text-align:center;  color:teal;">Du har ingen eventer, trykk <a href="<%=LAGEVENT_URL%>?dato=<%=lagCurrentDate()%>">her</a> for å lage en.</h3>
			</c:otherwise>
		</c:choose>
		</c:otherwise>
	</c:choose>
	</c:otherwise>
	</c:choose>
	<div class="ui form warning">

		<div class="ui divider"></div>
		<div class="ui red warning message">
			<p></p>
			<p>Starter du eventet blir pin-koden generert, og publikum kan gi
				tilbakemeldinger.</p>
			<p>
				Denne handlingen kan <strong>ikke </strong>omgjøres.
			</p>
		</div>
	</div>
</div>

<jsp:include page="../../../partials/footer.jsp" />