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
import scb.bussines.ProgramaAcademico;

/**
 *
 * @author Admin
 */
public class ProgramaAcademicoDB {
    /**
     * Retorna la lista de programas academicos dados de alta en la bd
     *
     * @return
     */
    public static List<ProgramaAcademico> getAllProgramas() {
        // Obtiene una instancia del ConnectionPool para gestionar las conexiones a la base de datos
        ConnectionPool pool = ConnectionPool.getInstance();

        // obtiene una conexión del pool
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        //consulta para obtener todos los programas academicos
        String query = "SELECT * FROM PROGRAMA_ACADEMICO";

        try {
            //prepara la declaración SQL
            ps = connection.prepareStatement(query);

            //ejecuta la consulta
            rs = ps.executeQuery();

            //Colección para almacenar la lista de programas academicos
            ArrayList<ProgramaAcademico> programas = new ArrayList<>();

            //creamos un objeto de tipo ProgramaAcademico
             ProgramaAcademico programa  = null;

            //se recorre la lista de elementos encontrados en la bd
            while (rs.next()) {
                programa = new ProgramaAcademico();
                programa.setId(rs.getInt("PRO_ID"));
                programa.setNombre(rs.getString("PRO_NOMBRE"));
                programas.add(programa); //se agrega el elemento a la colección.
            }

            return programas;
        } catch (SQLException e) {
            System.out.println(e);
            Error.descripcion = e.getMessage();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
     
    }//fin del método getAllProgramas

    /**
     * Devuelve el programa academico que coincida con el ID
     * @param id
     * @return 
     */
    public static ProgramaAcademico findProgramaById(int id) {
         // Obtiene una instancia del pool de conexiones
        ConnectionPool pool = ConnectionPool.getInstance();

        // Obtiene una conexión del pool
        Connection connection = pool.getConnection();

        // Prepara las declaraciones SQL
        PreparedStatement ps = null;

        // Inicializa el conjunto de resultados
        ResultSet rs = null;

        // Define la consulta SQL para obtener información del programa academico por su id
        String query = "SELECT * FROM PROGRAMA_ACADEMICO "
                + "WHERE PRO_ID = ?";
        try {
            // Prepara la declaración con la consulta SQL
            ps = connection.prepareStatement(query);

            // Establece el valor del parámetro de matrícula en la consulta
            ps.setInt(1, id);

            // Ejecuta la consulta y obtiene el conjunto de resultados
            rs = ps.executeQuery();

            // Inicializa un objeto programa
            ProgramaAcademico programa = null;

            // Verifica si hay resultados en el conjunto
            if (rs.next()) {
                // Crea un objeto ProgramaAcademico y asigna los valores de las columnas
                programa = new ProgramaAcademico();
                programa.setId(id);
                programa.setNombre(rs.getString("PRO_NOMBRE"));
            }

            // Retorna el objeto programa
            return programa;
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

    }//fin del metodo
}//fin de la clase
