<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/05
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
<html>
<head>
  <title><fmt:message key="postRegister"/></title>
  <link rel="stylesheet" href="/style.css" />
  <meta charset="UTF-8" />
</head>

<body>
<c:choose>
  <c:when test="${empty post}">
    <c:set var="action" value="/postRegister.do" />
  </c:when>
  <c:otherwise>
    <c:set var="action" value="/postUpdate.do" />
  </c:otherwise>
</c:choose>

<form method="post" action="${action}">
  <table>
    <tbody>
    <tr>
      <th><fmt:message key="postWriterUserId"/></th>
      <td><input type="text" name="writerUserId" disabled value="${post.getWriterUserId()}" /></td>
    </tr>
    <tr>
      <th><fmt:message key="postTitle"/></th>
      <td><input type="text" name="title" value="${post.getTitle()}" required /></td>
    </tr>
    <tr>
      <th><fmt:message key="postContent"/></th>
      <td><input type="text" name="content" value="${post.getContent()}" required /></td>
    </tr>
    </tbody>
  </table>
  <p>
    <button type="submit">
      <input type="hidden" name="id" value="${post.getId()}" />
      <input type="hidden" name="writerUserId" value="${post.getWriterUserId()}" />
      <c:choose>
        <c:when test="${empty post}">
          <fmt:message key="postRegister"/>
        </c:when>
        <c:otherwise>
          <fmt:message key="postModify"/>
        </c:otherwise>
      </c:choose>
    </button>
  </p>
</form>
</body>
</fmt:bundle>
</html>
