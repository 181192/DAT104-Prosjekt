<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="dato" class="no.hvl.dat104.util.DatoUtil" />
<jsp:include page="../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.foo {
	width: 20px;
	height: 20px;
	margin: 5px;
	border: 1px solid rgba(0, 0, 0, .2);
}

.aktivitet {
	width: 10%;
	margin: auto;
	display: block;
}
</style>

</head>
<div class="ui container">
	<div id='calendar'></div>
</div>
<script>
var datoen;

$('#calendar').fullCalendar({
			schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source',
			editable: false,
			aspectRatio: 2,
			scrollTime: '07:00',
			dayClick: function(date, jsEvent, view, resourceObj) {
				// ny event modul.
				console.log("dayclick");
				lagmodal(date);
    		},
    		eventClick: function(calEvent, jsEvent, view) {
    			console.log(calEvent);
    			eventModal(calEvent);
    	    },
			header: {
				left: 'today prev,next',
				center: 'title',
				right: 'timelineDay,month'
			},
			defaultView: 'month',
			//aktiviteter
			resourceGroupField: 'title',
			resources: [
				<c:forEach var="aktivitet" items="${aktiviteter}" varStatus="loop">
					{ id: '${aktivitet.id}', title: '${aktivitet.navn}', eventColor: '${color[loop.index]}'},
				</c:forEach>
				],
			events: [
				<c:forEach var="event" items="${alleEventer}">
					{ id: '${event.id}', resourceId: '${event.idAktivitet.id}', start: '${dato.timestampTilStrengForKalender(event.tidFra)}', end: '${dato.timestampTilStrengForKalender(event.tidTil)}', title: '${event.navn}'},
				</c:forEach>
			]
		});
function lagEvent() {
	window.location.href = "<%=LAGEVENT_URL%>";
};

</script>

<div class="container">
	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

</div>

<table class="aktivitet">
	<thead>
		<tr>
			<td></td>
			<td></td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="aktivitet" items="${aktiviteter}" varStatus="loop">
			<tr>
				<td onclick="fjernAktivitet(<c:out value="${aktivitet.id}"/>)"
					class="foo"
					style="background-color: <c:out value="${color[loop.index]}"/>"></td>
				<td>${aktivitet.navn}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../../partials/footer.jsp" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
  function lagmodal(date) {
      $("#myModal").modal()
      console.log("lagmodal");
      $(".modal-body").load("<%=LAGEVENT_URL%>?dato="+date.format());
  }
  function eventModal(event) {
	  $("#myModal").modal()
      $(".modal-body").load("<%=VIS_EVENT_URL%>?id=" + event.id);
  }
  function fjernAktivitet(aktivitet){
	  console.log(aktivitet);
	  $('#calendar').fullCalendar('removeResource', aktivitet.toString());  
  }
</script>
