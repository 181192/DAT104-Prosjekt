<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<jsp:include page="../../../partials/header.jsp" />
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://cdn.rawgit.com/mdehoog/Semantic-UI/6e6d051d47b598ebab05857545f242caf2b4b48c/dist/semantic.min.css"
	rel="stylesheet" type="text/css" />
<div class="ui container">
<h1 class="ui header">Rediger Event: ${hendelse.tittel }</h1>
	<form method="post" id="myForm" action="<%= REDIGEREVENT_URL%>"
		class="ui form">
		<div class="field">
			<label>Tittel: <span class="fjerndata" style="color: #B03060">${redigerEventSkjema.tittelFeilmelding }</span></label>
			<input type="text" class="fjerndata" name="tittel"
				value="${hendelse.tittel}" placeholder="Tittel">
		</div>
		<div class="field" id="datoen">
			<label>Dato: <span class="fjerndata" style="color: #B03060">${redigerEventSkjema.datoFeilmelding }</span></label>
			<div class="ui calendar" id="datepicker">
				<div class="ui input left icon">
					<i class="calendar icon"></i> <input name="dato" type="text" value="${hendelse.dato}"
						placeholder="Dato">
				</div>
			</div>
		</div>
		<div class="two fields" style="margin: 0 0;">
			<div class="field">
				<label>Fra: <span class="fjerndata" style="color: #B03060">${redigerEventSkjema.fraFeilmelding }</span></label><input
					class="timepicker fjerndata" value="${hendelse.fra }"
					type="text" name="fra" placeholder="hh.mm">
			</div>
			<div class="field">
				<label>Til: <span class="fjerndata" style="color: #B03060">${redigerEventSkjema.tilFeilmelding } ${redigerEventSkjema.framindreennminFeilmeilding }</span></label><input
					type="text" class="timepicker fjerndata"
					value="${hendelse.til }" name="til" placeholder="hh.mm">
			</div>
		</div>
		<div class="field">
			<label>Hvor: <span class="fjerndata" style="color: #B03060">${redigerEventSkjema.hvorFeilmelding }</span></label><input
				type="text" class="fjerndata" value="${hendelse.hvor }"
				name="hvor" placeholder="Hvor">
		</div>
		<div class="field">
			<label>Beskrivelse <span class="fjerndata"
				style="color: #B03060">${redigerEventSkjema.beskrivelseFeilmelding }</span></label>
			<input type="text" class="fjerndata" name="beskrivelse"
				value="${hendelse.beskrivelse }" placeholder="Beskrivelse">
		</div>
		<input type="hidden" value="${eventId}" name="eventId">
		<input type="submit" class="ui primary button" value="Oppdater event">
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
		<c:remove var="hendelse" scope="session" />
		<c:remove var="redigerEventSkjema" scope="session" />
			<c:remove var="false" scope="session" />
		$(".fjerndata").val('');
		$(".fjerndata").text('');
	});
</script>