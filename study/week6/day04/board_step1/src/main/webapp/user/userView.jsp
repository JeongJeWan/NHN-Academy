<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/08
  Time: 9:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
<html>
<head>
    <title><fmt:message key="userView"/></title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<table>
    <tbody>

    <tr>
        <th><fmt:message key="userId"/></th>
        <td>${user.id}</td>
    </tr>

    <tr>
        <th><fmt:message key="userPassword"/></th>
        <td>${user.password}</td>
    </tr>

    <tr>
        <th><fmt:message key="userName"/></th>
        <td>${user.name}</td>
    </tr>

    <tr>
        <th><fmt:message key="userProfileFileName"/></th>
            <%--        <td>${user.getProfileFileName()}</td>--%>
        <td>${user.profileFileName}</td>
    </tr>

    </tbody>
</table>
<ul>
    <li><a href="/userList.do"><fmt:message key="userList"/></a></li>
    <li>
        <c:url var="userUpdate_link" value="/userUpdate.do" >
            <c:param name="id" value="${user.id}" />
        </c:url>
        <a href="${userUpdate_link}"><fmt:message key="userModify"/></a>
    </li>
    <li>
        <form method="post" action="/userDelete.do">
            <input type="hidden" name="id" value="${user.id}" />
            <button type="submit"><fmt:message key="userDelete"/></button>
        </form>
    </li>

</ul>
</body>
</fmt:bundle>
</html>