package listado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import main.*;

public class listado extends JInternalFrame{
	
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
		
		panel_central.setLayout(new FlowLayout());
		panel_central.setBackground(Color.BLACK);
		
		panel_central.setLayout(new BorderLayout());
		
		//JTable
	
		scp = new JScrollPane();
		
		tablaPersonas = new JTable();
		tablaPersonas.setBackground(Color.WHITE);
		//tablaPersonas.addMouseListener(this);
		//tablaSeguimiento.addKeyListener(this);
		tablaPersonas.setOpaque(false);
		scp.setViewportView(tablaPersonas);
		
		construirTabla();
		
		
		panel_central.add(panel_norte_busqueda, BorderLayout.NORTH);
		panel_central.add(scp, BorderLayout.CENTER);
		panel_norte.add(lbl_titulo);
		
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
		
		String sql = "SELECT * FROM persona";
		
		
		try {
			rs = cp.consulta(Main.con, sql);
			
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
	             
	             //Comprobaciones BORRAR DESPUES
	             System.out.println(rs.getString(1));
	             String cad = per.getNombre();
	             System.out.println(cad);
	             
	         }
			
			
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
		
		titulosList.add("DNI");
		titulosList.add("NOMBRE");
		titulosList.add("APELLIDO");
		titulosList.add("CUENTA BANCARIA");
		titulosList.add("PASSWORD");
		titulosList.add("FECHA DE NACIMIENTO");
		titulosList.add("TELEFONO");
		titulosList.add("CORREO ELECTRONICO");
		titulosList.add("ROL");
		titulosList.add("Modificar"); //Estos serán los huecos para los botones
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
			
		}
		
		return informacion;
	}
	
	private void crearTabla(String[] titulos, Object[][] data) {
		
		modelo = new ModeloTabla(data, titulos);
		
		tablaPersonas.setModel(modelo);
		
		filasTabla = tablaPersonas.getRowCount();
		columnasTabla = tablaPersonas.getColumnCount();
		
		for (int i = 0; i < titulos.length; i++) {
			tablaPersonas.getColumnModel().getColumn(i).setCellRenderer(new Celdas());	
		}
		
		
		tablaPersonas.getTableHeader().setReorderingAllowed(false);
		tablaPersonas.setRowHeight(25);//tamaño de las celdas
		
		//Se define el tamaño de largo para cada columna y su contenido
		tablaPersonas.getColumnModel().getColumn(Indicadores.DNI).setCellRenderer(new Celdas());//dni
		tablaPersonas.getColumnModel().getColumn(Indicadores.NOMBRE).setCellRenderer(new Celdas());//nombre
		tablaPersonas.getColumnModel().getColumn(Indicadores.APELLIDO).setCellRenderer(new Celdas());//apellido
		tablaPersonas.getColumnModel().getColumn(Indicadores.CUENTABANC).setCellRenderer(new Celdas());//cuenta bancaria
		tablaPersonas.getColumnModel().getColumn(Indicadores.PASS).setCellRenderer(new Celdas());//password
		tablaPersonas.getColumnModel().getColumn(Indicadores.FECHANAC).setCellRenderer(new Celdas());//fecha de nacimiento
		tablaPersonas.getColumnModel().getColumn(Indicadores.TELEFONO).setCellRenderer(new Celdas());//telefono
		tablaPersonas.getColumnModel().getColumn(Indicadores.CORREO).setCellRenderer(new Celdas());//correo electrónico
		tablaPersonas.getColumnModel().getColumn(Indicadores.ROL).setCellRenderer(new Celdas());//rol
		tablaPersonas.getColumnModel().getColumn(Indicadores.MODIFICAR).setPreferredWidth(20);//boton modificar
		tablaPersonas.getColumnModel().getColumn(Indicadores.ELIMINAR).setPreferredWidth(20);//boton eliminar
		
		//personaliza el encabezado
		JTableHeader jtableHeader = tablaPersonas.getTableHeader();
	    jtableHeader.setDefaultRenderer(new Encabezado());
	    tablaPersonas.setTableHeader(jtableHeader);
	    
	    scp.setViewportView(tablaPersonas);


	}

}


	

