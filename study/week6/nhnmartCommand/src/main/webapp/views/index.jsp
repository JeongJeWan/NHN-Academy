<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/06
  Time: 11:26 AM
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
    <title>index</title>
</head>
<fmt:setLocale value="<%=locale%>"/>
<fmt:bundle basename="message">
<body>
<ol>
    <li><a href="/init.do"><fmt:message key="init"/></a></li>
    <li><a href="/foods.do"><fmt:message key="foodList"/></a></li>
    <li><a href="/cart.do"><fmt:message key="basket"/></a></li>
    <li><a href="/change-lang?lang=ko"><fmt:message key="ko"/></a></li>
    <li><a href="/change-lang?lang=en"><fmt:message key="en"/></a></li>
</ol>
</body>
</fmt:bundle>
</html>