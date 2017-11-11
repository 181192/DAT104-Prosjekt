<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<%@ page import="static no.hvl.dat104.controller.Attributter.*"%>
<%@ page import="static no.hvl.dat104.model.Status.*" %>
<c:set var="PAAGANDE" value="<%=PAAGANDE%>"></c:set>
<c:set var="PLANLAGT" value="<%=PLANLAGT%>"></c:set>

<div class="ui container segment delta"
	style="margin-top: 10%; width: 60%;">
	
	<!-- Gå til livevisning av et startet event -->
	<table class="ui fixed single line celled table">
		<thead>
			<tr>
				<th>Event navn</th>
				<th>Tidspunkt (faktisk start)</th>
				<th>Live visning</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${eventer}" var="event">
				<c:if test="${event.status eq PAAGANDE}">
				<tr>
					<td><c:out value="${event.navn}" /></td>
					<td
						class="positive"><c:out
							value="${event.faktiskStart}" /></td>
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
	
	<!-- Starte eventer -->
	<table class="ui fixed single line celled table">
		<thead>
			<tr>
				<th>Event navn</th>
				<th>Tidspunkt (planlagt start)</th>
				<th>Tving eventet i gang</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${eventer}" var="event">
			<c:if test="${event.status eq PLANLAGT}">
				<tr>
					<td><c:out value="${event.navn}" /></td>
					<td
						class="warning"><c:out
							value="${event.tidFra}" /></td>
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