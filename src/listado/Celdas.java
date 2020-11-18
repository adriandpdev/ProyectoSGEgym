package listado;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Celdas extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font normal = new Font("Verdana", Font.PLAIN, 12);
	private Font bold = new Font("Verdana", Font.BOLD, 12);
	// etiqueta que almacenará el icono a mostrar
	private JLabel label = new JLabel();

	public Celdas() {

	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
			int row, int column) {

		/*
		 * Este metodo controla toda la tabla, podemos obtener el valor que contiene
		 * definir que celda está seleccionada, la fila y columna al tener el foco en
		 * ella.
		 * 
		 * cada evento sobre la tabla invocará a este metodo
		 */

		// definimos colores por defecto
		Color colorFondo = null;
		Color colorFondoPorDefecto = new Color(192, 192, 192);
		Color colorFondoSeleccion = new Color(140, 140, 140);

		// Si la celda del evento es la seleccionada se asigna el fondo por defecto para
		// la selección

		if (selected) {
			this.setBackground(colorFondoPorDefecto);
		} else {
			// Para las que no están seleccionadas se pinta el fondo de las celdas de blanco
			this.setBackground(Color.white);
		}

		// si es tipo texto define el color de fondo del texto y de la celda así como la
		// alineación
		if (focused) {
			colorFondo = colorFondoSeleccion;
		} else {
			colorFondo = colorFondoPorDefecto;
		}

		return this;

	}

}
