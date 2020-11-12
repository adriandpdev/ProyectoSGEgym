import java.awt.*;

import javax.swing.*;

public class V_AdminScheList extends JInternalFrame{
	JPanel pnhorario;
	JLabel jlvisualizartabla;
	String[] nombrecolumnas;
	private Object [][] data;
	//7 dias por 14 horas
	
	public V_AdminScheList() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		pnhorario= new JPanel();
		nombrecolumnas = new String[] {"Hora", "Lunes", "Martes", "Miércoles","Jueves","Viernes","Sabado","Domingo" };
		
		jlvisualizartabla=new JLabel("VISUALIZAR TABLA");
		 data = new Object[7][14];
	        //create table with data
	        JTable table = new JTable(data,nombrecolumnas);
		
		//pnhorario.add(lunes);
	       
	    getContentPane().add(jlvisualizartabla, BorderLayout.NORTH);
		
		getContentPane().add(pnhorario, BorderLayout.CENTER);
		
	}
	
	
}
