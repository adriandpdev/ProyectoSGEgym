package AdminFront;

import java.awt.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

public class V_AdminScheList_Renderer extends JTextArea implements TableCellRenderer {
	 
    public V_AdminScheList_Renderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
        
    }
 
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            }
            else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
            setFont(table.getFont());
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }