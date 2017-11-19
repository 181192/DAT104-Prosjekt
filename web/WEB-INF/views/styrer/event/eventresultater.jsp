<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../../partials/header.jsp" />
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="public/js/charts/eventResultaterChart.js"></script>

<script type="text/javascript">
	<c:set var = "a" scope = "request" value = "${aktivitet}"/>
	<c:set var = "e" scope = "request" value = "${event}"/>
	<c:set var = "melding" scope = "page" value = "Tilbakemeldinger for"/>
	<c:set var = "ft" scope = "request" value = "${requestScope.formaterteTilbakemeldinger}"/>
	
	<c:choose>
		<c:when test="${not empty ft}">
		google.charts.load('current', {
			'packages' : [ 'corechart', 'bar' ]
		});

		google.charts.setOnLoadCallback(drawChart);
		function drawChart() {
			var data1 = new google.visualization.DataTable();
			data1.addColumn('number', 'Tilbakemelding');
			data1.addColumn('number', 'Fornøyd');
			data1.addColumn({
				type : 'string',
				role : 'tooltip'
			});
			data1.addColumn('number', 'Nøytral');
			data1.addColumn({
				type : 'string',
				role : 'tooltip'
			});
			data1.addColumn('number', 'Misfornøyd');
			data1.addColumn({
				type : 'string',
				role : 'tooltip'
			});
			
			
			data1.addRows([				
			<c:forEach items="${ft}" var="t" varStatus="count">
			<fmt:formatDate pattern = "yyyy-MM-dd hh:mm" 
		         value = "${t.tid}" var="tidFormatert" />
				[${count.count}, ${t.fornoyd}, "${tidFormatert}", ${t.noytral}, "${tidFormatert}", ${t.misfornoyd}, "${tidFormatert}"],
			</c:forEach>
				]);
			var view = new google.visualization.DataView(data1);
			view.setColumns([ 0, 1, 6 ]);
			view.setColumns([ 0, 3, 6 ]);
			view.setColumns([ 0, 5, 6 ]);

			view.setColumns([ 0, 1, 2, 3, 4, 5, 6 ]);

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
					title : "Antall"
				},
				hAxis : {
					title : "Tilbakemeldinger",
					format : ""
				}
			};
		
			var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
			chart.draw(view, options);
		}
		</c:when>
		<c:otherwise>
			<c:set var = "melding" scope = "page" value = "Det finnes ingen tilbakemeldinger for"/>
		</c:otherwise>
	</c:choose>
</script>
<div class="ui container" style="color:teal; text-align:center; margin-bottom:2%;">
	<h3>Aktivitet - ${a.navn}:</h3>
	<h3>${melding} "${e.navn}"</h3>
</div>
<c:if test = "${not empty ft}">
  <div class="ui container">
    <div id="chart_div"></div>
    <div id="filter_div"></div>
    <button class="ui blue basic button" id="byttKnapp" onclick="switchView()"><i class="bar chart icon"></i>Bytt graf</button>
    <button class="ui green basic button" id="green" onclick="showColumn(1, this.id)"><i class="smile icon"></i>Vis fornøyd</button>
    <button class="ui orange basic button" id="orange" onclick="showColumn(2, this.id)"><i class="meh icon"></i>Vis nøytral</button>
    <button class="ui red basic button" id="red" onclick="showColumn(3, this.id)"><i class="frown icon"></i>Vis misfornøyd</button>
    <button class="ui blue basic button" id="visAlleKnapp" onclick="showAll()"><i class="ellipsis horizontal icon"></i>Vis alle</button>
  </div>
 </c:if>
<jsp:include page="../../../partials/footer.jsp" />