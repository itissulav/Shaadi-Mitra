<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/home.css" />

<style>
		@font-face {
  font-family: 'Amsterdam';
  src: url('${pageContext.request.contextPath}/resources/fonts/AmsterdamOne-eZ12l.ttf') format('truetype');
}
</style>
	
</head>
<body>

	<jsp:include page="header.jsp"/>

	<div class="main-body">
	
		<div class = greeting-image>
			<img alt="Greeting Image" src="${pageContext.request.contextPath}/resources/images/system/greetingImage.png">
		</div>
		
		<div class = "greeting">
			<h2>WELCOME TO</h2>
			<h1>Shaadi Mitra</h1>
			<h3>Your Partner in Finding the Perfect Partner</h3>
		</div>		
		
	</div>
	
	<a href="login" class="find-mitra-btn">Find My Mitra</a>
	

</body>
</html>