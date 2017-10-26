<%@ page import="static no.hvl.dat104.controller.UrlMappings.*" %>

	<jsp:include page="../../partials/header.jsp" />
	<p>Vis aktivitet</p>
	<p>her er din aktivitet ${aktivitetsId}</p>

	<form action="<%=GITILBAKEMELDING_URL %>" method="post">

		<div class="ui three buttons">


			<button name="0" value="utilfreds"
				class="ui button">
				<img alt="utilfreds"
					src="http://d2trtkcohkrm90.cloudfront.net/images/emoji/apple/ios-10/256/pouting-face.png">
			</button>

			<button name="1" value="tilfreds" class="ui button">
				<img alt="tilfreds"
					src="http://d2trtkcohkrm90.cloudfront.net/images/emoji/apple/ios-10/256/neutral-face.png">
			</button>

			<button name="2" value="fornoyd" class="ui button">
				<img alt="fornøyd"
					src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDmH57vEXms3FEXFuU9-6IYtOK_ZwCHlN7mZZtWUWneF-jxmkNkA">
			</button>

		</div>


	</form>


	<jsp:include page="../../partials/footer.jsp" />
