<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<my:pagetemplate title="${dog.name}">
	<jsp:attribute name="body">
		<div class="row">
        	<div class="col-xs-6">
        		<p><c:out value="ID   : ${dog.id}"/></p>
            	<p><c:out value="Owner: ${dog.customer.name} ${dog.customer.surname}"/></p>
            	<p><c:out value="Breed: ${dog.breed}"/></p>
            	<p><c:out value="Color: ${dog.color}"/></p>
            	<p><c:out value="Born : "/><fmt:formatDate value="${dog.bornDate.time}" pattern="yyyy-MM-dd"/></p>
        	</div>
    	</div>
    	
    	<h3>Service subscription</h3>
		<table class="table table-bordered">
        	<thead>
        		<tr>
            		<th>Service</th>
            		<th>Lenght</th>
            		<th>Price</th>
        		</tr>
        	</thead>
        	<tbody>
        		<c:forEach items="${dog.services}" var="service">
            		<tr>
            			<td><c:out value="${service.serviceName}"/></td>
            			<td><c:out value="${service.lengthInMinutes}"/></td>
            			<td><c:out value="${service.price}"/></td>
            		</tr>
        		</c:forEach>
        	</tbody>
    	</table>
	</jsp:attribute>
</my:pagetemplate>

