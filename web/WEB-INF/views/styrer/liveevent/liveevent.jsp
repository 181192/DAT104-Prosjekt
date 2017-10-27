<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../../partials/header.jsp" />

<button class="ui labeled icon button" id="start-stopp">
	<i class="pause icon"></i> Stopp
</button>
<button class="ui right labeled icon button" id="forlengKnapp">
	<i class="right arrow icon"></i> Forleng
</button>

<div id="chart_div" style="width: 1250px; height: 500px;"></div>
<div class="ui divider"></div>
<div class="ui buttons">
	<button class="ui red basic button" id="daarligKnapp"
		onclick="daarligKnappHandeler()">Daarlig</button>
	<button class="ui blue basic button" id="middelsKnapp"
		onclick="middelsKnappHandeler()">Middels</button>
	<button class="ui green basic button" id="braKnapp"
		onclick="godKnappHandeler()">Bra</button>
</div>

<script type="text/javascript">
<!-->Må hente request attributten her i jsp siden, så kan man inkludere resten av scripten.</-->
	var frekvensTabell = "${requestScope.data}";
</script>

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script src="public/js/liveEvent.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart', 'bar' ]
	});
	var frekvensTabell = "${requestScope.dummyData}";
	var klargjortData = vaskData(frekvensTabell, 15, 4);
	console.log(klargjortData);
	google.charts.setOnLoadCallback(tegnDiagram);
</script>

<jsp:include page="../../../partials/footer.jsp" />