<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<my:pagetemplate title="Edit service">
<jsp:attribute name="body">

    <form:form method="POST" action="${pageContext.request.contextPath}/service/update/${updatedService.id}"
               modelAttribute="updatedService" cssClass="form-horizontal">
        <div class="form-group ${serviceName_error?'has-error':''}">
            <form:label path="serviceName" cssClass="col-sm-2 control-label">ServiceName</form:label>
            <div class="col-sm-10">
                <form:input path="serviceName" cssClass="form-control"/>
                <form:errors path="serviceName" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${serviceName_error?'has-error':''}">
            <form:label path="lengthInMinutes" cssClass="col-sm-2 control-label">Length</form:label>
            <div class="col-sm-10">
                <form:input path="lengthInMinutes" cssClass="form-control"/>
                <form:errors path="lengthInMinutes" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${serviceName_error?'has-error':''}">
            <form:label path="price" cssClass="col-sm-2 control-label">Price</form:label>
            <div class="col-sm-10">
                <form:input path="price" cssClass="form-control"/>
                <form:errors path="price" cssClass="help-block"/>
            </div>
        </div>    
        
        


        <button class="btn btn-primary" type="submit">Edit!</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>