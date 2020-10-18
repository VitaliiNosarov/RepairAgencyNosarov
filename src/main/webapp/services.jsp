<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<!DOCTYPE html>
<head>
    <link href="css/services.css" rel="stylesheet" type="text/css">
      <title>New order</title>
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="content">

<div class="main_block">
<form class="update_form" method="post" action="service_manager">
    <h3 align="center">Service management</h3>
    <br/>
        <h5 align="center">Select service for update</h5>

         <c:if test="${sessionScope.infoMessage != null}">
              <div class="alert alert-success" role="alert">
                     <center>${infoMessage}</center>
                     <c:remove var="infoMessage"/>
              </div>
         </c:if>

        <div class="table_block">
            <table class="table table-striped">
                <tr>
                  <th scope="col">Service</th>
                  <th scope="col">Select</th>
                </tr>
                  <tr>
                         <td> <b>Create new service</b></td>
                         <td> <input type="radio" name="serviceId" value="0" checked></td>
                     </tr>
                <c:forEach items="${services}" var="service">
                    <tr>
                        <td> ${service.name} </td>
                        <td> <input type="radio" name="serviceId" value="${service.id}" selected></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
      <div class="form-group">
         <br/>
        <h6 align="center"><label for="userDevice">EN service name</label></h6>
        <input type="text" class="form-control" id="userDevice" name="name_en" minlength="5" maxlength = "80" placeholder="Service name" required>
        <br/>
        <h6 align="center"><label for="userDevice">RU service name</label></h6>
        <input type="text" class="form-control" id="userDevice" name="name_ru" minlength="5" maxlength = "80" placeholder="Название услуги" required>
        <br/>
      </div>
     <button class="btn btn-info" type="submit" class="btn btn-info">Save</button>
     <button class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info">Back</button>
 </form>
</div>
</div>


<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>