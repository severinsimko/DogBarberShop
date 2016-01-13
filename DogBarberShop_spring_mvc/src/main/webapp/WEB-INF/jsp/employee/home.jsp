<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<my:pagetemplate title="Employees">  
    
    <jsp:attribute name="body">
         <div class="row">
        <c:forEach items="${employees}" var="employee">
            <div class="col-xs-12 col-sm-4 col-md-3 col-lg-2"><!-- bootstrap responsive grid -->
                
                <div class="thumbnail">
                <div class="caption">
                    <td><a href="/pa165/employee/view/${employee.id}"><c:out value="${employee.name} ${employee.surname}"/></a></td>
                  
                    <span style="color: red; font-weight: bold;"><c:out value="${employee.email}"/> </span>
                </div>
                </div>
            </div>
        </c:forEach>
    </div>
        
        
    </jsp:attribute>
</my:pagetemplate>