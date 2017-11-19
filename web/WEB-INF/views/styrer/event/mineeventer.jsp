<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<%@ page import="static no.hvl.dat104.model.Status.*" %>
<%@ page import="static no.hvl.dat104.util.DatoUtil.*" %>
<jsp:include page="../../../partials/header.jsp" />
	<c:set var="PAAGANDE" value="<%=PAAGANDE%>"></c:set>
<c:set var="AVSLUTTET" value="<%=AVSLUTTET%>"></c:set>
<div class="ui container">
	<h2 style="text-align:center;">Eventer til Aktiviteten: <span style="color:teal;">  ${aktivitet.navn}</span></h2>
	<h3 style="margin-bottom: 10px;">Eventer <span><a class="ui teal basic button" href="<%=LAGEVENT_URL%>?dato=<%=lagCurrentDate()%>" style="float: right;">Opprett event</a></span></h3>
	<c:if test="${flash == 'success'}">
		<div class="ui positive message">
			<div class="header">Suksess!</div>
			<p style="color: #016936;">${melding}</p>
		</div>
		<c:remove var="flash" scope="session" />
	</c:if>
	<c:if test="${flash == 'error'}">
		<div class="ui warning  message">
			<div class="header">Beklager, noe gikk galt!</div>
			<p style="color: #B03060;">${melding}</p>
		</div>
		<c:remove var="flash" scope="session" />
	</c:if>
	<table class="ui fixed single line celled table">
	  <thead>
	    <tr><th>Navn</th>
	    <th>Status</th>
	    <th>Rediger</th>
	    <th>Slett</th>
	  </tr></thead>
	  <tbody>	  
	  	<c:forEach  items="${eventer}" var = "event">
	        <tr>
		      <td><a href="<%= EVENTRESULTATER_URL%>?eventId=${event.id}"><c:out value = "${event.navn}"/></a></td>
		      <td class="${event.status eq PAAGANDE ? 'positive' : (event.status eq AVSLUTTET) ? 'error' : 'warning'}"><c:out value = "${event.status}"/></td>
		      <td><a href="<%= REDIGEREVENT_URL%>?eventId=${event.id}">Rediger</a></td>
		      <td><a href="<%= SLETTEVENT_URL%>?eventId=${event.id}">Slett</a></td>
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