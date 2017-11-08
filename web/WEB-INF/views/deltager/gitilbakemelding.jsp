<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../partials/header.jsp" />

<c:if test="${flash=='Success'}">
	<div class="ui info message">
		<i class="window close icon"></i>
		${melding}
	</div>
	<c:remove var="flash" scope="session" />
</c:if>

<div class="ui equal width grid">
	<!--ui stackable five column grid -->
	<form action="<%=GITILBAKEMELDING_URL%>" method="post">
		<div class="column">
			<button name="tilbakemelding" value="0" class="ui button">
				<img alt="utilfreds"
					src="http://d2trtkcohkrm90.cloudfront.net/images/emoji/apple/ios-10/256/frowning-face.png">
			</button>
		</div>
		<div class="column">
			<button name="tilbakemelding" value="1" class="ui button">
				<img alt="tilfreds"
					src="http://d2trtkcohkrm90.cloudfront.net/images/emoji/apple/ios-10/256/neutral-face.png">
			</button>
		</div>
		<div class="column">
			<button name="tilbakemelding" value="2" class="ui button">
				<img alt="fornoyd"
					src="http://d2trtkcohkrm90.cloudfront.net/images/emoji/apple/ios-10/256/grinning-face.png">
			</button>
		</div>
	</form>
</div>

<jsp:include page="../../partials/footer.jsp" />