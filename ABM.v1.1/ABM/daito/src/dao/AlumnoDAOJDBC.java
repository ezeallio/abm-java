/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Persona.Alumno;
import Persona.MiCalendar;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Néstor
 */
public class AlumnoDAOJDBC extends DAO<Alumno, Integer>
{

	public AlumnoDAOJDBC(String server, int puerto) throws SQLException
	{
		conexion = DriverManager.getConnection("jdbc:mysql://" + server + ":" + puerto + "/test", "root", "Discus");
		
		String sentencia =
			"INSERT INTO alumno\n" +
			"(`dni`,\n" +
			"`ayn`,\n" +
			"`fechaNac`,\n" +
			"`fechaIngr`,\n" +
			"`sexo`,\n" +
			"`cantMatAprob`,\n" +
			"`promedio`)\n" +
                        "`carrera`)\n" +
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
			"delete from alumno\n" +
			"where dni = ?;\n";
		
		pStmtEliminar = conexion.prepareStatement(sentencia);
                
                sentencia = 
                        "UPDATE alumno\n" +
                        "SET `ayn` = ?, " +
                        "`fechaNac`=?," +
                        "`fechaIngr`=?," +
                        "`sexo` =?," +
                        "`cantMatAprob`=?," +
                        "`promedio`=?," +
                        "`carrera`=?," +
                        "`estado` =?" +
                        "WHERE `dni`=?;";
                pStmtActualizar = conexion.prepareStatement(sentencia);
	}
	
	
	@Override
	public void insertar(Alumno alu) throws Exception
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
	
	
	@Override
	public Alumno buscar(Integer id) throws Exception
	{
		pStmtBuscar.setInt(1, id);
		
		ResultSet rs = pStmtBuscar.executeQuery();
		
		if(!rs.next())
			return null;
		toFecha(rs);
		return new Alumno(rs.getInt("dni"), rs.getString("ayn"), rs.getString("sexo").charAt(0), new MiCalendar(Integer.valueOf(aux[0]),Integer.valueOf(aux[1]),Integer.valueOf(aux[2])), rs.getDouble("promedio"), rs.getInt("cantMatAprob"), rs.getString("carrera"),  new MiCalendar(Integer.valueOf(aux1[0]),Integer.valueOf(aux1[1]),Integer.valueOf(aux1[2])),rs.getString("estado"));
	}
        
	@Override
	public boolean existe(Integer id) throws Exception
	{
                pStmtBuscar.setInt(1, id);
		
		ResultSet rs = pStmtBuscar.executeQuery();
                if(rs == null)
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
            pStmtInsertar.setInt(1, obj.getDni());
            pStmtInsertar.setString(2, obj.getAyn());
            pStmtInsertar.setDate(3, obj.getFecha().toDate());
            pStmtInsertar.setDate(4, obj.getFechaIngr().toDate());
            pStmtInsertar.setString(5, String.valueOf(obj.getSexo()));
            pStmtInsertar.setInt(6, obj.getCantMat());
            pStmtInsertar.setDouble(7, obj.getProm());
            pStmtInsertar.setString(8, obj.getCarrera());
            pStmtInsertar.setString(9, obj.getEstado());
            pStmtInsertar.executeUpdate();
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
}
