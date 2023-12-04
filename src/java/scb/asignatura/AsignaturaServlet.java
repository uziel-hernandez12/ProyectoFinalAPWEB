/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package scb.asignatura;


import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import scb.bussines.Asignatura;
import scb.bussines.Docente;
import scb.bussines.ProgramaAcademico;
import scb.data.AsignaturaDB;
import scb.data.DocenteDB;
import scb.data.ProgramaAcademicoDB;

public class AsignaturaServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/index.jsp";

        // obtener la acción actual
        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // acción predeterminada
        }
        System.out.println(action);
        // realizar la acción y establecer la URL a la página apropiada
        if (action.equals("join")) {
            url = "/index.jsp";

        } // Si la acción es "aceptar-eliminacion" (se ha confirmado la eliminación de la asignatura)
        else if (action.equals("aceptar-eliminacion")) {
            // Obtengo el ID de la asignatura a eliminar
            int id = Integer.parseInt(request.getParameter("idAsignatura"));

            // Intento eliminar la asignatura de la base de datos
            int result = AsignaturaDB.delete(id);

            // Verifica el resultado de la eliminación
            if (result > 0) {
                // Se ha eliminado con éxito, configuro un mensaje de éxito y obtengo la lista actualizada de asignaturas
                request.setAttribute("mensaje", "Se ha eliminado la asignatura con ID: " + id);
                List<Asignatura> asignaturas = AsignaturaDB.getAllAsignaturas();
                request.setAttribute("asignaturas", asignaturas);

                // Redirige a la página de listado de asignaturas
                url = "/listado-de-asignaturas.jsp";
            } else {
                // Hubo un problema al eliminar la asignatura, configuro un mensaje de error y obtengo la descripción del error
                request.setAttribute("mensaje", "Hubo un problema al querer eliminar la asignatura de la base de datos");
                request.setAttribute("error", scb.data.Error.descripcion);

                // Redirige a la página de listado de asignaturas
                url = "/listado-de-asignaturas.jsp";
            }
        } // Si la acción es "eliminar" (se solicita la eliminación de una asignatura)
        else if (action.equals("eliminar")) {
            // Obtenemos el ID de la asignatura a eliminar desde los parámetros de la solicitud
            int id = Integer.parseInt(request.getParameter("asig_clave"));

            // Establecemos el ID de la asignatura como un atributo de la solicitud para pasarlo a la página de confirmación
            request.setAttribute("id", id);

            // Establecemos la URL de destino como la página de confirmación de eliminación
            url = "/confirmar-eliminacion.jsp";
        } //para editar una asignatura en la base de datos
        else if (action.equals("editar")) {
            //se obtienen los datos del formulario para agregar una asignatura
            String nombre = request.getParameter("nombre");
            String matricula = request.getParameter("docente");
            int programaId = Integer.parseInt(request.getParameter("programa"));

            //obtenemos el docente de la base de datos por medio de su matricula
            Docente docente = DocenteDB.findDocenteByMatricula(matricula);

            //obtiene el programa academico por el id
            ProgramaAcademico programa = ProgramaAcademicoDB.findProgramaById(programaId);

            //creo el objeto asignatura que se guardara en la base de datos
            Asignatura asignatura = new Asignatura(docente, programa, nombre);

            // Establece el ID de la asignatura utilizando el valor proporcionado en el parámetro "id_asignatura" del objeto request.
            asignatura.setId(Integer.parseInt(request.getParameter("id_asignatura")));

            //se actualiza en la bd
            int result = AsignaturaDB.update(asignatura);
            //si resultado es mayor que 0 entonces se realizó con éxito la operación
            if (result > 0) {
                request.setAttribute("mensaje", "Los cambios han sido guardados");

                //obtengo las asignaturas 
                List<Asignatura> asignaturas = AsignaturaDB.getAllAsignaturas();

                // Establece el atributo "programas" para pasar la lista de programas académicos al cliente.
                request.setAttribute("asignaturas", asignaturas);
                url = "/listado-de-asignaturas.jsp";
            } else {
                request.setAttribute("mensaje", "Hubo un problema al querer editar la información en la base de datos");
                request.setAttribute("error", scb.data.Error.descripcion);
                //obtengo las asignaturas 
                List<Asignatura> asignaturas = AsignaturaDB.getAllAsignaturas();

                // Establece el atributo "programas" para pasar la lista de programas académicos al cliente.
                request.setAttribute("asignaturas", asignaturas);
                url = "/listado-de-asignaturas.jsp";
            }

        } //redirecciona a la pagina editar-asignatura
        else if (action.equals("modificar")) {

            //obtengo el id de la asignatura
            int id = Integer.parseInt(request.getParameter("asig_clave"));

            //creo un objeto de tipo asignatura y lo obtengo de la bd
            Asignatura asignatura = AsignaturaDB.findAsignaturaById(id);

            //crea una lista para mandar los docentes y una lista para los programas academicos
            List<Docente> docentes = DocenteDB.getAllDocentes();
            List<ProgramaAcademico> programas = ProgramaAcademicoDB.getAllProgramas();

            //manda la asignatura que se encontró como parametro
            request.setAttribute("asignatura", asignatura);

            //manda la lista de docentes
            request.setAttribute("docentes", docentes);

            //manda la lista de programas academicos
            request.setAttribute("programas", programas);

            url = "/editar-asignatura.jsp";

        } //si quiere ver el listado de asignaturas
        else if (action.equals("listar-asignaturas")) {

            //obtenemos las asignaturas de la base de datos 
            List<Asignatura> asignaturas = AsignaturaDB.getAllAsignaturas();

            //establecemos las asignaturas como parametros
            request.setAttribute("asignaturas", asignaturas);

            url = "/listado-de-asignaturas.jsp";
        } //si va agregar una asignatura a la base de datos
        else if (action.equals("add")) {
            //se obtienen los datos del formulario para agregar una asignatura
            String nombre = request.getParameter("nombre");
            String matricula = request.getParameter("docente");
            int programaId = Integer.parseInt(request.getParameter("programa"));

            //obtenemos el docente de la base de datos por medio de su matricula
            Docente docente = DocenteDB.findDocenteByMatricula(matricula);

            //obtiene el programa academico por el id
            ProgramaAcademico programa = ProgramaAcademicoDB.findProgramaById(programaId);

            //creo el objeto asignatura que se guardara en la base de datos
            Asignatura asignatura = new Asignatura(docente, programa, nombre);

            int result = AsignaturaDB.insert(asignatura);

            //si el resultado es mayor que 0 si se agregó a la BD
            //si resultado es mayor que 0 entonces se realizó con éxito la operación
            if (result > 0) {
                request.setAttribute("mensaje", "La asignatura fue dada de alta en la bd");
                request.setAttribute("asignatura", asignatura);
                //obtengo los docentes dados de alta en la BD
                List<Docente> docentes = DocenteDB.getAllDocentes();

                //obtengo los programas dados de alta en la BD
                List<ProgramaAcademico> programas = ProgramaAcademicoDB.getAllProgramas();

                // Establece el atributo "docentes" para pasar la lista de docentes al cliente.
                request.setAttribute("docentes", docentes);

                // Establece el atributo "programas" para pasar la lista de programas académicos al cliente.
                request.setAttribute("programas", programas);
                url = "/asignatura.jsp";

            } else {
                request.setAttribute("mensaje", scb.data.Error.descripcion.substring(0, 15));
                request.setAttribute("error", scb.data.Error.descripcion);
                //obtengo los docentes dados de alta en la BD
                List<Docente> docentes = DocenteDB.getAllDocentes();

                //obtengo los programas dados de alta en la BD
                List<ProgramaAcademico> programas = ProgramaAcademicoDB.getAllProgramas();

                // Establece el atributo "docentes" para pasar la lista de docentes al cliente.
                request.setAttribute("docentes", docentes);

                // Establece el atributo "programas" para pasar la lista de programas académicos al cliente.
                request.setAttribute("programas", programas);
                url = "/asignatura.jsp";
            }
        } //la pagina principal
        else if (action.equals("principal")) {
            url = "/principal.jsp";
        } //la pagina donde se van a agregar las asignaturas nuevas
        else if (action.equals("asignatura")) {
            //obtengo los docentes dados de alta en la BD
            List<Docente> docentes = DocenteDB.getAllDocentes();

            //obtengo los programas dados de alta en la BD
            List<ProgramaAcademico> programas = ProgramaAcademicoDB.getAllProgramas();

            // Establece el atributo "docentes" para pasar la lista de docentes al cliente.
            request.setAttribute("docentes", docentes);

            // Establece el atributo "programas" para pasar la lista de programas académicos al cliente.
            request.setAttribute("programas", programas);

            url = "/asignatura.jsp";

        }else if (action.equals("cerrar-sesion")) {
            // Cerrar sesión
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            // Redirigir a la página de inicio o a donde desees después de cerrar sesión
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return; // Importante para salir del método después de la redirección
        }
        //para iniciar sesion
        if (action.equals("iniciar-sesion")) {
            // Obtener parámetros de la solicitud
            String user = request.getParameter("usuario");
            String contraseña = request.getParameter("contraseña");

            // Verificar que el usuario y la contraseña sean correctos (en este caso, solo un ejemplo)
            if (user.equals("admin") && contraseña.equals("campusjalpa")) {
               
                // Obtener la sesión actual o crear una nueva si no existe
                HttpSession session = request.getSession();

                session.setAttribute("usuario", user);
                // Redirigir a la página principal después de iniciar sesión
                response.sendRedirect(request.getContextPath() + "/principal.jsp");
                return; // Importante para salir del método después de la redirección
            } else {
                request.setAttribute("mensaje", "Usuario o contraseña incorrectos");
                // Puedes redirigir a la página de inicio de sesión nuevamente
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return; // Importante para salir del método después de la redirección
            }
        }

        // reenviar objetos de solicitud y respuesta a la URL especificada
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}
