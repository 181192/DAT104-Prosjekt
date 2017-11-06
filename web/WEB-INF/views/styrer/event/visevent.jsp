<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
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
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <td class="selectable"id="vis">
	        <p class="aktivitet-link" >${event.navn}<p>
	      </td>
	      <td class="positive">${event.tidFra}</td>
	      <td class="positive">${event.tidTil}</td>
	      <td class="${event.status eq 'ok' ? 'positive' : 'warning'}">${event.status}</td>
	      <td>${event.sted}</td>
	      <td class="selectable" id="rediger">
	      	<p class="aktivitet-link">Rediger<p>
		    <form id="redigerForm" action="<%= REDIGEREVENT_URL%>" method="get">
		      <input type="hidden" value="${event.id}" name="eventId">
		    </form>
		  </td>
	    </tr>	       
	  </tbody>
	</table>
	<form action="<%=EVENTRESULTATER_URL %>" method="post">
		<input type="hidden" value="${event.id}" name="eventId"><input class="ui primary button" type="submit" value="Vis Tilbakemeldinger">
	</form>
	
</div>
<script type="text/javascript">
 		var redigerForm = document.getElementById('rediger');
 		document.querySelector('#myModal > div').style.width = '70%'; 
 		redigerForm.addEventListener("click", function(){
 		    document.getElementById("redigerForm").submit();
 		});
 		
	 </script>
<jsp:include page="../../../partials/footer.jsp" />