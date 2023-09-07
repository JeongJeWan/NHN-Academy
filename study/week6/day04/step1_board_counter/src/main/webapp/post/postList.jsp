<%--
Created by IntelliJ IDEA.
User: jeongjewan
Date: 2023/04/05
Time: 12:45 PM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfmt" uri="http://nhnacademy.com/cfmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
<head>
  <title><fmt:message key="postList"/></title>
  <link rel="stylesheet" href="/style.css" />
</head>
<body>
<h1><fmt:message key="postList"/></h1>
<p><a href="/postRegister.do" ><fmt:message key="postRegister"/></a></p>
<p><a href="/logout.do"><fmt:message key="logout"/></a></p>
<p><a>로그인한 사용자 수: ${loginCounter},  현재 방문자수: ${currentCounter}</a></p>
<table>
  <thead>
  <tr>
    <th><fmt:message key="postId"/></th>
    <th><fmt:message key="postTitle"/></th>
    <th><fmt:message key="postContent"/></th>
    <th><fmt:message key="postWriterUserId"/></th>
    <th><fmt:message key="postWrtieTime"/></th>
    <th><fmt:message key="postViewCount"/></th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="post" items="${posts}">
    <tr>
      <td>${post.getId()}</td>
      <td>
        <c:url var="postView_link" value="/postView.do" scope="request">
          <c:param name="id" value="${post.id}" />
        </c:url>
        <a href="${postView_link}">${post.getTitle()}</a>
      </td>
      <td>${post.getContent()}</td>
      <td>
        <c:url var="userList_link" value="/userList.do" scope="request">
          <c:param name="id" value="${post.id}" />
        </c:url>
        <a href="${userList_link}">${post.getWriterUserId()}</a>
      </td>
      <td>${cfmt:formatDate(post.writeTime, 'yyyy-MM-dd HH:mm:ss')}</td>
      <td>${post.getViewCount()}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</fmt:bundle>
</html>