<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../../partials/header.jsp" />
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="public/js/charts/eventResultaterChart.js"></script>
<script type="text/javascript">
<c:set var = "a" scope = "request" value = "${aktivitet}"/>
<c:set var = "e" scope = "request" value = "${event}"/>
<c:set var = "melding" scope = "page" value = "Tilbakemeldinger for"/>

<c:choose>
<c:when test="${not empty requestScope.formaterteTilbakemeldinger}">
	enable = true;
	var array = [ ['Tid', 'Fornoyd', 'Noytral', 'Misfornoyd'], 
	<c:forEach items="${requestScope.formaterteTilbakemeldinger}" var="t">
		[new Date("${t.tid.toString()}"), ${t.fornoyd}, ${t.noytral}, ${t.misfornoyd}],
	</c:forEach>
	];
</c:when>
<c:otherwise>
<c:set var = "melding" scope = "page" value = "Det finnes ingen tilbakemeldinger for"/>
</c:otherwise>
</c:choose>

</script>
</head>
<body>

<h2>${a.navn}:</h2>
<h1>${melding} "${e.navn}":</h1>
  <div class="ui container">
    <div id="chart_div"></div>
    <div id="filter_div"></div>
    <button class="ui blue basic button" id="byttKnapp" onclick="switchView()"><i class="bar chart icon"></i>Bytt graf</button>
    <button class="ui green basic button" id="green" onclick="showColumn(1, this.id)"><i class="smile icon"></i>Vis fornøyd</button>
    <button class="ui orange basic button" id="orange" onclick="showColumn(2, this.id)"><i class="meh icon"></i>Vis nøytral</button>
    <button class="ui red basic button" id="red" onclick="showColumn(3, this.id)"><i class="frown icon"></i>Vis misfornøyd</button>
    <button class="ui blue basic button" id="visAlleKnapp" onclick="showAll()"><i class="ellipsis horizontal icon"></i>Vis alle</button>
  </div>
</body>
</html>
<jsp:include page="../../../partials/footer.jsp" />