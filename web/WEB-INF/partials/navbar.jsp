<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<div class="ui menu" id="nav-bar">
	<div class="item">
		<h1 id="nav-textfelt"></h1>
	</div>
	<!-- HVIS BRUKE ER INNLOGGET. -->
	<div class="right menu">
		<div class="item">
			<a class="ui teal button" href="<%=LANDING_STYRER_URL%>">
				Dashbord </a>
		</div>
		<div class="item">
			<a class="ui teal button" href="<%=PRE_EVENT_URL%>"> Pågående
				Event </a>
		</div>
		<div class="item">
			<a class="ui teal button" href="<%=MINEAKTIVITETER_URL%>">
				Mine Aktiviteter </a>
		</div>
		<div class="item">
			<button class="ui teal basic button">Innlogget Som: Atle</button>
		</div>
		<div class="item">
			<button class="ui teal basic button">loggut</button>
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