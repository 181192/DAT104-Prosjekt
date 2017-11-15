<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<%@ page import="static no.hvl.dat104.controller.Attributter.*"%>


<c:set var="tb" scope="request" value="${tilbakemeldinger}" />


<div class="ui container segment delta"
	style="margin-top: 10%; width: 60%;">
	<div class="ui ordered steps">
		<div class="completed step">
			<div class="content">
				<h1 class="ui header">Live tilbakemeldinger</h1>
			</div>
		</div>
		<div class="active step">
			<div class="content">
				<h1 class="ui red header">Sluttevaluering pågår</h1>
			</div>
		</div>
	</div>


	<div class="ui equal width center aligned padded grid">
		<div class="row">
			<div class="column">
				<img alt="utilfreds"
					src="http://d2trtkcohkrm90.cloudfront.net/images/emoji/apple/ios-10/256/frowning-face.png">
			</div>
			<div class="column">
				<img alt="tilfreds"
					src="http://d2trtkcohkrm90.cloudfront.net/images/emoji/apple/ios-10/256/neutral-face.png">
			</div>
			<div class="column">
				<img alt="fornoyd"
					src="http://d2trtkcohkrm90.cloudfront.net/images/emoji/apple/ios-10/256/grinning-face.png">
			</div>
		</div>
		<div class="row">
			<div class="column">${tb[0]}</div>
			<div class="column">${tb[1]}</div>
			<div class="column">${tb[2]}</div>
		</div>
	</div>



	<div class="ui sizer vertical segment">
		<form action=<%=POST_LIVE_EVENT_URL%> method="post">
			<button type="submit" class="ui green button">Tilbake til
				kalender</button>
		</form>
	</div>
</div>
</div>

<jsp:include page="../../../partials/footer.jsp" />