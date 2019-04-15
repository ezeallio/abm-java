/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidordao;

import Dao.AlumnoDAOJDBC;
import Dao.AlumnoDaoTxt;
import Dao.DAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Persona.Alumno;
import java.sql.SQLException;

/**
 *
 * @author Néstor
 */
public class ServidorDAO
{

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
        try {
                dao = new AlumnoDaoTxt(new File("C:\\Users\\Anónimo_PC\\Documents\\BASE DATOS TXT\\Alumnos.txt"));
                //dao = new AlumnoDAOJDBC("localhost","3306");
                serverSocket = new ServerSocket(Integer.valueOf(args[0]));
                while(true) {


                        Socket socket = serverSocket.accept();

                        ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

                        new AtencionCliente(salida, entrada, dao).start();


                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ServidorDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ServidorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	private static ServerSocket serverSocket;
	private static DAO<Alumno, Integer> dao;
}
