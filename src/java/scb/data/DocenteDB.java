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
import scb.bussines.Docente;

/**
 *
 * @author Admin
 */
public class DocenteDB {

    /**
     * Retorna la lista de docentes dados de alta en la bd
     *
     * @return
     */
    public static List<Docente> getAllDocentes() {
        // Obtiene una instancia del ConnectionPool para gestionar las conexiones a la base de datos
        ConnectionPool pool = ConnectionPool.getInstance();

        // obtiene una conexión del pool
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        //consulta para obtener todos los docentes
        String query = "SELECT * FROM DOCENTE";

        try {
            //prepara la declaración SQL
            ps = connection.prepareStatement(query);

            //ejecuta la consulta
            rs = ps.executeQuery();

            //Colección para almacenar la lista de docentes
            ArrayList<Docente> docentes = new ArrayList<>();

            //creamos un objeto de tipo docente
            Docente docente = null;

            //se recorre la lista de elementos encontrados en la bd
            while (rs.next()) {
                docente = new Docente();
                docente.setMatricula(rs.getString("DOC_MATRICULA"));
                docente.setDoc_nombre(rs.getString("DOC_NOMBRE"));
                docente.setDoc_apellidos(rs.getString("DOC_APELLIDOS"));
                docente.setDoc_telefono(rs.getString("DOC_TELEFONO"));
                docente.setDoc_email(rs.getString("DOC_EMAIL"));
                docentes.add(docente); //se agrega el elemento a la colección.
            }

            return docentes;
        } catch (SQLException e) {
            System.out.println(e);
            Error.descripcion = e.getMessage();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//fin del método getAllDocentes

    /**
     * Retorna el docente que coindcida con la matricula
     *
     * @return docente
     */
    public static Docente findDocenteByMatricula(String matricula) {

        // Obtiene una instancia del pool de conexiones
        ConnectionPool pool = ConnectionPool.getInstance();

        // Obtiene una conexión del pool
        Connection connection = pool.getConnection();

        // Prepara las declaraciones SQL
        PreparedStatement ps = null;

        // Inicializa el conjunto de resultados
        ResultSet rs = null;

        // Define la consulta SQL para obtener información del docente por matrícula
        String query = "SELECT * FROM DOCENTE "
                + "WHERE DOC_MATRICULA = ?";
        try {
            // Prepara la declaración con la consulta SQL
            ps = connection.prepareStatement(query);

            // Establece el valor del parámetro de matrícula en la consulta
            ps.setString(1, matricula);

            // Ejecuta la consulta y obtiene el conjunto de resultados
            rs = ps.executeQuery();

            // Inicializa un objeto Docente
            Docente docente = null;

            // Verifica si hay resultados en el conjunto
            if (rs.next()) {
                // Crea un objeto Docente y asigna los valores de las columnas
                docente = new Docente();
                docente.setMatricula(rs.getString("DOC_MATRICULA"));
                docente.setDoc_nombre(rs.getString("DOC_NOMBRE"));
                docente.setDoc_apellidos(rs.getString("DOC_APELLIDOS"));
                docente.setDoc_telefono(rs.getString("DOC_TELEFONO"));
                docente.setDoc_email(rs.getString("DOC_EMAIL"));
            }

            // Retorna el objeto Docente
            return docente;
        } catch (SQLException e) {
            // Maneja las excepciones SQL, imprime el error y establece la descripción del error
            System.out.println(e);
            Error.descripcion = e.getMessage();

            // Retorna nulo en caso de error
            return null;
        } finally {
            // Cierra el conjunto de resultados, la declaración preparada y libera la conexión al pool
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//Fin del metodo findDocenteByMatricula

}//fin de la clase DocenteDB
