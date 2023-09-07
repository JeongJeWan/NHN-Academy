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
<div class="login-info">
    <ul>
        <li><span>아이디: ${sessionScope.user.userId}</span></li>
        <li><span>이름: ${sessionScope.user.userName}</span></li>
        <li>
            <button type="button" id="btn-logout">로그아웃</button>
        </li>
    </ul>
</div>
<form method="post" id="logoutForm" action="/login/logout">
</form>

<script type="text/javascript">
    window.addEventListener("DOMContentLoaded",function(){
        const btnLogout = document.getElementById("btn-logout");
        btnLogout.addEventListener("click",function(){
            const logoutForm = document.getElementById("logoutForm");
            logoutForm.submit();
        });
    });
</script>

</body>
</html>
