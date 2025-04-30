<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admindashboard.css" />

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
	
  <main>
    <section class="stats">
      <div class="stat-box">
        <div class="stat-number">12,000</div>
        <div class="stat-label">Total Users</div>
      </div>
      <div class="stat-box">
        <div class="stat-number">1000</div>
        <div class="stat-label">Matches</div>
      </div>
      <div class="stat-box">
        <div class="stat-number">15</div>
        <div class="stat-label">Admins</div>
      </div>
    </section>

    <section class="bottom-section">
      <div class="circle">Match/ User</div>

      <div class="match-table">
        <table>
          <caption>Recent Matches</caption>
          <thead>
            <tr>
              <th>S.N.</th>
              <th>Partner</th>
              <th>Partner</th>
              <th>Success</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>1</td><td>Jhon Doe</td><td>Jane Doe</td><td>80%</td>
            </tr>
            <tr>
              <td>2</td><td>Kavya</td><td>Sandhya</td><td>30%</td>
            </tr>
            <tr>
              <td>3</td><td>Elieot</td><td>Joel</td><td>45%</td>
            </tr>
            <tr>
              <td>4</td><td>Chris</td><td>Brown</td><td>15%</td>
            </tr>
            <tr>
              <td>5</td><td>Jack</td><td>Rose</td><td>5%</td>
            </tr>
          </tbody>
        </table>
      </div>
    </section>
  </main>

</body>
</html>