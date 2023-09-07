<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/17
  Time: 5:10 pM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>student - list</title>
    <link rel="stylesheet" href="/resources/style.css">
    <meta charset="UTF-8" />
</head>
<body>
<jsp:include page="/WEB-INF/view/login/loginInfo.jsp" />
<h1>학생 리스트</h1>
<p><a href="/student/register.do" >학생(등록)</a></p>
<table border =1>
    <thead>
    <tr>
        <th>아이디</th>
        <th>이름</th>
        <th>성별</th>
        <th>나이</th>
        <th>cmd</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items = "${studentList}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.gender}</td>
            <td>${item.age}</td>
            <td>
                <c:url var="view_link" value="/student/view.do" scope="request">
                    <c:param name="id" value="${item.id}" />
                </c:url>
                <a href="${view_link}">조회</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>