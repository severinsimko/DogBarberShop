<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

<my:pagetemplate>
<jsp:attribute name="body">    
<div class="jumbotron">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h4 class="text-center login-title">Please sign in to enter the barbershop</h4>
            <div class="account-wall">
                <form class="form-signin" method="POST" action="/pa165/auth/login">
	                <input type="text" name="email" class="form-control" placeholder="Email"autofocus>
	                <input type="password" name="password" class="form-control" placeholder="Password">
	                <p align="left"></p>
	                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                </form>
    		</div>
            <p style="font-size:12px; text-align: left;" class="text-center login-title"> For project defense purposes only, you can use one of the following accounts to sign in.</p>
            <p style="font-size:12px; text-align: left;" class="text-center login-title"> 1, Admin email: admin@dogbarber.cz pass: admin</p>
            <p style="font-size:12px; text-align: left;" class="text-center login-title"> 2, Customer email: pepa@email.cz pass: heslo</p>
		</div>
	</div>
</div> 
</jsp:attribute>
</my:pagetemplate>