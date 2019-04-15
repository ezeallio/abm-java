/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidordao;

import Dao.DAO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import Persona.Alumno;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class AtencionCliente extends Thread {
    private final ObjectOutputStream salida;
    private final ObjectInputStream entrada;
	
    private final DAO<Alumno, Integer> dao;

    public AtencionCliente(ObjectOutputStream salida, ObjectInputStream entrada, DAO<Alumno, Integer> dao) {
        this.salida = salida;
        this.entrada = entrada;
        this.dao = dao;
    }

    
       
    @Override
    public void run() {
        String comando;
			
        try
        {
        while(!(comando = (String)entrada.readObject()).equals("fin"))
        {
            switch(comando.toLowerCase())
            {
                    case "insertar":
                            {
                                dao.insertar((Alumno)entrada.readObject());
                                salida.writeObject("TODO BIEN");
                                salida.reset();
                                break;
                            }

                    case "eliminar":
                            {
                                dao.eliminar((Alumno)entrada.readObject());
                                salida.writeObject("TODO BIEN");
                                salida.reset();
                                break;
                            }
                    
                    case "dardebaja":
                            {
                                dao.darDeBaja((Alumno) entrada.readObject());
                                salida.writeObject("TODO BIEN");
                                salida.reset();
                                break;
                            }
                            
                    case "actualizar":
                            {
                                dao.actualizar((Alumno) entrada.readObject());
                                salida.writeObject("TODO BIEN");
                                salida.reset();
                                break;
                            }
                            
                    case "buscar":
                            {
                                Alumno alumno = dao.buscar((Integer) entrada.readObject());
                                salida.writeObject("TODO BIEN");
                                salida.reset();
                                salida.writeObject(alumno);
                                salida.reset();
                                break;
                            }
                    case "existe":
                            {
                                Boolean existe = dao.existe((Integer) entrada.readObject());
                                salida.writeObject("TODO BIEN");
                                salida.reset();
                                salida.writeObject(existe);
                                salida.reset();
                                break;
                            }
                            
                    case "buscartodos":
                            {
                                ArrayList<Alumno> listAlumnos = dao.buscarTodos();
                                salida.writeObject("TODO BIEN");
                                salida.reset();
                                salida.writeObject(listAlumnos);
                                salida.reset();
                                break;
                            }
                    /*case "cerrar":
                            {
                                break;
                            }*/
           
                    default:
                            {
                                Logger.getLogger(ServidorDAO.class.getName()).log(Level.SEVERE, "Comando " + comando + " no reconocido");
                                break;
                            }
            }
        }
        if (comando.equals("fin")) 
            {
                try {
                    entrada.close();
                    salida.close();
                    System.out.println("Un cliente se ha desconectado.");
                } catch (IOException ex) {
                    Logger.getLogger(ServidorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        catch (Exception ex)
        {
            try {
                Logger.getLogger(ServidorDAO.class.getName()).log(Level.SEVERE, null, ex);
                salida.writeObject("ERROR: " + ex.getMessage());
            } catch (IOException ex1) {
                Logger.getLogger(AtencionCliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }        
        
    }
    
    
   private Socket socket; 
}
