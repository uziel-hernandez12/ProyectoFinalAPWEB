/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scb.bussines;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Docente implements Serializable {
    
    //declaracion de campos
    private String matricula;
    private int dept_id;
    private String doc_nombre;
    private String doc_apellidos;
    private String doc_telefono;
    private String doc_email;

    /**
     * Constructor
     * @param matricula
     * @param doc_nombre
     * @param doc_apellidos
     * @param doc_telefono
     * @param doc_email 
     */
    public Docente(String matricula, String doc_nombre, String doc_apellidos, String doc_telefono, String doc_email) {
        this.matricula = matricula;
        this.doc_nombre = doc_nombre;
        this.doc_apellidos = doc_apellidos;
        this.doc_telefono = doc_telefono;
        this.doc_email = doc_email;
    }

    //constructor vacio
    public Docente(){
    }
    
    //getters y setters
    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the dept_id
     */
    public int getDept_id() {
        return dept_id;
    }

    /**
     * @param dept_id the dept_id to set
     */
    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    /**
     * @return the doc_nombre
     */
    public String getDoc_nombre() {
        return doc_nombre;
    }

    /**
     * @param doc_nombre the doc_nombre to set
     */
    public void setDoc_nombre(String doc_nombre) {
        this.doc_nombre = doc_nombre;
    }

    /**
     * @return the doc_apellidos
     */
    public String getDoc_apellidos() {
        return doc_apellidos;
    }

    /**
     * @param doc_apellidos the doc_apellidos to set
     */
    public void setDoc_apellidos(String doc_apellidos) {
        this.doc_apellidos = doc_apellidos;
    }

    /**
     * @return the doc_telefono
     */
    public String getDoc_telefono() {
        return doc_telefono;
    }

    /**
     * @param doc_telefono the doc_telefono to set
     */
    public void setDoc_telefono(String doc_telefono) {
        this.doc_telefono = doc_telefono;
    }

    /**
     * @return the doc_email
     */
    public String getDoc_email() {
        return doc_email;
    }

    /**
     * @param doc_email the doc_email to set
     */
    public void setDoc_email(String doc_email) {
        this.doc_email = doc_email;
    }
    
    
}
