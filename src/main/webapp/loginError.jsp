<%-- 
    Document   : loginError
    Created on : May 10, 2016, 9:47:49 AM
    Author     : andre_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Ava Wine</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="site.css" rel="stylesheet" type="text/css"/>
         <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    </head>
    <body style="background-image: url(images/wineroom.jpg);">
       <jsp:include page="navBar.jsp" />
        <div id="msg">
        <h1 color="white" >Your login failed or timed out. Please log back in. If you are having issues please contact your Web Admin.</h1>
        </div>
        <div class="col-md-3" style="background-color: white; float: none; margin: 0 auto; padding-bottom: 25px;"> 
             <img class="img-responsive" style="margin: 0 auto; padding-top: 50px; max-height: 500px;" 
                                            src="images/redLogo.jpg">
        
             
       
             
             
             
             
        </div>
        
          <nav  id="foot" class="footer footer-inverse navbar-fixed-bottom" >
  <div class="container">
                <p class="navbar-text"><i>&copy; 2016 Andrew Wintermyer</i></p>  
            </div>
                 
        </nav>
        
        
        
        
        <script src="formValidation.js" type="text/javascript"></script>
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
