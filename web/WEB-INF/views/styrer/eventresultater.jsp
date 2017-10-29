<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../partials/header.jsp" />
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="public/js/eventResultat.js"></script>
</head>
<body>
<h1>Event resultat:</h1>
<div id="dashboard_div">
  <div id="chart_div"></div>
  <b style="padding-left:9em">Velg tidsperiode:</b>
  <br>
  <br>
  <br>
  <div id="filter_div"></div>
  <i style="padding-left:50%">Minutter</i>
</div>
</body>
</html>
<jsp:include page="../../partials/footer.jsp" />