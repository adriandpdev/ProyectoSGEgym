package EmplFront;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


import java.awt.Dimension;



import main.Conexion;
import main.Fecha;
import main.Main;

public class V_EmplPayLast extends JInternalFrame {

	private Fecha f= new  Fecha();
	Conexion c = new Conexion();
	private V_EmplHome vent;
	private JLabel lbfecha, jlcobrado;
	private JPanel jpanel;
	private  String fechaultimopago;
	private Date fecha;
	private int cantidad;
	

	public V_EmplPayLast(V_EmplHome vent) throws SQLException
	{ this.vent=vent;
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
	
		setLayout(new GridLayout(3,3));
		JPanel jpanelvacio= new JPanel();
		JPanel jpanelvacio1=new JPanel();
		JPanel jpanelvacio2= new JPanel();
		JPanel jpanelvacio3= new JPanel();
		JPanel jpanelvacio4= new JPanel();
		JPanel jpanelvacio5= new JPanel();
		JPanel jpanelvacio6= new JPanel();
		JPanel jpanelvacio7= new JPanel();

		if(comprobar())
		{
			
		consulta();
		jpanel=new JPanel();
		crearpanel();
		add(jpanelvacio);
		add(jpanel);
		add(jpanelvacio1);
		add(jpanelvacio2);
		add(jpanelvacio3);
		add(jpanelvacio4);
		add(jpanelvacio5);
		add(jpanelvacio6);
		add(jpanelvacio7);
	
		}
		else
		{
			JOptionPane.showMessageDialog(this, "No hay nóminas para mostrar","No hay datos",JOptionPane.OK_OPTION);
		}
				
}
	
	private void crearpanel()
	{
		Border br =  BorderFactory.createBevelBorder(10);
		jpanel.setLayout(new GridLayout(2,1));
	
		jpanel.setBackground(Color.white);
		jpanel.setBorder(BorderFactory.createTitledBorder(br, "Última nómina",2,0, new Font("Arial", Font.BOLD, 20)));
		jpanel.setPreferredSize(new Dimension(50,50));
		lbfecha=new JLabel("Su última nómina fue el: " + fecha, SwingConstants.CENTER);
		lbfecha.setFont(new Font("Arial",Font.PLAIN,20));
	    lbfecha.setBorder(BorderFactory.createLineBorder(new Color(137, 13, 84)));
	
		jlcobrado= new JLabel("Cuantía de: " + cantidad +" €", SwingConstants.CENTER);
		jlcobrado.setFont(new Font("Arial",Font.PLAIN,20));
		jlcobrado.setBorder(BorderFactory.createLineBorder(new Color(137, 13, 84)));
		jpanel.add(lbfecha);
		jpanel.add(jlcobrado);
	}
	
private void consulta() throws SQLException
{
	
	String query="select date  as fecha from Transacciones where dniUsuario like '"+ vent.getDNI1()+"' and pagado=1 and descripción like 'nomina' order by date desc limit 1 ";
	System.out.println(query);
	ResultSet r= c.consulta(Main.con,query);
		if(r.next())
		{
			
			fechaultimopago=r.getString("fecha");
			System.out.println(fechaultimopago);
			
		}
	
	String query1="select date as fecha, cantidad as cantidad from Transacciones"
			+ " where dniUsuario like '"+vent.getDNI1()+"' "
					+ "and date ='"+ fechaultimopago+"' and descripción like 'nomina' and Pagado=1";
	System.out.println(query1);
	ResultSet r1=c.consulta(Main.con, query1);
	while(r1.next())
	{
		fecha=r1.getDate("fecha");
		cantidad=r1.getInt("cantidad");
	}
}

private boolean comprobar() throws SQLException
{
		String query= "select * from Transacciones where  dniUsuario like '" +vent.getDNI1() + "'";

      ResultSet r = c.consulta(Main.con, query);
      if (r.next()) {
          return true;
      } else {
          return false;
      }
}
}
