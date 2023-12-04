<%-- 
    Document   : editar-asignatura
    Created on : 26 nov 2023, 16:38:33
    Author     : Admin
--%>
<%@ page language="java" import="java.util.*, scb.asignatura.*" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap links -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <!--Libreria sweetalert2 -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <!--Estiloa para el boton-->
        <link  rel="stylesheet" href="styles.css">
        <title>Editar Asignatura</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-sm navbar-dark" style="background-color: #0c1c3c;">
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
        <div class='container mt-5'>
            <div class='row'>
                <div class='col'>
                    <h1 class="text-center">Editar Asignatura</h1>
                </div>
            </div>
        </div>
        <div class='container'>
            <div class='row'>
                <div class='col'>
                    <form action='servlet' method='post' accept-charset="UTF-8">
                        <input type="hidden" name="action" value="editar">

                        <!-- Campo oculto para el ID de la asignatura -->
                        <input type="hidden" name="id_asignatura" value="${asignatura.id}">

                        <div class='form-outline mb-4'>
                            <label for="nombre" >Nombre de la Asignatura</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" value="${asignatura.nombre}" required>
                        </div>

                        <div class='form-outline mb-4'>
                            <label for='miSelect'>Docente: </label>
                            <select class='form-control ' id='docente' name='docente'>
                                <!-- uso de JSTL para iterar el ArrayList -->
                                <c:forEach var="opcion" items="${docentes}">
                                    <option value='${opcion.matricula}' ${opcion.matricula eq asignatura.docente.matricula ? 'selected' : ''}>${opcion.doc_nombre} ${opcion.doc_apellidos}</option>
                                </c:forEach>

                            </select>
                        </div>

                        <div class='form-outline mb-4'>
                            <label for='miSelect'>Programa Academico: </label>
                            <select class='form-control ' id='programa' name='programa'>
                                <!-- uso de JSTL para iterar el ArrayList -->
                                <c:forEach var="opcion" items="${programas}">
                                    <option value='${opcion.id}' ${opcion.id eq asignatura.programa.id ? 'selected' : ''}>${opcion.nombre}</option>
                                </c:forEach>

                            </select>
                        </div>

                        <div class="container">
                            <div class="row text-start">
                                <div class="col">                    
                                    <button class="button2" type="submit" style="margin-right: 10px;">
                                        Guardar
                                    </button>
                                    <td><a href="servlet?action=listar-asignaturas" class="btn btn-danger" role="button">Cancelar</a><td>
                                </div>
                            </div>
                        </div>

                    </form>

                </div>
            </div>
    </body>


</html>
