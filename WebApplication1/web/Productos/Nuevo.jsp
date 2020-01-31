<%-- 
    Document   : Nuevo
    Created on : Jan 31, 2020, 3:31:40 PM
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

        <title>Nuevo Producto</title>

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
                        <h1>Nuevo Producto</h1>
                        <br />
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <form method="post" action="/WebApplication1/Manage/AddProducto.jsp">
                                <div class="form-group">
                                    <label for="categoria">Categoria: </label>
                                    <select id="categoria" name="categoriaID">
                                        <c:forEach items="${CategoriaSelect.entrySet()}" var="cs">
                                            <option value="${cs.getKey()}">${cs.getValue()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="nombre">Nombre: </label>
                                    <input type="text" class="form-control" id="nombre" placeholder="Introduzca el Nombre del producto" name="name">
                                </div>

                                <div class="form-group">
                                    <label for="pV">Precio Venta: </label>
                                    <input type="number" class="form-control" id="pV" placeholder="Introduzca el precio de venta" name="precioVenta">
                                </div>
                                <div class="form-group">
                                    <label for="st">Stock: </label>
                                    <input type="number" class="form-control" id="st" placeholder="Introduzca el Stock" name="stock">
                                </div>
                                <input type="submit" class="btn btn-primary" value="Submit"/>
                                <input type="reset" value="Limpiar" class="btn btn-danger" />
                            </form>
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
</html>