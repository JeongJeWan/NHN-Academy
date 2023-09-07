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
<head>
  <title>게시물 리스트</title>
  <link rel="stylesheet" href="/style.css" />
</head>
<body>
<h1>게시물 리스트</h1>
<p><a href="/postRegister.do" >게시물 등록</a></p>
<table>
  <thead>
  <tr>
    <th>게시물 아이디</th>
    <th>제목</th>
    <th>본문</th>
    <th>작성자</th>
    <th>작성 시간</th>
    <th>조회수</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="post" items="${posts}">록
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
</html>