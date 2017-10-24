<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../partials/header.jsp" />
  ${datoen}
  <div class="ui container">
  <form method="post" action="/" class="ui form">
    <div class="field"><label>Tittel:</label> <input type="text" name="tittel" placeholder="Tittel"></div>
    <div class="field" id="datoen"><label>Dato:</label><input type="text" name="dato" id="datepicker" value=""></div>
    <div class="two fields">
      <div class="field">
        <label>Fra:</label><input class="timepicker" type="text" name="fra" placeholder="hh.mm">
      </div>
      <div class="field">
        <label>Til:</label><input type="text" class="timepicker" name="til" placeholder="hh.mm">
      </div>
    </div>
    <div class="field"><label>Hvor:</label><input type="text" name="hvor" placeholder="Hvor"></div>
    <div class="field">
    <label>Beskrivelse</label>
    <textarea rows="2" name="beskrivelse"></textarea>
    </div>
    <div class="field">
      <label>Aktivitet</label>
      <div class="ui selection dropdown aktivitet">
        <input type="hidden" name="aktivitet">
        <i class="dropdown icon"></i>
        <div class="default text">Aktivitet</div>
        <div class="menu">
          <!-- FOR LØKKE SOM GÅR GJENNOM ALLE AKTIVITETER.-->
          <div class="item" data-value="1">DAT103</div>
          <div class="item" data-value="0">MAT102</div>
        </div>
      </div>
    </div>
    <button class="ui button">Lag event</button>
  </form>
  </div>
<jsp:include page="../../partials/footer.jsp" />
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
 