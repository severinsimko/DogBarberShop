<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<my:pagetemplate title="New dog">
	<jsp:attribute name="body">

	    <form:form method="POST" action="${pageContext.request.contextPath}/dog/create"
	               modelAttribute="dogCreate" cssClass="form-horizontal">
	        <div class="form-group ${name_error?'has-error':''}">
	            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
	            <div class="col-sm-10">
	                <form:input path="name" cssClass="form-control"/>
	                <form:errors path="name" cssClass="help-block"/>
	            </div>
	        </div>
	        <div class="form-group ${breed_error?'has-error':''}">
	            <form:label path="breed" cssClass="col-sm-2 control-label">Breed</form:label>
	            <div class="col-sm-10">
	                <form:input path="breed" cssClass="form-control"/>
	                <form:errors path="breed" cssClass="help-block"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <form:label path="color" cssClass="col-sm-2 control-label">Color</form:label>
	            <div class="col-sm-10">
	                <form:select path="color" items="${colors}" />
	            </div>
	        </div>
	        <div class="form-group ${customerId_error?'has-error':''}">
	            <form:label path="customerId" cssClass="col-sm-2 control-label">Customer</form:label>
	            <div class="col-sm-10">
	                <form:select path="customerId">
		    			<c:forEach items="${customers}" var="customer">
		    				<option value="${customer.id}">${customer}</option>
		    			</c:forEach>
			    	</form:select>
	                <form:errors path="customerId" cssClass="help-block"/>
	            </div>
	        </div>
	        
	        <button class="btn btn-primary" type="submit">Create dog</button>
	    </form:form>

	</jsp:attribute>
</my:pagetemplate>