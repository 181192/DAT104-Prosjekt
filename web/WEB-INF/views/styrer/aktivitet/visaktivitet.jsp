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
	<c:forEach var="e" items="${arrayMedTilbakemeldinger}">
		hentData.push(["${e.navn}","${e.fornoyd}","${e.noytral}","${e.misfornoyd}"]);
	</c:forEach>
</script>
</head>
<body>
	<jsp:include page="../../../partials/header.jsp" />
	<div class="ui container">
		<h2 style="text-align:center;"><span style="color:teal;">Tilbakemeldinger for ${aktivitetsNavn}</span></h2>
		<div id="chart_div" style="width: 900px; height: 500px"></div>
		<div class="ui centered grid">
			<div class="ten wide column">
				<button class="ui green basic button" id="refresh" onclick="toggleColumn(1)">Toggle fornøyelse</button>
				<button class="ui orange basic button" id="refresh" onclick="toggleColumn(2)">Toggle nøytralitet</button>
				<button class="ui red basic button" id="refresh" onclick="toggleColumn(3)">Toggle misnøye</button>
			</div>
		</div>
	</div>
	

	<jsp:include page="../../../partials/footer.jsp" />
</body>
</html>