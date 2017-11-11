<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="ui menu" id="nav-bar" style="margin-bottom: 3%;">
	<div class="item">
		<h1 id="nav-textfelt"></h1>
	</div>
	<c:if test="${not empty bruker}">
		<!-- HVIS BRUKE ER INNLOGGET. -->
		<div class="right menu">
			<div class="item">
				<a class="ui teal button" href="<%=LANDING_STYRER_URL%>">
					Dashbord </a>
			</div>
			<div class="item">
				<a class="ui teal button" href="<%=PRE_LIVE_EVENT_URL%>">
					Pågående Event </a>
			</div>
			<div class="item">
				<a class="ui teal button" href="<%=MINEAKTIVITETER_URL%>"> Mine
					Aktiviteter </a>
			</div>
			<div class="item">
				<a class="ui teal basic button" href="<%=REDIGERBRUKER_URL%>">Innlogget
					som ${bruker.fornavn}</a>
			</div>
			<div class="item">
				<a class="ui teal basic button" href="<%=LOGGUT_URL%>">Logg Ut</a>
			</div>
		</div>
	</c:if>
	<c:if test="${empty bruker}">
		<c:if test="${not empty admin}">
			<div class="right menu">
				<div class="item">
					<a class="ui teal basic button" href="<%=LOGGUT_URL%>">Logg Ut</a>
				</div>
			</div>
		</c:if>
		<c:if test="${empty admin}">
			<div class="right menu">
				<div class="item">
					<a class="ui button" href="<%=LOGGINN_URL%>">Logg inn</a>
				</div>
				<div class="item">
					<a class="ui button" href="<%=OPPRETTBRUKER_URL%>">Registrer</a>
				</div>
			</div>
		</c:if>
	</c:if>
</div>
<jsp:include page="../partials/footer.jsp" />