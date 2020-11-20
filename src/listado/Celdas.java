package listado;
import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Celdas extends DefaultTableCellRenderer{
	
	private String tipo;

	//se definen por defecto los tipos de datos a usar
	private Font normal = new Font( "Verdana",Font.PLAIN ,12 );
	private Font bold = new Font( "Verdana",Font.BOLD ,12 );
	
	
	public Celdas(){
		
	}

	public Celdas(String tipo){
		this.tipo=tipo;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
		

        Color colorFondo = null;
        Color colorFondoPorDefecto=new Color( 192, 192, 192);
        Color colorFondoSeleccion=new Color( 140, 140 , 140);
    	
        /*
         * Si la celda del evento es la seleccionada se asigna el fondo por defecto para la selección
         */
        if (selected) {                
            this.setBackground(colorFondoPorDefecto );   
        }
        else
        {
        	//Para las que no están seleccionadas se pinta el fondo de las celdas de blanco
            this.setBackground(Color.white);
        }
                
        /*
         * Se definen los tipos de datos que contendrán las celdas basado en la instancia que
         * se hace en la ventana de la tabla al momento de construirla
         */
        if( tipo.equals("campo"))
        {
        	//si es tipo texto define el color de fondo del texto y de la celda así como la alineación
            if (focused) {
    			colorFondo=colorFondoSeleccion;
    		}else{
    			colorFondo= colorFondoPorDefecto;
    		}
            this.setHorizontalAlignment( JLabel.LEFT );
            this.setText( (String) value );
            //this.setForeground( (selected)? new Color(255,255,255) :new Color(0,0,0) );   
            //this.setForeground( (selected)? new Color(255,255,255) :new Color(32,117,32) );
            this.setBackground( (selected)? colorFondo :Color.WHITE);	
            this.setFont(normal);   
            //this.setFont(bold);
            return this;
        }
         
        //si el tipo es icono entonces valida cual icono asignar a la etiqueta.
        if(tipo.equals("boton"))
        {
            if(String.valueOf(value).equals("modificar"))
            {
            	JButton btn_modificar = new JButton("Modificar");
            	return btn_modificar;
            } else if(String.valueOf(value).equals("eliminar")) {
            	JButton btn_eliminar = new JButton("Eliminar");
            	return btn_eliminar;
            }
         
        }
      
		return this;
		
		
	}
	
	
	
}