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

<html>
<head>
  <title>학생-등록</title>
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
      <th>사용자</th>
      <td><input type="text" name="writerUserId" disabled value="${post.getWriterUserId()}" /></td>
    </tr>
    <tr>
      <th>제목</th>
      <td><input type="text" name="title" value="${post.getTitle()}" required /></td>
    </tr>
    <tr>
      <th>본문</th>
      <td><input type="text" name="content" value="${post.getContent()}" required /></td>
    </tr>
    </tbody>
  </table>
  <p>
    <button type="submit">
      <input type="hidden" name="postId" value="${post.getId()}" />
      <c:choose>
        <c:when test="${empty post}">
          등록
        </c:when>
        <c:otherwise>
          수정
        </c:otherwise>
      </c:choose>
    </button>
  </p>
</form>
</body>
</html>
