<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/04
  Time: 8:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Food List</title>
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
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
<body>
<c:if test="${not empty foods}">
  <h2><fmt:message key="foodList"/></h2>
  <table border="1">
    <tr>
      <th><fmt:message key="name"/></th>
      <th><fmt:message key="price"/></th>
      <th><fmt:message key="stock"/></th>
      <th><fmt:message key="basket"/></th>
    </tr>
    <c:forEach items="${foods}" var="food">
      <tr>
        <td>${food.name}</td>
        <td>${food.price}</td>
        <td>${food.quantity}</td>
        <td>
          <form method="post" action="/cart.do">
            <input type="hidden" name="name" value="${food.name}">
            <input type="hidden" name="price" value="${food.price}">
            <label>
              <input type="number" name="quantity" min="1" max="${food.quantity}" required>
            </label>
            <input type="submit" value="담기">
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>
</c:if>

<c:if test="${empty foods}">
  <h2><fmt:message key="foodNone"/></h2>
</c:if>

</body>
</fmt:bundle>
</html>
