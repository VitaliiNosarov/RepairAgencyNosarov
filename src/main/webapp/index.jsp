<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<head>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
<title>Repair Agency</title>
</head>
<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="intro_inner">
    <h1 class="intro_title">Welcome to <br> computer repair agency<br></h1>
<a class="consultation_button" href="${PATH}/create_order">Enter</a>
</div>

<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>