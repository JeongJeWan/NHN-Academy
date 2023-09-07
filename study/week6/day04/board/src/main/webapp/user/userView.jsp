<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/08
  Time: 9:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>사용자-조회</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<table>
    <tbody>

    <tr>
        <th>아이디</th>
        <td>${user.id}</td>
    </tr>

    <tr>
        <th>비밀번호</th>
        <td>${user.password}</td>
    </tr>

    <tr>
        <th>사용자이름</th>
        <td>${user.name}</td>
    </tr>

    <tr>
        <th>프로필이름</th>
            <%--        <td>${user.getProfileFileName()}</td>--%>
        <td>${user.profileFileName}</td>
    </tr>

    </tbody>
</table>
<ul>
    <li><a href="/userList.do">리스트</a></li>
    <li>
        <c:url var="userUpdate_link" value="/userUpdate.do" >
            <c:param name="id" value="${user.id}" />
        </c:url>
        <a href="${userUpdate_link}">수정</a>
    </li>
    <li>
        <form method="post" action="/userDelete.do">
            <input type="hidden" name="id" value="${user.id}" />
            <button type="submit">삭제</button>
        </form>
    </li>

</ul>

</body>
</html>