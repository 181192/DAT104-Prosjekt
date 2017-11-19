<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<jsp:useBean id="dato" class="no.hvl.dat104.util.DatoUtil" />
<%@ page import="static no.hvl.dat104.model.Status.*" %>
<c:set var="PAAGANDE" value="<%=PAAGANDE%>"></c:set>
<c:set var="AVSLUTTET" value="<%=AVSLUTTET%>"></c:set>
	 <div class="ui container">
	Aktivitet: <a href="<%=VISAKTIVITET_URL%>?aktivitetId=${aktivitet.id}">${aktivitet.navn}</a>
	<table class="ui fixed celled table">
	  <thead>
	    <tr>
	      <th>Navn</th>
	      <th>Dato</th>
	      <th>Fra</th>
	      <th>Til</th>
	      <th>Status</th>
	      <th>Sted</th>
	      <th>Rediger</th>
	      <th>Slett</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <td class="selectable">
	        <div style="padding:11px;">${event.navn}</div>
	      </td>
	      <td>${fn:substring(dato.fraEngTilNorskDatov2(event.tidFra), 0, 10)}</td>
	      <td>${fn:substring(event.tidFra, 10, 16)}</td>
	      <td>${fn:substring(event.tidTil, 10, 16)}</td>
	      <td class="${event.status eq PAAGANDE ? 'positive' : (event.status eq AVSLUTTET) ? 'error' : 'oransje'}">${event.status}</td>
	      <td>${event.sted}</td>
	      <td class="selectable" id="rediger">
	      	<a href="<%= REDIGEREVENT_URL%>?eventId=${event.id}">Rediger</a>
		  </td>
		  <td class="selectable" id="slett">
		  	<a href="<%=SLETTEVENT_URL%>?eventId=${event.id}">Slett</a>
		  </td>
	    </tr>	       
	  </tbody>
	</table>
	<h4 style="margin-top:0;" class="ui header">
		Beskrivelse: <div class="sub header"><div class="ui message" style="overflow:auto;">${event.beskrivelse }</div></div>
	</h4>
	<form action="<%=EVENTRESULTATER_URL %>" method="get">
		<input type="hidden" value="${event.id}" name="eventId"><input class="ui primary button" type="submit" value="Vis Tilbakemeldinger">
	</form>
	
</div>
<script type="text/javascript">
 		var redigerForm = document.getElementById('rediger');
 		document.querySelector('#myModal > div').style.width = '70%'; 
 		redigerForm.addEventListener("click", function(){
 		    document.getElementById("redigerForm").submit();
 		});
 		
 		var slettForm = document.getElementById('slett');
 		document.querySelector('#myModal > div').style.width = '70%'; 
 		slettForm.addEventListener("click", function(){
 		    document.getElementById("slettForm").submit();
 		});
	 </script>
<jsp:include page="../../../partials/footer.jsp" />