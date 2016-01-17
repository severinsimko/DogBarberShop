<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<my:pagetemplate title="Services">



    <jsp:attribute name="body">

        <c:if test="${not empty admin}"><my:a href="/service/new" class="btn btn-primary">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                New Service
            </my:a>
        </c:if>    
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
                        <c:if test="${not empty admin}">
                            <td>
                                <form method="get" action="${pageContext.request.contextPath}/service/update/${service.id}">
                                    <button type="submit" class="btn btn-primary">Edit</button>
                                </form>
                            </td>


                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/service/delete/${service.id}">
                                    <button type="submit" class="btn btn-primary">Delete</button>
                                </form>
                            </td>

                        </c:if>


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

