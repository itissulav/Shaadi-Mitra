<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Explore</title>

	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/header.css" />
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/explore.css" />
	
	<style>
			@font-face {
	  font-family: 'Amsterdam';
	  src: url('${pageContext.request.contextPath}/resources/fonts/AmsterdamOne-eZ12l.ttf') format('truetype');
	}
	
		@font-face {
	  font-family: 'Montserrat';
	  src: url('${pageContext.request.contextPath}/resources/fonts/Montserrat-VariableFont_wght.ttf') format('truetype');
	}
	
	
	</style>

</head>
<body>
	
	<jsp:include page="header.jsp"/>
	
	<h1>Meet your Match</h1>
	
	<div class = "profile">
	
		<div class = "profile-card">
			<div class = "profile-card-image">
				<img alt="Jack and Rose" src="${pageContext.request.contextPath}/resources/images/system/jackandrose.jpg">
			</div>
			<div class = "profile-card-details">
				<div>
					<h2 class = 'title'>Jack and Rose</h2>
				</div>
				<div>
					<p> Age: 23</p>
					<p> Religion: AI</p>
					<p> Salary: 23</p>
				</div>
				
				<div class = "card-button-container">
					<button>Yes</button>
					<button>No</button>
				</div>
				
			</div>
		</div>				
	</div>
	
</body>
</html>