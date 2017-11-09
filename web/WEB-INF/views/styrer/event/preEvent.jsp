<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<%@ page import="static no.hvl.dat104.controller.Attributter.*"%>


<div class="ui container segment delta"
	style="margin-top: 10%; width: 60%;">

	<table class="ui fixed single line celled table">
		<thead>
			<tr>
				<th>Event navn</th>
				<th>Tidspunkt (planlagt start)</th>
				<th>Live tilbakemeldinger</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${eventer}" var="event">
				<tr>
					<td><c:out value="${event.navn}" /></td>
					<td
						class="${event.status eq PAAGANDE ? 'positive' : (event.status eq AVSLUTTET) ? 'error' : 'warning'}"><c:out
							value="${event.tidFra}" /></td>
					<td>
						<form action="preevent" method="post">
							<button class="fluid ui button" type="submit">${event.status eq PAAGAENDE ? 'event pågår nå' : 'Start event'}</button>
							<input type="hidden" name="liveeventid" value="${event.id}">
						</form>
					</td>
				</tr>
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