<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<%@ page import="static no.hvl.dat104.model.Status.*" %>
<jsp:include page="../../../partials/header.jsp" />
<c:set var="PAAGANDE" value="<%=PAAGANDE%>"></c:set>
<c:set var="AVSLUTTET" value="<%=AVSLUTTET%>"></c:set>
<div class="ui container">
	<h2 style="text-align:center;">Mine Aktiviteter</h2>
	<span style="margin-bottom: 10px;"><a class="ui teal basic button" href="<%=LAGAKTIVITET_URL%>" style="float: right;">Opprett aktivitet</a> </span>	
	<c:if test="${flash == 'success'}">
		<div class="ui positive message">
			<div class="header">Suksess!</div>
			<p style="color: #016936;">${melding}</p>
		</div>
		<c:remove var="flash" scope="session" />
	</c:if>
	<c:if test="${flash == 'error'}">
		<div class="ui negative  message">
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
	  	<c:forEach  items="${aktiviteter}" var = "aktivitet">
	        <tr>
		      <td><a href="<%=MINEEVENTER_URL%>?aktivitetId=${aktivitet.id}"><c:out value = "${aktivitet.navn}"/></a></td>
		      <td class="${aktivitet.status eq PAAGANDE ? 'positive' : (aktivitet.status eq AVSLUTTET) ? 'error' : 'warning'}"><c:out value = "${aktivitet.status}"/></td>
		      <td><a href="<%= REDIGERAKTIVITET_URL%>?aktivitetId=${aktivitet.id}">Rediger</a></td>
		      <td><a href="<%= SLETTAKTIVITET_URL%>?aktivitetId=${aktivitet.id}">Slett</a></td>
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