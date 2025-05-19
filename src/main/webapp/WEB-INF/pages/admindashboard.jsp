<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.shaadimitra.model.PartnerMatch" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admindashboard.css" />
</head>
<body>

<jsp:include page="header.jsp" />

<main>
    <section class="stats">
        <div class="stat-box">
            <div class="stat-number"><%= request.getAttribute("totalUsers") %></div>
            <div class="stat-label">Total Users</div>
        </div>
        <div class="stat-box">
            <div class="stat-number"><%= request.getAttribute("totalMatches") %></div>
            <div class="stat-label">Matches</div>
        </div>
        <div class="stat-box">
            <div class="stat-number">15</div>
            <div class="stat-label">Admins</div>
        </div>
    </section>

    <section class="bottom-section">
        <div class="circle">Match / User</div>

        <div class="match-table">
            <table>
                <caption>Recent Matches</caption>
                <thead>
                    <tr>
                        <th>S.N.</th>
                        <th>Partner One ID</th>
                        <th>Partner Two ID</th>
                        <th>Match %</th>
                        <th>Success</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<PartnerMatch> matchList = (List<PartnerMatch>) request.getAttribute("matchList");
                        int serial = 1;
                        if (matchList != null) {
                            for (PartnerMatch match : matchList) {
                    %>
                    <tr>
                        <td colspan="7">
                            <form action="${pageContext.request.contextPath}/admindashboard" method="post">
                                <div class="form-row">
                                    <div><strong><%= serial++ %></strong></div>

                                    <div>
                                        <input type="hidden" name="partnerone_id" value="<%= match.getPartnerone_id() %>" />
                                        <%= match.getPartnerone_id() %>
                                    </div>

                                    <div>
                                        <input type="hidden" name="partnertwo_id" value="<%= match.getPartnertwo_id() %>" />
                                        <%= match.getPartnertwo_id() %>
                                    </div>

                                    <div>
                                        <input type="number" name="matchPercent" value="<%= match.getMatchPercent() %>" min="0" max="100" />
                                    </div>

                                    <div>
                                        <input type="checkbox" name="success" <%= match.isSuccess() ? "checked" : "" %> />
                                    </div>

                                    <div>
                                        <input type="text" name="matchStatus" value="<%= match.getMatchStatus() %>" />
                                    </div>

                                    <div>
                                        <button type="submit" class="submit-button">Update</button>
                                    </div>
                                </div>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="7">No matches found.</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </section>
</main>

</body>
</html>
