package listado;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel{
	
	String[] titulos;
	Object[][] datos;
	
	
	//Creamos el constructor de la clase
	
	public ModeloTabla(Object[][]datos, String[] titulos) {
		super();
		this.titulos = titulos;
		this.datos = datos;
		setDataVector(datos, titulos);
		}
	
	public ModeloTabla() {
		// TODO Auto-generated constructor stub
	}

	//Esta funcion evita que las celdas se puedan editar
	public boolean isCellEditable (int row, int column) {
		
	return false; 
	       
	}
}
