<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="jakarta.servlet.http.HttpServletRequest"%>

<%
// Initialize necessary objects and variables
HttpSession userSession = request.getSession(false);
String currentUser = (String) (userSession != null ? userSession.getAttribute("username") : null);

String contextPath = request.getContextPath();

String actionUrl;
String formMethod;
String buttonLabel;

if (currentUser != null) {
	actionUrl = contextPath + "/logout";
	formMethod = "post";
	buttonLabel = "Logout";
} else {
	actionUrl = contextPath + "/login";
	formMethod = "get";
	buttonLabel = "Login";
}
%>

<style>
	@font-face {
  font-family: 'Montserrat';
  src: url('${pageContext.request.contextPath}/resources/fonts/Montserrat-VariableFont_wght.ttf') format('truetype');
}

</style>

<div id="header">
	<header class="header">
		<div class="logo">
			<h1>SHAADI MITRA</h1>
		</div>
		<div class = "nav-button">
			<ul class="main-nav">
				<li><a href="home">Home</a></li>
				<li><a href="about">About</a></li>
				<li><a href="explore">Explore</a></li>
				<li><a href="contact">Contact</a></li>
			<li>
				<form action="<%=actionUrl%>" method="<%=formMethod%>">
					<input type="submit" value="<%=buttonLabel%>" />
				</form>
			</li>
			</ul>
		</div>
	</header>
</div>