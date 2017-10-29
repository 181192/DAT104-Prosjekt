<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
	 <div class="ui container">
	<p>Aktivitet ${aktivitet.navn}</p>
	<table class="ui celled table">
	  <thead>
	    <tr>
	      <th>Name</th>
	      <th>fra</th>
	      <th>til</th>
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
	      <td class="selectable" id="rediger">
	      	<p class="aktivitet-link">Rediger<p>
		    <form id="redigerForm" action="<%=REDIGEREVENT_URL %>" method="post">
		      <input type="hidden" value="${event.id}" name="eventId">
		    </form>
		  </td>
	    </tr>	       
	  </tbody>
	</table>
</div>
<script type="text/javascript">
 		var redigerForm = document.getElementById('rediger');
 		
 		
 		redigerForm.addEventListener("click", function(){
 		    document.getElementById("redigerForm").submit();
 		});
 		
	 </script>
<jsp:include page="../../partials/footer.jsp" />