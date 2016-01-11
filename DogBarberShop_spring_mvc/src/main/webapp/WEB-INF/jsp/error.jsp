<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<my:pagetemplate title="">
    <jsp:attribute name="body">

        <h1><fmt:message key="error.header"/></h1>
        
        <c:choose>
            <c:when test="${not empty errMsg}">
                <p>${errMsg}</p>
            </c:when>
            <c:otherwise>
                <fmt:message key="error.general"/>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
</my:pagetemplate> 