<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<div class="ui menu">
		<div class="right menu">
		  <div class="item">
		    <a href="<%=LANDING_STYRER_URL%>">
		      <div class="ui button">styrer</div>
		    </a>
		  </div>
		  <div class="item">
		    <div class="ui primary button">Opprett Aktivitet</div>
		  </div>
		  <div class="item">
		    <div class="ui button">loggut</div>
		  </div>
		</div>
	</div>
<jsp:include page="../partials/footer.jsp" />