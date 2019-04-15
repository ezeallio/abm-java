/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import Persona.Alumno;

/**
 *
 * @author Nicolás
 */
public class MiModeloTabla extends AbstractTableModel{
        private String[] columnas = {"DNI" , "Nombre y Apellido" , "Sexo" , "Fecha de Nacimiento" ,"Promedio","Cant de Materias","Carrera","Fecha de Ingreso"};
    
    public void setLista(List<Alumno> lista)
	{
		this.listaAlumnos = lista;
		
		fireTableDataChanged();
	}

    @Override
    public String getColumnName(int column) {
        return columnas[column]; //To change body of generated methods, choose Tools | Templates.
    }
	
	
	@Override
	public int getRowCount()
	{
		while(listaAlumnos != null)
                    {
                     return listaAlumnos.size();
                    }
                return 0;
	}
	
	
	@Override
	public int getColumnCount()
	{
		return 8;
	}
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Alumno alu = listaAlumnos.get(rowIndex);
		
		switch(columnIndex)
		{
			case 0: return alu.getDni();
			case 1:	return alu.getAyn();
                        case 2: return alu.getSexo();
                        case 3:	return alu.getFecha();
                        case 4:	return alu.getProm();
                        case 5:	return alu.getCantMat();
                        case 6:	return alu.getCarrera();
                        case 7:	return alu.getFechaIngr();
			default: return null;
		}
	}
	
	
	private List<Alumno> listaAlumnos;
                
        }

