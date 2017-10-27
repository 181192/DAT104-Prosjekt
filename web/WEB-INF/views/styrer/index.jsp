<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<div id='calendar'></div>

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
				
				{ id: '${aktiviteten.id}', title: '${aktiviteten.navn}' },
				/* { id: 'dat103', Hvor: 'f117', title: 'dat103' },
				{ id: 'dat104', Hvor: 'f119', title: 'dat104', eventColor: 'green' },
				{ id: 'mat102', Hvor: 'e403', title: 'mat102', eventColor: 'orange' } */
				],
			events: [
				
				{id: '${eventet.id}', resourceId: '${eventet.idAktivitet}', start: '${eventet.tidFra}', end: '${eventet.tidTil}', title: '${eventet.navn}'}
				/* { id: '1', resourceId: 'dat103', start: '2017-10-07T02:00:00', end: '2017-10-07T07:00:00', title: 'Forelesning' },
				{ id: '2', resourceId: 'dat104', start: '2017-10-07T05:00:00', end: '2017-10-07T22:00:00', title: 'Forelesning' },
				{ id: '3', resourceId: 'mat102', start: '2017-10-06', end: '2017-10-06', title: 'Lab' }, */ 
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
