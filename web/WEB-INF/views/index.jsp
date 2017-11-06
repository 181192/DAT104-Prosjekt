<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>

<div class="ui container segment delta" id="delta" style="margin-top:10%;
	width:60%;">
<form action="<%=DELTAEVENT_URL %>" method="post">
	<div class="ui form warning">
	  <div class="field">
	    <label>Kode-Ord</label>
	    <input type="text" placeholder="1234" name="kodeord">
	  </div>
	  <c:if test="${feilmelding=='true'}">
		  <div class="ui warning message">
		    <div class="header">Dette gikk galt!</div>
		    <ul class="list">
		      <li>det kodeordet er ikke gyldig.</li>
		    </ul>
		  </div>
	  </c:if>
	  <input type="submit" class="ui primary button" value="Delta Event">
	</div>
</form>
</div>


<jsp:include page="../partials/footer.jsp" />