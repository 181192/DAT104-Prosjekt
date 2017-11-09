<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>


<div class="ui container segment delta"
	style="margin-top: 10%; width: 60%;">

	<table class="ui fixed single line celled table">
		<thead>
			<tr>
				<th>Event navn</th>
				<th>Tidspunkt (planlagt start)</th>
				<th>Gå til event</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${eventer}" var="event">
				<tr>
					<td><a href="<%= EVENTRESULTATER_URL%>?eventId=${event.id}"><c:out
								value="${event.navn}" /></a></td>
					<td
						class="${event.status eq PAAGANDE ? 'positive' : (event.status eq AVSLUTTET) ? 'error' : 'warning'}"><c:out
							value="${event.status}" /></td>
					<td><a href="<%= LIVE_EVENT_URL%>?eventId=${event.id}">Rediger</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<form action="preevent" method="post">
		<div class="ui form warning">

			<div class="ui divider"></div>
			<div class="ui red warning message">
				<div class="ui medium header">Advarsel</div>
				<p></p>
				<p>Starter du eventet blir pin-koden generert, og publikum kan
					gi tilbakemeldinger.</p>
				<p>
					Denne handlingen kan <strong>ikke </strong>omgjøres.
				</p>
			</div>
			<input type="submit" class="ui primary button" value="Start Event">
		</div>
	</form>
</div>

<jsp:include page="../../../partials/footer.jsp" />