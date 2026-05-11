package CTRL_SRVS;

import DAO.EmployeeDAO;
import OOP.Employee;
import OOP.ValidationRule;
import OOP.InputValidator;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Service layer for employee business logic operations.
 * Handles employee CRUD operations and validation.
 */
public class EmployeeService {
    private final EmployeeDAO employeeDAO;
    
    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }
    
    /**
     * Gets employee details by ID.
     * @param employeeId The employee ID to search for
     * @return Employee object if found, null otherwise
     */
    public Employee getEmployeeDetails(String employeeId) {
        try {
            String[] employeeData = employeeDAO.findEmployeeById(employeeId);
            if (employeeData != null && employeeData.length >= 21) {
                return new Employee(
                    employeeData[0], // employeeId
                    employeeData[1], // lastName
                    employeeData[2], // firstName
                    employeeData[4], // birthday
                    employeeData[10], // status
                    employeeData[5], // contactNumber
                    employeeData[8], // address (using position field as address)
                    parseDouble(employeeData[17]), // basicSalary
                    parseDouble(employeeData[18]), // hourlyRate
                    parseDouble(employeeData[14]), // riceSubsidy
                    parseDouble(employeeData[15]), // phoneAllowance
                    parseDouble(employeeData[16]), // clothingAllowance
                    employeeData[12], // sss
                    employeeData[14], // pagIBIG
                    employeeData[13], // philHealth
                    employeeData[15], // tin
                    employeeData[19], // email
                    employeeData[20]  // password
                );
            }
        } catch (Exception e) {
            System.err.println("Error getting employee details: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Creates a new employee record.
     * @param employee The employee to create
     * @throws Exception if creation fails
     */
    public void createEmployee(Employee employee) throws Exception {
        employeeDAO.createEmployee(employee);
    }
    
    /**
     * Updates an existing employee record.
     * @param employee The employee with updated information
     * @throws Exception if update fails
     */
    public void updateEmployee(Employee employee) throws Exception {
        employeeDAO.updateEmployee(employee);
    }
    
    /**
     * Deletes an employee record.
     * @param employeeId The employee ID to delete
     * @throws Exception if deletion fails
     */
    public void deleteEmployee(String employeeId) throws Exception {
        employeeDAO.deleteEmployee(employeeId);
    }
    
    /**
     * Validates employee data using comprehensive validation rules.
     * @param employee The employee data to validate
     * @return null if validation passes, error message string if validation fails
     */
    public String validateEmployeeData(Employee employee) {
        Map<javax.swing.JTextField, ValidationRule> rules = new HashMap<>();
        
        // Required fields validation - these must always be filled
        rules.put(createTextField(employee.getEmployeeNumber()), ValidationRule.REQUIRED);
        rules.put(createTextField(employee.getLastName()), ValidationRule.REQUIRED);
        rules.put(createTextField(employee.getFirstName()), ValidationRule.REQUIRED);
        rules.put(createTextField(employee.getPosition()), ValidationRule.REQUIRED);
        rules.put(createTextField(employee.getStatus()), ValidationRule.REQUIRED);
        rules.put(createTextField(employee.getContactNumber()), ValidationRule.REQUIRED);
        rules.put(createTextField(employee.getSssNumber()), ValidationRule.REQUIRED);
        
        // Format validation (applied only if field is not empty)
        rules.put(createTextField(employee.getEmployeeNumber()), ValidationRule.EMPLOYEE_ID);
        rules.put(createTextField(employee.getLastName()), ValidationRule.NAME);
        rules.put(createTextField(employee.getFirstName()), ValidationRule.NAME);
        rules.put(createTextField(employee.getSssNumber()), ValidationRule.SSS);
        rules.put(createTextField(employee.getPhilHealthNumber()), ValidationRule.PHILHEALTH);
        rules.put(createTextField(employee.getTinNumber()), ValidationRule.TIN);
        rules.put(createTextField(employee.getPagIbigNumber()), ValidationRule.PAGIBIG);
        rules.put(createTextField(employee.getContactNumber()), ValidationRule.PHONE);
        rules.put(createTextField(employee.getBirthday()), ValidationRule.DATE_YYYYMMDD);
        rules.put(createTextField(employee.getAddress()), ValidationRule.GENERIC_TEXT);
        rules.put(createTextField(employee.getImmediateSupervisor()), ValidationRule.NAME);
        
        // Number validation (applied only if field is not empty)
        rules.put(createTextField(String.valueOf(employee.getBasicSalary())), ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(createTextField(String.valueOf(employee.getRiceSubsidy())), ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(createTextField(String.valueOf(employee.getPhoneAllowance())), ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(createTextField(String.valueOf(employee.getClothingAllowance())), ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(createTextField(String.valueOf(employee.getBasicSalary() / 2)), ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(createTextField(String.valueOf(employee.getHourlyRate())), ValidationRule.DECIMAL_NON_NEGATIVE);
        
        // Validation checker
        return InputValidator.validate(rules);
    }
    
    /**
     * Validates if an employee ID is unique.
     * @param employeeId The employee ID to check
     * @return true if unique, false if already exists
     */
    public boolean validateEmployeeIdUniqueness(String employeeId) {
        try {
            String[] existingEmployee = employeeDAO.findEmployeeById(employeeId);
            return existingEmployee == null;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Loads all employees from the data source.
     * @return List of all employees
     * @throws Exception if loading fails
     */
    public List<Employee> getAllEmployees() throws Exception {
        return employeeDAO.getAllEmployees();
    }
    
    /**
     * Finds an employee by ID and returns raw data array.
     * @param employeeId The employee ID to search for
     * @return Raw employee data array if found, null otherwise
     */
    public String[] findEmployeeById(String employeeId) {
        try {
            return employeeDAO.findEmployeeById(employeeId);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Helper method to create a JTextField for validation.
     * @param value The value to set in the text field
     * @return JTextField with the specified value
     */
    private javax.swing.JTextField createTextField(String value) {
        javax.swing.JTextField textField = new javax.swing.JTextField();
        textField.setText(value != null ? value : "");
        return textField;
    }
    
    /**
     * Helper method to parse double values safely.
     * @param value The string value to parse
     * @return The parsed double value, or 0.0 if parsing fails
     */
    private double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) return 0.0;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    
    /**
     * Loads employee data from CSV file into a 2D array format.
     * This method is used by the GUI layer to populate employee tables.
     * 
     * @param csvFilePath The path to the CSV file to load from
     * @return 2D array containing employee data, where each row represents an employee
     * @throws Exception if loading fails
     */
    public String[][] loadEmployeeDataFromCSV(String csvFilePath) throws Exception {
        java.util.List<String[]> employeeDataList = new java.util.ArrayList<>();
        
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;
            
            while ((line = br.readLine()) != null) {
                // Skip header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                // Parse CSV line
                String[] rowData = parseCSVLine(line);
                if (rowData != null && rowData.length > 0) {
                    employeeDataList.add(rowData);
                }
            }
        } catch (java.io.IOException e) {
            throw new Exception("Failed to load data from CSV file: " + e.getMessage());
        }
        
        // Convert to 2D array
        return employeeDataList.toArray(new String[0][]);
    }
    
    /**
     * Parses a CSV line into an array of values, handling quoted fields.
     * 
     * @param line The CSV line to parse
     * @return Array of parsed values
     */
    private String[] parseCSVLine(String line) {
        java.util.List<String> values = new java.util.ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;
        boolean escapedQuote = false;
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (escapedQuote) {
                currentField.append(c);
                escapedQuote = false;
            } else if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    // Escaped quote within quoted field
                    currentField.append('"');
                    i++; // Skip the next quote
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                values.add(currentField.toString());
                currentField.setLength(0);
            } else {
                currentField.append(c);
            }
        }
        
        // Add the last field
        values.add(currentField.toString());
        
        return values.toArray(new String[0]);
    }
    
    /**
     * Saves employee data from a table model to CSV file.
     * This method is used by the GUI layer to persist employee data.
     * 
     * @param model The table model containing employee data
     * @param csvFilePath The path to the CSV file to save to
     * @throws Exception if saving fails
     */
    public void saveEmployeeDataToCSV(javax.swing.table.TableModel model, String csvFilePath) throws Exception {
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(csvFilePath))) {
            // Write column headers
            for (int j = 0; j < model.getColumnCount(); j++) {
                String header = model.getColumnName(j);
                if (header.contains(",") || header.contains("\"")) {
                    header = "\"" + header.replace("\"", "\"\"") + "\"";
                }
                bw.write(header);
                if (j < model.getColumnCount() - 1) bw.write(",");
            }
            bw.newLine();
            
            // Write data rows
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Object value = model.getValueAt(i, j);
                    String cell = (value != null) ? value.toString() : "";
                    
                    // Handle password column (last column)
                    if (j == model.getColumnCount() - 1 && !cell.isEmpty()) {
                        // Only hash if not already hashed (BCrypt format)
                        if (!OOP.HashUtil.isHashed(cell)) {
                            String hashedPassword = OOP.HashUtil.hashPassword(cell);
                            cell = hashedPassword;
                        }
                    }
                    
                    // Escape cell value
                    if (cell.contains(",") || cell.contains("\"") || cell.contains("\n")) {
                        cell = "\"" + cell.replace("\"", "\"\"") + "\"";
                    }
                    bw.write(cell);
                    if (j < model.getColumnCount() - 1) bw.write(",");
                }
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            throw new Exception("Failed to save data to CSV file: " + e.getMessage());
        }
    }
}
