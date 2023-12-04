<%-- 
    Document   : principal
    Created on : 21 nov 2023, 12:07:07
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inicio </title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <!-- Google Fonts Roboto -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap">
        <!-- Bootstrap links -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.10.2/umd/popper.min.js"></script>
        <link  rel="stylesheet" href="styles.css">
        <!-- Latest compiled JavaScript -->
    </head>
    <body class="d-flex flex-column min-vh-100">
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
                        <li class="nav-item">
                            <a class="nav-link" href="servlet?action=cerrar-sesion">Cerrar Sesión</a>
                        </li>
                    </ul>
                </div>
        </nav>
        <div class="container flex-grow-1" style="margin-top: 40px;">
            <div class="row">
                <div class="col-12 text-center">
                    <h1>Sistema de Control Bibliográfico</h1>
                </div>
            </div>
            <div class="row justify-content-center align-items-center">
                <div class="col-sm-6 col-md-4 col-lg-3">
                    <img src="imagenes/logo.png" class="img-fluid" alt="Logo">
                </div>
            </div>
        </div>


        <footer class="bg-light text-center text-white">
            <!-- Grid container -->
            <div class="container p-4 pb-0">
                <!-- Section: Social media -->
                <section class="mb-4">
                    <!-- Facebook -->
                    <a
                        class="btn btn-primary btn-floating m-1"
                        style="background-color: #3b5998;"
                        href="https://www.facebook.com/uazcampusjalpa?mibextid=ZbWKwL"
                        role="button"
                        ><i class="fab fa-facebook-f"></i
                        ></a>
                    <!-- Instagram -->
                    <a
                        class="btn btn-primary btn-floating m-1"
                        style="background-color: #ac2bac;"
                        href="https://instagram.com/uaz_oficial?igshid=MzMyNGUyNmU2YQ=="
                        role="button"
                        ><i class="fab fa-instagram"></i
                        ></a>

                    <!-- Linkedin -->
                    <a
                        class="btn btn-primary btn-floating m-1"
                        style="background-color: #0082ca;"
                        href="https://www.linkedin.com/in/uziel-jonathan-hern%C3%A1ndez-llamas-6290022a3?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"
                        role="button"
                        ><i class="fab fa-linkedin-in"></i
                        ></a>
                    <!-- Github -->
                    <a
                        class="btn btn-primary btn-floating m-1"
                        style="background-color: #333333;"
                        href="https://github.com/uziel-hernandez12"
                        role="button"
                        ><i class="fab fa-github"></i
                        ></a>
                </section>
                <!-- Section: Social media -->
            </div>
            <!-- Grid container -->

            <!-- Copyright -->
            <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
                © 2023 Copyright:
                <a class="text-white" href="https://campusjalpa.uaz.edu.mx/">UAZ Campus Jalpa</a>
            </div>
            <!-- Copyright -->
        </footer>
    </body>
</html>
