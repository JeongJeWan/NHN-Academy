<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/04
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.nhnacademy.nhnmart.util.CookieUtils" %>
<%
    Cookie cookie = CookieUtils.getCookie(request, "locale");
    String locale = "ko"; // default language is English
    if (cookie != null) {
        locale = cookie.getValue();
    }
%>
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
<fmt:setLocale value="<%=locale%>"/>
<fmt:bundle basename="message">
<body>
<h1><fmt:message key="loginSuccess"/></h1>
<p><a href="/loginForm.do"><fmt:message key="logout"/></a></p>
<p><a href="/index.do"><fmt:message key="home"/></a></p>
</body>
</fmt:bundle>
</html>

