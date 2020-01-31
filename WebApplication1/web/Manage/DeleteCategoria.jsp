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
            
        String idTemp = request.getParameter("id");
        int Id = Integer.parseInt(idTemp);
        
        CategoriaService services = new CategoriaService();
        services.DeleteCategoria(Id);
        
        response.sendRedirect("/WebApplication1/Categorias");
            %>
    </body>
</html>
