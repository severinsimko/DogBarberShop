<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<my:pagetemplate title="Information about the service called: ${serviceView.serviceName}">
    <jsp:attribute name="body">
        <div class="row">
        	<div class="col-xs-6">
            	<p><c:out value="The name is: ${serviceView.serviceName}"/></p>
            	<p><c:out value="It takes: ${serviceView.lengthInMinutes} minutes"/></p>
            	<p><c:out value="The price for service is: ${serviceView.price}"/></p>
        	</div>
    	</div>
    
    	<h3>List of employees </h3>
		<table class="table table-bordered">
        	<thead>
        		<tr>
            		<th>name</th>
                        <th>surname</th>
                        <th>cell phone number</th>
        		</tr>
        	</thead>
        	<tbody>
        		<c:forEach items="${serviceView.allEmployees}" var="employee">
            		<tr>
            			<td><a href="/pa165/employee/view/${employee.id}"><c:out value="${employee.name}"/></a></td>
                                <td><a href="/pa165/employee/view/${employee.id}"><c:out value="${employee.surname}"/></a></td>
                                <td><c:out value="${employee.phone_number}"/></td>
            		</tr>
        		</c:forEach>
        	</tbody>
    	</table>    
    </jsp:attribute>
</my:pagetemplate>    