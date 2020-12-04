package UserFront;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class V_UserPromList_Renderer extends AbstractTableModel {
 ArrayList<String[]> promociones = new ArrayList<String[]>();
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return promociones.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
Object resultado = promociones.get(rowIndex) [columnIndex];
		
		return resultado;
	}

}
