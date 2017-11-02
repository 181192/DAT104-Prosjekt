<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>


<div class="ui container segment delta"
	style="margin-top: 10%; width: 60%;">

	<form action="preevent" method="post">
		<div class="ui form warning">
			<div class="field">
				<label>Navn: ${sessionScope.liveevent.navn}</label> 
				<label>Sted: ${sessionScope.liveevent.sted}</label> 
				<label>Start: ${sessionScope.liveevent.tidFra}</label> 
				<label>Slutt: ${sessionScope.liveevent.tidTil}</label> 
				<label></label>
			</div>
			<div class="ui divider"></div>
			<div class="ui red warning message">
				<div class="ui medium header">Advarsel</div>
				<p></p>
				<p>Starter du eventet blir pin-koden generert, og publikum kan gi tilbakemeldinger.</p> 
				<p>Denne handlingen kan <strong>ikke </strong>omgjøres.</p>
			</div>
			<input type="submit" class="ui primary button" value="Start Event">
		</div>
	</form>
</div>

<jsp:include page="../../../partials/footer.jsp" />