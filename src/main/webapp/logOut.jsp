<%-- 
    Document   : logOut
    Created on : Mar 21, 2016, 12:59:31 PM
    Author     : andre_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    </head>
    <body>
         <jsp:include page="navBar.jsp" />
         <div style="margin-top: 200px;">
         <!-- font color is a ServletContext object and name is a Session Object. -->
        <h1 align="center" style="color: ${fontColor};" >Goodbye ${username}</h1>
         </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
