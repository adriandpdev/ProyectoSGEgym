package listado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.JTableHeader;

import AdminFront.V_AdminUserAdd;

public class ListadoBajas extends JInternalFrame implements MouseListener,KeyListener, ActionListener{
	
	
	private JLabel lbl_titulo, lbl_criterios;
	private JPanel panel_norte, panel_sur, panel_central, panel_norte_busqueda, panel_central_listado;
	private JTable tablaPersonasBaja;
	private JScrollPane scp;
	private JButton btn_refrescar, btn_buscar;
	private JTextField tf_busqueda;
	private DefaultComboBoxModel<String> modelo_combo;
	private JComboBox<String> combo_campos;
	
	ArrayList<PersonaBaja> listaPersonasBaja;
	
	private int filasTabla, columnasTabla;
	private Conexion cp;
	private Connection conn;
	private ResultSet rs;
	private ModeloTabla modelo;
	private int criterio;
	private String parametro;
	
	public ListadoBajas() {
		
		this.setLayout(new BorderLayout());

		//Fondo de los paneles
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
		
		//Panel norte interior con los criterios de busqueda y el botÃ³n para refrescar
		panel_norte_busqueda.setLayout(new GridLayout(1,4,5,5));
		panel_norte_busqueda.setBackground(new Color(137, 13, 84));
		
		btn_buscar = new JButton("BUSCAR");
		btn_buscar.addActionListener(this);
		btn_buscar.setFont(new Font("Verdana",Font.PLAIN,19));
		
		btn_refrescar = new JButton("REFRESCAR");
		btn_refrescar.addActionListener(this);
		btn_refrescar.setFont(new Font("Verdana",Font.PLAIN,19));
		
		//Creamos la caja de busqueda y le aÃ±adimos un objeto de la clase Textpromt para aÃ±adirle un placeholder
		tf_busqueda = new JTextField();
		tf_busqueda.setFont(new Font("Verdana",Font.PLAIN,19));
		TextPrompt placeholder = new TextPrompt("Inserte su busqueda aquÃ­...", tf_busqueda);
		placeholder.changeAlpha(0.5f);
		
		
		lbl_criterios = new JLabel("Criterios de Busqueda:");
		lbl_criterios.setBackground(new Color(137, 13, 84));
		lbl_criterios.setFont(new Font("Verdana",Font.PLAIN,23));
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
		modelo_combo.addElement("Mas Antigüas");
		modelo_combo.addElement("Mas Nuevas");
				
		combo_campos = new JComboBox<String>();
		combo_campos.setModel(modelo_combo);
		combo_campos.setFont(new Font("Verdana",Font.PLAIN,19));
		
		panel_norte_busqueda.add(lbl_criterios);
		panel_norte_busqueda.add(combo_campos);
		panel_norte_busqueda.add(tf_busqueda);
		panel_norte_busqueda.add(btn_buscar);
		panel_norte_busqueda.add(btn_refrescar);
		
		//Color de fondo personalizado del tÃ­tulo
		panel_norte.setBackground(new Color(137, 13, 84));
		
		//TÃƒÂ­tulo personalizado 
		lbl_titulo = new JLabel("Listado de Bajas:");
		lbl_titulo.setFont(new Font("Verdana",Font.PLAIN,35));
		lbl_titulo.setForeground(Color.WHITE);
		
		panel_central.setLayout(new BorderLayout());
		
		//JTable
	
		scp = new JScrollPane();
		
		tablaPersonasBaja = new JTable();
		
		tablaPersonasBaja.addMouseListener(this);
	
		//construirTabla();
		refrescarTabla();
		
		tf_busqueda.addKeyListener(this);
		
		panel_central.add(panel_norte_busqueda, BorderLayout.NORTH);
		panel_central.add(scp, BorderLayout.CENTER);
		panel_norte.add(lbl_titulo);
		panel_sur.setBackground(new Color(137, 13, 84));
		
		
		//Con esto eliminamos los bordes y la barra de tÃ­tulos superior
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		this.getContentPane().add(panel_norte, BorderLayout.NORTH);
		this.getContentPane().add(panel_central, BorderLayout.CENTER);
		this.getContentPane().add(panel_sur, BorderLayout.SOUTH);
		this.setResizable(false);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	//En este mÃƒÂ©todo cogemos todos los datos de la tabla personas
	
	public ArrayList<PersonaBaja> datarPersonas(){
		ArrayList<PersonaBaja> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM PersonaBaja";
		
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
	             PersonaBaja per = new PersonaBaja();
	             per.setDni(rs.getString(1));
	             per.setNombre(rs.getString(2));
	             per.setApellido(rs.getString(3));
	             per.setCuentabanc(rs.getString(4));
	             per.setFechanac(rs.getString(5));
	             per.setTelefono(rs.getInt(6));
	             per.setCorreo(rs.getString(7));
	             per.setRol(rs.getString(8));
	             per.setFechabaja(rs.getString(9));
	             lista.add(per);
	         }
			
			conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
		
	}
	
	public ArrayList<PersonaBaja> datarPersonasBusqueda(){
		ArrayList<PersonaBaja> lista = new ArrayList<>();
		
		String sql_busqueda = null;
					
			
		if(criterio == 0) {
			sql_busqueda = "SELECT * FROM PersonaBaja WHERE dni like '" + parametro + "%'";
		} else if(criterio == 1) {
			sql_busqueda = "SELECT * FROM PersonaBaja WHERE nombre like '" + parametro + "%'";
		} else if(criterio == 2) {
			sql_busqueda = "SELECT * FROM PersonaBaja WHERE apellido like '" + parametro + "%'";
		} else if(criterio == 3) {
			sql_busqueda = "SELECT * FROM PersonaBaja WHERE cuentabanc like '" + parametro + "%'";
		} else if(criterio == 4) {
			sql_busqueda = "SELECT * FROM PersonaBaja WHERE fechanac like '" + parametro + "%'";
		} else if(criterio == 5) {
			sql_busqueda = "SELECT * FROM PersonaBaja WHERE telefono like '" + parametro + "%'";
		} else if(criterio == 6) {
			sql_busqueda = "SELECT * FROM PersonaBaja WHERE correo like '"+ parametro + "%'";
		} else if(criterio == 7) {
			sql_busqueda = "SELECT * FROM PersonaBaja WHERE rol like '" + parametro + "%'";
		} else if(criterio == 8) {
			sql_busqueda = "SELECT * FROM PersonaBaja WHERE fecha_index ORDER BY fecha_index ASC";
		} else if(criterio == 9) {
			sql_busqueda = "SELECT * FROM PersonaBaja WHERE fecha_index ORDER BY fecha_index DESC";
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
	             PersonaBaja per = new PersonaBaja();
	             per.setDni(rs.getString(1));
	             per.setNombre(rs.getString(2));
	             per.setApellido(rs.getString(3));
	             per.setCuentabanc(rs.getString(4));
	             per.setFechanac(rs.getString(5));
	             per.setTelefono(rs.getInt(6));
	             per.setCorreo(rs.getString(7));
	             per.setRol(rs.getString(8));
	             per.setFechabaja(rs.getString(9));
	             lista.add(per);
	             
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
			listaPersonasBaja = datarPersonas();
		} else if (tipo == "bus") {
			listaPersonasBaja = datarPersonasBusqueda();
		}
		
		ArrayList<String> titulosListBaja=new ArrayList<>();
		
		//Estos son los encabezados de las columnas
		
		titulosListBaja.add("DNI");
		titulosListBaja.add("NOMBRE");
		titulosListBaja.add("APELLIDO");
		titulosListBaja.add("CUENTA BANCARIA");
		titulosListBaja.add("FECHA DE NACIMIENTO");
		titulosListBaja.add("TELEFONO");
		titulosListBaja.add("CORREO ELECTRONICO");
		titulosListBaja.add("ROL");
		titulosListBaja.add("FECHA BAJA");
		titulosListBaja.add("Reincorporar");
		titulosListBaja.add("Eliminar"); 
				
		//se asignan los tÃƒÂ­tulos de las columnas para enviarlas al constructor de la tabla
		
		String titulos[] = new String[titulosListBaja.size()];
		for (int i = 0; i < titulos.length; i++) {
			titulos[i]=titulosListBaja.get(i);
		}
		
		Object[][] data = arrayDatos(titulosListBaja);
		crearTabla(titulos,data);
		
	}
	
	public Object[][] arrayDatos(ArrayList<String> titulosList) {
	
		//Creamos un  array bidimensional donde las filas que corresponden a los usuarios son dinÃƒÂ¡micas y las columnas que pertenecen a los campos son estÃƒÂ¡ticas
		String informacion[][] = new String[listaPersonasBaja.size()][titulosList.size()];
		
		for (int i = 0; i < informacion.length; i++) {
			
			informacion[i][IndicadoresBaja.DNI] = listaPersonasBaja.get(i).getDni()+ "";
			informacion[i][IndicadoresBaja.NOMBRE] = listaPersonasBaja.get(i).getNombre()+ "";
			informacion[i][IndicadoresBaja.APELLIDO] = listaPersonasBaja.get(i).getApellido()+ "";
			informacion[i][IndicadoresBaja.CUENTABANC] = listaPersonasBaja.get(i).getCuentabanc()+ "";
			informacion[i][IndicadoresBaja.FECHANAC] = listaPersonasBaja.get(i).getFechanac()+ "";
			informacion[i][IndicadoresBaja.TELEFONO] = listaPersonasBaja.get(i).getTelefono()+ "";
			informacion[i][IndicadoresBaja.CORREO] = listaPersonasBaja.get(i).getCorreo()+ "";
			informacion[i][IndicadoresBaja.ROL] = listaPersonasBaja.get(i).getRol()+ "";
			informacion[i][IndicadoresBaja.FECHABAJA] = listaPersonasBaja.get(i).getFechabaja()+ "";
			informacion[i][IndicadoresBaja.REINCORPORAR] = "reincorporar";
			informacion[i][Indicadores.ELIMINAR] = "eliminar";
			
		}
		
		return informacion;
	}
	
	public void crearTabla(String[] titulos, Object[][] data) {
		
		modelo = new ModeloTabla(data, titulos);
		
		tablaPersonasBaja.setModel(modelo);
		
		filasTabla = tablaPersonasBaja.getRowCount();
		columnasTabla = tablaPersonasBaja.getColumnCount();
		
		for (int i = 0; i < titulos.length; i++) {
			tablaPersonasBaja.getColumnModel().getColumn(i).setCellRenderer(new Celdas("campo"));	
		}
		tablaPersonasBaja.getColumnModel().getColumn(IndicadoresBaja.REINCORPORAR).setCellRenderer(new Celdas("boton"));
		tablaPersonasBaja.getColumnModel().getColumn(IndicadoresBaja.ELIMINAR).setCellRenderer(new Celdas("boton"));
		
		
		tablaPersonasBaja.getTableHeader().setReorderingAllowed(false);
		tablaPersonasBaja.setRowHeight(25);//tamaÃƒÂ±o de las celdas
		
		//Se define el tamaÃƒÂ±o de largo para cada columna y su contenido
		tablaPersonasBaja.getColumnModel().getColumn(IndicadoresBaja.DNI).setCellRenderer(new Celdas("campo"));//dni
		tablaPersonasBaja.getColumnModel().getColumn(IndicadoresBaja.NOMBRE).setCellRenderer(new Celdas("campo"));//nombre
		tablaPersonasBaja.getColumnModel().getColumn(IndicadoresBaja.APELLIDO).setCellRenderer(new Celdas("campo"));//apellido
		tablaPersonasBaja.getColumnModel().getColumn(IndicadoresBaja.CUENTABANC).setCellRenderer(new Celdas("campo"));//cuenta bancaria
		tablaPersonasBaja.getColumnModel().getColumn(IndicadoresBaja.FECHANAC).setCellRenderer(new Celdas("campo"));//fecha de nacimiento
		tablaPersonasBaja.getColumnModel().getColumn(IndicadoresBaja.TELEFONO).setCellRenderer(new Celdas("campo"));//telÃƒÂ©fono
		tablaPersonasBaja.getColumnModel().getColumn(IndicadoresBaja.CORREO).setCellRenderer(new Celdas("campo"));//correo electrÃƒÂ³nico
		tablaPersonasBaja.getColumnModel().getColumn(IndicadoresBaja.ROL).setCellRenderer(new Celdas("campo"));//rol
		tablaPersonasBaja.getColumnModel().getColumn(IndicadoresBaja.REINCORPORAR).setCellRenderer(new Celdas("boton"));//boton modificar
		tablaPersonasBaja.getColumnModel().getColumn(IndicadoresBaja.ELIMINAR).setCellRenderer(new Celdas("boton"));//boton eliminar
		
		//personaliza el encabezado
		JTableHeader jtableHeader = tablaPersonasBaja.getTableHeader();
	    jtableHeader.setDefaultRenderer(new Encabezado());
	    tablaPersonasBaja.setTableHeader(jtableHeader);
	    
	    scp.setViewportView(tablaPersonasBaja);


	}
	
	public void EliminarRegistro(String dni_borrar) {
		
		System.out.println(dni_borrar);
		
		String sql_borrar = "DELETE from PersonaBaja WHERE dni='"+dni_borrar+"'";
				
		cp = new Conexion();
		
		try {
			conn = cp.conectar();
			cp.eliminar(conn, sql_borrar);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void refrescarTabla(){
		construirTabla("lis");
	}

	//	EVENTOS DINÁMICOS:
	
	//Eventos al pulsar los botones de la tabla con el click del ratÃƒÂ³n
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//Aqui nos dice que fila es en la que estÃƒÂ¡ clickando
		int fila = tablaPersonasBaja.rowAtPoint(e.getPoint());
		//Aqui nos dice que columna es en la que estÃƒÂ¡ clickando
		int columna = tablaPersonasBaja.columnAtPoint(e.getPoint());
		
		
		if (columna == IndicadoresBaja.REINCORPORAR) {
		//Lanzamos la ventana de modificacion
			int row = tablaPersonasBaja.getSelectedRow();
			int reply = JOptionPane.showConfirmDialog(tablaPersonasBaja, "¿Esta seguro de que quiere reincorporar este usuario?", "Reincorporar",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			if(reply == JOptionPane.YES_OPTION) {
			Reincorporar(tablaPersonasBaja.getValueAt(tablaPersonasBaja.getSelectedRow(), 0).toString());
			EliminarRegistro(tablaPersonasBaja.getValueAt(tablaPersonasBaja.getSelectedRow(), 0).toString());
			JOptionPane.showMessageDialog(tablaPersonasBaja, "El registro ha sido reincorporado a la tabla de usuarios activos correctamente","Reincorporar", JOptionPane.PLAIN_MESSAGE);
			construirTabla("lis");
			}
			
		} else if (columna == Indicadores.ELIMINAR) {
			System.out.println("pulsado eliminar");
			int reply = JOptionPane.showConfirmDialog(tablaPersonasBaja, "¿Esta seguro de que quiere borrar el registro DEFINITIVAMENTE?", "Eliminar",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			if(reply == JOptionPane.YES_OPTION) {
				//Elimina el registro de la BBDD
				EliminarRegistro(tablaPersonasBaja.getValueAt(tablaPersonasBaja.getSelectedRow(), 0).toString());
				
				JOptionPane.showMessageDialog(tablaPersonasBaja, "Registro eliminado");
				//Vuelvo a construir la tabla para reflejar los cambios
				construirTabla("lis");
			}
		}
	}
	
public void Reincorporar(String dni_rein) {
		
		
		try {
			conn = cp.conectar();
			
			String pass_pro = V_AdminUserAdd.getPassword(8);
			
			
				
			
			int telefono = Integer.valueOf(tablaPersonasBaja.getValueAt(tablaPersonasBaja.getSelectedRow(), 5).toString());
			
			String sql_insertar_baja = "INSERT INTO Persona (DNI, nombre, apellido, cuentabanc, pass, fechanac, telefono, correo, rol) VALUES"
				+ "('"+dni_rein+"','"+tablaPersonasBaja.getValueAt(tablaPersonasBaja.getSelectedRow(), 1).toString()+"',"
						+ " '"+tablaPersonasBaja.getValueAt(tablaPersonasBaja.getSelectedRow(), 2).toString()+"',"
								+ " '"+tablaPersonasBaja.getValueAt(tablaPersonasBaja.getSelectedRow(), 3).toString()+"',"
										+ " '"+pass_pro+"',"
												+ " '"+tablaPersonasBaja.getValueAt(tablaPersonasBaja.getSelectedRow(), 5).toString()+"',"
														+ " '"+telefono+"',"
														+ "'"+tablaPersonasBaja.getValueAt(tablaPersonasBaja.getSelectedRow(), 6).toString()+"',"
																+ "'"+tablaPersonasBaja.getValueAt(tablaPersonasBaja.getSelectedRow(), 7).toString()+"')";
																		
			
			
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
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			criterio = combo_campos.getSelectedIndex();
			parametro = getTf_busqueda().getText().toString();
			
			construirTabla("bus");
		}
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