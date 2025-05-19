<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "com.shaadimitra.controller.ProfileController" %>
<%@ page import = "com.shaadimitra.model.PartnerModel" %>

<%
	String username = (String) session.getAttribute("username");
	PartnerModel partner = (PartnerModel) session.getAttribute("userProfileDetails");
	List<String> imageList = (List<String>) session.getAttribute("imageList");
	
	String profileImage = partner.getProfileimage();
	String firstName = partner.getFirst_name();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/profile.css" />

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
	
	
		
	<div class = "profile-container">
		<div class = "image-grid">
			<% for (String image: imageList) { %>
			    <div class="image-box">
			        <img src="<%= request.getContextPath() %>/resources/images/gallery/<%= username %>/feed/<%= image%>" alt="photo <%= image %>" />
			    </div>
			<% } %>
			<form action="${pageContext.request.contextPath}/profile" method="post" class="image-box upload-box" enctype="multipart/form-data">
			    <label for="imageUpload" class="upload-label">+</label>
			    <input type="file" id="imageUpload" name="newImage" accept="image/*" onchange="this.form.submit()">
			</form>

		    
		</div>
		<div class = "details_container">
			<div class = "profile-image-container">
				<img src="<%= request.getContextPath() %>/resources/images/gallery/<%= username %>/<%= profileImage %>">
			</div>
			<h1 class = "user-name"><%= username %></h1>
			<div class = "profile-details">
				<div>
					<h1 class = "profile-details-count">5</h1>
					<h1 class = "profile-details-heading">Photos</h1>
				</div>

				<div>
					<h1 class = "profile-details-count">130</h1>
					<h1 class = "profile-details-heading">Likes</h1>	
				</div>
			
				<div>
					<h1 class = "profile-details-count">9</h1>
					<h1 class = "profile-details-heading">Matches</h1>
				</div>				
			</div>
			<h1 class = "user-name">Hello my name is <%=firstName %></h1>		
			
			<div class = "profile-buttons">
				<a href="editprofile">Edit Profile</a>
				<a href="#">Share Profile</a>
			</div>
					
		</div>
	</div>
	
</body>
</html>