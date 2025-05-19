<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.shaadimitra.model.PartnerModel" %>
<%@ page import = "com.shaadimitra.service.EditProfileService" %>
<%@ page import = "java.time.LocalDate" %>

<%
	String username = (String) session.getAttribute("username");
	PartnerModel partner = (PartnerModel) session.getAttribute("partnerDetails");
	
	String firstName = partner.getFirst_name();
	String lastName = partner.getLast_name();
	String salary = String.valueOf(partner.getSalary());
	LocalDate dob = partner.getDob();
	String gender = partner.getGender();
	String profession = partner.getProfession();
	String email = partner.getEmail();
	String number = partner.getNumber();
	String religion = partner.getReligion();	
	String password = partner.getPassword();	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Profile</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/editprofile.css" />

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
	
	<div class = "form-container">

		<form class="form" action="${pageContext.request.contextPath}/editprofile" method="post">
		  <div class="field">
		    <label>Name</label>
		    <input type="text" name="firstName" value="<%= firstName %>">
		    <div class="sub-label">First Name</div>
		  </div>
		  <div class="field">
		    <label>&nbsp;</label>
		    <input type="text" name="lastName" value="<%= lastName %>">
		    <div class="sub-label">Last Name</div>
		  </div>
		
		  <div class="field">
		    <label>Date of Birth</label>
		    <input type="date" name="dob" value="<%= dob %>">
		    <div class="sub-label">DD/MM/YYYY</div>
		  </div>
		
		  <div class="field">
		    <label>Salary</label>
		    <input type="text" name="salary" value="<%= salary %>">
		    <div class="sub-label">In USD per Year</div>
		  </div>
		
		  <div class="field">
		    <label>Gender</label>
		    <input type="text" name="gender" value="<%= gender %>">
		  </div>
		
		  <div class="field">
		    <label>Religion</label>
		    <input type="text" name="religion" value="<%= religion %>">
		  </div>
		
		  <div class="field">
		    <label>Username</label>
		    <p><input type="hidden" name="userName" value="<%= username %>"><%= username %></p>
		  </div>
		
		  <div class="field">
		    <label>Profession</label>
		    <input type="text" name="profession" value="<%= profession %>">
		  </div>
		
		  <div class="field">
		    <label>Email</label>
		    <input type="email" name="email" value="<%= email %>">
		  </div>
		
		  <div class="field">
		    <label>Phone Number</label>
		    <input type="text" name="number" value="<%= number %>">
		  </div>
		  
		  <div class="buttons">
			  <button type="submit" name = "action" value = "save">Save Profile</button>
			  <button type="submit" class="discard" name = "action" value = "discard">Discard</button>
		</div>
		  
		</form>

	</div>
	


  
</body>
</html>
</body>
</html>