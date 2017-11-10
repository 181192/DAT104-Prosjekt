<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<jsp:include page="../../../partials/header.jsp" />

<c:set var="e" scope="request" value="${event}" />
<c:set var="k" scope="session" value="${kodeord}" />
<c:set var="flt" scope="request" value="${liveTilbakemeldinger}" />

<h1>${e.navn }</h1>
<div class="ui container">
	<div class="ui tiny modal" id="avsluttModal">
		<div class="header">Avslutt event</div>
		<div class="content">
			<p>�nsker du � avslutte eventet?</p>
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
	<c:if test = "${not empty flt}">
	  <div class="ui container">
	    <div id="chart_div"></div>
	    <div id="filter_div"></div>
	    <button class="ui blue basic button" id="byttKnapp" onclick="switchView()"><i class="bar chart icon"></i>Bytt graf</button>
	    <button class="ui green basic button" id="green" onclick="showColumn(1, this.id)"><i class="smile icon"></i>Vis forn�yd</button>
	    <button class="ui orange basic button" id="orange" onclick="showColumn(2, this.id)"><i class="meh icon"></i>Vis n�ytral</button>
	    <button class="ui red basic button" id="red" onclick="showColumn(3, this.id)"><i class="frown icon"></i>Vis misforn�yd</button>
	    <button class="ui blue basic button" id="visAlleKnapp" onclick="showAll()"><i class="ellipsis horizontal icon"></i>Vis alle</button>
	  </div>
	</c:if>

	<div class="ui divider"></div>
	<button class="ui labeled icon button" id="start-stopp"
		onclick="lagBekreftAvsluttmodal()">Avslutt event</button>
	
</div>


<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script src="public/js/charts/eventResultaterChart.js"></script>

<script type="text/javascript">
	var pinContainer = document.getElementById('nav-textfelt');
	pinContainer.innerText = 'PIN: ${k.kode}';

	function lagBekreftAvsluttmodal() {
		$("#avsluttModal").modal('show');
	}
	enable = true;
	var array = [ ['Tid', 'Fornoyd', 'Noytral', 'Misfornoyd'], 
		<c:forEach items="${flt}" var="t">
			[new Date("${t.tid.toString()}"), ${t.fornoyd}, ${t.noytral}, ${t.misfornoyd}],
		</c:forEach>
		];
</script>

<jsp:include page="../../../partials/footer.jsp" />