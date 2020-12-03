package UserFront;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

import EmplFront.V_EmplHome;
import main.Conexion;
import main.Fecha;
import main.Main;

public class V_UserPayInfo extends JInternalFrame {


	private Fecha f= new  Fecha();
	Conexion c = new Conexion();
	private V_UserHome vent;
	private JLabel lbfecha, jlcobrado;
	private JPanel jpanel;
	private JPanel jpanel2;

	private Date fecha;
	private int cantidad;
	private Date fechapendiente;
	private int cantidadpend;
	

	public V_UserPayInfo(V_UserHome vent) throws SQLException
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

			
	
		if(pagospendientes())
		{
			consultaproximopago();
			jpanel=new JPanel();
			jpanel2=new JPanel();
			crearpanel(fecha,cantidad);
			add(jpanelvacio);
			add(jpanel);
			add(jpanelvacio1);
			add(jpanelvacio2);
			crearpanelpend(fechapendiente, cantidadpend);
			add(jpanel2);
			add(jpanelvacio4);
			add(jpanelvacio6);
			add(jpanelvacio7);
			add(jpanelvacio5);
		}
		else
		{
			consultaproximopago();
			jpanel=new JPanel();
			crearpanel(fecha,cantidad);
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
			
				
}
	
	private void crearpanel(Date fecha, int cantidad)
	{
		Border br =  BorderFactory.createBevelBorder(10);
		jpanel.setLayout(new GridLayout(2,1));
	
		jpanel.setBackground(Color.white);
		jpanel.setBorder(BorderFactory.createTitledBorder(br, "Próximos pagos", 2,0, new Font("Arial", Font.BOLD, 20)));
		jpanel.setPreferredSize(new Dimension(50,50));
		lbfecha=new JLabel("El próximo pago será: " + fecha, SwingConstants.CENTER);
		lbfecha.setFont(new Font("Arial",Font.PLAIN,20));	 
		lbfecha.setBorder(BorderFactory.createLineBorder(new Color(137, 13, 84)));
		jlcobrado= new JLabel("Con un total de : " + cantidad +" €", SwingConstants.CENTER);
		jlcobrado.setFont(new Font("Arial",Font.PLAIN,20));
		jlcobrado.setBorder(BorderFactory.createLineBorder(new Color(137, 13, 84)));
		jpanel.add(lbfecha);
		jpanel.add(jlcobrado);
	}
	private void crearpanelpend(Date fecha, int cantidad)
	{
		Border br =  BorderFactory.createBevelBorder(10);
		jpanel2.setLayout(new GridLayout(2,1));
	
		jpanel2.setBackground(Color.white);
		jpanel2.setBorder(BorderFactory.createTitledBorder(br, "Pendientes de pagar", 2,0, new Font("Arial", Font.BOLD, 20)));
		jpanel2.setPreferredSize(new Dimension(50,50));
		lbfecha=new JLabel("Tiene los pagos pendientes de la fecha: " + fecha, SwingConstants.CENTER);
		lbfecha.setFont(new Font("Arial",Font.PLAIN,20));	 
		lbfecha.setBorder(BorderFactory.createLineBorder(new Color(137, 13, 84)));
		jlcobrado= new JLabel("Con un total de : " + cantidad +" €", SwingConstants.CENTER);
		jlcobrado.setFont(new Font("Arial",Font.PLAIN,20));
		jlcobrado.setBorder(BorderFactory.createLineBorder(new Color(137, 13, 84)));
		jpanel2.add(lbfecha);
		jpanel2.add(jlcobrado);
	}
	
private void consultaproximopago()  throws SQLException
{

	String query=" select date as fecha, cantidad as cantidad from Transacciones where dniUsuario like '"+ vent.getDNI1() +"' and Pagado=0 and descripción like 'cobro' and date>'"+f.fechaActual()+"'";

	ResultSet r= c.consulta(Main.con,query);
	while(r.next())
		{
			
			fecha=r.getDate("fecha");
			cantidad=r.getInt("cantidad");
			
		}
}

public boolean pagospendientes() throws SQLException
{
	boolean enc=false;
	

	String query2="select date as fecha,  cantidad as cantidad "
			+ "from Transacciones "
			+ "where Pagado=0 "
				+ "and dniUsuario like '"+vent.getDNI1()+"' "
						+ "and descripción like 'cobro' and date < '"+f.fechaActual()+"'";
				
				ResultSet r=c.consulta(Main.con,query2);
				while(r.next()&& !enc)
				{
					fechapendiente=r.getDate("fecha");
					cantidadpend=r.getInt("cantidad");
					
					enc=true;
				}
	
	return enc;

}
}
