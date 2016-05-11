<%-- 
    Document   : winelistpage
    Created on : Mar 17, 2016, 8:31:55 PM
    Author     : andre_000
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ava Wine</title>
        <%-- Custom CSS goes here --%>
        
        <link href="site.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet"
              
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    </head>
    <body>
        <jsp:include page="navBar.jsp" />
     
        <br>
            <div class="container" id="pageWrapper" >
            <div class="row" >
                <h1>Ava Wine List</h1>
                <div class="col-md-8 ScrollStyle">
                    <sec:authorize access="hasAnyRole('ROLE_MGR')">
                    <form method="POST" action=<%= response.encodeURL("WineListController") %>>
                        <input type="hidden" name="action" value="addEditDelete" />
                        <input class="btn"  type="submit" name="submit" value="Add"  />
                    </form>
                    </sec:authorize>
                    <table class="table table-hover active" align="center">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Price</th>
                            </tr>
                        </thead>


                        <tbody>

                            <c:forEach var="wine" items="${wineList}">
                                <tr class="${wine.wineName}" name="${wine.wineName}" id=${wine.wineId} >
                                    <td>
                                        <c:out value="${wine.wineId}" />
                                    </td>
                                    <td>
                                        <c:out value="${wine.wineName}"/>
                                    </td>
                                    <td>            
                                        <c:out value="${wine.winePrice}"/>
                                    </td>
                                    <td>
                                        <form method="POST" action="WineListController">
                                            <input type="hidden" name="action" value="addEditDelete" />
                                            <input type="hidden" name="wineID" value="${wine.wineId}" />
                                            <sec:authorize access="hasAnyRole('ROLE_MGR')">
                                            <input class="btn"  type="submit" name="submit" value="Edit"  />
                                            <input class="btn"  type="submit" name="submit" value="Delete"  />
                                            </sec:authorize>
                                        </form>
                                    </td>    
                                </tr>
                            </c:forEach>  
                        </tbody>

                    </table>
                    <h1>${errMsg}</h1>
                </div>
            </div>
        </div>
                 <jsp:include page="footer.jsp" />
                 <script src="formValidation.js" type="text/javascript"></script>
    </body>
</html>
