<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<%@ page import="static no.hvl.dat104.controller.Attributter.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../../../partials/header.jsp" />
<c:set var="e" scope="request" value="${eventsend}" />
<c:set var="k" scope="request" value="${koden}" />
<c:set var="flt" scope="session" value="${liveTilbakemeldinger}" />



<div class="ui container">
	<h1 style="color:teal;">${e.navn }</h1>
	<div class="ui tiny modal" id="avsluttModal">
		<div class="header">Avslutt event</div>
		<div class="content">
			<p>Ønsker du å avslutte eventet?</p>
		</div>
		<div class="actions">
			<div class="ui items">
				<div class="item">
					<form action=<%=LIVE_EVENT_URL%> method="post">
						<button type="submit" class="ui green button">Bekreft</button>
						<input type="hidden" name="liveeventknapp" value="avslutt">
						<input type="hidden" name="eventId" value="${e.id}">
					</form>
				</div>
				<div class="item">
					<button class="ui red cancel button">Avbryt</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Denne inneholder grafen over liveevents -->
	<c:if test = "${not empty flt}">
		<div id="chart_div"></div>
	</c:if>

	<c:if test = "${empty flt}">
		<h3 class="ui header">Ingen tilbakemeldinger.</h3>
	</c:if>
	
	<div class="ui divider"></div>
	<button class="ui labeled icon button" id="start-stopp"
		onclick="lagBekreftAvsluttmodal()">Avslutt event</button>
	
</div>

<script type="text/javascript">	
	var pinContainer = document.getElementById('nav-textfelt');
	pinContainer.innerText = 'PIN: ${k.kode}';
	
	function lagBekreftAvsluttmodal() {
		$("#avsluttModal").modal('show');
	};
</script>

<!-- Chart script -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">	
	google.charts.load('current', {
		'packages' : [ 'corechart', 'bar' ]
	});

	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Tidsinterval på 5 min');
		data.addColumn('number', 'Fornøyd');
		data.addColumn('number', 'Nøytral');
		data.addColumn('number', 'Misfornøyd');

		data.addRows([				
		<c:forEach items="${flt}" var="t">
			["${t.tid}", ${t.fornoyd}, ${t.noytral}, ${t.misfornoyd}],
		</c:forEach>
			]);

		var view = new google.visualization.DataView(data);
		view.setColumns([ 0, 1, 2, 3]);

		var options = {
			height : 400,

			series : {
				0 : {
					type : 'bars',
					color : 'green'
				},
				1 : {
					type : 'bars',
					color : 'yellow'
				},
				2 : {
					type : 'bars',
					color : 'red'
				}
			},
			vAxis : {
				format : ""
			},
			hAxis : {
				title : "Tilbakemeldinger",
				format : ""
			}
		};
		var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
		chart.draw(view, options);
	}	
</script>
<jsp:include page="../../../partials/footer.jsp" />