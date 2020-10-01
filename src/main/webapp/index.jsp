<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<head>
</head>
<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>
<h2>Welcome to my Computer Repair Agency</h2>
<br/>
<a href="login">Log in</a> <br/>
<a href="users">all users/a> <br/>
<a href="registration">Registration</a> <br/>
<a href="order?orderId=1">Order by id</a> <br/>

<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>