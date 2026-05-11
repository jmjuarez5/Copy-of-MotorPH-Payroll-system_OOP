package DAO;

import java.io.*;
import java.util.*;
import OOP.HashUtil;
import OOP.Employee;

public final class EmployeeDAO implements EmployeeDAOInterface, UserDAOInterface {
    private static final String CSV_FILE = "src/main/java/CSV/EmpData.csv";
    private final List<String[]> employeeData;

    public EmployeeDAO() {
        employeeData = new ArrayList<>();
        try {
            loadData();
        } catch (Exception ex) {
            System.getLogger(EmployeeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public String[][] loadData() throws Exception {
        System.out.println("PATH: " + new File(CSV_FILE).getAbsolutePath());
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            employeeData.clear();
            while ((line = br.readLine()) != null) {
                String[] values = parseCSVLine(line);
                employeeData.add(values);
            }
            return employeeData.toArray(String[][]::new);
        } catch (FileNotFoundException e) {
            throw new Exception("CSV file not found: " + CSV_FILE);
        }
    }
    
    /**
     * Parse a CSV line properly handling quoted fields
     */
    private String[] parseCSVLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    // Escaped quote
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
        
        return values.toArray(String[]::new);
    }

    @Override
    public String[][] loadEmployees() throws Exception {
        return loadData();
    }

    @Override
    public String[] findEmployeeById(String employeeId) {
        for (String[] employee : employeeData) {
            if (employee[0].equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public void addEmployee(String[] employeeData) throws Exception {
        this.employeeData.add(employeeData);
        saveToCSV();
    }
    
    /**
     * Creates a new employee record from Employee object
     * @param employee The employee to create
     * @throws Exception if creation fails
     */
    public void createEmployee(Employee employee) throws Exception {
        String[] employeeData = new String[21];
        employeeData[0] = employee.getEmployeeNumber();
        employeeData[1] = employee.getLastName();
        employeeData[2] = employee.getFirstName();
        employeeData[3] = ""; // Middle name not available in Employee class
        employeeData[4] = employee.getBirthday();
        employeeData[5] = employee.getContactNumber();
        employeeData[6] = ""; // Hire date not available in Employee class
        employeeData[7] = ""; // Department not available in Employee class
        employeeData[8] = ""; // Position not available in Employee class
        employeeData[9] = ""; // Supervisor not available in Employee class
        employeeData[10] = employee.getStatus();
        employeeData[11] = ""; // Salary rate not available in Employee class
        employeeData[12] = employee.getSssNumber();
        employeeData[13] = employee.getPhilHealthNumber();
        employeeData[14] = employee.getPagIbigNumber();
        employeeData[15] = employee.getTinNumber();
        employeeData[16] = ""; // Bank account not available in Employee class
        employeeData[17] = String.valueOf(employee.getBasicSalary());
        employeeData[18] = String.valueOf(employee.getRiceSubsidy());
        employeeData[19] = employee.getEmail();
        employeeData[20] = employee.getPassword();
        
        this.employeeData.add(employeeData);
        saveToCSV();
    }
    
    /**
     * Updates an existing employee record
     * @param employee The employee with updated information
     * @throws Exception if update fails
     */
    public void updateEmployee(Employee employee) throws Exception {
        for (int i = 0; i < employeeData.size(); i++) {
            String[] currentEmployee = employeeData.get(i);
            if (currentEmployee[0].equals(employee.getEmployeeNumber())) {
                currentEmployee[1] = employee.getLastName();
                currentEmployee[2] = employee.getFirstName();
                currentEmployee[3] = ""; // Middle name not available in Employee class
                currentEmployee[4] = employee.getBirthday();
                currentEmployee[5] = employee.getContactNumber();
                currentEmployee[6] = ""; // Hire date not available in Employee class
                currentEmployee[7] = ""; // Department not available in Employee class
                currentEmployee[8] = ""; // Position not available in Employee class
                currentEmployee[9] = ""; // Supervisor not available in Employee class
                currentEmployee[10] = employee.getStatus();
                currentEmployee[11] = ""; // Salary rate not available in Employee class
                currentEmployee[12] = employee.getSssNumber();
                currentEmployee[13] = employee.getPhilHealthNumber();
                currentEmployee[14] = employee.getPagIbigNumber();
                currentEmployee[15] = employee.getTinNumber();
                currentEmployee[16] = ""; // Bank account not available in Employee class
                currentEmployee[17] = String.valueOf(employee.getBasicSalary());
                currentEmployee[18] = String.valueOf(employee.getRiceSubsidy());
                currentEmployee[19] = employee.getEmail();
                currentEmployee[20] = employee.getPassword();
                
                saveToCSV();
                return;
            }
        }
        throw new Exception("Employee not found: " + employee.getEmployeeNumber());
    }
    
    /**
     * Gets all employees as Employee objects
     * @return List of Employee objects
     * @throws Exception if retrieval fails
     */
    public List<Employee> getAllEmployees() throws Exception {
        List<Employee> employees = new ArrayList<>();
        for (String[] data : employeeData) {
            if (data.length >= 21) {
                // Use the Employee constructor that accepts double values for compensation
                Employee employee = new Employee(
                    data[0], // employeeId
                    data[1], // lastName
                    data[2], // firstName
                    data[4], // birthday
                    data[10], // status
                    data[5], // contactNumber
                    data[8], // address (using position field as address)
                    parseDouble(data[17]), // basicSalary
                    parseDouble(data[18]), // hourlyRate
                    parseDouble(data[14]), // riceSubsidy
                    parseDouble(data[15]), // phoneAllowance
                    parseDouble(data[16]), // clothingAllowance
                    data[12], // sss
                    data[14], // pagIBIG
                    data[13], // philHealth
                    data[15], // tin
                    data[19], // email
                    data[20]  // password
                );
                
                employees.add(employee);
            }
        }
        return employees;
    }
    
    private double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) return 0.0;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    @Override
    public void updateEmployee(String employeeId, String[] newData) throws Exception {
        for (int i = 0; i < employeeData.size(); i++) {
            if (employeeData.get(i)[0].equals(employeeId)) {
                employeeData.set(i, newData);
                saveToCSV();
                return;
            }
        }
        throw new Exception("Employee not found: " + employeeId);
    }

    @Override
    public void deleteEmployee(String employeeId) throws Exception {
        boolean found = false;
        for (Iterator<String[]> iterator = employeeData.iterator(); iterator.hasNext();) {
            String[] employee = iterator.next();
            if (employee[0].equals(employeeId)) {
                iterator.remove();
                found = true;
                break;
            }
        }
        if (!found) {
            throw new Exception("Employee not found: " + employeeId);
        }
        saveToCSV();
    }

    @Override
    public void saveToCSV() throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (String[] employee : employeeData) {
                bw.write(String.join(",", employee));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new Exception("Error saving to CSV: " + e.getMessage());
        }
    }

    @Override
    public boolean validateCredentials(String email, String password) {
        for (String[] employee : employeeData) {
            if (employee.length > 20 && employee[19] != null && employee[19].trim().equals(email)) {
                String storedPassword = employee[20].trim();
                System.out.println("[EMPLOYEE DAO] Checking password for email: " + email);
                System.out.println("[EMPLOYEE DAO] Stored password: '" + storedPassword + "'");
                System.out.println("[EMPLOYEE DAO] Is stored password hashed: " + HashUtil.isHashed(storedPassword));
                
                // Check if the stored password is already hashed
                if (HashUtil.isHashed(storedPassword)) {
                    // Password is already hashed, use BCrypt comparison
                    boolean result = HashUtil.checkPassword(password, storedPassword);
                    System.out.println("[EMPLOYEE DAO] BCrypt comparison result: " + result);
                    return result;
                } else {
                    // Password is plaintext, use direct comparison for legacy data
                    boolean result = storedPassword.equals(password);
                    System.out.println("[EMPLOYEE DAO] Legacy comparison result: " + result);
                    return result;
                }
            }
        }
        return false;
    }
    
    /**
     * Loads employee data from CSV file into a 2D array.
     * This method is used by the service layer to provide data to the GUI.
     * 
     * @param csvFilePath The path to the CSV file
     * @return 2D array containing employee data
     * @throws Exception if file cannot be read
     */
    public String[][] loadEmployeeDataFromCSV(String csvFilePath) throws Exception {
        List<String[]> data = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstRow = true;

            while ((line = br.readLine()) != null) {
                if (isFirstRow) {
                    isFirstRow = false; // Skip the header row
                    continue;
                }
                
                String[] rowData = parseCSVLine(line);
                data.add(rowData);
            }
        } catch (IOException e) {
            throw new Exception("Failed to load employee data from CSV: " + e.getMessage());
        }
        
        return data.toArray(String[][]::new);
    }
    
    /**
     * Saves employee data to CSV file.
     * This method is used by the service layer to persist data from the GUI.
     * 
     * @param model The table model containing employee data
     * @param csvFilePath The path to the CSV file
     * @throws Exception if file cannot be written
     */
    public void saveEmployeeDataToCSV(javax.swing.table.TableModel model, String csvFilePath) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
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
                    // Handle password column (last column)
                    if (j == model.getColumnCount() - 1 && value != null && !value.toString().isEmpty()) {
                        String pwd = value.toString();
                        // Prevent empty or invalid passwords
                        if (pwd.trim().isEmpty()) {
                            throw new Exception("Password cannot be blank for row " + (i + 1));
                        }
                        // Only hash if not already hashed (BCrypt format)
                        if (!HashUtil.isHashed(pwd)) {
                            String hashedPassword = HashUtil.hashPassword(pwd);
                            ((javax.swing.table.DefaultTableModel) model).setValueAt(hashedPassword, i, j);
                            value = hashedPassword;
                        }
                    }
                    // Escape cell value
                    String cell = (value != null) ? value.toString() : "";
                    if (cell.contains(",") || cell.contains("\"") || cell.contains("\n")) {
                        cell = "\"" + cell.replace("\"", "\"\"") + "\"";
                    }
                    bw.write(cell);
                    if (j < model.getColumnCount() - 1) bw.write(",");
                }
                bw.newLine();
            }
        } catch (IOException e) {
            throw new Exception("Failed to save data to CSV file: " + e.getMessage());
        }
    }
}
