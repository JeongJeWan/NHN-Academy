<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/05/02
  Time: 1:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.nhnacademy.airport.Passenger " %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<%!
  List<Passenger> passengerList = new ArrayList<>();
  String driverName = "com.mysql.cj.jdbc.Driver";
  String dataBaseUrl = "jdbc:mysql://localhost:3306/module06";
  String userName = "root";
  String userPwd = "258011";
  public static void loadDriver(String driverName){
    try {
      Class.forName(driverName);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  public List<Passenger> getData(){
    loadDriver(driverName);
    Connection myConnection = null;
    PreparedStatement statement = null;
    ResultSet result = null;
    try {
      myConnection = DriverManager.getConnection(dataBaseUrl, userName, userPwd);
      String sqlQuery = "SELECT PassengerNo, PassengerName, Grade, Age From Passenger";
      statement= myConnection.prepareStatement(sqlQuery);
      result = statement.executeQuery();
      while(result.next()){
        this.passengerList.add(new Passenger(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4)));
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
    return passengerList;
  }

%>
<html>
<head>
  <title>JSP - Hello World</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>승객목록</h2>
<%List<Passenger> passengers = new ArrayList<>();%>
<%passengers = getData();%>
<table>
  <tr>
    <td>번호</td>
    <td>이름</td>
    <td>등급</td>
    <td>나이</td>
  </tr>
  <% for(Passenger p : passengers){%>
  <tr>
    <td><%=p.getPassengerNo()%></td>
    <td><a href="info.jsp?passenger=<%=p.getPassengerNo()%>"><%=p.getPassengerName()%></a></td>
    <td><%=p.getGrade()%></td>
    <td><%=p.getAge()%></td>
  </tr>
  <%}%>
</table>
<h2><a href="member.jsp?page=1"> 멤버 확인하러 가기</a> </h2>
<%passengers.clear();%>
</body>
</html>

