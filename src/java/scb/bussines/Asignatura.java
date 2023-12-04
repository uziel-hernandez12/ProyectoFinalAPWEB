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
public class Asignatura implements Serializable{
    
    //declaracion de campos
    private int id;
    private Docente docente;
    private ProgramaAcademico programa;
    private String nombre;
    
    //constructores
    public Asignatura(int id, Docente docente, ProgramaAcademico programa, String nombre) {
        this.id = id;
        this.docente = docente;
        this.programa = programa;
        this.nombre = nombre;
    }
    
    public Asignatura(Docente docente, ProgramaAcademico programa, String nombre){
        this.docente = docente;
        this.programa = programa;
        this.nombre = nombre;
    }
    
    public Asignatura(){}
    //getters y setters

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the docente
     */
    public Docente getDocente() {
        return docente;
    }

    /**
     * @param docente the docente to set
     */
    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    /**
     * @return the programa
     */
    public ProgramaAcademico getPrograma() {
        return programa;
    }

    /**
     * @param programa the programa to set
     */
    public void setPrograma(ProgramaAcademico programa) {
        this.programa = programa;
    }
    
  

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
     public String toString(){
        return this.nombre;
    }
}
