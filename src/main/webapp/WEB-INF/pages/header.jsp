<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="jakarta.servlet.http.HttpServletRequest"%>

<%
// Initialize necessary objects and variables
HttpSession userSession = request.getSession(false);
String currentUser = (String) (userSession != null ? userSession.getAttribute("username") : null);
// need to add data in attribute to select it in JSP code using JSTL core tag
pageContext.setAttribute("currentUser", currentUser);
%>

<!-- Set contextPath variable -->
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


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
        <div class="nav">
            <ul class="main-nav">
                <li><a class="nav-button" href="home">Home</a></li>
                <li><a class="nav-button" href="admindashboard">About</a></li>
                <li><a class="nav-button" href="explore">Explore</a></li>
                <li><a class="nav-button" href="profile">Profile</a></li>
				<li>
					<c:choose>
							<c:when test="${not empty currentUser}">
								<form action="${contextPath}/logout" method="post">
									<input type="submit" class="nav-button" value="Logout" />
								</form>
							</c:when>
							<c:otherwise>
								<a href="${contextPath}/login">Login</a>
							</c:otherwise>
					</c:choose>
				</li>
            </ul>
        </div>
    </header>
</div>
