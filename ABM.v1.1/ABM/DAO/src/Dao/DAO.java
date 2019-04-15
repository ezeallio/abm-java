/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Persona.Alumno;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public abstract class DAO <T,U> {
    public abstract void insertar(T obj) throws Exception;
    public abstract void actualizar(T obj) throws Exception;
    public abstract T buscar(U id) throws Exception;
    public abstract void eliminar(T obj) throws Exception;
    public abstract void darDeBaja(T obj) throws Exception;
    public abstract boolean existe(U id) throws Exception;
    public abstract void cerrar() throws Exception;
    public abstract Long buscarPoss(U id) throws Exception;
    public abstract ResultSet table() throws SQLException;
    public abstract ArrayList<Alumno> buscarTodos() throws Exception;

    
}
