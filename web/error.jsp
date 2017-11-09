<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gruppe3</title>
<link rel="stylesheet" type="text/css"
	href="public/css/scheduler.min.css">
<link rel="stylesheet" type="text/css"
	href="public/css/fullcalendar.print.min.css" media='print'>
<link rel="stylesheet" type="text/css"
	href="public/css/fullcalendar.min.css">
<link rel="stylesheet" type="text/css"
	href="public/css/semantic.min.css">
<link rel="stylesheet" type="text/css"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.min.css">
<link rel="stylesheet" type="text/css" href="public/css/style.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div style="margin-top: 5em;" class="ui center middle aligned grid">
		<div class="ui negative message">
			<i style="font-size: 10em; color: #B03060;" class="fa fa-frown-o"></i>
			<div class="header">Uuups, her gikk noe galt</div>
			<c:if test="${exception}">
				<p>${exception.message}</p>
				<p><% exception.printStackTrace(response.getWriter()); %></p>
			</c:if>
		</div>
	</div>



	<script src="public/js/jquery.min.js"></script>
	<script src="public/js/moment.min.js"></script>
	<script src="public/js/jquery-ui.min.js"></script>
	<script src="public/js/semantic.min.js"></script>
	<script src='public/js/fullcalendar.min.js'></script>
	<script src='public/js/scheduler.min.js'></script>
	<script src="public/js/script.js"></script>

	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js.map"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/components/dropdown.min.js"></script>

</body>
</html>