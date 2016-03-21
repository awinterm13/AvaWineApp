<%-- 
    Document   : addEdit
    Created on : Mar 20, 2016, 9:07:46 PM
    Author     : andre_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ava Wine List</title>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    </head>
    <body>
         <jsp:include page="navBar.jsp" />
        <h1>Hello sluts welcome to the ADD EDIT PAGE.</h1>
        
         <form method="POST" action="<%= response.encodeURL("WineListController")%>">
            <table width="500" border="1" cellspacing="0" cellpadding="4">
               
                <c:choose>
                    <c:when test="${not empty wine}">
                        <tr>
                            <td style="background-color: black;color:white;" align="left">Product ID</td>
                            <td align="left"><input type="text" value="${wine.productId}" name="wineID" readonly/></td>
                        </tr>         
                    </c:when>
                </c:choose>
                
                <tr>
                    <td style="background-color: black;color:white;" align="left">Product Name</td>
                    <td align="left"><input type="text" value="${wine.productName}" name="productName" required/></td>
                </tr>
                
               
                <tr>
                    <td style="background-color: black;color:white;" align="left">Price</td>
                    <td align="left"><input type="text" value="${wine.productPrice}" name="price"  /></td>
                   
                </tr>
                
               
                
                <tr>
                    <input type="submit" value="Cancel" name="action" />&nbsp;
                    <input type="submit" value="Save" name="action" />
                    
                </tr>
                
                 <tr>
                    <td style="background-color: black;color:white;" align="left">Image URL</td>
                    <td align="left"><input type="text" value="${wine.imageURL}" name="imageUrl"  /></td>
                   
                </tr>
                
            </table>
        </form>
                    <jsp:include page="footer.jsp" />
    </body>
</html>
