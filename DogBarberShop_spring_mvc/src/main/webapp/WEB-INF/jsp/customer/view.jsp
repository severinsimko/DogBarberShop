<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<my:pagetemplate title="Customer ${customer.name} ${customer.surname} has following dogs">
    <jsp:attribute name="body">
        <div class="row">
        	<div class="col-xs-6">
        		<p><c:out value="ID   : ${customer.id}"/></p>
            	<p><c:out value="Name : ${customer.name} ${customer.surname}"/></p>
                <p><c:out value="Email : ${customer.email}"/></p>
                <p><c:out value="Address : ${customer.adress} "/></p>
                <p><c:out value="Phone Number : ${customer.phoneNumber}"/></p>
        	</div>
    	</div>
   
        <h3>List of dogs </h3>
     	<table class="table">
	        <thead>
		        <tr>
		            <th>name</th>
		        </tr>
	        </thead>
	        <tbody>
	        	<c:forEach items="${customer.allDogs}" var="dog">
		            <tr>
                                <td><p><a href="/pa165/dog/view/${dog.id}"><c:out value="${dog.name}"/></a></p></td>
		            </tr>
		        </c:forEach>
	        </tbody>
	    </table>    
    </jsp:attribute>
</my:pagetemplate>  