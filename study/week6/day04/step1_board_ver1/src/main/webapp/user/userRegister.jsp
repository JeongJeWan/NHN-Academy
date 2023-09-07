<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/08
  Time: 9:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
<html>
<head>
  <title><fmt:message key="userRegister"/></title>
  <link rel="stylesheet" href="/style.css" />
  <meta charset="UTF-8" />
</head>

<body>
<c:choose>
  <c:when test="${empty user}">
    <c:set var="action" value="/userRegister.do" />
  </c:when>
  <c:otherwise>
    <c:set var="action" value="/userUpdate.do" />
  </c:otherwise>
</c:choose>

<form method="post" action="${action}">
  <table>
    <tbody>
    <tr>
      <th><fmt:message key="userId"/></th>
      <td><input type="text" name="id" value="${user.id}" required /></td>
    </tr>
    <tr>
      <th><fmt:message key="userPassword"/></th>
      <td><input type="text" name="password" value="${user.password}" required /></td>
    </tr>
    <tr>
      <th><fmt:message key="userName"/></th>
        <td><input type="text" name="name" value="${user.name}" required /></td>
    </tr>
    <tr>
      <th><fmt:message key="userProfileFileName"/></th>
      <td><input type="text" name="profileFileName" value="${user.profileFileName}" required /></td>
    </tr>
    </tbody>
  </table>
  <p>
    <button type="submit">
      <c:choose>
        <c:when test="${empty user}">
          <fmt:message key="userRegister"/>
        </c:when>
        <c:otherwise>
          <fmt:message key="userModify"/>
        </c:otherwise>
      </c:choose>
    </button>
  </p>
</form>
</body>
</fmt:bundle>
</html>
