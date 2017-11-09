<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<jsp:include page="../../../partials/headerNoNavbar.jsp" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.css">
  <div class="ui container">
  <form method="post" action="<%=LAGEVENT_URL %>" class="ui form">
    <div class="field"><label>Tittel: <span style="color:#B03060">${skjema.tittelFeilmelding }</span></label> <input type="text" name="tittel" value="${skjema.tittel }" placeholder="Tittel"></div>
    <div class="field" id="datoen"><label>Dato: <span style="color:#B03060">${skjema.datoFeilmelding }</span></label><input type="text" name="dato" id="datepicker" value="${dato}"></div>
    <div class="two fields">
      <div class="field">
        <label>Fra: <span style="color:#B03060">${skjema.fraFeilmelding }</span></label><input class="timepicker" value="${skjema.fra }" type="text" name="fra" placeholder="hh.mm">
      </div>
      <div class="field">
        <label>Til: <span style="color:#B03060">${skjema.tilFeilmelding }</span></label><input type="text" class="timepicker" value="${skjema.til }" name="til" placeholder="hh.mm">
      </div>
    </div>
    <div class="field"><label>Hvor: <span style="color:#B03060">${skjema.hvorFeilmelding }</span></label><input type="text" value="${skjema.hvor }" name="hvor" placeholder="Hvor"></div>
    <div class="field">
    <label>Beskrivelse <span style="color:#B03060">${skjema.hvorFeilmelding }</span></label>
    <input type="text" name="beskrivelse" value="${skjema.beskrivelse }" placeholder="Beskrivelse">
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
    <input type="submit" class="ui button" value="Lag event">
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
 