<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="dato" class="no.hvl.dat104.util.DatoUtil"/>
<jsp:include page="../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<div id='calendar'></div>
${color[0]}
<script>
var datoen;

$('#calendar').fullCalendar({
			editable: false,
			aspectRatio: 1.8,
			scrollTime: '07:00',
			dayClick: function(date, jsEvent, view, resourceObj) {
				// ny event modul.
				datoen = date.format();
				console.log(datoen);
				lagmodal();
    		},
			header: {
				left: 'today prev,next',
				center: 'title',
				right: 'timelineDay,month'
			},
			defaultView: 'month',
			//aktiviteter
			resources: [
				<c:forEach var="aktivitet" items="${aktiviteter}" varStatus="loop">
					{ id: '${aktivitet.id}', title: '${aktivitet.navn}', eventColor: 'green'},
				</c:forEach>
				],
			events: [
				<c:forEach var="event" items="${alleEventer}">
					{ id: '${event.id}', resourceId: '${event.idAktivitet}', start: '${dato.timestampTilStrengForKalender(event.tidTil)}', end: '${dato.timestampTilStrengForKalender(event.tidFra)}', title: '${event.navn}'},
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
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>

    </div>
  </div>

</div>

<jsp:include page="../../partials/footer.jsp" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
  function lagmodal() {
      $("#myModal").modal()
      $(".modal-body").load("<%=LAGEVENT_URL%>");
  }

</script>
