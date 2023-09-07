<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/17
  Time: 5:10 pM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="login-container">
    <form method="post" action="/login" >

    ID: <input type="text" name="userId"/>
    <c:if test="${id ==true}">
        <span style="color :red">userId is empty!</span>
    </c:if>
    <br />
    PWD: <input type="password" name="userPassword"/>
        <c:if test="${password ==true}">
            <span style="color :red">userPassword is empty!</span>
        </c:if>
        <br />
    <c:if test="${message ==true}">
        <p style="color :red">로그인 실패</p>
    </c:if>
    <p><button type="submit">로그인</button> </p>
    </form>
</div>
</body>
</html>
