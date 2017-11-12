
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<jsp:include page="../../../partials/headerNoNavbar.jsp" />
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://cdn.rawgit.com/mdehoog/Semantic-UI/6e6d051d47b598ebab05857545f242caf2b4b48c/dist/semantic.min.css"
	rel="stylesheet" type="text/css" />

<div class="ui container">
	<form method="post" id="myForm" action="<%=LAGEVENT_URL%>"
		class="ui form">
		<div class="field">
			<label>Tittel: <span class="fjerndata" style="color: #B03060">${eventSkjema.tittelFeilmelding }</span></label>
			<input type="text" class="fjerndata" name="tittel"
				value="${eventSkjema.tittel }" placeholder="Tittel">
		</div>
		<div class="field" id="datoen">
			<label>Dato: <span class="fjerndata" style="color: #B03060">${eventSkjema.datoFeilmelding }</span></label>
			<div class="ui calendar" id="datepicker">
				<div class="ui input left icon">
					<i class="calendar icon"></i> <input name="dato" type="text" value="${dato}"
						placeholder="Dato">
				</div>
			</div>
		</div>
		<div class="two fields" style="margin-bottom:0;">
			<div class="field" style="padding-left:6px;">
				<label>Fra: <span class="fjerndata" style="color: #B03060">${eventSkjema.fraFeilmelding }</span></label><input
					class="timepicker fjerndata" value="${eventSkjema.fra }"
					type="text" name="fra" placeholder="hh.mm">
			</div>
			<div class="field" style="padding-right: 6px;">
				<label>Til: <span class="fjerndata" style="color: #B03060">${eventSkjema.tilFeilmelding } ${eventSkjema.framindreennminFeilmeilding } </span></label><input
					type="text" class="timepicker fjerndata"
					value="${eventSkjema.til }" name="til" placeholder="hh.mm">
			</div>
		</div>
		<div class="field">
			<label>Hvor: <span class="fjerndata" style="color: #B03060">${eventSkjema.hvorFeilmelding }</span></label><input
				type="text" class="fjerndata" value="${eventSkjema.hvor }"
				name="hvor" placeholder="Hvor">
		</div>
		<div class="field">
			<label>Beskrivelse <span class="fjerndata"
				style="color: #B03060">${eventSkjema.beskrivelseFeilmelding }</span></label>
			<input type="text" class="fjerndata" name="beskrivelse"
				value="${eventSkjema.beskrivelse }" placeholder="Beskrivelse">
		</div>
		<div class="field">
			<label>Aktivitet</label>
			<div class="ui selection dropdown aktivitet">
				<input type="hidden" name="aktivitet"> <i
					class="dropdown icon"></i>
				<div class="default text">Aktivitet</div>
				<div class="menu">
					<c:choose>
						<c:when test="${not empty aktiviteter }">
							<c:forEach var="aktivitet" items="${aktiviteter}"
								varStatus="count">
								<div class="item" data-value="${aktivitet.id}">${aktivitet.navn}</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div id="opprettAktivitet" class="item">
								<a href="<%=LAGAKTIVITET_URL%>">Opprett Aktivitet</a>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<input type="submit" class="ui primary button" value="Lag event">
		<input type="button" id="fjernAlt" class="ui red button"
			value="Fjern Data">
	</form>

</div>

<jsp:include page="../../../partials/footer.jsp" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui-calendar/0.0.8/calendar.min.js"></script>

<script>
	$(function() {
		$('#datepicker').calendar({
			type : 'date',
			firstDayOfWeek: 1,
			text: {
			      days: ['M', 'T', 'O', 'T', 'F', 'L', 'S'],
			      months: ['Januar', 'Februar', 'Mars', 'April', 'Mai', 'Juni', 'Juli', 'August', 'September', 'Oktober', 'November', 'Desember'],
			      monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Des'],
			      today: 'Idag',
			      now: 'Nå'
			    },
		 	initialDate: null,
		 	closable: true,
		 	today: true,
		 	formatter: {
		 	    date: function (date, settings) {
		 	      if (!date) return '';
		 	      var day = date.getDate();
		 	      var month = date.getMonth() + 1;
		 	      var year = date.getFullYear();
		 	      return (day<10?'0'+day:day) + '.' + (month<10?'0'+month:month) + '.' + year;
		 	    }
		 	}
		});
		$('.timepicker').timepicker({
			'scrollDefault' : 'now',
			'timeFormat' : 'H:i',
			'step' : 15
		});
		$('.aktivitet').dropdown();

	});
</script>
<script>
	$("#fjernAlt").click(function() {
		<c:remove var="eventSkjema" scope="session" />
		$(".fjerndata").val('');
		$(".fjerndata").text('');
	});
</script>