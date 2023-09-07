<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/04
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
Created by IntelliJ IDEA.
User: jeongjewan
Date: 2023/04/04
Time: 8:28 PM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<body>
<c:if test="${not empty cart.itemList}">
  <h2>장바구니</h2>
  <table border="1">
    <tr>
      <th>상품명</th>
      <th>가격</th>
      <th>수량</th>
      <th>합계</th>
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
      <th colspan="3">전체 금액</th>
      <td>
        <c:set var="totalAmount" value="0" />
        <c:forEach items="${cart.itemList}" var="item">
          <c:set var="totalAmount" value="${totalAmount + item.price * item.amount}" />
        </c:forEach>
          ${totalAmount}
      </td>
    </tr>
  </table>
  <a href="/foods.do">상품 목록</a>
</c:if>
<c:if test="${empty cart.itemList}">

  <h2>장바구니가 비어 있습니다.</h2>
</c:if>
</body>
</html>