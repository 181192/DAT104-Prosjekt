<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<div class="ui menu">
		<div class="right menu">
		  <div class="item">
		    <a href="<%=LANDING_STYRER_URL%>">
		      <div class="ui button">Dashbord</div>
		    </a>
		  </div>
		  <div class="item">
		    <a href="<%=LIVE_EVENT_URL%>">
		      <div class="ui primary button">Pågående Event</div>
		    </a>
		  </div>
		  <div class="item">
		   <a href="<%=MINEAKTIVITETER_URL %>">
		    <div class="ui primary button">Aktiviteter</div>
		   </a>
		  </div>
		  <div class="item">
		    <div class="ui button">loggut</div>
		  </div>
		</div>
	</div>
<jsp:include page="../partials/footer.jsp" />