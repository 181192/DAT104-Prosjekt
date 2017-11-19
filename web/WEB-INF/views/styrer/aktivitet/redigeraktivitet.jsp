<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.*"%>
<jsp:include page="../../../partials/header.jsp" />

<div class="ui container">
	<h3>Rediger aktivitet</h3>
	<p>Her er din aktivitet ${aktivitet.navn}</p>
	<c:if test="${flash == 'success'}">
		<div class="ui positive message">
			<div class="header">Suksess!</div>
			<p style="color: #016936;">${melding}</p>
		</div>
		<c:remove var="flash" scope="session" />
	</c:if>
	<c:if test="${flash == 'error'}">
		<div class="ui negative  message">
			<div class="header">Beklager, noe gikk galt!</div>
			<p style="color: #B03060;">${melding}</p>
		</div>
		<c:remove var="flash" scope="session" />
	</c:if>
	<form class="ui form" method="post" action="<%=REDIGERAKTIVITET_URL%>">

		<!-- Endre aktivitetsnavn -->
		<div class="field">
			<label>Aktivitet</label> <input type="text" name="navn"
				value="${aktivitet.navn}">
		</div>

		<!-- Endre status -->
		<div class="field">
			<label>Status</label> <select name="status">
				<option
					${aktivitet.status eq 'pagaende' ? 'selected="selected"' : ''}
					value="pagaende">Pågående</option>
				<option
					${aktivitet.status eq 'planlagt' ? 'selected="selected"' : ''}
					value="planlagt">Planlagt</option>
				<option
					${aktivitet.status eq 'avsluttet' ? 'selected="selected"' : ''}
					value="avsluttet">Avsluttet</option>
			</select>
		</div>
		<input type="hidden" name="id" value="${aktivitet.id}">
		<button class="ui button" type="submit">Submit</button>
	</form>
</div>

<jsp:include page="../../../partials/footer.jsp" />