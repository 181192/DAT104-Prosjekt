<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../partials/headerNoNavbar.jsp" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<div style="margin-top: 5em;" class="ui container">
	<h2 style="text-align: center; margin-bottom: 1em;">Gi
		tilbakemelding</h2>
	<c:if test="${flash=='Success'}">
		<div style="margin-bottom: 2em;" class="ui info message">${melding}</div>
		<c:remove var="flash" scope="session" />
	</c:if>
	<div class="ui center aligned stackable three column grid">
		<div class="column">
			<form action="<%=GITILBAKEMELDING_URL%>" method="post">
				<a href="javascript:;" onclick="parentNode.submit();"><abbr
					title="Jeg er fornøyd med forelesningen slik den er."><i
						style="font-size: 10em; color: #016936;" class="fa fa-smile-o"></i></abbr></a>
				<input type="hidden" name="tilbakemelding" value="0" />
			</form>
		</div>
		<div class="column">
			<form action="<%=GITILBAKEMELDING_URL%>" method="post">
				<a href="javascript:;" onclick="parentNode.submit();"><abbr
					title="Jeg henger med, men foreleser kunne med fordel roe litt ned"><i
						style="font-size: 10em; color: #FFD700;" class="fa fa-meh-o"></i></abbr></a>
				<input type="hidden" name="tilbakemelding" value="1" />
			</form>
		</div>
		<div class="column">
			<form action="<%=GITILBAKEMELDING_URL%>" method="post">
				<a href="javascript:;" onclick="parentNode.submit();"><abbr
					title=" Jeg henger ikke med og trenger svar på et spørsmål for å henge med videre"><i
						style="font-size: 10em; color: #B03060;" class="fa fa-frown-o"></i></abbr></a>
				<input type="hidden" name="tilbakemelding" value="2" />
			</form>
		</div>
	</div>
</div>

<jsp:include page="../../partials/footer.jsp" />