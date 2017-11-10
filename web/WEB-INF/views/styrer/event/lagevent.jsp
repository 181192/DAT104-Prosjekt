	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<jsp:include page="../../../partials/headerNoNavbar.jsp" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.css">
  <div class="ui container">
  <form method="post" action="<%=LAGEVENT_URL %>" class="ui form">
    <div class="field"><label>Tittel: <span class="fjerndata" style="color:#B03060">${eventSkjema.tittelFeilmelding }</span></label> <input type="text" class="fjerndata" name="tittel" value="${eventSkjema.tittel }" placeholder="Tittel"></div>
    <div class="field" id="datoen"><label>Dato: <span class="fjerndata" style="color:#B03060">${eventSkjema.datoFeilmelding }</span></label><input class="fjerndata" type="text" name="dato" id="datepicker" value="${dato}"></div>
    <div class="two fields">
      <div class="field">
        <label>Fra: <span class="fjerndata" style="color:#B03060">${eventSkjema.fraFeilmelding }</span></label><input class="timepicker fjerndata" value="${eventSkjema.fra }" type="text" name="fra" placeholder="hh.mm">
      </div>
      <div class="field">
        <label>Til: <span class="fjerndata" style="color:#B03060">${eventSkjema.tilFeilmelding }</span></label><input type="text" class="timepicker fjerndata" value="${eventSkjema.til }" name="til" placeholder="hh.mm">
      </div>
    </div>
    <div class="field"><label>Hvor: <span class="fjerndata" style="color:#B03060">${eventSkjema.hvorFeilmelding }</span></label><input type="text" class="fjerndata" value="${eventSkjema.hvor }" name="hvor" placeholder="Hvor"></div>
    <div class="field">
    <label>Beskrivelse <span class="fjerndata" style="color:#B03060">${eventSkjema.hvorFeilmelding }</span></label>
    <input type="text" class="fjerndata" name="beskrivelse" value="${eventSkjema.beskrivelse }" placeholder="Beskrivelse">
    </div>
    <div class="field">
      <label>Aktivitet</label>
      <div class="ui selection dropdown aktivitet">
        <input type="hidden" name="aktivitet">
        <i class="dropdown icon"></i>
        <div class="default text">Aktivitet</div>
        <div class="menu">
        <c:choose>
          <c:when test = "${not empty aktiviteter }">
         	<c:forEach var="aktivitet" items="${aktiviteter}" varStatus="count">
          	  <div class="item" data-value="${aktivitet.id}">${aktivitet.navn}</div>
            </c:forEach>
      	  </c:when>
      	  <c:otherwise>
      	  	<div class="item"><a href="<%=LAGEVENT_URL %>?">Opprett Aktivitet</a></div>
      	  </c:otherwise>
      	</c:choose>
        </div>
      </div>
    </div>
    <input type="submit" class="ui primary button" value="Lag event">
    <input type="button" id="fjernAlt" class="ui red button" value="Fjern Data">
  </form>
 
  </div>
  <jsp:include page="../../../partials/footer.jsp" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
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
  <script>
	  $("#fjernAlt").click(function(){
		  console.log("fjerner");
		  $(".fjerndata").val('');
	  });
  </script>