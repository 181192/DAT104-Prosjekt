<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>
<div class="ui menu">
		<!-- HVIS BRUKE ER INNLOGGET. -->
		<div class="right menu">
		  <div class="item">
		    <a href="<%=LANDING_STYRER_URL%>">
		      <div class="ui primary button">Dashbord</div>
		    </a>
		  </div>
		  <div class="item">
		    <a href="<%=LIVE_EVENT_URL%>">
		      <div class="ui primary button">Pågående Event</div>
		    </a>
		  </div>
		  <div class="item">
		   <a href="<%=MINEAKTIVITETER_URL %>">
		    <div class="ui primary button">Mine Aktiviteter</div>
		   </a>
		  </div>
		  <div class="item">
		    <div class="ui button">Innlogget Som: "Atle"</div>
		  </div>
		  <div class="item">
		    <div class="ui button">loggut</div>
		  </div>
		</div>
		<!-- HVIS IKKE INNLOGGET VIS LOGIN REGISTRER -->
		
		<!-- <div class="right menu">
		  <div class="item">
		    <div class="ui button">login</div>
		  </div>
		  <div class="item">
		    <div class="ui button">Registrer</div>
		  </div>
		 </div> -->
	</div>
<jsp:include page="../partials/footer.jsp" />