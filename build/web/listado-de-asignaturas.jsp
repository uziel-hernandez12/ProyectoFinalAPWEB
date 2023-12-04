<%-- 
    Document   : listado-de-asignaturas
    Created on : 25 nov 2023, 18:21:49
    Author     : Admin
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Listado de Asignaturas en la BD</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap links -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <!--Libreria sweetalert2 -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <!<!-- Datatable -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/dataTables.bootstrap5.min.css"/>

        <!-- archiv js -->
        <script type="text/javascript" src="main.js"></script>
    </head>
    <body class="d-flex flex-column min-vh-100">
        <nav class="navbar navbar-expand-sm navbar-dark fixed-top" style="background-color: #0c1c3c;">
            <div class="container">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="true" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="navbar-collapse collapse show" id="navbarSupportedContent" style="">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="servlet?action=principal">Inicio</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Asignaturas</a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="servlet?action=asignatura">Agregar Asignatura</a>
                                <a class="dropdown-item" href="servlet?action=listar-asignaturas">Listar Asignaturas</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Docente</a>
                        </li> 
                        <li class="nav-item">
                            <a class="nav-link" href="#">Departamento</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Programa Academico</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Bibliografía</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Libros</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Autores</a>
                        </li>
                    </ul>
                </div>
        </nav>
        <div class="container mt-5">
            <div class="row">
                <div class="col-lg-6">
                    <h1 class="mt-4">Asignaturas en la BD</h1>
                </div>
                <div class="col-lg-6 text-end">
                    <a href="servlet?action=asignatura" class="btn btn-info mt-4" role="button">Nueva asignatura</a>
                </div>
                <div class="col">

                    <hr>
                    <table class="table table-striped " id="miTabla">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Titulo</th>
                                <th scope="col">Programa Academico</th>
                                <th scope="col">Docente</th>
                                <th scope="col" colspan="2">Operaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="asignatura" items="${asignaturas}">
                                <tr>
                                    <td>${asignatura.id}</td>
                                    <td>${asignatura.nombre}</td>
                                    <td>${asignatura.programa.nombre}</td>
                                    <td>${asignatura.docente.doc_nombre} ${asignatura.docente.doc_apellidos}</td>
                                    <td><a href="servlet?action=modificar&asig_clave=${asignatura.id}" class="btn btn-info" role="button">Modificar</a></td>
                                    <td><a href="servlet?action=eliminar&asig_clave=${asignatura.id}" class="btn btn-danger" role="button">Eliminar</a></td>
                                </tr>    
                            </c:forEach>
                        </tbody>    
                    </table>
                </div>
            </div>
        </div>

        <%
            // Verifica si hay un mensaje del servlet y si no se ha mostrado ya
            String mensaje = (String) request.getAttribute("mensaje");
            String error = (String) request.getAttribute("error");

            if (mensaje != null && !mensaje.isEmpty() && session.getAttribute("mensajeMostrado") == null) {
                // Muestra el mensaje
%>
        <script>
            Swal.fire({
                title: '<%= (error == null || error.isEmpty()) ? "Éxito" : "Error"%>',
                text: '<%= mensaje%>',
                icon: '<%= (error == null || error.isEmpty()) ? "success" : "error"%>',
                confirmButtonText: 'Aceptar'
            });


        </script>
        <%

            }
        %>
        <!-- Libreria  jquery -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

        <!-- Data table -->
        <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.7/js/dataTables.bootstrap5.min.js"></script>



    </body>
</html>