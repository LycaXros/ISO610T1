<%-- 
    Document   : Edit
    Created on : Jan 30, 2020, 10:54:19 AM
    Author     : Jesus Dicent
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Categoria</title>
        <!-- Bootstrap core CSS -->
        <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="../css/simple-sidebar.css" rel="stylesheet">
    </head>
    <body>
        <div class="d-flex" id="wrapper">

            <!-- Sidebar -->
            <div class="bg-light border-right" id="sidebar-wrapper">

                <div class="sidebar-heading">Probando con Excel</div>
                <div class="list-group list-group-flush">
                    <a href="/WebApplication1/" class="list-group-item list-group-item-action bg-light">Dashboard</a>
                    <a href="/WebApplication1/Categorias" class="list-group-item list-group-item-action bg-light">Categorias</a>
                    <a href="/WebApplication1/Productos" class="list-group-item list-group-item-action bg-light">Productos</a>                  
                </div>
            </div>
            <!-- /#sidebar-wrapper -->

            <!-- Page Content -->
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <br />
                    <div class="row">
                        <h1>Editar Categoria</h1>
                        <br />
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <c:forEach items="${getCategoriaById}" var="p">
                        <form method="post" action="/WebApplication1/Manage/EditCategoria.jsp">

                                    <input type="hidden" value="${p.getIdCategoria()}" name="Id"/>
                                    <input type="hidden" value="${p.getRowIndex()}" name="rowIndex"/>

                                    <div class="form-group">
                                        <label for="nombre">Nombre: </label>
                                        <input type="text" class="form-control" 
                                               value="${p.getNombre()}"
                                               id="nombre" 
                                               placeholder="Introduzca el Nombre de la Categoria" 
                                               name="name">
                                    </div>

                                    <div class="form-group">
                                        <label for="descT">Descripcion: </label>
                                        <input type="text" class="form-control"
                                               value="${p.getDescripcion()}"
                                                id="descT" placeholder="Introduzca la descripcion de la Categoria" name="desc">
                                    </div>
                                    <div class="form-group">
                                        <label for="state">Estado:</label>
                                        <select id="state" name="estado" >                                            
                                            <option value="Activo">Activo</option>
                                            <option value="Inactivo">Inactivo</option>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                    <input type="reset" value="Limpiar" class="btn btn-danger" />
                                </form>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <script src="../vendor/jquery/jquery.min.js"></script>
        <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
