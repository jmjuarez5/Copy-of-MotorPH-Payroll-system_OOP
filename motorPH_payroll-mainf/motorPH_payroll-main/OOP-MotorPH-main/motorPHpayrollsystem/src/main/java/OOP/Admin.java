package OOP;

import GUI.ViewPortal;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.*;

public class Admin extends User {

    public Admin(String email, String password) {
        super(email, password, "Admin");
    }

    public void populateFieldsFromTable(JTable jTable1, JTextField[] textFields, JTextField emailField, JTextField passwordField) {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TableModel model = jTable1.getModel();

        // Only populate fields that exist in the array (0-19 for 20 elements)
        for (int i = 0; i < textFields.length && i < 20; i++) {
            if (i == 8 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 17) {
                textFields[i].setText(removeQuotes(getValue(model, row, i)));
            } else {
                textFields[i].setText(getValue(model, row, i));
            }
        }
        
        // Set email field (index 19)
        if (emailField != null) {
            emailField.setText(getValue(model, row, 19));
        }
        
        // Set password field (index 20) - but don't populate it for security
        if (passwordField != null) {
            passwordField.setText(""); // Clear password field for security
        }
    }

    public void updateEmployeeDetails(JTable jTable1, JTextField[] textFields, JTextField emailField, JTextField passwordField) {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] newValues = new String[textFields.length];
        for (int i = 0; i < textFields.length; i++) {
            if (i == 8 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 17) {
                newValues[i] = addQuotes(textFields[i].getText().trim());
            } else {
                newValues[i] = textFields[i].getText().trim();
            }
        }

        String email = emailField != null ? emailField.getText().trim() : "";

        if (isEmployeeNumberDuplicate(jTable1, newValues[0], selectedRow)) {
            JOptionPane.showMessageDialog(null, "Employee number already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //  Updates with new values
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (int i = 0; i < newValues.length; i++) {
            model.setValueAt(newValues[i], selectedRow, i);
        }
        model.setValueAt(email, selectedRow, 19);
        // Don't update password field directly - use PasswordService for security

        // Saves updated info
        int option = JOptionPane.showConfirmDialog(null, "Confirm update?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                saveDataToCSV(model, SystemIT.EMPLOYEE_CSV);
                JOptionPane.showMessageDialog(null, "Employee details updated successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving to CSV: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveDataToCSV(TableModel model, String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                bw.write(model.getColumnName(j));
                if (j < model.getColumnCount() - 1) {
                    bw.write(",");
                }
            }
            bw.newLine();

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    bw.write(model.getValueAt(i, j).toString());
                    if (j < model.getColumnCount() - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();
            }
        }
    }

    //  Loads employee data into JTable
    public void viewEmployeeData(ViewPortal frame) {
        DefaultTableModel model = (DefaultTableModel) frame.viewEmployeeTable.getModel();
        model.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader(SystemIT.EMPLOYEE_CSV))) {
            String line;
            boolean isFirstRow = true;
            while ((line = br.readLine()) != null) {
                if (isFirstRow) {
                    isFirstRow = false; // Skip header
                    continue;
                }
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                model.addRow(data);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading employee data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //  Removes quotes for display in JTextFields
    private String removeQuotes(String text) {
        return text.replaceAll("^\"|\"$", "");
    }

    //  Adds quotes when saving to CSV for Address, Immediate Supervisor, Basic Salary, etc.
    private String addQuotes(String text) {
        if (!text.startsWith("\"") || !text.endsWith("\"")) {
            return "\"" + text + "\"";
        }
        return text;
    }

    //  Method that gets table value
    private String getValue(TableModel model, int row, int column) {
        Object value = model.getValueAt(row, column);
        return value != null ? value.toString().trim() : "";
    }

    //  Duplicate checking
    private boolean isEmployeeNumberDuplicate(JTable table, String empNum, int selectedRow) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (i != selectedRow && model.getValueAt(i, 0).equals(empNum)) {
                return true;
            }
        }
        return false;
    }

    }

