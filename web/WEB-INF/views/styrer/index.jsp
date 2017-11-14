<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="dato" class="no.hvl.dat104.util.DatoUtil" />
<jsp:useBean id="val" class="no.hvl.dat104.util.ValidatorUtil"/>
<jsp:include page="../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<%@ page import="static no.hvl.dat104.model.Status.*" %>
<c:set var="PLANLAGT" value="<%=PLANLAGT%>"></c:set>

<div class="ui container">
	<c:if test="${(not empty aktiviteter) and (empty alleEventer)}">
		<div class="ui positive message fjern_meld">Du har ingen hendelser! Opprett en ved å trykke på kalenderen.</div>
	</c:if>
	<c:if test="${flash == 'success'}">
		<div class="ui positive message fjern_meld">${melding}</div>
		<c:remove var="flash" scope="session" />
	</c:if>
	<c:if test="${flash == 'error'}">
		<div class="ui negative message fjern_meld">${melding}</div>
		<c:remove var="flash" scope="session" />
	</c:if>
	<!-- Dette er knappen for å gå til en pågående event -->
	<c:if test="${not empty paagandeEvent}">
		<c:set var="paagandeMeld" value="pågår, trykk her for å starte den!"/>
 		<c:if test="${paagandeEvent.status eq PAAGANDE}">
 			<c:set var="paagandeMeld" value="har startet, trykk her for å gå til den!"/>
 		</c:if>
		<a href="<%=LIVE_EVENT_URL%>?paagandeEvent=${paagandeEvent.id}"><div class="ui positive message" style="margin-bottom: 20px;"><span style="color: teal;">"${paagandeEvent.navn}"</span> ${paagandeMeld}</div></a>
	</c:if>
</div>

<div class="ui grid">
  <div class="two wide column"></div>
  <div class="eleven wide column">
  	<div id='calendar'></div>
  </div>
  <div class="two wide column">
   <div class="ui card">
   	<div class="content">
   	  <div class="header" style="overflow:hidden;">Mine Aktiviter:</div>
   	  <p>Opprett ny <a href="<%=LAGAKTIVITET_URL%>">her!</a></p>
   	</div>
   	<div class="conentet" style="overflow:hidden;">Importer kalender:
   		<form method="post" action="importerkalender" class="ui form">
   			<input type="text" placeholder="importer" name="url">
   		</form>
   	</div>
   	
   	<div class="content" style="overflow:hidden;">
   		<table class="aktivitet">
		<thead>
			<tr>
				<td></td>
				<td></td>
			</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${not empty aktiviteter }">
				<c:forEach var="aktivitet" items="${aktiviteter}" varStatus="loop">
					<tr>
						<td onclick="fjernAktivitet(<c:out value="${aktivitet.id}"/>)"
							class="foo"
							style="background-color: <c:out value="${color[loop.index]}"/>"></td>
						<td><a href="<%=VISAKTIVITET_URL%>?aktivitetId=${aktivitet.id}">${val.formaterText(aktivitet.navn)}</a></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="item">
					Du har ingen aktiviteter. 
				</div>
			</c:otherwise>
		</c:choose>			
		</tbody>
	  </table>
   	</div>
   	</div>
   </div>
   <div class="one wide column"></div>
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
				right: 'month'
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

<div class="ui modal" style="overflow:hidden;">
	<div id="event_modal" class="header">Opprett event</div>
	<div id="ingenAktivitet" class="content modal_content"></div>
</div>
<div style="display:none;" class="sjekkInnlogg">
</div>

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
	  $('.fjern_meld').remove();
      var visForm = '${not empty aktiviteter }';
	  if((visForm === "false")){
		  $("#event_modal").text('Ops! Du har ingen Aktiviteter ennå - Opprett Aktivitet før du kan opprette en hendelse!');
		  $("#event_modal").css('color', '#FE9A76');
    	  $('.modal_content').append('<div> <a href="lagaktivitet">Opprett Aktivitet!</a> </div>');
      }else {
    	  $("#event_modal").text('Lag hendelse!');
    	  $(".modal_content").load("<%=LAGEVENT_URL%>?dato="+date.format());
      }
	  $('.ui.modal')
      .modal({
    	  inverted: true,
    	    observeChanges: true
      })
      .modal('show');
      
  }
  function eventModal(event) {
	  $("#event_modal").text('Din hendelse!');
      $(".modal_content").load("<%=VIS_EVENT_URL%>?id=" + event.id);
      $('.fjern_meld').remove();
	  $('.ui.modal')
	  .modal({
		  inverted: true,
    	    observeChanges: true
      })
	  .modal('show');
  }
  function fjernAktivitet(aktivitet){
	  $('#calendar').fullCalendar('removeResource', aktivitet.toString());  
  }
</script>
<script>
	  <c:if test='${not empty eventFeil}'>
	  	  $('.fjern_meld').remove();
		  $('.ui.modal')
		  .modal({
			  inverted: true,
	    	    observeChanges: true
	      })
		  .modal('show');
		  $("#event_modal").text('Skriv inn riktig data!');
		  <c:remove var="eventFeil" scope="session" />
		  $(".modal_content").load("<%=LAGEVENT_URL%>?dato="+ '${eventFeil}');
	  </c:if>	  
</script>
<jsp:include page="../../partials/footer.jsp" />
