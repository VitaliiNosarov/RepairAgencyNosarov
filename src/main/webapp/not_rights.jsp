<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
    <head>
         <title>Access denied</title>
    </head>
    <body>
    <jsp:include page="/page_component/header.jsp"></jsp:include>
    <h1>You do not have rights for visiting this page</h1>
    <h1 align="center"><input type="button" onclick="history.back();" value="back"/></h1>
    <jsp:include page="/page_component/footer.jsp"></jsp:include>
    </body>
  </html>