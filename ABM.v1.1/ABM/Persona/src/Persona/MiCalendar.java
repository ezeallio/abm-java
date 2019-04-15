/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persona;

import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author user
 */
public class MiCalendar extends GregorianCalendar {
    private int anio;
    private int mes;
    private int dia;

    public MiCalendar(int dia, int mes, int anio) throws Exception {
        super(anio, mes-1, dia);
        this.setAnio(anio);
        this.setMes(mes);
        this.setDia(dia);
        super.setLenient(false);
        try{
            super.get(Calendar.DAY_OF_MONTH);
        }
        catch(IllegalArgumentException e){
            throw new Exception("Error fecha invalida");
        }
    }
    

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", dia,mes,anio);
    }
    
    public Date toDate()
    {
        return new Date(getTimeInMillis());
    }
    
}
