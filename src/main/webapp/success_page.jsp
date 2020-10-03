<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "ex" uri = "customtags"%>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<html>
     <head>
          <title>Success</title>

     </head>
  <body>
     <jsp:include page="/page_component/header.jsp"></jsp:include>
        <ex:Hello message = "CREATING_ORDER" />
        <h1 align="center"><input type="button" onclick="history.back();" value="Back"/></h1>
     <jsp:include page="/page_component/footer.jsp"></jsp:include>
  </body>
</html>