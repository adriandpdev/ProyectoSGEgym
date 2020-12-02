package AdminFront;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import main.Conexion;
import main.Main;



public class V_AdminPayUs extends JInternalFrame {
	
	private JLabel lblTitulo, lblidTrans, lblDni, lblCantidad, lblDesc, lblFecha, lblPago;
	private JTextField txtidTrans, txtCant, txtDesc;
	private JComboBox cbDni;
	private JButton btnUpdate, btnCancelar;
	private JPanel jpCentro, jpRadio, jpSur;
	private JRadioButton rbSi, rbNo;
	private JDateChooser date;
	
	Conexion c = new Conexion();
	
	public V_AdminPayUs()
	{
		this.setTitle("Lista de Usuarios");
		this.setSize(700, 300);
		this.setLocation(20, 20);
		
		this.setLayout(new BorderLayout());
		
		//Parte norte del BorderLayout
		lblTitulo = new JLabel("PAGOS Y COBROS");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblTitulo, BorderLayout.NORTH);
		
		//Parte Central del BorderLayout
		jpCentro = new JPanel();
		jpCentro.setLayout(new GridLayout(3, 4, 10, 10));
		
		lblidTrans = new JLabel("Id Transaccion");
		txtidTrans = new JTextField();

		int idAuto = 0;
		
		try {
			idAuto= c.nuevoID(Main.con, "idTransaccion", "Transacciones");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		txtidTrans.setText(String.valueOf(idAuto));
		txtidTrans.setEditable(false);
		
		lblDni = new JLabel("DNI: ");
		cbDni = new JComboBox();
		
		String qu = "SELECT DNI FROM Persona";
		String xu = "dni";
		
		cbDni.removeAllItems();
		ArrayList<String> lista = new ArrayList<String>();
		try {
			lista = c.llenarCombo(Main.con, qu, xu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i <lista.size();i++)
		{
			cbDni.addItem(lista.get(i));
		}
		
		lblCantidad = new JLabel("Cantidad:");
		txtCant = new JTextField();
		lblDesc = new JLabel("Descripcion");
		txtDesc = new JTextField();
		
		final class Descrip implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
		cbDni.addActionListener(new Descrip());
			
		txtDesc.setEditable(false);
		lblFecha = new JLabel("Fecha: ");
		
		jpCentro.add(lblidTrans);
		jpCentro.add(txtidTrans);
		jpCentro.add(lblDni);
		jpCentro.add(cbDni);
		jpCentro.add(lblCantidad);
		jpCentro.add(txtCant);
		jpCentro.add(lblDesc);
		jpCentro.add(txtDesc);
		jpCentro.add(lblFecha);
		date = new JDateChooser();
		date.setDateFormatString("dd-MM-yyyy");
		jpCentro.add(date);
		
		jpRadio = new JPanel();
		jpRadio.setLayout(new GridLayout(1, 3, 10, 10));
		
		ButtonGroup grupo1 = new ButtonGroup();

		lblPago = new JLabel("Pagado: ");
		rbSi = new JRadioButton("Si");
		rbNo = new JRadioButton("No");
		
		
		grupo1.add(rbSi);
		grupo1.add(rbNo);
		
		jpRadio.add(lblPago);
		jpRadio.add(rbSi);
		jpRadio.add(rbNo);
		
		jpCentro.add(jpRadio);
		
		this.getContentPane().add(jpCentro, BorderLayout.CENTER);
		
		
		jpSur = new JPanel();
		jpSur.setLayout(new GridLayout(1, 2, 10, 10));
		btnUpdate = new JButton("MODIFICAR");
		btnCancelar = new JButton("CANCELAR");
		
		jpSur.add(btnUpdate);
		jpSur.add(btnCancelar);
		
		this.getContentPane().add(jpSur, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}	
	
	public JComboBox getCbDni() {
		return cbDni;
	}

	public void setCbDni(JComboBox cbDni) {
		this.cbDni = cbDni;
	}
	
}
