<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:choose>
	<c:when test="${not empty authUser || not empty admin}">
<my:pagetemplate title="${dog.name}">
	<jsp:attribute name="body">
		<div class="row">
        	<div class="col-xs-6">
        		<p><c:out value="ID   : ${dog.id}"/></p>
            	<p>Owner: <a href="/pa165/customer/view/${dog.customer.id}"><c:out value="${dog.customer.name} ${dog.customer.surname}"/></a></p>
            	<p><c:out value="Breed: ${dog.breed}"/></p>
            	<p><c:out value="Color: ${dog.color}"/></p>
            	<p><c:out value="Registration: "/><fmt:formatDate value="${dog.bornDate.time}" pattern="yyyy-MM-dd"/></p>
        	</div>
    	</div>
    	
    	<div class="row">
	    	<div class="col-xs-6">
		    	<h3>Service subscription</h3>
				<table class="table">
		        	<thead>
		        		<tr>
		            		<th>Service</th>
		            		<th>Length</th>
		            		<th>Price</th>
		        		</tr>
		        	</thead>
		        	<tbody>
		        		<c:forEach items="${dog.services}" var="service">
		            		<tr>
		            			<td><a href="/pa165/service/view/${service.id}"><c:out value="${service.serviceName}"/></a></td>
		            			<td><c:out value="${service.lengthInMinutes}"/></td>
		            			<td><c:out value="${service.price}"/></td>
		            			<td>
		                    		<my:a href="/dog/unsubscribe/${dog.id}/${service.serviceName}" class="btn btn-primary">Unsubscribe</my:a>
		                		</td>
		            		</tr>
		        		</c:forEach>
		        	</tbody>
		    	</table>
		    	
		    	<h4>Subscribe for another service</h4>
		    	<form action="${pageContext.request.contextPath}/dog/subscribe/${dog.id}/" method="post">
		    		<select name="services">
		    			<c:forEach items="${services}" var="service">
		    				<option value="${service.id}">${ service.serviceName }</option>
		    			</c:forEach>
		    		</select>
		    		<input type="submit" value="Subscribe" class="btn btn-primary">
		    	</form>
	    	</div>
    	</div>
    	
    	<c:if test="${not empty admin}">
	    	<h3>In case that dog passed out</h3>
	    	<form method="post" action="${pageContext.request.contextPath}/dog/delete/${dog.id}">
	            <button type="submit" class="btn btn-primary">Delete</button>
	        </form>
    	</c:if>
	</jsp:attribute>
</my:pagetemplate>
</c:when>
	<c:otherwise>
		<h1>Unauthorized access!</h1>
		<a href="http://localhost:8080/pa165"> Homepage </a>
	</c:otherwise>
</c:choose>