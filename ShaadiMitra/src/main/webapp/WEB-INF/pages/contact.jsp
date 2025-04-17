<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Us</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/contact.css" />

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
	
	<h1>Get in Touch</h1>
	<p class="subtitle">We'd love to hear from you!</p>
	    
	    <div class="container">
	
		    <div class="contact-form-container">
			      <form action="submitContact.jsp" method="post" class="contact-form">
			        <input type="text" name="name" placeholder="Your Name" required />
			        <input type="email" name="email" placeholder="Your Email" required />
			        <input type="text" name="subject" placeholder="Subject" />
			        <textarea name="message" rows="5" placeholder="Your Message" required></textarea>
			        <button type="submit">Send Message</button>
			      </form>
		      </div>
	
		      <div class="contact-info">
			        <h3>Contact Information</h3>
			        <p><strong>Email:</strong> support@patni.com</p>
			        <p><strong>Phone:</strong> 9876543210</p>
			        <p><strong>Address:</strong> 123 Love Lane, Islington College, Nepal</p>
		      </div>
	    </div>
	    
</body>
</html>