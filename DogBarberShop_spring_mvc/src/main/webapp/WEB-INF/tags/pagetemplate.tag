<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><c:out value="${title}"/></title>
        <!-- bootstrap loaded from content delivery network -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"  crossorigin="anonymous">
        <jsp:invoke fragment="head"/>
    </head>
    <body>

        <!-- navigation bar -->
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <c:if test="${ not empty auth }">
                    	<a class="navbar-brand" href="${pageContext.request.contextPath}/service"><f:message key="navigation.project"/></a>
                    </c:if>
                    <c:if test="${ empty auth }">
                    	<a class="navbar-brand" href="${pageContext.request.contextPath}"><f:message key="navigation.project"/></a>
                    </c:if>
                </div>        
                <c:if test="${ not empty auth }">
                    <div id="navbar" class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <c:if test="${ not empty authUser}">
                                <li><my:a href="/dog/list">My Dogs</my:a></li>
                                <li><my:a href="/service">Services</my:a></li>
                                </c:if>
                                <c:if test="${ not empty admin}">
                                <li><my:a href="/customer">Customers</my:a></li>
                                <li><my:a href="/service">Services</my:a></li>
                                <li><my:a href="/dog/list">Dogs</my:a></li>		            
                                <li><my:a href="/employee">Employees</my:a></li>
                                </c:if>
                        </ul>
                        <ul class="nav navbar-nav navbar-right pull-right">
                            <c:if test="${ not empty authUser}">
                                <li><my:a href="#">Your total payment is: ${total} </my:a></li>
                                </c:if>
                            <li class="dropdown" id="menuLogin">
                                <a class="dropdown-toggle" href="#" data-toggle="dropdown" id="navLogin">
                                    <c:if test="${ not empty admin}">
                                        <span class="userName">${admin.surname}</span>
                                    </c:if>
                                    <c:if test="${ not empty authUser}">
                                        <span class="userName">${authUser.surname}</span>
                                    </c:if>
                                    <span class="caret"></span>
                                </a>
                                <div class="dropdown-menu" style="padding:17px;"> 
                                    <span class="userName">${auth.name} ${auth.surname}</span><br>
                                    <p>Email: <span class="userName">${auth.email}</span></p><br>
                                    <FORM METHOD="LINK" ACTION="${pageContext.request.contextPath}/auth/logout">
                                        <INPUT TYPE="submit" VALUE="logout">
                                    </FORM>
                                    </div>
                                </li>
                            </ul>
                        </div>
                </c:if>	
            </div>
        </nav>

        <div class="container">

            <!-- page title -->
            <c:if test="${not empty title}">
                <div class="page-header">
                    <h1><c:out value="${title}"/></h1>
                </div>
            </c:if>

            <!-- alerts -->
            <c:if test="${not empty alert_danger}">
                <div class="alert alert-danger" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <c:out value="${alert_danger}"/></div>
                </c:if>
                <c:if test="${not empty alert_info}">
                <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
            </c:if>
            <c:if test="${not empty alert_success}">
                <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
            </c:if>
            <c:if test="${not empty alert_warning}">
                <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
            </c:if>

            <!-- page body -->
            <jsp:invoke fragment="body"/>
        </div>
        <!-- javascripts placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </body>
</html>
