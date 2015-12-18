<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:pagetemplate>
<jsp:attribute name="body">
     <div class="jumbotron">
          <h5>Admin email: admin@dogbarber.cz  pass: admin</h5>
          <h5>Customer email: pepa@email.cz  pass: heslo</h5>
        <form method="POST" action="/pa165/auth/login">
            Email: <input type="text" name="email" value="admin@dogbarber.cz"/><br/>
            Password: <input type="password" name="password" value="admin"/><br/>
            <input value="GO" type="submit"/>
        </form>
    </div>
</jsp:attribute>
</my:pagetemplate>