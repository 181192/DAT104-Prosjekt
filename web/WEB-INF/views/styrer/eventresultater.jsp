<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../partials/header.jsp" />
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="public/js/eventResultat.js"></script>
</head>
<body>
<!---->
<h1>Event resultat:</h1>
<div id="dashboard_div">
  <div id="chart_div"></div>
  <p>chartrangefilter</p>
  <div id="filter_div"></div>
  <button class="ui blue basic button" id="byttKnapp"
		onclick="switchView()">Bytt graf</button>
		<button class="ui green basic button" id="green"
		onclick="showColumn(1, this.id)">Vis fornøyd</button>
		<button class="ui orange basic button" id="orange"
		onclick="showColumn(2, this.id)">Vis nøytral</button>
		<button class="ui red basic button" id="red"
		onclick="showColumn(3, this.id)">Vis misfornøyd</button>
		<button class="ui blue basic button" id="visAlleKnapp"
		onclick="showAll()">Vis alle</button>
</div>
</body>
</html>
<jsp:include page="../../partials/footer.jsp" />