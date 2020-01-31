<%-- 
    Document   : Index
    Created on : Jan 31, 2020, 3:31:19 PM
    Author     : Jesus Dicent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Productos</title>

        <!-- Bootstrap core CSS -->
        <link href="/WebApplication1/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="/WebApplication1/css/simple-sidebar.css" rel="stylesheet">

    </head>

    <body>

        <div class="d-flex" id="wrapper">

            <!-- Sidebar -->
            <div class="bg-light border-right" id="sidebar-wrapper">
                <div class="sidebar-heading">Probando con Excel</div>
                <div class="list-group list-group-flush">
                    <a href="/WebApplication1" class="list-group-item list-group-item-action bg-light">Dashboard</a>
                    <a href="/WebApplication1/Categorias" class="list-group-item list-group-item-action bg-light">Categorias</a>
                    <a href="/WebApplication1/Productos" class="list-group-item list-group-item-action bg-light">Productos</a>                  
                </div>
            </div>
            <div id="page-content-wrapper">
                <br />
                <div class="container">
                    <div class="row">

                        <div class="col-sm-6" >
                            <h1>Listado Productos</h1>
                        </div>
                        <div class="col-sm"></div>
                        <div class="col-sm-2" >
                            <a href="/WebApplication1/Producto/Nuevo" class="btn btn-primary">Nuevo Producto</a>
                        </div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-sm-8" >

                            <table class="table table-bordered">
                                <thead class="thead-dark">
                                    <tr>
                                        <th> Id </th>
                                        <th> Categoria </th>
                                        <th> Nombre </th>
                                        <th> Precio Venta </th>
                                        <th> Stock </th>
                                        <th> Action</th>
                                    </tr>
                                </thead>
                                <tbody>

                                <c:forEach var="ca" items="${categorias}">
                                    <tr>
                                        <td>${ca.getIdProducto()}</td>
                                        <td>${CategoriaSelect.get(ca.getIdCategoria())}</td>
                                        <td>${ca.getNombre()}</td>
                                        <td>${ca.getPrecioVenta()}</td>
                                        <td>${ca.getStock()}</td>
                                        <td>
                                            <a href="/WebApplication1/Producto/Edit?id=${ca.getIdProducto()}" 
                                               class="btn btn-primary">
                                                Editar</a> 
                                            &nbsp;
                                            
                                            <a href="/WebApplication1/Produto/Delete?id=${ca.getIdProducto()}" 
                                               onclick="return confirm('Desea Eliminar este Registro?');"
                                               class="btn btn-danger">
                                                Eliminar</a> 
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /#page-content-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- Bootstrap core JavaScript -->
        <script src="/WebApplication1/vendor/jquery/jquery.min.js"></script>
        <script src="/WebApplication1/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Menu Toggle Script -->
        <script>
            $("#menu-toggle").click(function (e) {
                e.preventDefault();
                $("#wrapper").toggleClass("toggled");
            });
        </script>



    </body>
</html>
