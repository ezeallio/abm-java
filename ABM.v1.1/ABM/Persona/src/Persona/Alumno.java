/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persona;

/**
 *
 * @author user
 */
public class Alumno extends Persona{
    private double prom;
    private int cantMat;
    private String carrera;
    private MiCalendar fechaIngr;
    private String estado;

    
    public Alumno(int dni, String ayn, char sexo, MiCalendar fecha,double prom,int cantMat,String carrera,MiCalendar fechaIngr,String estado) throws Exception {
        super(dni, ayn, sexo, fecha);
        this.setCantMat(cantMat);
        this.setCarrera(carrera);
        this.setProm(prom);
        this.setFechaIngr(fechaIngr);
        this.setEstado(estado);
    }

    public Alumno() {
        super();
    }


    public double getProm() {
        return prom;
    }

    public void setProm(double prom) throws Exception {
        if(prom <1 || prom>10)
            throw new Exception("El promedio es incorrecto");
        this.prom = prom;
    }

    public int getCantMat() {
        return cantMat;
    }

    public void setCantMat(int cantMat) throws Exception{
        if(cantMat <0||cantMat>100)
            throw new Exception("La cantidad de materias es incorrecta");
        this.cantMat = cantMat;
    }

    public String getCarrera() {
        return carrera;
    }

    @Override
    public String toString() {
        int promedio=(int) prom;
        if(prom!=10)
            return super.toString() + String.format("\t%.2f\t%02d\t%-20s\t",prom,cantMat,carrera) + fechaIngr + String.format("\t%-2s\t",this.getEstado());    
        else    
            return super.toString() + String.format("\t10  \t%02d\t%-20s\t",cantMat,carrera) + fechaIngr + String.format("\t%-2s\t",this.getEstado()); 
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public MiCalendar getFechaIngr() {
        return fechaIngr;
    }

    public void setFechaIngr(MiCalendar fechaIngr) {
        this.fechaIngr = fechaIngr;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
