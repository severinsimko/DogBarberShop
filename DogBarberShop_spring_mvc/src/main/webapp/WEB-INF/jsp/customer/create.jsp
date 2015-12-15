<%-- 
    Document   : view
    Created on : Dec 14, 2015, 8:40:10 PM
    Author     : Martin Penaz
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New customer">
<jsp:attribute name="body">

    <form:form method="POST" action="${pageContext.request.contextPath}/customer/create"
               modelAttribute="customerCreate" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${surname_error?'has-error':''}">
            <form:label path="surname" cssClass="col-sm-2 control-label">Surname</form:label>
            <div class="col-sm-10">
                <form:input path="surname" cssClass="form-control"/>
                <form:errors path="surname" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${email_error?'has-error':''}">
            <form:label path="email" cssClass="col-sm-2 control-label">Email</form:label>
            <div class="col-sm-10">
                <form:input path="email" cssClass="form-control"/>
                <form:errors path="email" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${phoneNumber_error?'has-error':''}">
            <form:label path="phoneNumber" cssClass="col-sm-2 control-label">Phone number</form:label>
            <div class="col-sm-10">
                <form:input path="phoneNumber" cssClass="form-control"/>
                <form:errors path="phoneNumber" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${adress_error?'has-error':''}">
            <form:label path="adress" cssClass="col-sm-2 control-label">Address</form:label>
            <div class="col-sm-10">
                <form:input path="adress" cssClass="form-control"/>
                <form:errors path="adress" cssClass="help-block"/>
            </div>
        </div>



        <button class="btn btn-primary" type="submit">Create customer</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>