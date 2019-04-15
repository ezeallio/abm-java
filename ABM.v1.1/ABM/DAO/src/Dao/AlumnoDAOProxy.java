/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Persona.Alumno;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                salida.reset();
		salida.writeObject(obj);
                salida.reset();
		respuesta = (String)entrada.readObject();
		
		if(respuesta.equals("ERROR"))
			throw new Exception(respuesta);
	}

	@Override
	public void actualizar(Alumno obj) throws Exception
	{
            salida.writeObject("actualizar");
            salida.reset();
            salida.writeObject(obj);
            salida.reset();
            respuesta = (String) entrada.readObject();

            if (respuesta.equals("ERROR")) 
            {
                throw new Exception(respuesta);
            }
	}

	@Override
	public Alumno buscar(Integer id) throws Exception
	{
	    salida.writeObject("buscar");
            salida.reset();
            salida.writeObject(id);
            salida.reset();
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
            salida.reset();
            salida.writeObject(id);
            salida.reset();
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
            salida.reset();
            salida.writeObject(obj);
            salida.reset();
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
                salida.reset();
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
            salida.writeObject("dardebaja");
            salida.reset();
            salida.writeObject(obj);
            salida.reset();
            respuesta = (String) entrada.readObject();

            if (respuesta.equals("ERROR")) 
            {
                throw new Exception(respuesta);
            }
	}

    /*@Override
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
        
    }*/

    /*@Override
    public void darDeAlta(Alumno alumno) throws Exception {
        salida.writeObject("darDeAlta");
        salida.writeObject(alumno);
        respuesta = (String) entrada.readObject();

        if (respuesta.equals("ERROR")) {
            throw new Exception(respuesta);
        }
    }*/

        @Override
        public Long buscarPoss(Integer id) throws Exception {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public ResultSet table() throws SQLException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public ArrayList<Alumno> buscarTodos() throws Exception {
            salida.writeObject("buscartodos");
            salida.reset();
            respuesta = (String) entrada.readObject();

            if (respuesta.equals("ERROR")) {
                throw new Exception(respuesta);
            }
        
            ArrayList<Alumno> alumnosList = new ArrayList();
            alumnosList = (ArrayList<Alumno>) entrada.readObject();
            return alumnosList;
    }
    
        private Socket socket;
	private final ObjectInputStream entrada;
	private final ObjectOutputStream salida;
        private String respuesta;
}
