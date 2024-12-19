package com.View;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import com.Model.Patient;

class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private PatientTableView tableView;

    public ButtonEditor(JCheckBox checkBox, PatientTableView tableView,PatientRegistrationView view) {
        super(checkBox);
        this.tableView = tableView;
        button = new JButton("Update");
        button.addActionListener(e -> {
            int row = tableView.table.getSelectedRow();
            if (row != -1) {
                int mrdID = (int) tableView.table.getValueAt(row, 0);
                Patient patientToUpdate = tableView.allPatients.stream()
                        .filter(p -> p.getMrdID() == mrdID).findFirst().orElse(null);
                if (patientToUpdate != null) {
                    //new PatientUpdateView(patientToUpdate, tableView);
                	new PatientRegistrationView(patientToUpdate,tableView,(ArrayList<Patient>) tableView.allPatients);

                }
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                                 int row, int column) {
        return button;
    }
}
