<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/04
  Time: 8:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="userList"/></title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<h2 style="text-align: center"><fmt:message key="userView"/></h2>
<span ><a href="/logout.do" style="text-align: right"><fmt:message key="logout"/></a></span>
<c:choose>
    <c:when test="${not empty admin}">
        <p><a href="/userRegister.do" ><fmt:message key="userRegister"/></a></p>
    </c:when>
</c:choose>
<table>
    <thead>
    <tr>
        <c:choose>
            <c:when test="${not empty admin}">
                <th><fmt:message key="userId"/></th>
                <th><fmt:message key="userPassword"/></th>
            </c:when>
        </c:choose>
        <th><fmt:message key="userName"/></th>
        <th><fmt:message key="userProfileFileName"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <c:choose>
                <c:when test="${not empty admin}">
                    <td>${user.id}</td>
                    <td>${user.password}</td>
                    <td>
                        <c:url var="userView_link" value="/userView.do" scope="request">
                            <c:param name="id" value="${user.id}" />
                        </c:url>
                        <a href="${userView_link}">${user.name}</a>
                    </td>
                </c:when>
                <c:otherwise>
                    <td>${user.name}</td>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${empty user.getProfileFileName()}">
                    <td>없음</td>
                </c:when>
                <c:otherwise>
                    <td>
<%--                        <c:url var = "image_link" value="/fileImage.do">--%>
<%--                            <c:param name="fileName" value="${user.getProfileFileName()}"/>--%>
<%--                        </c:url><img src="${image_link}">--%>
                        ${user.profileFileName}
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</fmt:bundle>
</html>
