<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<%@ page import="static no.hvl.dat104.model.Status.*" %>
<jsp:include page="../../../partials/header.jsp" />
	<c:set var="PAAGANDE" value="<%=PAAGANDE%>"></c:set>
<c:set var="AVSLUTTET" value="<%=AVSLUTTET%>"></c:set>
<div class="ui container">
	<h3>${aktivitet.navn} - Eventer:</h3>
	<table class="ui fixed single line celled table">
	  <thead>
	    <tr><th>Navn</th>
	    <th>Status</th>
	    <th>Rediger</th>
	  </tr></thead>
	  <tbody>	  
	  	<c:forEach  items="${eventer}" var = "event">
	        <tr>
		      <td><a href="<%= EVENTRESULTATER_URL%>?eventId=${event.id}"><c:out value = "${event.navn}"/></a></td>
		      <td class="${event.status eq PAAGANDE ? 'positive' : (event.status eq AVSLUTTET) ? 'error' : 'warning'}"><c:out value = "${event.status}"/></td>
		      <td><a href="<%= REDIGEREVENT_URL%>?eventId=${event.id}">Rediger</a></td>
	    	</tr>	  
	     </c:forEach>
	  </tbody>
	</table>
</div>
	 <script type="text/javascript">
	    var visForm = document.getElementById('vis');
 		var redigerForm = document.getElementById('rediger');
 		
 		visForm.addEventListener("click", function(){
 			document.getElementById("visForm").submit();
 		});
 		
 		redigerForm.addEventListener("click", function(){
 		    document.getElementById("redigerForm").submit();
 		});
 		
	 </script>
<jsp:include page="../../../partials/footer.jsp" />