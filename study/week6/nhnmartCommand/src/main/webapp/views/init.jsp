<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/04
  Time: 8:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.nhnacademy.nhnmart.util.CookieUtils" %>
<%
  Cookie cookie = CookieUtils.getCookie(request, "locale");
  String locale = "ko"; // default language is English
  if (cookie != null) {
    locale = cookie.getValue();
  }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Init</title>
  <style>
    table {
      border-collapse: collapse;
      width: 40%;
    }

    th, td {
      text-align: left;
      padding: 8px;
    }

    th {
      background-color: #4CAF50;
      color: white;
    }

    tr:nth-child(even) {
      background-color: #f2f2f2;
    }
  </style>
</head>
<fmt:setLocale value="<%=locale%>"/>
<fmt:bundle basename="message">
<body>
<h2><fmt:message key="foodReady"/></h2>

<table>
  <thead>
  <tr>
    <th><fmt:message key="name"/></th>
    <th><fmt:message key="price"/></th>
    <th><fmt:message key="quantity"/></th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${foods}" var="food">
    <tr>
      <td>${food.name}</td>
      <td>${food.price}</td>
      <td>${food.quantity}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<a href="/foods.do"><fmt:message key="foodList"/></a>
</body>
</fmt:bundle>
</html>
