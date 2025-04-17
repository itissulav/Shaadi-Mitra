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
	
	<div class = "review">
	
		<div class = "review_card">
			<img alt="Jack and Rose" src="${pageContext.request.contextPath}/resources/images/system/jackandrose.jpg">
			<h2 class = 'title'>Jack and Rose</h2>
			<div>
				<p> Age: 23</p>
				<p> Religion: AI</p>
				<p> Salary: 23</p>
			</div>
		</div>
		
		<div class = "review_card">
			<img alt="Jack and Rose" src="${pageContext.request.contextPath}/resources/images/system/jackandrose.jpg">
			<h2 class = 'title'>Jack and Rose</h2>
			<div>
				<p> Age: 23</p>
				<p> Religion: AI</p>
				<p> Salary: 23</p>
			</div>
		</div>
		
		<div class = "review_card">
			<img alt="Jack and Rose" src="${pageContext.request.contextPath}/resources/images/system/jackandrose.jpg">
			<h2 class = 'title'>Jack and Rose</h2>
			<div>
				<p> Age: 23</p>
				<p> Religion: AI</p>
				<p> Salary: 23</p>
			</div>
		</div>
		
		<div class = "review_card">
			<img alt="Jack and Rose" src="${pageContext.request.contextPath}/resources/images/system/jackandrose.jpg">
			<h2 class = 'title'>Jack and Rose</h2>
			<div>
				<p> Age: 23</p>
				<p> Religion: AI</p>
				<p> Salary: 23</p>
			</div>
		</div>
		
		<div class = "review_card">
			<img alt="Jack and Rose" src="${pageContext.request.contextPath}/resources/images/system/jackandrose.jpg">
			<h2 class = 'title'>Jack and Rose</h2>
			<div>
				<p> Age: 23</p>
				<p> Religion: AI</p>
				<p> Salary: 23</p>
			</div>
		</div>
		
		<div class = "review_card">
			<img alt="Jack and Rose" src="${pageContext.request.contextPath}/resources/images/system/jackandrose.jpg">
			<h2 class = 'title'>Jack and Rose</h2>
			<div>
				<p> Age: 23</p>
				<p> Religion: AI</p>
				<p> Salary: 23</p>
			</div>
		</div>
				
	</div>
	
	

<script>
  const reviewContainer = document.querySelector('.review');

  reviewContainer.addEventListener('wheel', function(e) {
    // If scrolling horizontally (shift key) or deltaX is used, allow it
    if (Math.abs(e.deltaX) > Math.abs(e.deltaY)) return;

    // Otherwise, prevent vertical scroll from turning into horizontal scroll
    e.preventDefault();
  }, { passive: false });
</script>
	
</body>
</html>