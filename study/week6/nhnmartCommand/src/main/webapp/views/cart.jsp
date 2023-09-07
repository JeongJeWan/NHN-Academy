<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/04
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <title>장바구니</title>
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
<c:if test="${not empty cart.itemList}">
  <h2><fmt:message key="basket"/></h2>
  <table border="1">
    <tr>
      <th><fmt:message key="name"/></th>
      <th><fmt:message key="price"/></th>
      <th><fmt:message key="quantity"/></th>
      <th><fmt:message key="priceSum"/></th>
    </tr>
    <c:forEach items="${cart.itemList}" var="item">
      <tr>
        <td>${item.name}</td>
        <td>${item.price}</td>
        <td>${item.amount}</td>
        <td>${item.price * item.amount}</td>
      </tr>
    </c:forEach>
    <tr>
      <th colspan="3"><fmt:message key="totalPrice"/></th>
      <td>
        <c:set var="totalAmount" value="0" />
        <c:forEach items="${cart.itemList}" var="item">
          <c:set var="totalAmount" value="${totalAmount + item.price * item.amount}" />
        </c:forEach>
          ${totalAmount}
      </td>
    </tr>
  </table>
  <a href="/foods.do"><fmt:message key="foodList"/></a>
</c:if>
<c:if test="${empty cart.itemList}">

  <h2><fmt:message key="basketNone"/></h2>
</c:if>
</body>
</fmt:bundle>
</html>