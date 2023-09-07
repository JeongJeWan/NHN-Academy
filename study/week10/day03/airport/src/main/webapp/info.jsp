<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/05/02
  Time: 2:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.nhnacademy.airport.FlightData " %>
<%@ page import="com.nhnacademy.airport.DbUtils" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String i = request.getParameter("passenger");%>

<!DOCTYPE html>
<%!
    List<FlightData> flightDataList = new ArrayList<>();

    public List<FlightData> getData(String i){
        DataSource dataSource = DbUtils.getDataSource();
        Connection myConnection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            myConnection = dataSource.getConnection();
            String sqlQuery = "SELECT aircraftNo, reservedDate, deparetures, arrival, price, FlightDate From Passenger as p\n" +
                    "inner join reservation as r on p.PassengerNo = r.PassengerNo\n" +
                    "inner join flight as f on r.FlightNo = f.FlightNo where p.passengerNo = ?\n";
            statement= myConnection.prepareStatement(sqlQuery);
            statement.setString(1, i);
            result = statement.executeQuery();
            while(result.next()){
                this.flightDataList.add(new FlightData(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5), result.getString(6)));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                result.close();
                statement.close();
                myConnection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flightDataList;
    }

%>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>승객예약정보</h2>
<%List<FlightData> flightDataList = new ArrayList<>(); flightDataList=getData(i);%>
<table>
    <tr>
        <td>항공기번호</td>
        <td>예약 날짜</td>
        <td>출발지</td>
        <td>도착지</td>
        <td>가격</td>
        <td>출발 상세 시간</td>
    </tr>
    <% for(FlightData f : flightDataList){%>
    <tr>
        <td><a href="aircraft.jsp?aircraftNo=<%=f.getAircraftNo()%>"><%=f.getAircraftNo()%></a></td>
        <td><%=f.getReservationDate()%></td>
        <td><%=f.getDeparture()%></td>
        <td><%=f.getArrival()%></td>
        <td><%=f.getPrice()%></td>
        <td><%=f.getDepartureDetail()%></td>
    </tr>
    <%}%>
</table>
<h2><a href="passenger.jsp">승객 목록</a> </h2>
<%flightDataList.clear();%>
</body>
</html>