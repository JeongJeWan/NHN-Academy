<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/04
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Success</title>
    <style>
        body {
            background-color: #f5f5f5;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        h1 {
            text-align: center;
            color: #0077cc;
        }
        p {
            text-align: center;
            font-size: 18px;
        }
        a {
            color: #0077cc;
            text-decoration: none;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
    <body>
        <h1>loginSuccess</h1>
        <p><a href="/logout.do">logout</a></p>
        <c:choose>
            <c:when test="${not empty admin}">
                <p><a href="/userList.do">userList</a></p>
            </c:when>
            <c:when test="${empty admin}">
                <p><a href="/postList.do">postList</a></p>
            </c:when>
        </c:choose>
    </body>
</fmt:bundle>
</html>

