<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<jsp:include page="../../../partials/header.jsp" />
<div class="ui container">
	<p>Mine aktiviteter</p>
	<table class="ui celled table">
	  <thead>
	    <tr>
	      <th>Name</th>
	      <th>Status</th>
	      <th>Rediger</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <td class="selectable"id="vis">
	        <p class="aktivitet-link" >Dat103<p>
		    <form id="visForm" action="<%=VISAKTIVITET_URL%>" method="get">
		      <input type="hidden" value="1234" name="aktivitetId">
		    </form>
	      </td>
	      <td class="positive">Pågående</td>
	      <td class="selectable" id="rediger">
	      	<p class="aktivitet-link">Rediger<p>
		    <form id="redigerForm" action="<%=REDIGERAKTIVITET_URL%>" method="post">
		      <input type="hidden" value="1234" name="aktivitetId">
		    </form>
		  </td>
	    </tr>
	    <tr>
	      <td>Mat102</td>
	      <td class="warning">Avsluttet</td>
	      <td class="selectable">
	        <a href="#">Rediger</a>
	      </td>
	    </tr>
	    <tr>
	      <td>Dat104</td>
	      <td class="positive">Pågående</td>
	      <td class="selectable">
	        <a href="#">Rediger</a>
	      </td>
	    </tr>	       
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