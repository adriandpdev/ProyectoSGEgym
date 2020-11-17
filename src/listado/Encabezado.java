package listado;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

//Esta clase personaliza los colores y las animaciones de la tabla así como la dimension de las filas y columnas

public class Encabezado implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocul,
			int column, int arg5) {
		// TODO Auto-generated method stub
		JComponent jcomponent = null;

		if (value instanceof String) {
			jcomponent = new JLabel((String) value);
			((JLabel) jcomponent).setHorizontalAlignment(SwingConstants.CENTER);
			((JLabel) jcomponent).setSize(30, jcomponent.getWidth());
			((JLabel) jcomponent).setPreferredSize(new Dimension(6, jcomponent.getWidth()));
		}

		jcomponent
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(255, 255, 255)));
		jcomponent.setOpaque(true);
		jcomponent.setBackground(new Color(65, 65, 65));
		jcomponent.setToolTipText("Tabla Seguimiento");
		jcomponent.setForeground(Color.white);

		return jcomponent;
	}

}
