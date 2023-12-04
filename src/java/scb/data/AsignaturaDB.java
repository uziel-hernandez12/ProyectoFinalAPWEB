/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scb.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import scb.bussines.Asignatura;
import scb.bussines.Docente;
import scb.bussines.ProgramaAcademico;

/**
 *
 * @author Admin
 */
public class AsignaturaDB {

    /**
     * Inserta una asignatura en la base de datos
     *
     * @param asignatura
     * @return
     */
    public static int insert(Asignatura asignatura) {
        // Obtiene una instancia del ConnectionPool para gestionar las conexiones a la base de datos
        ConnectionPool pool = ConnectionPool.getInstance();

        // Obtiene una conexión de la pool
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        // Consulta SQL para insertar una nueva asignatura en la tabla 'ASIGNATURA'
        String query
                = "INSERT INTO ASIGNATURA (ASIG_TITULO, PRO_ID, DOC_MATRICULA) "
                + "VALUES (?, ?, ?)";
        try {
            // Prepara la declaración SQL
            ps = connection.prepareStatement(query);

            // Establece los valores en la consulta preparada a partir del objeto asignatura proporcionado
            ps.setString(1, asignatura.getNombre());
            ps.setInt(2, asignatura.getPrograma().getId());
            ps.setString(3, asignatura.getDocente().getMatricula());

            // Ejecuta la actualización de la base de datos y retorna el número de filas afectadas
            return ps.executeUpdate();
        } catch (SQLException e) {
            // Si ocurre una excepción SQLException, captura el error
         
            Error.descripcion = e.getMessage();  // Asigna el mensaje de error a un objeto Error          
            return 0; // Retorna 0 para indicar fallo en la inserción
        } finally {
            // Asegura el cierre de la PreparedStatement y la liberación de la conexión en el bloque finally
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    /**
     * Obtener todas las asignaturas dadas de alta en la BD
     * @return 
     */
    public static List<Asignatura> getAllAsignaturas() {
        // Obtiene una instancia del ConnectionPool para gestionar las conexiones a la base de datos
        ConnectionPool pool = ConnectionPool.getInstance();

        // obtiene una conexión del pool
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        //consulta para obtener todas las asignaturas
        String query = "SELECT \n"
                + "    ASIGNATURA.*,\n"
                + "    DOCENTE.*,\n"
                + "    PROGRAMA_ACADEMICO.*\n"
                + "FROM \n"
                + "    ASIGNATURA\n"
                + "JOIN \n"
                + "    DOCENTE ON ASIGNATURA.DOC_MATRICULA = DOCENTE.DOC_MATRICULA\n"
                + "JOIN \n"
                + "    PROGRAMA_ACADEMICO ON ASIGNATURA.PRO_ID = PROGRAMA_ACADEMICO.PRO_ID";

        try {
            //prepara la declaración SQL
            ps = connection.prepareStatement(query);

            //ejecuta la consulta
            rs = ps.executeQuery();

            //Colección para almacenar la lista de asignaturas
            ArrayList<Asignatura> asignaturas = new ArrayList<>();

            //creamos un objeto de tipo asignaturas
            Asignatura asignatura = null;

            // creamos un objeto de tipo docente
            Docente docente = null;

            //creamos un objeto de tipo programaAcademico
            ProgramaAcademico programa = new ProgramaAcademico();
            //se recorre la lista de elementos encontrados en la bd
            while (rs.next()) {
                asignatura = new Asignatura();
                asignatura.setId(rs.getInt("ASIG_CLAVE"));
                asignatura.setNombre(rs.getString("ASIG_TITULO"));

                //instanciamos el objeto docente a la clase Docente
                docente = new Docente();
                docente.setMatricula(rs.getString("DOC_MATRICULA"));
                docente.setDoc_nombre(rs.getString("DOC_NOMBRE"));
                docente.setDoc_apellidos(rs.getString("DOC_APELLIDOS"));
                docente.setDoc_telefono(rs.getString("DOC_TELEFONO"));
                docente.setDoc_email(rs.getString("DOC_EMAIL"));

                //instanciamos el objeto programa a la clase ProgramaAcademico
                programa = new ProgramaAcademico(rs.getInt("PRO_ID"),
                        rs.getString("PRO_NOMBRE"));

                //establezco el docente y programa academico en el objeto asignatura
                asignatura.setDocente(docente);
                asignatura.setPrograma(programa);

                //añado la asignatura al list
                asignaturas.add(asignatura);
            }

            return asignaturas;
        } catch (SQLException e) {
            System.out.println(e);
            Error.descripcion = e.getMessage();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }//fin del metodo getAllAsignaturas

    /**
     * Obtener una asignatura mediante su clave
     *
     * @param id
     * @return
     */
    public static Asignatura findAsignaturaById(int id) {
        // Obtener una instancia del pool de conexiones
        ConnectionPool pool = ConnectionPool.getInstance();

        // Obtener una conexión del pool
        Connection connection = pool.getConnection();

        // Declaración de objetos para manejar consultas y resultados
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Consulta SQL para obtener datos de ASIGNATURA, DOCENTE y PROGRAMA_ACADEMICO
        String query = "SELECT \n"
                + "    ASIGNATURA.*,\n"
                + "    DOCENTE.*,\n"
                + "    PROGRAMA_ACADEMICO.*\n"
                + "FROM \n"
                + "    ASIGNATURA\n"
                + "JOIN \n"
                + "    DOCENTE ON ASIGNATURA.DOC_MATRICULA = DOCENTE.DOC_MATRICULA\n"
                + "JOIN \n"
                + "    PROGRAMA_ACADEMICO ON ASIGNATURA.PRO_ID = PROGRAMA_ACADEMICO.PRO_ID "
                + "WHERE ASIG_CLAVE = ?;";

        try {
            // Preparar la consulta SQL
            ps = connection.prepareStatement(query);

            // Establecer el valor del parámetro en la consulta SQL
            ps.setInt(1, id);

            // Ejecutar la consulta y obtener el conjunto de resultados
            rs = ps.executeQuery();

            // Declaraciones de objetos para almacenar resultados
            Asignatura asignatura = null;
            Docente docente = null;
            ProgramaAcademico programa = null;

            // Verificar si hay resultados en el conjunto
            if (rs.next()) {
                // Crear un objeto Asignatura y establecer sus propiedades desde los resultados
                asignatura = new Asignatura();
                asignatura.setId(rs.getInt("ASIG_CLAVE"));
                asignatura.setNombre(rs.getString("ASIG_TITULO"));

                // Crear un objeto Docente y establecer sus propiedades desde los resultados
                docente = new Docente();
                docente.setMatricula(rs.getString("DOC_MATRICULA"));
                docente.setDoc_nombre(rs.getString("DOC_NOMBRE"));
                docente.setDoc_apellidos(rs.getString("DOC_APELLIDOS"));
                docente.setDoc_telefono(rs.getString("DOC_TELEFONO"));
                docente.setDoc_email(rs.getString("DOC_EMAIL"));

                // Crear un objeto ProgramaAcademico y establecer sus propiedades desde los resultados
                programa = new ProgramaAcademico(rs.getInt("PRO_ID"), rs.getString("PRO_NOMBRE"));

                // Establecer el docente y el programa académico en el objeto Asignatura
                asignatura.setDocente(docente);
                asignatura.setPrograma(programa);
            }

            // Devolver el objeto Asignatura
            return asignatura;

        } catch (SQLException e) {
            // Manejar excepciones SQL
            System.out.println(e);
            Error.descripcion = e.getMessage();
            return null;
        } finally {
            // Cerrar recursos
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            // Liberar la conexión de nuevo al pool
            pool.freeConnection(connection);
        }

    }//fin del metodo findAsignaturaById

    /**
     * Actualiza una asignatura en la base de datos
     *
     * @param asignatura
     * @return
     */
    public static int update(Asignatura asignatura) {

        // Obtener una instancia del pool de conexiones
        ConnectionPool pool = ConnectionPool.getInstance();

        // Obtener una conexión del pool
        Connection connection = pool.getConnection();

        // Declarar un objeto PreparedStatement para ejecutar la actualización
        PreparedStatement ps = null;

        // Consulta SQL para realizar la actualización en la tabla ASIGNATURA
        String query = "UPDATE ASIGNATURA SET "
                + "ASIG_TITULO = ?, "
                + "DOC_MATRICULA = ?, "
                + "PRO_ID = ? "
                + "WHERE ASIG_CLAVE = ?";

        try {
            // Preparar la consulta SQL
            ps = connection.prepareStatement(query);

            // Establecer los valores de los parámetros en la consulta SQL utilizando métodos setXXX
            ps.setString(1, asignatura.getNombre());
            ps.setString(2, asignatura.getDocente().getMatricula());
            ps.setInt(3, asignatura.getPrograma().getId());
            ps.setInt(4, asignatura.getId());

            // Ejecutar la actualización y devolver el número de filas afectadas
            return ps.executeUpdate();

        } catch (SQLException e) {
            // Manejar excepciones SQL
            System.out.println(e);
            Error.descripcion = e.getMessage();
            return 0;

        } finally {
            // Cerrar el objeto PreparedStatement y liberar la conexión al pool
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//fin del metodo update

    /**
     * Elimina una asignatura de la BD
     *
     * @param id
     * @return
     */
    public static int delete(int id) {
        // Obtener una instancia del pool de conexiones
        ConnectionPool pool = ConnectionPool.getInstance();

        // Obtener una conexión del pool
        Connection connection = pool.getConnection();

        // Inicializar un objeto PreparedStatement que se utilizará para ejecutar la consulta preparada
        PreparedStatement ps = null;

        // Definir la consulta SQL para eliminar una fila de la tabla ASIGNATURA basada en la clave ASIG_CLAVE
        String query = "DELETE FROM ASIGNATURA "
                + "WHERE ASIG_CLAVE = ?";

        try {
            // Preparar la consulta con la conexión obtenida
            ps = connection.prepareStatement(query);

            // Establecer el valor del parámetro en la posición 1 con el valor proporcionado (id)
            ps.setInt(1, id);

            // Ejecutar la consulta y devolver el número de filas afectadas
            return ps.executeUpdate();
        } catch (SQLException e) {
            // Capturar y manejar cualquier excepción SQL que pueda ocurrir
            System.out.println(e);

            // Asignar la descripción del error a una variable global para su posterior uso (si es necesario)
            Error.descripcion = e.getMessage();

            // Devolver 0 para indicar que no se realizaron eliminaciones (o manejar el error según tus necesidades)
            return 0;
        } finally {
            // Cerrar el PreparedStatement para liberar recursos
            DBUtil.closePreparedStatement(ps);

            // Devolver la conexión al pool para su reutilización
            pool.freeConnection(connection);
        }

    }//fin del metodo delete 

}//fin de la clase
