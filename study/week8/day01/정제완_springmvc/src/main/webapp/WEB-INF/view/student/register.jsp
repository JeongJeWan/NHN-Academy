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
    <title>Title</title>
    <link rel="stylesheet" href="/resources/style.css">
    <meta charset="UTF-8" />
</head>
<body>
<jsp:include page="/WEB-INF/view/login/loginInfo.jsp" />
        <c:choose>
            <c:when test="${empty student}">
                <c:set var="action" value="/student/register.do" />
            </c:when>
            <c:otherwise>
                <c:set var="action" value="/student/update.do" />
            </c:otherwise>
        </c:choose>
        <form method="post" action=${action}>
        <tr>
        </tr>
    <h2>ID : <input type="text" name="id" value=${student.id}> </h2>
    <h2>이름 : <input type="text" name="name"> </h2>
    <h2>성별 : <input type="radio" name = "gender" value = "M"> 남 <input type="radio" name = "gender" value = "F"> 여
        <input type="radio" name = "gender" value = "C"> ??</h2>
    <h2>나이 : <input type="text" name="age"> </h2>
    <p>
        <button type="submit">
            <c:choose>
                <c:when test="${empty student}">
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
