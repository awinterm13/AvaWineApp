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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ava Wine List</title>
        <%-- Custom CSS goes here --%>
        
        <link href="site.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet"
              
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    </head>
    <body>
        <jsp:include page="navBar.jsp" />
         <div class="col-md-11" align="right">
        <form method="POST" action=<%= response.encodeURL("WineListController") %>>
        <input class="btn"  type="submit" name="action" value="logOut" align="right"  />
        </form>
         </div>
        <br>
            <div class="container" id="pageWrapper" >
            <div class="row" >
                <h1>Ava Wine List</h1>
                <div class="col-md-8 ScrollStyle">
                    <form method="POST" action=<%= response.encodeURL("WineListController") %>>
                        <input type="hidden" name="action" value="addEditDelete" />
                        <input class="btn"  type="submit" name="submit" value="Add"  />
                    </form>

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
                                <tr class="${wine.productName}" name="${wine.productName}" id=${wine.productId} >
                                    <td>
                                        <c:out value="${wine.productId}" />
                                    </td>
                                    <td>
                                        <c:out value="${wine.productName}"/>
                                    </td>
                                    <td>            
                                        <c:out value="${wine.productPrice}"/>
                                    </td>
                                    <td>
                                        <form method="POST" action="WineListController">
                                            <input type="hidden" name="action" value="addEditDelete" />
                                            <input type="hidden" name="wineID" value="${wine.productId}" />
                                            
                                            <input class="btn"  type="submit" name="submit" value="Edit"  />
                                            <input class="btn"  type="submit" name="submit" value="Delete"  />
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
    </body>
</html>
