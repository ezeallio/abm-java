/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Néstor
 */
public abstract class DAO<T, U>
{
	public abstract void insertar(T obj) throws Exception;
	public abstract void actualizar(T obj) throws Exception;
	public abstract T buscar(U id) throws Exception;
	public abstract boolean existe(U id) throws Exception;
	public abstract void eliminar(T obj) throws Exception;
	public abstract void darDeBaja(T obj) throws Exception;

	public void cerrar()
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
