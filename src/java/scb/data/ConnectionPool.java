/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.data;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author admin
 */
public class ConnectionPool {

    // Instancia única del pool.
    private static ConnectionPool pool = null;

    // Fuente de datos para obtener conexiones.
    private static DataSource dataSource = null;

    // Constructor privado para evitar instanciación directa.
    private ConnectionPool() {
        try {
            // Se obtiene el contexto inicial.
            InitialContext ic = new InitialContext();

            // Se busca y asigna la fuente de datos (DataSource) en el contexto JNDI.
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/SCB");
        } catch (NamingException e) {
            // Manejo de excepciones en caso de problemas al buscar la fuente de datos.
            System.out.println(e);
        }
    }

    // Método estático para obtener la única instancia del pool (patrón Singleton).
    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    // Método para obtener una conexión de la fuente de datos.
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            // Manejo de excepciones en caso de problemas al obtener una conexión.
            System.out.println(e);
            return null;
        }
    }

    // Método para liberar una conexión cerrándola.
    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            // Manejo de excepciones en caso de problemas al cerrar la conexión.
            System.out.println(e);
        }
    }
}
