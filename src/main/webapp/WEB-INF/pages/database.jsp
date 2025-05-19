<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.shaadimitra.model.PartnerModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    List<PartnerModel> partners = (List<PartnerModel>) request.getAttribute("partners");

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin - Manage Partners</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/database.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" />
</head>
<body>

	<jsp:include page="header.jsp"/>

    <h1>Partner Management</h1>
    
		<form action="${pageContext.request.contextPath}/database" method="post" class="search-form">
		    <input type="text" name="search" placeholder="Search..." class="search-input" />
		    <button type="submit" class="search-button" name = "action" value = "search">Search</button>
		    <button type="submit" class="search-button" name = "action" value = "reset">Reset</button>
		</form>

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

    <table>
        <thead>
            <tr>
                <th>ID</th><th>First Name</th><th>Last Name</th><th>Gender</th><th>Salary</th><th>Religion</th><th>Profession</th><th>Email</th><th>Number</th><th>Username</th><th>DOB</th><th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="partner" items="${partners}">
                <form action="${pageContext.request.contextPath}/database" method="post">
                    <tr>
                        <td><input type="hidden" name="partner_id" value="${partner.partner_id}" />${partner.partner_id}</td>
                        <td><input type="text" name="first_name" value="${partner.first_name}" /></td>
                        <td><input type="text" name="last_name" value="${partner.last_name}" /></td>
                        <td><input type="text" name="gender" value="${partner.gender}" /></td>
                        <td><input type="number" name="salary" value="${partner.salary}" /></td>
                        <td><input type="text" name="religion" value="${partner.religion}" /></td>
                        <td><input type="text" name="profession" value="${partner.profession}" /></td>
                        <td><input type="text" name="email" value="${partner.email}" /></td>
                        <td><input type="text" name="number" value="${partner.number}" /></td>
                        <td><input type="text" name="username" value="${partner.username}" /></td>
                        <td><input type="date" name="dob" value="${partner.dob}" /></td>
                        <td>
                        	<button type="submit" name = "action" value = "save">Save</button>
                        	<button type="submit" name = "action" value = "delete">Delete</button>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            
                <form action="${pageContext.request.contextPath}/database" method="post">
                    <tr>
                    	
                    	<td><input type="hidden" name="partner_id"/></td>
                        <td><input type="text" name="first_name" value = "First Name"/></td>
                        <td><input type="text" name="last_name" value = "Last Name"/></td>
                        <td><input type="text" name="gender" value = "Gender"/></td>
                        <td><input type= "number" name="salary" value = "0"/></td>
                        <td><input type="text" name="religion" value = "Religion"/></td>
                        <td><input type="text" name="profession" value = "Profession"/></td>
                        <td><input type="text" name="email" value = "Email"/></td>
                        <td><input type="text" name="number" value = "Number"/></td>
                        <td><input type="text" name="username" value = "Username"/></td>
                        <td><input type="text" name="password" value = "Password"/></td>
                        <td><input type="date" name="dob" value = "Date of Birth"></td>
                        <td>
                        	<button type="submit" name = "action" value = "add">Add</button>
                        </td>
                    </tr>
                </form>

        </tbody>
    </table>
</body>
</html>
