<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="jakarta.servlet.http.HttpServletRequest"%>

<%
String role = "partner";
HttpSession userSession = request.getSession(false);
String username = null;

if (userSession != null) {
    username = (String) userSession.getAttribute("username");
    if ("admin".equals(username)) {  // avoid NullPointerException
        role = "admin";
    }
}
String currentUser = username; // this will be null if session is null
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
                
                <% if (role.equals("admin")) { %>
                	<li><a class="nav-button" href="admindashboard">Dash</a></li>
                <%} else { %>
                	<li><a class="nav-button" href="about">About</a></li>
                <%} %>
                <% if (role.equals("admin")) { %>
                	<li><a class="nav-button" href="database">Database</a></li>
                <%} else { %>
                	<li><a class="nav-button" href="explore">Explore</a></li>
                <%} %>
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
