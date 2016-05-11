<%-- 
    Document   : mgrLandingPage
    Created on : May 10, 2016, 8:50:25 PM
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link href="site.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet"
              
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    </head>
    <body>
        <jsp:include page="navBar.jsp" />
         <sec:authorize access="hasAnyRole('ROLE_MGR')">
          <div align="center">
        <h1>Admin Page</h1>
         <form method="POST" action=<%= response.encodeURL("WineListController?action=getWineList") %>>
        <input class="btn"  type="submit" name="action" value="Manage Wines" align="right"  />
        </form>
          </div>
         </sec:authorize>
        <jsp:include page="footer.jsp" />
    </body>
</html>
