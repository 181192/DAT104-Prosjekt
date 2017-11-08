<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../../partials/header.jsp" />
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>

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
				<h1 class="ui red header">
					Sluttevaluering pågår
				</h1>
			</div>
		</div>
	</div>

	<div class="ui sizer vertical segment">
		<img alt="utilfreds"
			src="http://d2trtkcohkrm90.cloudfront.net/images/emoji/apple/ios-10/256/frowning-face.png">
		<img alt="tilfreds"
			src="http://d2trtkcohkrm90.cloudfront.net/images/emoji/apple/ios-10/256/neutral-face.png">
		<img alt="fornoyd"
			src="http://d2trtkcohkrm90.cloudfront.net/images/emoji/apple/ios-10/256/grinning-face.png">
	</div>
	<div class="ui sizer vertical segment">
		<h1 class="ui large header">    3             5             2 </h1>
			<a class="ui teal button" href="<%=LANDING_STYRER_URL%>">
				Tilbake til Kalender </a>
		</div>
	</div>
</div>

<jsp:include page="../../../partials/footer.jsp" />