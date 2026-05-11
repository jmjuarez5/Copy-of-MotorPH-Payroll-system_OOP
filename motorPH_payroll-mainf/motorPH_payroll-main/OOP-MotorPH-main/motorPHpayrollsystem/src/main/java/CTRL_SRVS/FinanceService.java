package CTRL_SRVS;

import OOP.ValidationRule;
import OOP.InputValidator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;   // FIX: was missing — Arrays.copyOf used in saveFinanceColumns
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Service layer for Finance Portal business logic
 * Handles data access, validation, and business operations
 */
public class FinanceService {
    
    /**
     * Loads employee data from CSV into the table model
     * @param model The table model to populate
     */
    public void loadEmployeeData(DefaultTableModel model) {
        model.setRowCount(0);
        
        java.io.BufferedReader br = null;
        try {
            br = new java.io.BufferedReader(
                    new java.io.FileReader(OOP.SystemIT.EMPLOYEE_CSV));
            String line;
            String splitBy = ",";
            br.readLine(); // Skip header
            
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                if (data.length < 19) continue;
                
                Object[] row = new Object[19];
                for (int i = 0; i < 19; i++) {
                    row[i] = data[i].trim();
                }
                model.addRow(row);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading employee data.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Loads attendance data from CSV into the table model
     * @param model The table model to populate
     */
    public void loadAttendanceData(DefaultTableModel model) {
        model.setRowCount(0);
        
        java.io.BufferedReader br = null;
        try {
            br = new java.io.BufferedReader(
                    new java.io.FileReader(OOP.SystemIT.ATTENDANCE_CSV));
            String line;
            String splitBy = ",";
            br.readLine(); // Skip header
            
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                if (data.length < 4) continue;
                
                Object[] row = new Object[4];
                for (int i = 0; i < 4; i++) {
                    row[i] = data[i].trim();
                }
                model.addRow(row);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading attendance data.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Loads attendance data from CSV file into a 2D array.
     * This method is used by the service layer to provide data to the GUI.
     * 
     * @param csvFilePath The path to the CSV file
     * @return 2D array containing attendance data
     * @throws Exception if file cannot be read
     */
    public String[][] loadAttendanceDataFromCSV(String csvFilePath) throws Exception {
        List<String[]> data = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstRow = true;

            while ((line = br.readLine()) != null) {
                if (isFirstRow) {
                    isFirstRow = false; // Skip the header row
                    continue;
                }
                
                String[] rowData = line.split(",");
                // Ensure that row has four columns
                if (rowData.length < 4) {
                    rowData = Arrays.copyOf(rowData, 4);
                }
                data.add(rowData);
            }
        } catch (IOException e) {
            throw new Exception("Failed to load attendance data from CSV: " + e.getMessage());
        }
        
        return data.toArray(String[][]::new);
    }
    
    /**
     * Searches for an employee by ID, last name, or first name
     * @param model The table model to search in
     * @param searchTerm The search term
     * @return The row index if found, -1 if not found
     */
    public int searchEmployee(DefaultTableModel model, String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return -1;
        }
        
        String searchLower = searchTerm.toLowerCase().trim();
        
        for (int i = 0; i < model.getRowCount(); i++) {
            // Check employee number (column 0), last name (column 1), and first name (column 2)
            String empNum = model.getValueAt(i, 0).toString().toLowerCase();
            String lastName = model.getValueAt(i, 1).toString().toLowerCase();
            String firstName = model.getValueAt(i, 2).toString().toLowerCase();
            
            if (empNum.equals(searchLower) || 
                lastName.equals(searchLower) || 
                firstName.equals(searchLower)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Validates finance form fields
     * @param fields Map of field names to validation rules
     * @return null if valid, error message if invalid
     */
    public String validateFinanceFields(Map<JTextField, ValidationRule> fields) {
        return InputValidator.validate(fields);
    }
    
    /**
     * Creates a map of validation rules for finance fields
     * @return Map of fields to validation rules
     */
    public Map<JTextField, ValidationRule> createFinanceValidationRules(
            JTextField employeeIDField, JTextField sssField, 
            JTextField philHealthField, JTextField pagIbigField,
            JTextField workingHoursField, JTextField basicSalaryField,
            JTextField taxIncomeField, JTextField taxField,
            JTextField totalDeductionField, JTextField grossPayField,
            JTextField netPayField) {
        
        Map<JTextField, ValidationRule> rules = new HashMap<>();
        
        // Required information
        rules.put(employeeIDField, ValidationRule.REQUIRED);
        rules.put(employeeIDField, ValidationRule.EMPLOYEE_ID);
        
        // Government ID validation
        rules.put(sssField, ValidationRule.SSS);
        rules.put(philHealthField, ValidationRule.PHILHEALTH);
        rules.put(pagIbigField, ValidationRule.PAGIBIG);
        
        // Number requirements
        rules.put(workingHoursField, ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(basicSalaryField, ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(taxIncomeField, ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(taxField, ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(totalDeductionField, ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(grossPayField, ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(netPayField, ValidationRule.DECIMAL_NON_NEGATIVE);
        
        return rules;
    }

    /**
     * Saves ONLY the 6 Finance-editable columns (Basic Salary through Hourly Rate,
     * CSV columns 13-18) for the given employee ID.
     *
     * Reads every CSV line in full, splits it, replaces only indices 13-18,
     * rejoins and writes back. All other columns are left completely untouched.
     *
     * FIX: Added missing java.util.Arrays import at top of file.
     * FIX: Both sides of the ID comparison are now trimmed to prevent
     *      whitespace-caused mismatches that caused silent save failures.
     */
    public void saveFinanceColumns(String employeeId, String[] newValues) throws Exception {
        String csvPath = OOP.SystemIT.EMPLOYEE_CSV;
        List<String> outputLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    outputLines.add(line);
                    isHeader = false;
                    continue;
                }

                // Split preserving quoted fields (handles addresses that contain commas)
                String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                // FIX: trim both sides so "10001 " equals "10001"
                if (parts.length > 0 && parts[0].trim().equals(employeeId.trim())) {
                    if (parts.length < 19) parts = Arrays.copyOf(parts, 19);
                    for (int i = 0; i < newValues.length && (13 + i) < parts.length; i++) {
                        parts[13 + i] = newValues[i];
                    }
                    outputLines.add(String.join(",", parts));
                } else {
                    outputLines.add(line);
                }
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvPath))) {
            for (String l : outputLines) {
                bw.write(l);
                bw.newLine();
            }
        }
    }
}