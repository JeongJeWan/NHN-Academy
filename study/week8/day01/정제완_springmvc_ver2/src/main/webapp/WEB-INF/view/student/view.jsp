<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/17
  Time: 5:10 pM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfmt" uri="http://nhnacademy.com/cfmt" %>
<html>
<head>
  <link rel="stylesheet" href="/resources/style.css">
  <title>학생-조회</title>

</head>
<body>
<jsp:include page="/WEB-INF/view/login/loginInfo.jsp" />
<table>
  <tbody>
    <tr>
      <td>아이디</td>
      <td>${student.id}</td>
    </tr>
    <tr>
      <td>이름</td>
      <td>${student.name}</td>
    </tr>
    <tr>
      <td>성별</td>
      <td>${student.gender}</td>
    </tr>
    <tr>
      <td>나이</td>
      <td>${student.age}</td>
    </tr>
    <tr>
      <th>등록일</th>
      <td>${cfmt:formatDate(student.createdAt, 'yyyy-MM-dd HH:mm:ss')}</td>
    </tr>
  </tbody>
</table>
<ul>
  <li><a href="/student/list.do">리스트</a></li>
  <li>
    <form method="get" action="/student/update.do">
      <input type="hidden" name="id" value="${student.id}" />
      <button type="submit">수정</button>
    </form>
  </li>
  <li>
    <form method="post" action="/student/delete.do">
      <input type="hidden" name="id" value="${student.id}" />
      <button type="submit">삭제</button>
    </form>
  </li>
</ul>
</body>
</html>