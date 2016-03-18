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
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    </head>
    <body>
            <div class="container" id="pageWrapper">
            <div class="row">
                <h1>Ava Wine List</h1>
                <div class="col-md-8 ScrollStyle">
                    

                    <table class="table table-hover active">
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
                                </tr>
                            </c:forEach>  
                        </tbody>

                    </table>
                    <h1>${errorMsg}</h1>
                </div>
            </div>
        </div>
    </body>
</html>
