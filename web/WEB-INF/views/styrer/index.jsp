<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="dato" class="no.hvl.dat104.util.DatoUtil" />
<jsp:include page="../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/components/modal.min.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/components/dimmer.min.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/components/transition.min.css">
<head>

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
	<c:if test="${flash == 'success'}">
		<div class="ui positive message">${melding}</div>
		<c:remove var="flash" scope="session" />
	</c:if>
	<c:if test="${flash == 'error'}">
		<div class="ui negative message">${melding}</div>
		<c:remove var="flash" scope="session" />
	</c:if>
	<div id='calendar'></div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.6.2/locale/nb.js"></script>
<script>
var datoen;

$('#calendar').fullCalendar({
			schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source',
			editable: false,
			aspectRatio: 2,
			scrollTime: '07:00',
			locale: 'nb',
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

<div class="ui modal">
	<div id="event_modal" class="header">Opprett event</div>
	<div id="ingenAktivitet" class="content"></div>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/components/modal.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/components/dimmer.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/components/transition.min.js"></script>

<script>
  function lagmodal(date) {
      $('.ui.modal')
      .modal({
    	  inverted: true,
    	    observeChanges: true
      })
      .modal('show');
      var visForm = '${not empty aktiviteter }';
	  if((visForm === "false")){
		  $("#event_modal").text('Ops! Du har ingen Aktiviteter ennå - Opprett Aktivitet før du kan oppretter en Event!');
		  $("#event_modal").css('color', '#FE9A76');
    	  $('.content').append('<div> <a href="lagaktivitet">Opprett Aktivitet!</a> </div>');
      }else {
    	  $(".content").load("<%=LAGEVENT_URL%>?dato="+date.format());
      }
      
  }
  function eventModal(event) {
	  $('.ui.modal')
	  .modal({
		  inverted: true,
    	    observeChanges: true
      })
	  .modal('show');
	  $("#event_modal").text('Ditt Event!');
      $(".content").load("<%=VIS_EVENT_URL%>?id=" + event.id);
  }
  function fjernAktivitet(aktivitet){
	  $('#calendar').fullCalendar('removeResource', aktivitet.toString());  
  }
</script>
<jsp:include page="../../partials/footer.jsp" />
