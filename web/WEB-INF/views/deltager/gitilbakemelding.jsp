<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>

<jsp:include page="../../partials/header.jsp" />
<p>Vis aktivitet</p>
<p>her er din aktivitet ${aktivitetsId}</p>

	<div class="ui grid">
	    <form action="<%=GITILBAKEMELDING_URL%>" method="post">
	        <div class="column 5 wide tablet 5 wide computer 16 wide mobile column">
	            <div>
	                <button name="tilbakemelding" value="0" class="ui button">
	                    <img alt="utilfreds" src="http://d2trtkcohkrm90.cloudfront.net/images/emoji/apple/ios-10/256/pouting-face.png">
	                </button>
	            </div>
	        </div>
	        <div class="column 5 wide tablet 5 wide computer 16 wide mobile column">
	            <div>
	                <button name="tilbakemelding" value="1" class="ui button">
	                    <img alt="tilfreds" src="http://d2trtkcohkrm90.cloudfront.net/images/emoji/apple/ios-10/256/neutral-face.png">
	                </button>
	            </div>
	        </div>
	        <div class="column 5 wide tablet 5 wide computer 16 wide mobile column">
	            <div>
	                <button name="tilbakemelding" value="2" class="ui button">
	                    <img alt="fornoyd" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDmH57vEXms3FEXFuU9-6IYtOK_ZwCHlN7mZZtWUWneF-jxmkNkA">
	                </button>
	            </div>
	        </div>
	    </form>
	</div>
	
<jsp:include page="../../partials/footer.jsp" />
