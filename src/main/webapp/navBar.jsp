<%-- 
    Document   : navBar
    Created on : Mar 20, 2016, 11:43:45 PM
    Author     : andre_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                <li class="active"><a href=<%= response.encodeURL("index.jsp")%> style="background-color: black; color: ghostwhite;">Home</a></li>
            </ul>
        </div>
    </div>
</nav>
  <div class="col-md-11" align="right">
        
        <a href='<%= this.getServletContext().getContextPath() + "/j_spring_security_logout"%>'>Log Out</a>
         
        
         </div>
        <br>
        <br>