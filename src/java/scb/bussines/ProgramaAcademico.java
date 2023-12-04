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
public class ProgramaAcademico implements Serializable{
    //declaracion de campos de la calse ProgramaAcademico
    private int id;
    private String nombre;
    
    //constructores
    public ProgramaAcademico(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }
    public ProgramaAcademico(){}
    
    //getter y setters

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
    
}
