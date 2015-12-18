<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<my:pagetemplate title="Information about the employee: ${employee.name} ${employee.surname}">
    <jsp:attribute name="body">
        
    <div class="row">
         <div class="col-xs-6">
             <p><c:out value="Employee name:  ${employee.name}"/></p>
             <p><c:out value="Employee email: ${employee.email} "/></p>
             <p><c:out value="Employee address: ${employee.address}"/></p>
                <p><c:out value="Employee salary: ${employee.salary} $"/></p>
                <p><c:out value="Employee phone number: ${employee.phone_number}"/></p>
         </div>
     </div>
        
       
     
    </jsp:attribute>
</my:pagetemplate>
