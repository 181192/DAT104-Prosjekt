<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<%@ page import="static no.hvl.dat104.model.Status.*" %>
<c:set var="PAAGANDE" value="<%=PAAGANDE%>"></c:set>
<c:set var="AVSLUTTET" value="<%=AVSLUTTET%>"></c:set>
<c:set var="PAAGANDE" value="<%=PLANLAGT%>"></c:set>
	 <div class="ui container">
	Aktivitet: <a href="<%=VISAKTIVITET_URL%>?aktivitetId=${aktivitet.id}">${aktivitet.navn}</a>
	<table class="ui celled table">
	  <thead>
	    <tr>
	      <th>Navn</th>
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
	      <td class="selectable"id="vis">
	        <p class="aktivitet-link" >${event.navn}<p>
	      </td>
	      <td>${event.tidFra}</td>
	      <td>${event.tidTil}</td>
	      <td class="${event.status eq PAAGANDE ? 'positive' : event.status eq PLANLAGT ?  'warning' : 'red'}">${event.status}</td>
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