<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.shaadimitra.util.ImageUtil" %>
<%@ page import="java.util.List" %>
<%@ page import = "com.shaadimitra.model.PartnerModel" %>

<% 
List<PartnerModel> partners = (List<PartnerModel>) session.getAttribute("partnerList"); 
Integer index = (Integer) session.getAttribute("partnerIndex");
 
PartnerModel currentPartner = null;
if (partners != null && index != null && index < partners.size()) {
    currentPartner = partners.get(index);
}
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Explore</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/explore.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" />

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

<% if (currentPartner != null) {
    List<String> imageList = ImageUtil.getUserImageFileNames(currentPartner.getUsername());
    String fullName = currentPartner.getFirst_name() + " " + currentPartner.getLast_name();
%>

  <div class="explore-wrapper">
    <div class="explore-content">
      <!-- Left Scrollable Image Panel -->
      <div class="group-left">
        <% for (String image: imageList) { %>
          <div class="image-box">
            <img src="<%= request.getContextPath() %>/resources/images/gallery/<%= currentPartner.getUsername() %>/feed/<%= image%>" alt="photo <%= image %>" />
          </div>
        <% } %>
      </div>

      <div class="group-center">
        <div class="group-center-top">
          <img src="<%= request.getContextPath() %>/resources/images/gallery/<%= currentPartner.getUsername() %>/<%= currentPartner.getProfileimage() %>" alt="profile photo">
        </div>
        <div class="group-center-bottom">
          <h1><%= fullName %></h1>
          <p>Hello, I am <%= fullName %>. Nice to Meet you</p>
          <div class="button-group">
            <form action="${pageContext.request.contextPath}/explore" method="post">
              <button type="submit" name="action" value="yes" class="yes-button">Yes</button>
              <button type="submit" name="action" value="no" class="no-button">No</button>
            </form>
          </div>
        </div>
      </div>

      <!-- Right Info Card -->
      <div class="group-right">
        <div class="group-right-top">
          <h2>Gender: <%= currentPartner.getGender() %></h2>
          <h2>Religion: <%= currentPartner.getReligion() %></h2>
          <h2>Profession: <%= currentPartner.getProfession() %></h2>
          <h2>Salary: <%= currentPartner.getSalary() %></h2>
        </div>
        <div class="group-right-bottom">
          <div>
            <h1 class="profile-details-count"><%= index %></h1>
            <h1 class="profile-details-heading">Index</h1>
          </div>
          <div>
            <h1 class="profile-details-count">9</h1>
            <h1 class="profile-details-heading">Matches</h1>
          </div>
          <div>
            <h1 class="profile-details-count">130</h1>
            <h1 class="profile-details-heading">Likes</h1>
          </div>
        </div>
      </div>
    </div>
  </div>

<% } else { %>
  <p>No more partners to show.</p>
<% } %>

</body>
</html>
