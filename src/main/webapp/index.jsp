
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import ="java.util.List" %>
<%@page import="com.emrgentes.producto" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  List<producto> lista = (List<producto>) request.getAttribute("lista");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>JSP Page</title>
    </head>
    <body>
        <h1>PRODUCTOS</h1>  
   <p><a href="MainServlet?op=nuevo">Nuevo Producto</a></p>
    <table border="1">
              <tr>
              <th>id</th>
              <th>Producto</th>
              <th>Precio</th>
              <th>Cantidad</th>
              <th></th>
 </tr>

           <c:forEach var="item" items="${lista}">
            <tr>
            <td>${item.id}</td>
            <td>${item.producto}</td>
            <td>${item.precio}</td>
            <td>${item.cantidad}</td>
           <td>
 <a href="MainServlet?op=editar&id=${item.id}">Editar</a>
         </td>
         <td>
 <a href="MainServlet?op=eliminar&id=${item.id}"
 onclick="return(confirm('Esta seguro ???'))">Eliminar</a>
       </td>
       </tr>
       </c:forEach>
 </table>      
    </body>
</html>
