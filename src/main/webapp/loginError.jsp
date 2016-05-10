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
       <nav id="nav" class="navbar navbar-default navbar-static-top" >
        <div class="container">
            <div class="navbar-header">
                <p style="float: left;"><img class="img-responsive" style="max-width:75px; margin-top: 7px; margin-right: 10px;"
                         src="images/blackLogo.jpg"></p>
                <h2 style="display:inline-block; padding-top: 15px; padding-left: 25px; color:ghostwhite;">Ava Wine and Spirits LLC</h2>
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#collapse-menu" >
                    <span class="sr-only">Toggle Navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="collapse-menu"  >
                <ul class="nav navbar-nav navbar-right" >
                    <li class="active"><a href="index.html" style="background-color: black; color: ghostwhite;">Home</a></li>
                </ul>
           </div>
        </div>
    </nav>
        <div id="msg">
        <h1 color="white" >You have been logged Out. Please log back in. If you are having issues please contact your Web Admin.</h1>
        </div>
        <div class="col-md-3" style="background-color: white; float: none; margin: 0 auto; padding-bottom: 25px;"> 
             <img class="img-responsive" style="margin: 0 auto; padding-top: 50px; max-height: 500px;" 
                                            src="images/redLogo.jpg">
        
             
        <form method="POST" name="login" action="WineListController"  onsubmit="return validateForm()" style="text-align: center; padding-top: 25px;">
            <label>Name</label>
            <input type="text" name="username" value="" required />
            <br>
            <br>
            <label>Color</label>
            <input type="text" name="fontColor" value="" required/>
            <br>
            <br>
            <input type="submit" name="submit" value="Submit"/>     
            <input type="hidden" name="action" value="getWineList" />
        </form>
             
             
             
             
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
