<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About us</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/about.css" />

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
	<div class = 'container'>
		<div class = 'img-container'>
			<img alt="Greeting Image" src="${pageContext.request.contextPath}/resources/images/system/aboutPage.png">
		</div>
		
		<div class = 'text-container'>
			<p>At <span class = 'title'>Shaadi Mitra</span> we celebrate the essence of Indian marriages — where families unite,
                    traditions are honored, and love blooms in the most beautiful ways. Our platform is crafted to help
                    individuals find not just a partner, but a soulmate who understands the values that make our culture unique.</p>
            
            <p>
                    Whether you're seeking a traditional match or a modern connection with deep roots, Patni.com is your trusted
                    companion in this journey.
            </p>
		</div>
	</div>
	
	<h1>Meet our Users</h1>
	
	<div class = "review">
	
		<div class = "review_card">
			<img alt="Jack and Rose" src="${pageContext.request.contextPath}/resources/images/system/jackandrose.jpg">
			<h2 class = 'title'>Jack and Rose</h2>
			<p>Jack and Rose met on Shaadi Mitra, now they are a happy couple going on a cruise ship across the world.</p>
		</div>
		
		<div class = "review_card">
			<img alt="Jack and Rose" src="${pageContext.request.contextPath}/resources/images/system/romeoandjuliet.jpg">
			<h2 class = 'title'>Romeo and Juliet</h2>
			<p>Romeo and Juliet met on Shaadi Mitra. They are a happy couple now. There have been multiple movies made on their love.</p>
		</div>
		
		<div class = "review_card">
			<img alt="Jack and Rose" src="${pageContext.request.contextPath}/resources/images/system/ronandhermione.jpg">
			<h2 class = 'title'>Ron and Hermione</h2>
			<p>Ron and Hermione had their first conversation through Shaadi Mitra back when they were students.</p>
		</div>
		
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>