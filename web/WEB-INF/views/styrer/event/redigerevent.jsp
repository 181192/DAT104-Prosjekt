<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<jsp:include page="../../../partials/header.jsp" />

<div class="ui container">
	<h3>Rediger event</h3>
	<p>Her er ditt event ${event.navn}</p>


	<form class="ui form" method="post" action="<%=REDIGEREVENT_URL%>">
		<!-- Endre navn på event -->
		<div class="field">
			<label>Event</label> <input type="text" name="navn"
				value="${event.navn}">
		</div>

		<!-- Endre beskrivelse -->
		<div class="field">
			<label>Beskrivelse</label> <input type="text" name="beskrivelse"
				value="${event.beskrivelse}">
		</div>
<!-- Endre tid og dato -->

 <div class="field" id="datoen">
 <label>Dato:</label><input type="text" name="dato" id="datepicker" value="${dato}"></div>
    <div class="two fields">
      <div class="field">
        <label>Fra:</label><input class="timepicker" type="text" name="fra" ${fra eq null ? 'placeholder="hh.mm"' : fra}>
      </div>
      <div class="field">
        <label>Til:</label><input type="text" class="timepicker" name="til" ${til eq null ? 'placeholder="hh.mm"' : til}>
      </div>
    </div>

		<!-- Endre status -->
		<div class="field">
			<label>Status</label> <select name="status">
				<option ${event.status eq 'pagaende' ? 'selected="selected"' : ''}
					value="pagaende">Pågående</option>
				<option ${event.status eq 'planlagt' ? 'selected="selected"' : ''}
					value="planlagt">Planlagt</option>
				<option ${event.status eq 'avsluttet' ? 'selected="selected"' : ''}
					value="avsluttet">Avsluttet</option>
			</select>
		</div>

		<!-- Endre sted -->
		<div class="field">
			<label>Sted</label> <input type="text" name="sted"
				value="${event.sted}">
		</div>

		<button class="ui button" type="submit">Submit</button>
	</form>
</div>
 <script>
      $( function() {
          $( "#datepicker" ).datepicker({
              dateFormat: "dd.mm.yy"
          });
          $('.timepicker').timepicker({
              'scrollDefault': 'now',
              'timeFormat': 'H:i',
              'step': 15
          });
          $('.aktivitet').dropdown();
      } );

  </script>
<jsp:include page="../../../partials/footer.jsp" />