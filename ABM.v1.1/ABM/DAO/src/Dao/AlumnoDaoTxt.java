/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Persona.Alumno;
import Persona.MiCalendar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class AlumnoDaoTxt extends DAO<Alumno,Integer> {
    
    public AlumnoDaoTxt(File path) throws FileNotFoundException {
        archivo = new RandomAccessFile(path,"rws");
    }

    @Override
    public void insertar(Alumno obj) throws Exception {
        System.out.print("esta usando este DAO");
        archivo.seek(archivo.length());
        archivo.writeBytes(obj.toString() + System.lineSeparator());
    }

    @Override
    public void actualizar(Alumno obj) throws Exception {
            
            long poss =0;
            archivo.seek(0);
            String linea;
            while((linea=archivo.readLine())!= null)
            {
                if(obj.getDni() == Integer.valueOf(linea.substring(0, 8)))
            /*boolean encontro = false;
            String linea = archivo.readLine();
            while(linea != null && !encontro)
            {if(obj.getDni() == Integer.valueOf(linea.substring(0, 8)))
             {obj = buscar(obj.getDni());*/
                {    
                    archivo.seek(poss);
                    archivo.writeBytes(obj.toString() + System.lineSeparator());
                }
                poss=archivo.getFilePointer();
             /*}

             linea = archivo.readLine();
            } */  
              
            }
    }

    @Override
    public Alumno buscar(Integer id) throws Exception {
        String linea;   
        System.out.print("esta usando este DAO");
        if(existe(id))
        {
            archivo.seek(0);
            while((linea = archivo.readLine()) != null)
            {
                if(Integer.valueOf(linea.substring(0, 8)).equals(id))
                {
                    String[] campos = linea.split("\t"); 
                    alu1.setDni(Integer.valueOf(campos[0]));
                    alu1.setAyn(campos[1]);
                    alu1.setSexo(campos[2].charAt(0));
                    alu1.setProm(Double.valueOf(campos[4].replace(",",".")));
                    alu1.setCantMat(Integer.valueOf(campos[5]));
                    alu1.setCarrera(campos[6].substring(0,3));
                    toFechas(campos[3],campos[7]);
                    alu1.setEstado(campos[8].substring(0, 1));
                    return alu1;
                }
            }        
        }       
        return alu1;
    }

    @Override
    public void eliminar(Alumno obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //ACA NO SE HACE
    }

    @Override
    public void darDeBaja(Alumno obj) throws Exception {
    	long tamlinea;
        archivo.seek(0);

        String linea;
        while((tamlinea = archivo.getFilePointer())>=0  && (linea=archivo.readLine()) != null)
       // while ((puntero = archivo.getFilePointer()) != null &&(linea = archivo.readLine()) != null)
            {
             if(obj.getDni() == Integer.valueOf(linea.substring(0, 8)) && (existe(obj.getDni())))
             {
              obj = buscar(obj.getDni());
              obj.setEstado("B");
              archivo.seek(tamlinea);
              archivo.writeBytes(obj.toString() + System.lineSeparator());
              //JOptionPane.showMessageDialog(null, "La persona fue dada de baja exitosamente", "CONFIRMACION", JOptionPane.INFORMATION_MESSAGE);    
              //archivo.seek(0);

              
             }

             //linea = archivo.readLine();
            }    
    }

    @Override
    public boolean existe(Integer id) throws Exception {
        String linea;
        archivo.seek(0);
        while((linea = archivo.readLine()) != null)
            {
                 char variable=linea.charAt(93);
                if(Integer.valueOf(linea.substring(0, 8)).equals(id) && (variable =='A'))
                {   
                    return true;
                }
            }
        return false;
    }
      @Override
    public void cerrar() throws Exception {
        archivo.close();
    }

    @Override
    public Long buscarPoss(Integer id) throws Exception {
        Long poss = null;
	archivo.seek(0);
        String linea;
	while((linea = archivo.readLine()) != null)
		{
                    String[] campos = linea.split("\t"); 
                    Integer aluDNI = Integer.valueOf(campos[0]);
                    poss = archivo.getFilePointer();
                    if((id == aluDNI) && (campos[8].charAt(0)) == 'A')
		 	{			  
			  break;
			}
		}
	return poss-2;
    }
    
    private void toFechas(String Nac,String Ingr) throws Exception {
    String[] auxNac  = Nac.split("/");
    String[] auxIngr = Ingr.split("/");
        
    alu1.setFecha(new MiCalendar(Integer.valueOf(auxNac[0]),Integer.valueOf(auxNac[1]),Integer.valueOf(auxNac[2])));
    alu1.setFechaIngr(new MiCalendar(Integer.valueOf(auxIngr[0]),Integer.valueOf(auxIngr[1]),Integer.valueOf(auxIngr[2])));
        
    }
    
    private RandomAccessFile archivo;  
    private Alumno alu1 = new Alumno();

    @Override
    public ResultSet table() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Alumno> buscarTodos() throws Exception {
            try {
                File fail = new File("Alumno.txt");
                String ruta = fail.getAbsolutePath();
                lista.removeAll(lista);
                File archivo = new File(ruta);
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                String [] IngrString, NacString, auxiliar;
                Iterator iter = lista.iterator();
                while((linea =br.readLine()) != null)
                {
                    auxiliar = linea.split("\t");
                    if(((auxiliar[8]).charAt(0)) == 'A')
                    {
                        Alumno aluAux = new Alumno();
                        aluAux.setDni(Integer.valueOf(auxiliar[0]));
                        aluAux.setAyn(auxiliar[1]);
                        aluAux.setSexo(auxiliar[2].charAt(0));
                        NacString = auxiliar[3].split("/");
                        aluAux.setFecha(new MiCalendar(Integer.valueOf(NacString[0]),Integer.valueOf(NacString[1]),Integer.valueOf(NacString[2])));
                        aluAux.setProm(Double.valueOf(auxiliar[4].replace(",",".")));
                        aluAux.setCantMat(Integer.valueOf(auxiliar[5]));
                        aluAux.setCarrera(auxiliar[6]);
                        IngrString = auxiliar[7].split("/");
                        aluAux.setFechaIngr(new MiCalendar(Integer.valueOf(IngrString[0]),Integer.valueOf(IngrString[1]),Integer.valueOf(IngrString[2])));
                        aluAux.setEstado(auxiliar[8]);
                        lista.add(aluAux);
                    }
                    
                }
                
            } catch (Exception ex) {
                Logger.getLogger(AlumnoDaoTxt.class.getName()).log(Level.SEVERE, null, ex);
            }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return lista;
    }
    
    ArrayList<Alumno> lista = new ArrayList<>();
}
