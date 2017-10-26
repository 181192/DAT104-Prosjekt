<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../../partials/header.jsp" />
<p>LIVE EVENT JSP1</p>

<button id="start-stopp">Start</button>
<br>
<br>
<div id="chart_div" style="width: 1250px; height: 500px;"></div>


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
	google.charts.setOnLoadCallback(tegnDiagram);
</script>

<jsp:include page="../../../partials/footer.jsp" />