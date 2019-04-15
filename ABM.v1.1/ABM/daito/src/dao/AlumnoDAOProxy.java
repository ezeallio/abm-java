/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Persona.Alumno;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Néstor
 */
public class AlumnoDAOProxy extends DAO<Alumno, Integer>
{
	public AlumnoDAOProxy(String server, int puerto) throws IOException
	{
		socket = new Socket(server, puerto);
		salida = new ObjectOutputStream(socket.getOutputStream());
		entrada = new ObjectInputStream(socket.getInputStream());
	}
	
	
	@Override
	public void insertar(Alumno obj) throws Exception
	{
		salida.writeObject("insertar");
		salida.writeObject(obj);
		respuesta = (String)entrada.readObject();
		
		if(respuesta.equals("ERROR"))
			throw new Exception(respuesta);
	}

	@Override
	public void actualizar(Alumno obj) throws Exception
	{
            salida.writeObject("actualizar");
            salida.writeObject(obj);
            respuesta = (String) entrada.readObject();

            if (respuesta.equals("ERROR")) 
            {
                throw new Exception(respuesta);
            }
	}

	@Override
	public Alumno buscar(Integer id) throws Exception
	{
	    salida.writeObject("buscar_id");
            salida.writeObject(id);
            respuesta = (String) entrada.readObject();

            if (respuesta.equals("ERROR")) 
            {
                throw new Exception(respuesta);
            }
        
            return (Alumno) entrada.readObject();
	}

	@Override
	public boolean existe(Integer id) throws Exception
	{
            salida.writeObject("existe");
            salida.writeObject(id);
            respuesta = (String) entrada.readObject();

            if (respuesta.equals("ERROR")) 
            {
                throw new Exception(respuesta);
            }

            return (Boolean) entrada.readObject();
	}

	@Override
	public void eliminar(Alumno obj) throws Exception
	{
            salida.writeObject("eliminar");
            salida.writeObject(obj);
            respuesta = (String) entrada.readObject();
        
            if (respuesta.equals("ERROR")) 
            {
                throw new Exception(respuesta);
            }
	}

        @Override
        public void cerrar() 
        {
            try 
            {
                salida.writeObject("fin");
                entrada.close();
                salida.close();
                socket.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(AlumnoDAOProxy.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }

	@Override
	public void darDeBaja(Alumno obj) throws Exception
	{
            salida.writeObject("darDeBaja");
            salida.writeObject(obj);
            respuesta = (String) entrada.readObject();

            if (respuesta.equals("ERROR")) 
            {
                throw new Exception(respuesta);
            }
	}

   // @Override
    public List<Alumno> buscar(String id) throws Exception {
        salida.writeObject("buscar");
        salida.writeObject(id);
        respuesta = (String) entrada.readObject();

        if (respuesta.equals("ERROR")) {
            throw new Exception(respuesta);
        }
        
        List<Alumno> alumnosList = new ArrayList();
        alumnosList = (List<Alumno>) entrada.readObject();
        return alumnosList;
        
    }

    /*@Override
    public void darDeAlta(Alumno alumno) throws Exception {
        salida.writeObject("darDeAlta");
        salida.writeObject(alumno);
        respuesta = (String) entrada.readObject();

        if (respuesta.equals("ERROR")) {
            throw new Exception(respuesta);
        }
    }*/

    //@Override
    public List<Alumno> buscar_todos() throws Exception {
        salida.writeObject("buscarTodos");
        respuesta = (String) entrada.readObject();

        if (respuesta.equals("ERROR")) {
            throw new Exception(respuesta);
        }
        
        List<Alumno> alumnosList = new ArrayList();
        alumnosList = (List<Alumno>) entrada.readObject();
        return alumnosList;
    }
    
	private Socket socket;
	private final ObjectInputStream entrada;
	private final ObjectOutputStream salida;
        private String respuesta;
}
