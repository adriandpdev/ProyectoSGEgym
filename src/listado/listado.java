package listado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;


//Implementamos un mouse listener porque queremos capturar las acciones que realice el ratón

public class listado extends JInternalFrame implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbl_titulo;
	private JPanel panel_norte, panel_sur, panel_central, panel_norte_busqueda, panel_central_listado;
	private JTable tablaPersonas;
	private JScrollPane scp;
	
	ArrayList<Persona> listaPersonas;
	
	private int filasTabla, columnasTabla;
	
	private Conexion cp;
	private Connection conn;
	private ResultSet rs;
	private ModeloTabla modelo;
	
	public listado() {
		
		this.setLayout(new BorderLayout());
		
		
		//Paneles del primer BorderLayout
		panel_norte = new JPanel();
		panel_central = new JPanel();
		panel_sur = new JPanel();
		
		//Paneles del segundo BorderLayout
		panel_norte_busqueda = new JPanel();
		panel_central_listado = new JPanel();
		
		//Color de fondo personalizado del título
		panel_norte.setBackground(new Color(137, 13, 84));
		
		//Título personalizado 
		lbl_titulo = new JLabel("LISTADO DE USUARIOS/PROFESORES");
		lbl_titulo.setFont(new Font("Verdana",Font.BOLD,22));
		lbl_titulo.setForeground(Color.WHITE);
		
		panel_central.setLayout(new BorderLayout());
		
		//JTable
	
		scp = new JScrollPane();
		
		tablaPersonas = new JTable();
		
		tablaPersonas.addMouseListener(this);
		//tablaSeguimiento.addKeyListener(this);
	
		construirTabla();
		
		panel_central.add(panel_norte_busqueda, BorderLayout.NORTH);
		panel_central.add(scp, BorderLayout.CENTER);
		panel_norte.add(lbl_titulo);
		
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
	
	private ArrayList<Persona> datarPersonas(){
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
	
	//Metodo para contruir la tabla
	
	private void construirTabla() {
		
		listaPersonas = datarPersonas();
		
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
	
	private Object[][] arrayDatos(ArrayList<String> titulosList) {
	
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
	
	private void crearTabla(String[] titulos, Object[][] data) {
		
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
			System.out.println(pm_fechanac);
			String pm_telefono = tablaPersonas.getValueAt(row, 6).toString();
			String pm_correo = tablaPersonas.getValueAt(row, 7).toString();
			String pm_rol = tablaPersonas.getValueAt(row, 8).toString();
			
			//Abrimos el panel para hacer las modificaciones sobrecargando su constructor
			PanelModificacion pamo = new PanelModificacion(pm_dni, pm_nombre, pm_apellido, pm_cuentabanc, pm_pass, pm_fechanac, pm_telefono, pm_correo, pm_rol);
			
		} else if (columna == Indicadores.ELIMINAR) {
			System.out.println("pulsado eliminar");
			int reply = JOptionPane.showConfirmDialog(tablaPersonas, "¿Esta seguro de que quiere borrar el registro?", "Eliminar",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			if(reply == JOptionPane.YES_OPTION) {
				//Elimina el registro de la BBDD
				EliminarRegistro(tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 0).toString());
				
				JOptionPane.showMessageDialog(tablaPersonas, "Registro eliminado");
				//Vuelvo a construir la tabla para reflejar los cambios
				construirTabla();
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
	
	public void EliminarRegistro(String dni_borrar) {
		
		System.out.println(dni_borrar);
		
		String sql_borrar = "DELETE from Persona WHERE dni='"+dni_borrar+"'";
				
		cp = new Conexion();
		
		try {
			conn = cp.conectar();
			cp.eliminar(conn, sql_borrar);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}


	

