package UserFront;


import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.sun.tools.javac.util.List;

public class avisosJtableModel extends AbstractTableModel {
 ArrayList<String[]> avisos = new ArrayList<String[]>();
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return avisos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
Object res = avisos.get(rowIndex) [columnIndex];
		return res;
	}

}
