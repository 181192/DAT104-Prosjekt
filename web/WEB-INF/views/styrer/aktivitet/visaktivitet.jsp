<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link rel="stylesheet" href="style.css">
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script src="public/js/charts/aktivitetsChart.js"></script>
<script>
	<!-->https://stackoverflow.com/questions/19036522/how-to-copy-multidimensional-array-from-java-request-variable-to-javascript-vari<-->
	var hentData = new Array();
	<c:forEach var="row" items="${arrayMedTilbakemeldinger}">
		hentData.push(['${row[0]}', '${row[1]}', '${row[2]}', '${row[3]}']);
	</c:forEach>
</script>
</head>
<body>
	<jsp:include page="../../../partials/header.jsp" />
	<h1>Tilbakemeldinger for ${aktivitetsNavn}</h1>
	
	<div id="chart_div"
		style="width: 900px; height: 500px"></div>
	<button class="ui blue basic button" id="refresh" onclick="refresh()">Oppdater</button>
	<button class="ui green basic button" id="refresh"
		onclick="toggleColumn(1)">Toggle fornøyelse</button>
	<button class="ui orange basic button" id="refresh"
		onclick="toggleColumn(2)">Toggle nøytralitet</button>
	<button class="ui red basic button" id="refresh"
		onclick="toggleColumn(3)">Toggle misnøye</button>

	<jsp:include page="../../../partials/footer.jsp" />
</body>
</html>