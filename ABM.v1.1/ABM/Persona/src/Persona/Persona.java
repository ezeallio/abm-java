/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persona;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Persona implements Serializable {

    public Persona(int dni, String ayn, char sexo, MiCalendar fecha) throws Exception {
        this.setDni(dni);
        this.setAyn(ayn);
        this.setSexo(sexo);
        this.setFecha(fecha);
    }

    public Persona() {
    }
    
    
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) throws Exception {
        if(dni <= 0||dni>99999999)
            throw new Exception("Error el dni es incorrecto");
        this.dni = dni;
    }

    public String getAyn() {
        return ayn;
    }

    public void setAyn(String ayn) {
        this.ayn = ayn;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) throws Exception{
        if(Character.toUpperCase(sexo) != 'F' && Character.toUpperCase(sexo) !='M')
            throw new Exception("Sexo no válido");
        this.sexo = Character.toUpperCase(sexo);
    }

    public MiCalendar getFecha() {
        return fecha;
    }

    public void setFecha(MiCalendar fecha) {
        this.fecha = fecha;
    }
    
    private int dni;
    private String ayn;
    private char sexo;
    private MiCalendar fecha;

    @Override
    public String toString() {
        return String.format("%08d\t%-30s\t%c\t",dni,ayn,sexo)+fecha ;
    }
    
    
    
}
