
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.emrgentes.producto" %>

<%
   producto pro = (producto) request.getAttribute("pro");
%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Practica nro 3</title>
    </head>
    <body>
        <h1>

        <c:if test="${pro.id == 0}"> Nuevo Producto</c:if>
        <c:if test="${pro.id != 0}">Editar Producto</c:if>
           
        
       </h1>
   <form action="MainServlet" method="post">
     <input name="id" type="hidden" value="${pro.id}">
         <table>
                  <tr>
                  <td>Producto</td>
    <td><input type="text" name="producto" value="${pro.producto}"></td>
                 </tr>
                 <tr>
                 <td>Precio</td>
    <td><input type="text" name="precio" value="${pro.precio}"></td>
                  </tr>

                 <tr>
                <td>Cantidad</td>
    <td><input type="text" name="cantidad" value="${pro.cantidad}"></td>
                </tr>

                <tr>
          <td></td>
     <td><input type="submit" value="Enviar"/></td>
 </tr>
     </table>
     </form>
    </body>
</html>
