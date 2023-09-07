<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/05/03
  Time: 10:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.nhnacademy.airport.AircraftData" %>
<%@ page import="com.nhnacademy.airport.DbUtils" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String i = request.getParameter("aircraftNo"); %>
<!DOCTYPE html>
<%!
    List<AircraftData> aircraftDataList = new ArrayList<>();

    public List<AircraftData> getData(String i) {
        DataSource dataSource = DbUtils.getDataSource();
        Connection myConnection = null;
        PreparedStatement statement = null;
        ResultSet  result = null;
        try {
            myConnection = dataSource.getConnection();
            String sqlQuery = "SELECT aircraftNo, kindOfAircraft, airline FROM Aircraft WHERE aircraftNo = ?";
            statement = myConnection.prepareStatement(sqlQuery);
            statement.setString(1, i);
            result = statement.executeQuery();

            while (result.next()) {
                this.aircraftDataList.add(
                        new AircraftData(result.getInt(1), result.getString(2), result.getString(3))
                );
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                result.close();
                statement.close();
                myConnection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return aircraftDataList;
    }
%>
<html>
<head>
    <title>항공기 정보</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>항공기 정보</h2>
<%List<AircraftData> aircraftDatas = new ArrayList<>(); aircraftDatas=getData(i);%>
<table>
    <tr>
        <td>항공기번호</td>
        <td>항공기종류</td>
        <td>항공사</td>
    </tr>
    <% for(AircraftData a : aircraftDatas) {%>
    <tr>
        <td><%=a.getAircraftNo()%></td>
        <td><%=a.getKindOfAircraft()%></td>
        <td><%=a.getAirline()%></td>
    </tr>
    <%}%>
</table>
<h2><a href="passenger.jsp">승객 목록</a> </h2>
<%aircraftDatas.clear();%>
</body>
</html>
