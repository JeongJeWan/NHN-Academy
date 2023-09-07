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
<html>
<head>
  <title>게시물-조회</title>
  <link rel="stylesheet" href="/style.css" />
</head>
<body>
<table>
  <tbody>

  <tr>
    <th>게시물 아이디</th>
    <td>${post.getId()}</td>
  </tr>

  <tr>
    <th>제목</th>
    <td>${post.getTitle()}</td>
  </tr>

  <tr>
    <th>본문</th>
    <td>${post.getContent()}</td>
  </tr>
  <tr>
    <th>작성자</th>
    <td>${post.getWriterUserId()}</td>
  </tr>

  <tr>
    <th>등록일</th>
    <td>${cfmt:formatDate(post.writeTime, 'yyyy-MM-dd HH:mm:ss')}</td>
  </tr>

  </tbody>
</table>
<ul>
  <li><a href="/postList.do">리스트</a></li>
  <li>
    <c:url var="postUpdate_link" value="/postUpdate.do" >
      <c:param name="id" value="${post.getId()}" />
    </c:url>
    <a href="${postUpdate_link}">수정</a>
  </li>

  <li>
    <form method="post" action="/postDelete.do">
      <input type="hidden" name="id" value="${post.getId()}"/>
      <button type="submit">삭제</button>
    </form>
  </li>

</ul>

</body>
</html>
