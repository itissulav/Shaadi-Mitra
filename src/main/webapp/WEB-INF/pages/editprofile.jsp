<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	<form class="form" action="${pageContext.request.contextPath}/editprofile" method="post">
	  <div class="field">
	    <label>Name</label>
	    <input type="text" name="firstName" value="Sulav">
	    <div class="sub-label">First Name</div>
	  </div>
	  <div class="field">
	    <label>&nbsp;</label>
	    <input type="text" name="lastName" value="Paudel">
	    <div class="sub-label">Last Name</div>
	  </div>
	
	  <div class="field">
	    <label>Date of Birth</label>
	    <input type="date" name="dob" value="2006-01-30">
	    <div class="sub-label">YYYY/MM/DD</div>
	  </div>
	
	  <div class="field">
	    <label>Salary</label>
	    <input type="text" name="salary" value="100000">
	    <div class="sub-label">In USD per Year</div>
	  </div>
	
	  <div class="field">
	    <label>Gender</label>
	    <input type="text" name="gender" value="Male">
	  </div>
	
	  <div class="field">
	    <label>Password</label>
	    <input type="password" name="password">
	  </div>
	
	  <div class="field">
	    <label>Religion</label>
	    <input type="text" name="religion" value="Hindu">
	  </div>
	
	  <div class="field">
	    <label>Username</label>
	    <input type="text" name="userName" value="sulavingg">
	  </div>
	
	  <div class="field">
	    <label>Profession</label>
	    <input type="text" name="profession" value="Engineer">
	  </div>
	
	  <div class="field">
	    <label>Email</label>
	    <input type="email" name="email" value="example@email.com">
	  </div>
	
	  <div class="field">
	    <label>Phone Number</label>
	    <input type="text" name="number" value="9876543210">
	  </div>
	  
	  <div class="buttons">
		  <button type="submit">Save Profile</button>
		  <button type="reset" class="discard">Discard</button>
	</div>
	  
	</form>


  
</body>
</html>
</body>
</html>