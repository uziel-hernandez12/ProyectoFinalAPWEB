<%-- 
    Document   : index
    Created on : 21 nov 2023, 14:38:52
    Author     : Admin
--%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Proyecto final</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <section class="vh-100" style="background-color: #0c1c3c;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col col-xl-10">
                        <div class="card" style="border-radius: 1rem;">
                            <div class="row g-0">
                                <div class="col-md-6 col-lg-5 d-none d-md-block">
                                    <img src="imagenes/logoUaz.jpg"
                                         alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem; margin: 45% auto" />
                                </div>
                                <div class="col-md-6 col-lg-7 d-flex align-items-center">
                                    <div class="card-body p-4 p-lg-5 text-black">

                                        <form action='servlet' method='post' accept-charset="UTF-8"> 
                                            <input type="hidden" name="action" value="iniciar-sesion">
                                            <div class="d-flex align-items-center mb-3 pb-1">
                                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                                <span class="h1 fw-bold mb-0">Sistema de Control Bibliográfico</span>
                                            </div>

                                            <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Inicia sesión</h5>

                                            <div class="form-outline mb-4">
                                                <input type="text" id="usuario" name="usuario" required="" class="form-control form-control-lg" />
                                                <label class="form-label" for="usuario">Usuario</label>
                                            </div>

                                            <div class="form-outline mb-4">
                                                <input type="password" id="contraseña" name="contraseña" required="" class="form-control form-control-lg" />
                                                <label class="form-label" for="contraseña">Contraseña</label>
                                            </div>
                                            <%-- Mostrar el mensaje de error si no es null --%>
                                            <% String mensajeError = (String) request.getAttribute("mensaje"); %>
                                            <% if (mensajeError != null && !mensajeError.isEmpty()) {%>
                                            <div style="color: red;">
                                                <%= mensajeError%>
                                            </div>
                                            <% }%>
                                            <div class="pt-1 mb-4">
                                                <button class="btn btn-dark btn-lg btn-block" type="submit">Entrar</button>
                                            </div>

                                            <a class="small text-muted" href="https://campusjalpa.uaz.edu.mx/">Conoce más sobre nosotros</a>

                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>

