/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Persona.Alumno;
import Persona.MiCalendar;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Néstor
 */
public class AlumnoDAOJDBC extends DAO<Alumno, Integer>
{
    /*final String driver = "com.mysql.jdbc.Driver";
    final String mysql = "jdbc:mysql://";
    final String servers = "localhost";
    final String dbName = "abmjdb";
    final String puertos = "3306";
    final String user = "root";
    final String pass = "enter";*/
	public AlumnoDAOJDBC(String server, String puerto) throws SQLException
	{
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/alumnos_bd", "root", "root");
                //conexion = DriverManager.getConnection("jdbc:mysql://" + server + ":" + puerto + "/alumnoabm" , "root", "root");
		//conexion = DriverManager.getConnection("jdbc:mysql://localhost/universidad?user=root", "root", "46987300");
                //conexion = DriverManager.getConnection("jdbc:mysql://" + server + ":" + puerto + "/universidad" , "root", "46987300");
		String sentencia =
			"INSERT INTO alumno\n" +
			"(`dni`,\n" +
			"`apyn`,\n" +
			"`fechaNac`,\n" +
			"`fechaIngr`,\n" +
			"`sexo`,\n" +
			"`cantMatAprob`,\n" +
			"`promedio`,\n" +
                        "`carrera`,\n" +
                        "`estado`)\n" +
			"VALUES\n" +
			"(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		pStmtInsertar = conexion.prepareStatement(sentencia);
		
		
		sentencia =
			"select *\n" +
			"from alumno\n" +
			"where dni = ?;\n";
		
		pStmtBuscar = conexion.prepareStatement(sentencia);
                
                sentencia =
			"update alumno\n" +
                        "set Estado = 'B'\n" +
			"where dni = ?;\n";
		
		pStmtDarDeBaja = conexion.prepareStatement(sentencia);
                
                sentencia =
			"update alumno\n" +
                        "set Estado = 'A'\n" +
			"where dni = ?;\n";
		
		pStmtDarDeAlta = conexion.prepareStatement(sentencia);
                
                sentencia =
			"delete from alumno\n" +
			"where dni = ?;\n";
		
		pStmtEliminar = conexion.prepareStatement(sentencia);
                
                sentencia = 
                        "update alumno\n" +
                        "set `dni` = ?," +
                        "`apyn` = ?, " +
                        "`fechaNac`=?," +
                        "`fechaIngr`=?," +
                        "`sexo` =?," +
                        "`cantMatAprob`=?," +
                        "`promedio`=?," +
                        "`carrera`=?," +
                        "`estado` =?" +
                        "where dni = ?;\n";
                pStmtActualizar = conexion.prepareStatement(sentencia);
	}
	
	
	@Override
	public void insertar(Alumno alu) throws Exception
	{   
                    pStmtBuscar.setInt(1, alu.getDni());
		
                    ResultSet rs = pStmtBuscar.executeQuery();
                     if(rs.next() || this.existe(alu.getDni()))
                    {
                        pStmtDarDeAlta.setInt(1, alu.getDni());
                        pStmtDarDeAlta.executeUpdate();
                    }
                    else
                    {
                        pStmtInsertar.setInt(1, alu.getDni());
                        pStmtInsertar.setString(2, alu.getAyn());
                        pStmtInsertar.setDate(3, alu.getFecha().toDate());
                        pStmtInsertar.setDate(4, alu.getFechaIngr().toDate());
                        pStmtInsertar.setString(5, String.valueOf(alu.getSexo()));
                        pStmtInsertar.setInt(6, alu.getCantMat());
                        pStmtInsertar.setDouble(7, alu.getProm());
                        pStmtInsertar.setString(8, alu.getCarrera());
                        pStmtInsertar.setString(9, alu.getEstado());  
                        pStmtInsertar.executeUpdate();
                    }
                    
	}
	
	
	@Override
	public Alumno buscar(Integer id) throws Exception
	{
		pStmtBuscar.setInt(1, id);
		
		ResultSet rs = pStmtBuscar.executeQuery();
		
		if((!rs.next()) || ((rs.getString("estado")).equals("B")))
			return null;
		toFecha(rs);
		return new Alumno(rs.getInt("dni"), rs.getString("apyn"), rs.getString("sexo").charAt(0), new MiCalendar(Integer.valueOf(aux[0]),Integer.valueOf(aux[1]),Integer.valueOf(aux[2])), rs.getDouble("promedio"), rs.getInt("cantMatAprob"), rs.getString("carrera"),  new MiCalendar(Integer.valueOf(aux1[0]),Integer.valueOf(aux1[1]),Integer.valueOf(aux1[2])),rs.getString("estado"));
	}
        
	@Override
	public boolean existe(Integer id) throws Exception
	{
                pStmtBuscar.setInt(1, id);
		
		ResultSet rs = pStmtBuscar.executeQuery();
                if(!rs.next() || (rs.getString("estado")).equals("B"))
                    {
                        return false;
                    }
                else
                    {
                        return true;
                    }
                
	}
	
        @Override
	public void actualizar(Alumno obj) throws Exception
	{
            pStmtActualizar.setInt(1, obj.getDni());
            pStmtActualizar.setString(2, obj.getAyn());
            pStmtActualizar.setDate(3, obj.getFecha().toDate());
            pStmtActualizar.setDate(4, obj.getFechaIngr().toDate());
            pStmtActualizar.setString(5, String.valueOf(obj.getSexo()));
            pStmtActualizar.setInt(6, obj.getCantMat());
            pStmtActualizar.setDouble(7, obj.getProm());
            pStmtActualizar.setString(8, obj.getCarrera());
            pStmtActualizar.setString(9, obj.getEstado());
            pStmtActualizar.setInt(10, obj.getDni());
            pStmtActualizar.executeUpdate();
	}

	@Override
	public void darDeBaja(Alumno obj) throws Exception
	{
            pStmtDarDeBaja.setInt(1, obj.getDni());  
            pStmtDarDeBaja.executeUpdate();
	}

        @Override
	public void eliminar(Alumno obj) throws Exception
	{
            pStmtEliminar.setInt(1, obj.getDni());
            pStmtEliminar.executeUpdate();
	}

        @Override
        public ResultSet table() throws SQLException
        {
            String sql = "select * from alumno";
            //String sql = "select * from alumnos";
            Statement stmnt = conexion.createStatement();
            ResultSet rs = stmnt.executeQuery(sql);
            return rs;
        }
        
        private void toFecha(ResultSet rs) throws Exception {
        SimpleDateFormat sdfi = new SimpleDateFormat("dd/MM/yyyy");
        String NacString = sdfi.format(rs.getDate("fechaNac"));
        String IngrString = sdfi.format(rs.getDate("fechaIngr"));
        
        aux  = NacString.split("/");
        aux1 = IngrString.split("/");
        
        }
	
	String[] aux;
        String[] aux1;
	private Connection conexion;
	PreparedStatement pStmtInsertar;
	PreparedStatement pStmtBuscar;
        PreparedStatement pStmtDarDeBaja;
        PreparedStatement pStmtEliminar;
        PreparedStatement pStmtActualizar;
        PreparedStatement pStmtDarDeAlta;

    @Override
    public void cerrar() throws Exception {
                try {
            pStmtInsertar.close();
            pStmtActualizar.close();
            pStmtBuscar.close();
            pStmtDarDeBaja.close();
            pStmtDarDeAlta.close();
            pStmtEliminar.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Long buscarPoss(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Alumno> buscarTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
