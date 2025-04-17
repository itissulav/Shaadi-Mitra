<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Register</title>
	
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/register.css" />
	
	<style>
		@font-face {
		  font-family: 'Amsterdam';
		  src: url('${pageContext.request.contextPath}/resources/fonts/AmsterdamOne-eZ12l.ttf') format('truetype');
		}
	</style>
	
</head>
<body>

	<jsp:include page="header.jsp"/>
	
	<div class="register-box">
		<h2>Create an Account</h2>
		
		<h1>Registration Form</h1>
		<%
		String errorMessage = (String) request.getAttribute("error");
		String successMessage = (String) request.getAttribute("success");

		if (errorMessage != null && !errorMessage.isEmpty()) {
			out.println("<p class=\"error-message\">" + errorMessage + "</p>");
		}

		if (successMessage != null && !successMessage.isEmpty()) {
		%>
		<p class="success-message"><%=successMessage%></p>
		<%
		}
		%>

		<form action="${pageContext.request.contextPath}/register" method="post">
			<div class="row">
				<div class="col">
					<label for="firstName">First Name:</label> <input type="text"
						id="firstName" name="firstName" required>
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<label for="lastName">Last Name:</label> <input type="text"
						id="lastName" name="lastName" required>
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<label for="gender">Gender:</label> <input type="text"
						id="gender" name="gender" required>
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<label for="number">Phone Number:</label> <input type="text"
						id="number" name="number" required>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<label for="dob">Date of Birth:</label> <input type="date"
						id="dob" name="dob" required>
				</div>
			</div>		

			<div class="row">
				<div class="col">
					<label for="userName">Username:</label> <input type="text"
						id="userName" name="userName" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="email">Email:</label> <input type="email"
						id="email" name="email" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> <input type="password"
						id="password" name="password" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="confirm-password">Confirm Password:</label> <input type="password"
						id="confirmpassword" name="confirmpassword" required>
				</div>
			</div>
			<button type="submit" class="register-button">Register</button>
		</form>
		<hr class="line">
		<a href="login" class="register">Already have an account? Login</a>
	</div>

</body>
</html>