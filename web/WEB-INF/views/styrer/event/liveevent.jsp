<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<jsp:include page="../../../partials/header.jsp" />


<div class="ui container">


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
					</form>
				</div>
				<div class="item">
					<button class="ui red cancel button">Avbryt</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Denne inneholder grafen over liveevents -->
	<div id="chart_div" style="width: 100%; height: 600px;"></div>

	<div class="ui divider"></div>
		<button class="ui labeled icon button" id="start-stopp"
		onclick="lagBekreftAvsluttmodal()">Avslutt event</button>
	<div class="ui buttons">
		<button class="ui red basic button" id="daarligKnapp"
			onclick="lagBekreftAvsluttmodal()">Avslutt event</button>
		<button class="ui blue basic button" id="middelsKnapp"
			onclick="middelsKnappHandeler()">Middels</button>
		<button class="ui green basic button" id="braKnapp"
			onclick="godKnappHandeler()">Bra</button>
	</div>
</div>


<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script src="public/js/liveEvent.js"></script>

<script type="text/javascript">
	var pinContainer = document.getElementById('nav-textfelt');
	pinContainer.innerText = 'PIN: ${ sessionScope.kodeord.kode }';

	function lagBekreftAvsluttmodal() {
		$("#avsluttModal").modal('show');
	}

	google.charts.load('current', {
		'packages' : [ 'corechart', 'bar' ]
	});
	var frekvensTabell = "${requestScope.dummyData}";
	var klargjortData = vaskData(frekvensTabell, 15, 4);

	google.charts.setOnLoadCallback(tegnDiagram);
</script>

<jsp:include page="../../../partials/footer.jsp" />