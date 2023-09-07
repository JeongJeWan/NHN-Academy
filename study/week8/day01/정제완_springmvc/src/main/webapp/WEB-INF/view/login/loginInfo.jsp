<%--
  Created by IntelliJ IDEA.
  User: jeongjewan
  Date: 2023/04/17
  Time: 5:10 pM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>아이디 :${sessionScope.user.userId} 이름 :${sessionScope.user.userName}</p>
<form method="get" action="/logout">
    <button type="submit">로그아웃</button>
</form>
</body>
</html>
