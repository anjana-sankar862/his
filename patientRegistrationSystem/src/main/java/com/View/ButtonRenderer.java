package com.View;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
    	ImageIcon orginalIcon=new ImageIcon("editblue.jpg");
        Image resizedImage= orginalIcon.getImage().getScaledInstance(16,16,Image.SCALE_SMOOTH);
        ImageIcon resizedIcon=new ImageIcon(resizedImage);

setIcon(resizedIcon);
        //setText("Edit");
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        return this;
    }
}
