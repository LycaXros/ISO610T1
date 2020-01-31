<%-- 
    Document   : EditCategoria
    Created on : Jan 30, 2020, 4:18:07 PM
    Author     : Jesus Dicent
--%>

<%@page import="mvc.model.Categoria"%>
<%@page import="mvc.services.CategoriaService"%>
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
            
        String idTemp = request.getParameter("Id");
        int Id = Integer.parseInt(idTemp);
        idTemp = request.getParameter("rowIndex");
        int rowIndex = Integer.parseInt(idTemp);
        
        String nombre = request.getParameter("name");
        String desc = request.getParameter("desc");
        String estado = request.getParameter("estado");
        
        CategoriaService services = new CategoriaService();
        services.SetCategoria(new Categoria(Id, rowIndex,nombre, desc, estado));
        
        response.sendRedirect("/WebApplication1/Categorias");
            %>
    </body>
</html>
