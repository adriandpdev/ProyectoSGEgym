package AdminFront;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import AdminBack.Ac_AdminPayUs;
import main.Conexion;
import main.Main;

// IMPORTS SIN USAR
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import com.itextpdf.text.Font;
import com.mysql.cj.xdevapi.Result;
import java.sql.ResultSet;
import java.sql.Statement;

public class V_AdminPayUs extends JInternalFrame {
	private JPanel superior, tabla;
	private JComboBox mes;
	private JSpinner año;
	private JTextField dni;
	private JTable t;
	private JButton filtrar;
	private String[] columnas = { "ID", "DNI", "CANTIDAD", "TIPO", "FECHA", "PAGADO" };
	private String[][] pagos;
	private Conexion c;
	JScrollPane scroll;

	private JLabel lblTitulo, lblidTrans, lblDni, lblCantidad, lblDesc, lblFecha, lblPago;
	private JTextField txtidTrans, txtCant, txtDesc;
	private JComboBox cbDni;
	private JButton btnUpdate, btnCancelar;
	private JPanel jpCentro, jpRadio, jpSur;
	private JRadioButton rbSi, rbNo;
	private JDateChooser date;

	private int idAuto;

	public V_AdminPayUs() {
		/*
		 * setLayout(new BorderLayout()); c=new Conexion(); instanciarElementos();
		 */

		this.setTitle("Lista de Usuarios");
		this.setSize(700, 300);
		this.setLocation(20, 20);

		this.setLayout(new BorderLayout());

		// Parte norte del BorderLayout
		lblTitulo = new JLabel("PAGOS Y COBROS");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblTitulo, BorderLayout.NORTH);

		// Parte Central del BorderLayout
		jpCentro = new JPanel();
		jpCentro.setLayout(new GridLayout(3, 4, 10, 10));

		lblidTrans = new JLabel("Id Transaccion");
		txtidTrans = new JTextField();

		int idAuto = 0;

		try {
			idAuto = c.nuevoID(Main.con, "idTransaccion", "Transacciones");
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
		for (int i = 0; i < lista.size(); i++) {
			cbDni.addItem(lista.get(i));
		}

		lblCantidad = new JLabel("Cantidad:");
		txtCant = new JTextField();
		lblDesc = new JLabel("Descripcion");
		txtDesc = new JTextField();

		final class Descrip implements ActionListener {

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

		// V1
		mes.setSelectedIndex(-1);
		filtrar = new JButton("Buscar");
		filtrar.addActionListener(new Ac_AdminPayUs(this));

		// FIN V1
		superior.add(new JLabel("Pagos usuarios-empleados"));
		superior.add(dni);

		añadirVacio(superior);
		superior.add(new JLabel("Elije el año"));
		superior.add(año);
		añadirVacio(superior);
		superior.add(new JLabel("Elije el mes"));
		superior.add(mes);
		añadirVacio(superior);
		superior.add(filtrar);
	}

	/*
	 * private void instanciarElementos() throws SQLException { añadirSuperior();
	 * 
	 * añadirTabla();
	 * 
	 * 
	 * add(superior,BorderLayout.NORTH); add(tabla,BorderLayout.CENTER);
	 * 
	 * }
	 */
	/*
	 * private void añadirTabla() throws SQLException { tabla=new JPanel();
	 * 
	 * String query="SELECT * from Transacciones";
	 * 
	 * String query1="select count(*) as cuenta from Transacciones";
	 * rellenarTabla(query, query1);
	 * 
	 * t=new JTable(new DefaultTableModel(pagos, columnas));
	 * t.setDefaultRenderer(Object.class, new V_AdminScheList_Renderer()); Font font
	 * = new Font("Verdana", Font.PLAIN, 12); t.setFont(font);
	 * t.setRowHeight(t.getRowHeight() * 5);
	 * 
	 * t.getTableHeader().setReorderingAllowed(false); t.setShowGrid(true);
	 * t.getTableHeader().setBackground(new Color(65,65,65));
	 * t.getTableHeader().setForeground(Color.white); t.getTableHeader().setFont(new
	 * Font("Verdana", Font.BOLD, 20)); scroll = new JScrollPane(t,
	 * JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	 * JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); scroll.setPreferredSize(new
	 * Dimension(800, 450));
	 * 
	 * tabla.add(scroll); }
	 */
	/*
	 * private void rellenarTabla(String query, String query1) throws SQLException {
	 * int cantidadfilas=0; ResultSet r1= c.consulta(Main.con,query1); if(r1.next())
	 * { cantidadfilas=r1.getInt("cuenta"); } pagos=new
	 * String[cantidadfilas][columnas.length]; ResultSet rs; rs=c.consulta(Main.con,
	 * query); if (rs.next()) { for (int i = 0; i < pagos.length; i++) {
	 * ResultSetMetaData metaData = rs.getMetaData(); for (int j = 1; j <=
	 * pagos[i].length; j++) { String columnName = metaData.getColumnName(j);
	 * if(!columnName.equals("Pagado")) pagos[i][j-1]=rs.getString(columnName); else
	 * { if(rs.getString(columnName).equals("1")) { pagos[i][j-1]="SI"; } else {
	 * pagos[i][j-1]="NO"; } } } rs.next(); } }
	 */

	public void añadirVacio(JPanel p) {
		p.add(new JLabel());
	}

	private void añadirSuperior() {
		superior = new JPanel();
		superior.setLayout(new GridLayout(1, 10));

		dni = new JTextField("Introduce el dni que buscas");
		dni.setForeground(Color.gray);
		dni.addFocusListener(new Ac_AdminPayUs(this));

		año = new JSpinner();
		año.setValue(2000);
		// ((DefaultEditor)año.getEditor()).getTextField().setEditable(false);

		mes = new JComboBox();
		for (int i = 0; i < 12; i++) {

			Calendar c = Calendar.getInstance();
			c.set(2, i);
			// ((mes.addItem(getMonthName(c.get(Calendar.MONTH),getDefaultLocale()));
		}
	}

	public String[][] getPagos() {
		return pagos;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public void setPagos(String[][] pagos) {
		this.pagos = pagos;
	}

	public String[] getColumnas() {
		return columnas;
	}

	public JComboBox getMes() {
		return mes;
	}

	public JSpinner getAño() {
		return año;
	}

	public JTextField getDni() {
		return dni;
	}

	public void setT(JTable t) {
		this.t = t;
	}

	public JTable getT() {
		return t;
	}

	public JPanel getTabla() {
		return tabla;
	}

	public String getMonthName(int month, Locale locale) {
		DateFormatSymbols symbols = new DateFormatSymbols(locale);
		String[] monthNames = symbols.getMonths();
		return monthNames[month];
	}

	public JComboBox getCbDni() {
		return cbDni;
	}

	public void setCbDni(JComboBox cbDni) {
		this.cbDni = cbDni;
	}

}
