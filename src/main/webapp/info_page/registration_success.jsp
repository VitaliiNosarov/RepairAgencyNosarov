<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
     <head>
          <title>Registration success</title>
     </head>
  <body>
     <jsp:include page="/page_component/header.jsp"></jsp:include>
     <h1 align="center"> New user was saved </h1>
     <h1 align="center"><input type="button" onclick="history.back();" value="back"/></h1>
     <jsp:include page="/page_component/footer.jsp"></jsp:include>
  </body>
</html>