  
<%-- 
    Document   : confirmar-eliminacion
    Created on : 27 nov. 2023, 9:23:32
    Author     : admin
--%>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Confirmacion de eliminación</title>
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            .texto-negrita{
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class='container mt-5'>
            <div class='row'>
                <div class='col'>
                    <h1 class='container'>Confirmar Eliminación</h1>
                    <hr>
                </div>
            </div>
        </div>

        <div class='container'>
            <div class='row'>
                <div class='col'>
                    <p>Esta seguro que desea eliminar la asignatura con <br>
                        ID: <span class="texto-negrita">${id}</span></p>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row text-start">
                <div class="col">                    
                    <td><a href="servlet?action=aceptar-eliminacion&idAsignatura=${id}" class="btn btn-danger" role="button">Eliminar</a><td>
                    <td><a href="servlet?action=listar-asignaturas" class="btn btn-danger" role="button">Cancelar</a><td>
                </div>
            </div>
        </div>
    </body>
</html>