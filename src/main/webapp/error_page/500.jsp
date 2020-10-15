<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

  <html>
    <head>
         <link href="css/error.css" rel="stylesheet" type="text/css">
         <title>500</title>
    </head>

    <body>
    <jsp:include page="/page_component/header.jsp"></jsp:include>
    <div class="content">

    <br/>
    <br/>
    <h1><center>Server Error</center></h1>
    <h1 align="center"><input type="button" onclick="history.back();" value="back"/></h1>
    <jsp:include page="/page_component/footer.jsp"></jsp:include>

    </div>
    </body>
 </html>