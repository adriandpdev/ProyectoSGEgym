package listado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;


//Implementamos un mouse listener porque queremos capturar las acciones que realice el ratón

public class listado extends JInternalFrame implements MouseListener,KeyListener, ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbl_titulo, lbl_criterios;
	private JPanel panel_norte, panel_sur, panel_central, panel_norte_busqueda, panel_central_listado;
	private JTable tablaPersonas;
	private JScrollPane scp;
	private JButton btn_refrescar, btn_buscar;
	private JTextField tf_busqueda;
	private DefaultComboBoxModel<String> modelo_combo;
	private JComboBox<String> combo_campos;
	
	ArrayList<Persona> listaPersonas;
	
	private int filasTabla, columnasTabla;
	private Conexion cp, cp2;
	private Connection conn;
	private Connection conn2;
	private ResultSet rs, rs2;
	private ModeloTabla modelo;

	public int criterio;
	public String parametro;
	
	public listado() {
		
		this.setLayout(new BorderLayout());
		
		UIManager.put("OptionPane.background",new ColorUIResource(new Color(137, 13, 84)));
		UIManager.put("OptionPane.messageForeground",new ColorUIResource(Color.WHITE));
		UIManager.put("Panel.background",new ColorUIResource(new Color(137, 13, 84)));
		
		
		//Paneles del primer BorderLayout
		panel_norte = new JPanel();
		panel_central = new JPanel();
		panel_sur = new JPanel();
		
		//Paneles del segundo BorderLayout
		panel_norte_busqueda = new JPanel();
		panel_central_listado = new JPanel();
		
		//Panel norte interior con los criterios de busqueda y el botón para refrescar
		panel_norte_busqueda.setLayout(new GridLayout(1,4,5,5));
		panel_norte_busqueda.setBackground(new Color(137, 13, 84));
		
		btn_buscar = new JButton("Buscar");
		btn_buscar.addActionListener(this);
		btn_buscar.setFont(new Font("Verdana",Font.PLAIN,18));
		
		btn_refrescar = new JButton("Reiniciar Tabla");
		btn_refrescar.addActionListener(this);
		btn_refrescar.setFont(new Font("Verdana",Font.PLAIN,18));
		
		//Creamos la caja de busqueda y le añadimos un objeto de la clase Textpromt para añadirle un placeholder
		tf_busqueda = new JTextField();
		tf_busqueda.setFont(new Font("Verdana",Font.PLAIN,18));
		TextPrompt placeholder = new TextPrompt("Inserte su busqueda aquí", tf_busqueda);
		placeholder.changeAlpha(0.5f);
		
		
		lbl_criterios = new JLabel("Criterios de Busqueda:");
		lbl_criterios.setBackground(new Color(137, 13, 84));
		lbl_criterios.setFont(new Font("Verdana",Font.PLAIN,22));
		lbl_criterios.setForeground(Color.WHITE);

		modelo_combo = new DefaultComboBoxModel<String>();
		modelo_combo.addElement("Dni");
		modelo_combo.addElement("Nombre");
		modelo_combo.addElement("Apellido");
		modelo_combo.addElement("Cuenta Bancaria");
		modelo_combo.addElement("Fecha de Nacimiento");
		modelo_combo.addElement("Telefono");
		modelo_combo.addElement("Correo Electrónico");
		modelo_combo.addElement("Rol");
				
		combo_campos = new JComboBox<String>();
		combo_campos.setModel(modelo_combo);
		combo_campos.setFont(new Font("Verdana",Font.PLAIN,19));
		
		panel_norte_busqueda.add(lbl_criterios);
		panel_norte_busqueda.add(combo_campos);
		panel_norte_busqueda.add(tf_busqueda);
		panel_norte_busqueda.add(btn_buscar);
		panel_norte_busqueda.add(btn_refrescar);
		
		//Color de fondo personalizado del título
		panel_norte.setBackground(new Color(137, 13, 84));
		
		//Título personalizado 
		lbl_titulo = new JLabel("Listado de Usuarios y Profesores:");
		lbl_titulo.setFont(new Font("Verdana",Font.PLAIN,35));
		lbl_titulo.setForeground(Color.WHITE);
		
		panel_central.setLayout(new BorderLayout());
		
		//JTable
	
		scp = new JScrollPane();
		
		tablaPersonas = new JTable();
		
		tablaPersonas.addMouseListener(this);
	
		//construirTabla();
		refrescarTabla();
		
		tf_busqueda.addKeyListener(this);
		
		panel_central.add(panel_norte_busqueda, BorderLayout.NORTH);
		panel_central.add(scp, BorderLayout.CENTER);
		panel_norte.add(lbl_titulo);
		panel_sur.setBackground(new Color(137, 13, 84));
		
		
		//Con esto eliminamos los bordes y la barra de títulos superior
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		this.getContentPane().add(panel_norte, BorderLayout.NORTH);
		this.getContentPane().add(panel_central, BorderLayout.CENTER);
		this.getContentPane().add(panel_sur, BorderLayout.SOUTH);
		this.setResizable(false);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	//En este método cogemos todos los datos de la tabla personas
	
	public ArrayList<Persona> datarPersonas(){
		ArrayList<Persona> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM Persona";
		
		cp = new Conexion();
		
		
		try {
			conn = cp.conectar();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = cp.consulta(conn, sql);
			
			while(rs.next()){
	             Persona per = new Persona();
	             per.setDni(rs.getString(1));
	             per.setNombre(rs.getString(2));
	             per.setApellido(rs.getString(3));
	             per.setCuentabanc(rs.getString(4));
	             per.setPass(rs.getString(5));
	             per.setFechanac(rs.getString(6));
	             per.setTelefono(rs.getInt(7));
	             per.setCorreo(rs.getString(8));
	             per.setRol(rs.getString(9));
	             lista.add(per);
	             //System.out.println(rs.getString(1));
	         }
			
			conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
		
	}
	
	public ArrayList<Persona> datarPersonasBusqueda(){
		ArrayList<Persona> lista = new ArrayList<>();
		
		String sql_busqueda = null;
					
			
		if(criterio == 0) {
			sql_busqueda = "SELECT * FROM Persona WHERE DNI like '" + parametro + "%'";
		} else if(criterio == 1) {
			sql_busqueda = "SELECT * FROM Persona WHERE nombre like '" + parametro + "%'";
		} else if(criterio == 2) {
			sql_busqueda = "SELECT * FROM Persona WHERE apellido like '" + parametro + "%'";
		} else if(criterio == 3) {
			sql_busqueda = "SELECT * FROM Persona WHERE cuentabanc like '" + parametro + "%'";
		} else if(criterio == 4) {
			sql_busqueda = "SELECT * FROM Persona WHERE fechanac like '" + parametro + "%'";
		} else if(criterio == 5) {
			sql_busqueda = "SELECT * FROM Persona WHERE telefono like '" + parametro + "%'";
		} else if(criterio == 6) {
			sql_busqueda = "SELECT * FROM Persona WHERE correo like '"+ parametro + "%'";
		} else if(criterio == 7) {
			sql_busqueda = "SELECT * FROM Persona WHERE rol like '" + parametro + "%'";
		}
		
		
		cp = new Conexion();
		
		
		try {
			conn = cp.conectar();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = cp.consulta(conn, sql_busqueda);
			
			while(rs.next()){
	             Persona per = new Persona();
	             per.setDni(rs.getString(1));
	             per.setNombre(rs.getString(2));
	             per.setApellido(rs.getString(3));
	             per.setCuentabanc(rs.getString(4));
	             per.setPass(rs.getString(5));
	             per.setFechanac(rs.getString(6));
	             per.setTelefono(rs.getInt(7));
	             per.setCorreo(rs.getString(8));
	             per.setRol(rs.getString(9));
	             lista.add(per);
	             //System.out.println(rs.getString(1));
	         }
			
			conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
		
	}
	
	//Metodo para contruir la tabla
	
	public void construirTabla(String tipo) {

		
		if(tipo == "lis") {
			listaPersonas = datarPersonas();
		} else if (tipo == "bus") {
			listaPersonas = datarPersonasBusqueda();
		}
		
		ArrayList<String> titulosList=new ArrayList<>();
		
		//Estos son los encabezados de las columnas
		
		titulosList.add("DNI");
		titulosList.add("NOMBRE");
		titulosList.add("APELLIDO");
		titulosList.add("CUENTA BANCARIA");
		titulosList.add("PASSWORD");
		titulosList.add("FECHA DE NACIMIENTO");
		titulosList.add("TELEFONO");
		titulosList.add("CORREO ELECTRONICO");
		titulosList.add("ROL");
		titulosList.add("Modificar");
		titulosList.add("Eliminar"); 
				
		//se asignan los títulos de las columnas para enviarlas al constructor de la tabla
		
		String titulos[] = new String[titulosList.size()];
		for (int i = 0; i < titulos.length; i++) {
			titulos[i]=titulosList.get(i);
		}
		
		Object[][] data = arrayDatos(titulosList);
		crearTabla(titulos,data);
		
	}
	
	public Object[][] arrayDatos(ArrayList<String> titulosList) {
	
		//Creamos un  array bidimensional donde las filas que corresponden a los usuarios son dinámicas y las columnas que pertenecen a los campos son estáticas
		String informacion[][] = new String[listaPersonas.size()][titulosList.size()];
		
		for (int i = 0; i < informacion.length; i++) {
			
			informacion[i][Indicadores.DNI] = listaPersonas.get(i).getDni()+ "";
			informacion[i][Indicadores.NOMBRE] = listaPersonas.get(i).getNombre()+ "";
			informacion[i][Indicadores.APELLIDO] = listaPersonas.get(i).getApellido()+ "";
			informacion[i][Indicadores.CUENTABANC] = listaPersonas.get(i).getCuentabanc()+ "";
			informacion[i][Indicadores.PASS] = listaPersonas.get(i).getPass()+ "";
			informacion[i][Indicadores.FECHANAC] = listaPersonas.get(i).getFechanac()+ "";
			informacion[i][Indicadores.TELEFONO] = listaPersonas.get(i).getTelefono()+ "";
			informacion[i][Indicadores.CORREO] = listaPersonas.get(i).getCorreo()+ "";
			informacion[i][Indicadores.ROL] = listaPersonas.get(i).getRol()+ "";
			informacion[i][Indicadores.MODIFICAR] = "modificar";
			informacion[i][Indicadores.ELIMINAR] = "eliminar";
			
		}
		
		return informacion;
	}
	
	public void crearTabla(String[] titulos, Object[][] data) {
		
		modelo = new ModeloTabla(data, titulos);
		
		tablaPersonas.setModel(modelo);
		
		filasTabla = tablaPersonas.getRowCount();
		columnasTabla = tablaPersonas.getColumnCount();
		
		for (int i = 0; i < titulos.length; i++) {
			tablaPersonas.getColumnModel().getColumn(i).setCellRenderer(new Celdas("campo"));	
		}
		tablaPersonas.getColumnModel().getColumn(Indicadores.MODIFICAR).setCellRenderer(new Celdas("boton"));
		tablaPersonas.getColumnModel().getColumn(Indicadores.ELIMINAR).setCellRenderer(new Celdas("boton"));
		
		
		tablaPersonas.getTableHeader().setReorderingAllowed(false);
		tablaPersonas.setRowHeight(25);//tamaño de las celdas
		
		//Se define el tamaño de largo para cada columna y su contenido
		tablaPersonas.getColumnModel().getColumn(Indicadores.DNI).setCellRenderer(new Celdas("campo"));//dni
		tablaPersonas.getColumnModel().getColumn(Indicadores.NOMBRE).setCellRenderer(new Celdas("campo"));//nombre
		tablaPersonas.getColumnModel().getColumn(Indicadores.APELLIDO).setCellRenderer(new Celdas("campo"));//apellido
		tablaPersonas.getColumnModel().getColumn(Indicadores.CUENTABANC).setCellRenderer(new Celdas("campo"));//cuenta bancaria
		tablaPersonas.getColumnModel().getColumn(Indicadores.PASS).setCellRenderer(new Celdas("campo"));//password
		tablaPersonas.getColumnModel().getColumn(Indicadores.FECHANAC).setCellRenderer(new Celdas("campo"));//fecha de nacimiento
		tablaPersonas.getColumnModel().getColumn(Indicadores.TELEFONO).setCellRenderer(new Celdas("campo"));//telefono
		tablaPersonas.getColumnModel().getColumn(Indicadores.CORREO).setCellRenderer(new Celdas("campo"));//correo electrónico
		tablaPersonas.getColumnModel().getColumn(Indicadores.ROL).setCellRenderer(new Celdas("campo"));//rol
		tablaPersonas.getColumnModel().getColumn(Indicadores.MODIFICAR).setCellRenderer(new Celdas("boton"));//boton modificar
		tablaPersonas.getColumnModel().getColumn(Indicadores.ELIMINAR).setCellRenderer(new Celdas("boton"));//boton eliminar
		
		//personaliza el encabezado
		JTableHeader jtableHeader = tablaPersonas.getTableHeader();
	    jtableHeader.setDefaultRenderer(new Encabezado());
	    tablaPersonas.setTableHeader(jtableHeader);
	    
	    scp.setViewportView(tablaPersonas);


	}
	
	
	//Método para eliminar una persona de la BBDD 
	
	public void EliminarRegistro(String dni_borrar){

		String sql_borrar = "DELETE from Persona WHERE DNI='"+dni_borrar+"'";
				
		try {
			conn = cp.conectar();
			cp.eliminar(conn, sql_borrar);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refrescarTabla(){
		construirTabla("lis");
		
	}

	//Método que añade los datos de la persona que causa baja a la tabla de bajas de la BBDD
	
	public void AgregarBaja(String dni_baja) {
		
		String sql_datos_baja = "SELECT * FROM Persona WHERE DNI='" + dni_baja + "'";
		
		//Obtenemos la fecha del sistema para que nos de el campo de la fecha de baja de la persona
		
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date(System.currentTimeMillis());
		String fecha_actual = formatter.format(date);
		System.out.println(fecha_actual);
		
	
		try {
			conn = cp.conectar();
			
			
			System.out.println(tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 1).toString());
			
			String sql_insertar_baja = "INSERT INTO PersonaBaja (DNI, nombre, apellido, cuentabanc, fechanac, telefono, correo, rol, fechabaja, fecha_index) VALUES"
				+ "('"+dni_baja+"','"+tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 1).toString()+"',"
						+ " '"+tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 2).toString()+"',"
								+ " '"+tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 3).toString()+"',"
										+ " '"+tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 5).toString()+"',"
												+ " '"+tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 6).toString()+"',"
														+ "'"+tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 7).toString()+"',"
																+ "'"+tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 8).toString()+"',"
																		+ "'"+fecha_actual+"', '"+date+"')";
			
			
			cp.alta(conn, sql_insertar_baja);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//	EVENTOS DINÁMICOS:
	
	//Eventos al pulsar los botones de la tabla con el click del ratón
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//Aqui nos dice que fila es en la que está clickando
		int fila = tablaPersonas.rowAtPoint(e.getPoint());
		//Aqui nos dice que columna es en la que está clickando
		int columna = tablaPersonas.columnAtPoint(e.getPoint());
		
		
		if (columna == Indicadores.MODIFICAR) {
		//Lanzamos la ventana de modificacion
			int row = tablaPersonas.getSelectedRow();
			
			String pm_dni = tablaPersonas.getValueAt(row, 0).toString();
			String pm_nombre = tablaPersonas.getValueAt(row, 1).toString();
			String pm_apellido = tablaPersonas.getValueAt(row, 2).toString();
			String pm_cuentabanc = tablaPersonas.getValueAt(row, 3).toString();
			String pm_pass = tablaPersonas.getValueAt(row, 4).toString();
			String pm_fechanac = tablaPersonas.getValueAt(row, 5).toString();
			String pm_telefono = tablaPersonas.getValueAt(row, 6).toString();
			String pm_correo = tablaPersonas.getValueAt(row, 7).toString();
			String pm_rol = tablaPersonas.getValueAt(row, 8).toString();
			
			//Abrimos el panel para hacer las modificaciones sobrecargando su constructor
			try {
				PanelModificacion pamo = new PanelModificacion(pm_dni, pm_nombre, pm_apellido, pm_cuentabanc, pm_pass, pm_fechanac, pm_telefono, pm_correo, pm_rol);
				construirTabla("lis");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if (columna == Indicadores.ELIMINAR) {

			int reply = JOptionPane.showConfirmDialog(tablaPersonas, "¿Esta seguro de que quiere borrar el registro?", "Eliminar",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			if(reply == JOptionPane.YES_OPTION) {
				
				//Añade el registro que se va a eliminar a la BBDD
				AgregarBaja(tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 0).toString());
				
				//Elimina el registro de la BBDD
				EliminarRegistro(tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 0).toString());
				
				JOptionPane.showMessageDialog(tablaPersonas, "Registro eliminado");
				//Vuelvo a construir la tabla para reflejar los cambios
				construirTabla("lis");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public JTextField getTf_busqueda() {
		return tf_busqueda;
	}

	public void setTf_busqueda(JTextField tf_busqueda) {
		this.tf_busqueda = tf_busqueda;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			criterio = combo_campos.getSelectedIndex();
			parametro = getTf_busqueda().getText().toString();
			construirTabla("bus");
			tf_busqueda.setText("");
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	//Boton de busqueda
	@Override
	public void actionPerformed(ActionEvent ac) {
		// TODO Auto-generated method stub
		if(ac.getSource() == btn_buscar) {
			criterio = combo_campos.getSelectedIndex();
			parametro = getTf_busqueda().getText().toString();
			construirTabla("bus");
			tf_busqueda.setText("");
		}
		
		if(ac.getSource() == btn_refrescar) {
			construirTabla("lis");
			tf_busqueda.setText("");
		}
	}
	
	
}


	

