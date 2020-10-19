<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <link href="css/services.css" rel="stylesheet" type="text/css">
      <title><fmt:message key="services.page_title"/></title>
      <link rel="shortcut icon" href="image/icon.png" />
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="content">

<div class="main_block">
<form class="update_form" method="post" action="service_manager">
    <h3 align="center"><fmt:message key="services.page_title"/></h3>
    <br/>
        <h5 align="center"><em><fmt:message key="services.title_text"/></em></h5>

         <c:if test="${sessionScope.infoMessage != null}">
              <div class="alert alert-success" role="alert">
                     <center><ctg:enumTranslate locale="${sessionScope.language}" value="${infoMessage}"/></center>
                     <c:remove var="infoMessage"/>
              </div>
         </c:if>

        <div class="table_block">
            <table class="table table-striped">
                <tr>
                  <th scope="col"><fmt:message key="services.table_column1"/></th>
                  <th scope="col"><fmt:message key="services.table_column2"/></th>
                  <th scope="col"><fmt:message key="services.table_column3"/></th>
                </tr>
                    <tr>
                      <td> <b><fmt:message key="services.create_new_service"/></b></td>
                      <td><b> ======================> </b></td>
                      <td> <input type="radio" name="serviceId" value="0" checked></td>
                    </tr>
                <c:forEach items="${services}" var="service">
                    <tr>
                        <td> ${service.nameEn} </td>
                        <td> ${service.nameRu} </td>
                        <td> <input type="radio" name="serviceId" value="${service.id}" selected></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
      <div class="form-group">
         <br/>
        <h6 align="center"><label for="userDevice"><fmt:message key="services.new_service_name_EN"/></label></h6>
        <input type="text" class="form-control" id="userDevice" name="name_en" minlength="5" maxlength = "80" placeholder="<fmt:message key="services.new_service_name_placeholder_EN"/>" required>
        <br/>
        <h6 align="center"><label for="userDevice"><fmt:message key="services.new_service_name_RU"/></label></h6>
        <input type="text" class="form-control" id="userDevice" name="name_ru" minlength="5" maxlength = "80" placeholder="<fmt:message key="services.new_service_name_placeholder_RU"/>" required>
        <br/>
      </div>
     <button class="btn btn-info" type="submit" class="btn btn-info"><fmt:message key="services.save_button"/></button>
     <button class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info"><fmt:message key="services.back_button"/></button>
 </form>
</div>
</div>


<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>