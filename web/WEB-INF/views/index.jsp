<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<div class="ui three column centered grid">
	<div class="column"></div>
	<div class="ui segment column">
		<h1 class="header">Delta event</h1>
		<form action="<%=DELTAEVENT_URL%>" method="post">
			<div class="ui form warning">
				<div class="field">
					<label>Kodeord</label> <input type="text" placeholder="Kodeord"
						name="kodeord">
				</div>
				<c:if test="${flash == 'error'}">
					<div class="ui negative  message">
						<div class="header">Beklager, noe gikk galt!</div>
						<p style="color: red;">${melding}</p>
					</div>
					<c:remove var="flash" scope="session" />
				</c:if>
				<c:if test="${flash=='Success'}">
					<div style="margin-bottom: 2em;" class="ui info message">${melding}</div>
					<c:remove var="flash" scope="session" />
				</c:if>
				<input type="submit" class="ui teal button" value="Delta Event">
			</div>
		</form>
	</div>
	<div class="column"></div>
</div>


<jsp:include page="../partials/footer.jsp" />