<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/08
  Time: 9:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>사용자-등록</title>
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
      <th>아이디</th>
      <td><input type="text" name="id" value="${user.id}" required /></td>
    </tr>
    <tr>
      <th>비밀번호</th>
      <td><input type="text" name="password" value="${user.password}" required /></td>
    </tr>
    <tr>
      <th>사용자이름</th>
        <td><input type="text" name="name" value="${user.name}" required /></td>
    </tr>
    <tr>
      <th>프로필일름</th>
      <td><input type="text" name="profileFileName" value="${user.profileFileName}" required /></td>
    </tr>
    </tbody>
  </table>
  <p>
    <button type="submit">
      <c:choose>
        <c:when test="${empty user}">
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
