package AdminFront;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Dimension;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import AdminBack.Ac_AdminPayUs;
import main.Conexion;
import main.Main;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class V_AdminPayUs extends JInternalFrame {
	private JPanel superior, tabla, superiorA;
	private JLabel titulo, lbl1, lbl2, lbl3;
	private JComboBox mes;
	private JSpinner año;
	private JTextField dni;
	private JTable t;
	private JButton filtrar;
	private String[] columnas = { "ID", "DNI", "CANTIDAD", "TIPO", "FECHA", "PAGADO" };
	private String[][] pagos;
	private Conexion c;
	JScrollPane scroll;

	public V_AdminPayUs() throws SQLException {
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		setLayout(new BorderLayout());
		c = new Conexion();
		instanciarElementos();
	}

	private void instanciarElementos() throws SQLException {
		añadirSuperior();
		añadirTabla();
		add(superiorA, BorderLayout.NORTH);
		add(tabla, BorderLayout.CENTER);
	}

	private void añadirTabla() throws SQLException {
		tabla = new JPanel();
		String query = "SELECT * from Transacciones";
		String query1 = "select count(*) as cuenta from Transacciones";
		rellenarTabla(query, query1);
		t = new JTable(new DefaultTableModel(pagos, columnas));
		t.setDefaultRenderer(Object.class, new V_AdminScheList_Renderer());
		Font font = new Font("Verdana", Font.PLAIN, 12);
		t.setFont(font);
		t.setRowHeight(t.getRowHeight() * 5);
		t.getTableHeader().setReorderingAllowed(false);
		t.setShowGrid(true);
		t.getTableHeader().setBackground(new Color(65, 65, 65));
		t.getTableHeader().setForeground(Color.white);
		t.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 20));
		scroll = new JScrollPane(t, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(1500, 1000));
		tabla.add(scroll);
	}

	private void rellenarTabla(String query, String query1) throws SQLException {
		int cantidadfilas = 0;
		ResultSet r1 = c.consulta(Main.con, query1);
		if (r1.next()) {
			cantidadfilas = r1.getInt("cuenta");
		}
		pagos = new String[cantidadfilas][columnas.length];
		ResultSet rs;
		rs = c.consulta(Main.con, query);
		if (rs.next()) {
			for (int i = 0; i < pagos.length; i++) {
				ResultSetMetaData metaData = rs.getMetaData();
				for (int j = 1; j <= pagos[i].length; j++) {
					String columnName = metaData.getColumnName(j);
					if (!columnName.equals("Pagado"))
						pagos[i][j - 1] = rs.getString(columnName);
					else {
						if (rs.getString(columnName).equals("1")) {
							pagos[i][j - 1] = "SI";
						} else {
							pagos[i][j - 1] = "NO";
						}
					}
				}
				rs.next();
			}
		}
	}

	public void añadirVacio(JPanel p) {
		p.add(new JLabel());
	}

	private void añadirSuperior() {
		superiorA = new JPanel();
		superiorA.setLayout(new GridLayout(2, 1));
		superiorA.setBackground(new Color(137, 13, 84));
		titulo = new JLabel("LISTADO DE COBROS Y NOMINAS", SwingConstants.CENTER);
		titulo.setFont(new Font("Verdana", Font.BOLD, 22));
		titulo.setForeground(Color.WHITE);
		superior = new JPanel();
		superior.setBackground(new Color(137, 13, 84));
		superior.setLayout(new GridLayout(1, 7, 10, 20));
		superior.setBorder(new EmptyBorder(5,5,5,5));
		dni = new JTextField("Introduce el DNI que buscas");
		dni.setForeground(Color.gray);
		dni.addFocusListener(new Ac_AdminPayUs(this));
		año = new JSpinner();
		año.setValue(2000);
		((DefaultEditor) año.getEditor()).getTextField().setEditable(false);
		mes = new JComboBox();
		for (int i = 0; i < 12; i++) {
			Calendar c = Calendar.getInstance();
			c.set(2, i);
			mes.addItem(getMonthName(c.get(Calendar.MONTH), getDefaultLocale()));
		}
		mes.setSelectedIndex(-1);
		filtrar = new JButton("Buscar");
		filtrar.addActionListener(new Ac_AdminPayUs(this));
		superior.add(lbl1 = new JLabel("Escribe un DNI", SwingConstants.RIGHT));
		lbl1.setForeground(Color.WHITE);
		superior.add(dni);
		superior.add(lbl2 = new JLabel("Selecciona un año", SwingConstants.RIGHT));
		lbl2.setForeground(Color.WHITE);
		superior.add(año);
		superior.add(lbl3 = new JLabel("Selecciona un mes", SwingConstants.RIGHT));
		lbl3.setForeground(Color.WHITE);
		superior.add(mes);
		superior.add(filtrar);
		superiorA.add(titulo);
		superiorA.add(superior);
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
}