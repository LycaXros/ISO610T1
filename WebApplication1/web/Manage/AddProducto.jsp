<%-- 
    Document   : AddCategoria
    Created on : Jan 30, 2020, 1:47:39 PM
    Author     : Jesus Dicent
--%>

<%@page import="mvc.model.Producto"%>
<%@page import="mvc.services.ProductosService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  
            System.out.println("LLego");
            
        String temp = request.getParameter("categoriaID");
        int idCategoria = Integer.parseInt(temp);
        temp = request.getParameter("precioVenta");
        double precioVenta = Double.parseDouble(temp);
        temp = request.getParameter("stock");
        int stock = Integer.parseInt(temp);
        
        String nombre = request.getParameter("name");
        
        ProductosService services = new ProductosService();
        services.SetProducto(new Producto(idCategoria, nombre, precioVenta, stock));
        
        response.sendRedirect("/WebApplication1/Productos");
            %>
    </body>
</html>
