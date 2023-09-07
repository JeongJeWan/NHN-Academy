<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/05/03
  Time: 7:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource"%>
<%@ page import="com.nhnacademy.airport.Member" %>
<%@ page import="com.nhnacademy.airport.DbUtilsModule12" %>

<% int pages = Integer.parseInt(request.getParameter("page")); %>

<%!
    private static List<Member> memberList = new ArrayList<>();

    public List<Member> getData(int page) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        int perpage = 20;
        int offset = (page -1) * 20;

        try {
            DataSource dataSource = DbUtilsModule12.getDataSource();
            connection = dataSource.getConnection();
            String sqlQuery = "SELECT id, name, city FROM Members LIMIT ?, ?";
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, offset);
            statement.setInt(2, perpage);

            result = statement.executeQuery();

            while (result.next()) {
                memberList.add(
                        new Member(result.getString(1), result.getString(2), result.getString(3))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return memberList;
    }

    public static int getPage() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        int totalPage = 1;

        try {
            DataSource dataSource = DbUtilsModule12.getDataSource();
            connection = dataSource.getConnection();
            String sqlQuery = "SELECT COUNT(*) FROM Members";
            statement = connection.prepareStatement(sqlQuery);
            result = statement.executeQuery();

            while (result.next()) {
                totalPage = result.getInt(1);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return totalPage;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>멤버 목록</title>
    <link rel="stylesheet" href="style.css">
</head>
<%List<Member> members; members = getData(pages); %>
<%  int totalPages = (int) Math.ceil(getPage() / (double)20);
    int startPage = ((pages-1) / 20) * 20 + 1;
    int endPage = Math.min(startPage + 19, totalPages);
%>
<body>
<h2>멤버 목록</h2>
<table>
    <tr>
        <td>아이디</td>
        <td>이름</td>
        <td>도시</td>
    </tr>
    <% for(Member m : members) {%>
    <tr>
        <td><%=m.getId()%></td>
        <td><%=m.getName()%></td>
        <td><%=m.getCity()%></td>
    </tr>
    <%}%>
</table>
<h2><a href="passenger.jsp">승객 목록</a> </h2>
<div class="pagination">
    <% if (startPage > 1) {%>
        <a href="?page=<%=startPage - 1%>">이전</a>
    <%}%>
    <% for (int i=startPage; i <= endPage; i++) {%>
        <span><a href="member.jsp?page=<%=i%>"><%=i%></a> </span>
    <%}%>
    <% if (endPage < totalPages) {%>
        <a href="?page=<%=endPage + 1%>">다음</a>
    <%}%>
</div>

</body>
<%members.clear();%>
</html>
