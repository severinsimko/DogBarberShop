<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:pagetemplate title="Services">
    
    <jsp:attribute name="body">
     <table class="table table-bordered">
        <thead>
        <tr>
            
            <th>name</th>
            <th>length</th>
            <th>price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${services}" var="service">
            <tr>
                
                
                
                <td><a href="/pa165/service/view/${service.id}"><c:out value="${service.serviceName}"/></a></td>
                <td><c:out value="${service.lengthInMinutes}"/></td>
                <td><c:out value="${service.price}"/></td>
                
                               
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
     
     
     <h4>Services sorted by price- the cheapest the best</h4>
             <div class='row'><div class='col-md-6'>
         
  
     <table class="table table-bordered">
        
         
        <thead>
        <tr>
            
            <th>name</th>
            <th>price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sorted}" var="sort">
            <tr>
                
                <td><a href="/pa165/service/view/${sort.id}"><c:out value="${sort.serviceName}"/></a></td>
                <td><c:out value="${sort.price}"/></td>
                
               
            </tr>
        </c:forEach>
        </tbody>
    </table>
          
                     
    </div></div>                 
</jsp:attribute>
</my:pagetemplate>

