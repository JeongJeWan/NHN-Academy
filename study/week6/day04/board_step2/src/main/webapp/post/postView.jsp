<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/05
  Time: 1:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfmt" uri="http://nhnacademy.com/cfmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
<html>
<head>
  <title><fmt:message key="postView"/></title>
  <link rel="stylesheet" href="/style.css" />
</head>
<body>
<table>
  <tbody>

  <tr>
    <th><fmt:message key="postId"/></th>
    <td>${post.getId()}</td>
  </tr>

  <tr>
    <th><fmt:message key="postTitle"/></th>
    <td>${post.getTitle()}</td>
  </tr>

  <tr>
    <th><fmt:message key="postContent"/></th>
    <td>${post.getContent()}</td>
  </tr>
  <tr>
    <th><fmt:message key="postWriterUserId"/></th>
    <td>${post.getWriterUserId()}</td>
  </tr>

  <tr>
    <th><fmt:message key="postWrtieTime"/></th>
    <td>${cfmt:formatDate(post.writeTime, 'yyyy-MM-dd HH:mm:ss')}</td>
  </tr>

  <tr>
    <th><fmt:message key="postViewCount"/></th>
    <td>${post.getViewCount()}</td>
  </tr>

  </tbody>
</table>
<ul>
  <li><a href="/postList.do"><fmt:message key="postList"/></a></li>
  <li>
    <c:url var="postUpdate_link" value="/postUpdate.do" >
      <c:param name="id" value="${post.getId()}" />
    </c:url>
    <a href="${postUpdate_link}"><fmt:message key="postModify"/></a>
  </li>

  <li>
    <form method="post" action="/postDelete.do">
      <input type="hidden" name="id" value="${post.getId()}"/>
      <button type="submit"><fmt:message key="postDelete"/></button>
    </form>
  </li>
</ul>
</body>
</fmt:bundle>
</html>