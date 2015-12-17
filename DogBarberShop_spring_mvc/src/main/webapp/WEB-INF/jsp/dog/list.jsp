<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<my:pagetemplate title="Dogs">
	<jsp:attribute name="body">
		<my:a href="/dog/create" class="btn btn-primary">
        	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        	Add Dog
    	</my:a>
    	
    	<table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>breed</th>
            <th>born date</th>
            <th>color</th>
            <th>customer</th>
            <th>taken</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dogs}" var="dog">
            <tr>
                <td>${dog.id}</td>
                <td><c:out value="${dog.name}"/></td>
                <td><c:out value="${dog.breed}"/></td>
                <td><fmt:formatDate value="${dog.bornDate.time}" pattern="yyyy-MM-dd"/></td>
                <td><c:out value="${dog.color}"/></td>
                <td><c:out value="${dog.customer.name}"/></td>
                <td><c:out value="${dog.takenByShop}" /></td>
                <td>
                    <my:a href="/dog/view/${dog.id}" class="btn btn-primary">View</my:a>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/dog/delete/${dog.id}">
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
	</jsp:attribute>
</my:pagetemplate>