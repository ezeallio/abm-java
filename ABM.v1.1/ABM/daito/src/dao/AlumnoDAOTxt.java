/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import Persona.Alumno;

/**
 *
 * @author Néstor
 */
public class AlumnoDAOTxt extends DAO<Alumno, Integer>
{
	public AlumnoDAOTxt(File archivoFile) throws FileNotFoundException
	{
		archivo = new RandomAccessFile(archivoFile, "rws");
	}
	
	
	
	@Override
	public void insertar(Alumno alu) throws Exception
	{
		archivo.seek(archivo.length());
		archivo.writeBytes(alu.toString() + System.lineSeparator());
	}
	
	
	@Override
	public void actualizar(Alumno obj) throws Exception
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	
	@Override
	public Alumno buscar(Integer id) throws Exception
	{
            System.out.print("Esta usando este DAO");
    	}
	
	
	@Override
	public boolean existe(Integer id) throws Exception
	{
		archivo.seek(0);
		String linea;
		while((linea = archivo.readLine()) != null)
		{
			if(Long.valueOf(linea.substring(0, 8)).equals(id))
				return true;
		}
		
		return false;
	}
	
	
	@Override
	public void eliminar(Alumno obj) throws Exception
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void darDeBaja(Alumno obj) throws Exception
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	
	
	private RandomAccessFile archivo;
}
