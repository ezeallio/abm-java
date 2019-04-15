/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daotest;

import Dao.AlumnoDaoTxt;
import Dao.DAO;
import Persona.Alumno;
import Persona.MiCalendar;
import java.io.File;

/**
 *
 * @author user
 */
public class DaoTest {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        DAO<Alumno,Integer> dao;
        dao = new AlumnoDaoTxt(new File("Alumno.txt"));
        Alumno a1 = new Alumno(12345678,"Marta Sanchez",'f',new MiCalendar(5,12,1994),5.3,8,"MED",new MiCalendar(5,8,2013),"A");
        Alumno a2 = new Alumno(35789632,"Jose Ponz",'M',new MiCalendar(5,12,1980),8.314,8,"INF",new MiCalendar(5,8,2012),"A");
        dao.insertar(a1);
        dao.insertar(a2);  
    }
    
}
