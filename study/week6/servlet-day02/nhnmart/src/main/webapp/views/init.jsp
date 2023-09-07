<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/04
  Time: 8:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<body>
<h2>식품 매대가 준비되었습니다.</h2>

<table>
  <thead>
  <tr>
    <th>상품명</th>
    <th>가격</th>
    <th>수량</th>
  </tr>
  </thead>
  <tbody>
  <jsp:useBean id="foods" scope="request" type="java.util.List"/>
  <c:forEach items="${foods}" var="food">
    <tr>
      <td>${food.name}</td>
      <td>${food.price}</td>
      <td>${food.quantity}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<a href="${pageContext.request.contextPath}/foods">상품 목록</a>
</body>
</html>
