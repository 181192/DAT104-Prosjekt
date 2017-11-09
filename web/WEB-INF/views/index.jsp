<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>

<div class="ui container segment delta" id="delta"
	style="margin-top: 10%; width: 60%;">
	<form action="<%=DELTAEVENT_URL%>" method="post">
		<div class="ui form warning">
			<div class="field">
				<label>Kodeord</label> <input type="text" placeholder="Kodeord"
					name="kodeord">
			</div>
			<c:if test="${flash == 'error'}">
				<div class="ui negative  message">
					<div class="header">Beklager, noe gikk galt!
					</div>
					<p style="color: red;">${melding}</p>
				</div>
				<c:remove var="flash" scope="session" />
			</c:if>
			<input type="submit" class="ui primary button" value="Delta Event">
		</div>
	</form>
</div>


<jsp:include page="../partials/footer.jsp" />