package CTRL_SRVS;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import DAO.EmployeeDAO;
import DAO.EmployeeDAOInterface;
import OOP.Employee;
import OOP.HashUtil;

/**
 * Service class for password management operations.
 * Handles password generation, hashing, and employee password operations.
 */
public class PasswordService {

    private EmployeeDAOInterface employeeDAO;
    private SecureRandom random;

    public PasswordService() {
        this.employeeDAO = new EmployeeDAO();
        this.random = new SecureRandom();
    }

    // -------------------------------------------------------------------------
    // PASSWORD GENERATION
    // -------------------------------------------------------------------------

    /**
     * Generates a system password for an employee.
     * Format: EMP{EmployeeID}{4 random digits}
     */
    public String generatePassword(String employeeId) {
        int randomDigits = 1000 + random.nextInt(9000);
        return "EMP" + employeeId.trim() + randomDigits;
    }

    public String generatePassword(Employee employee) {
        return generatePassword(employee.getEmployeeNumber());
    }

    // -------------------------------------------------------------------------
    // HASHING / VALIDATION
    // -------------------------------------------------------------------------

    public String hashPassword(String password) {
        return HashUtil.hashPassword(password);
    }

    public boolean validatePassword(String password, String hashedPassword) {
        return HashUtil.checkPassword(password, hashedPassword);
    }

    public boolean isPasswordHashed(String password) {
        return HashUtil.isHashed(password);
    }

    // -------------------------------------------------------------------------
    // EMPLOYEE PASSWORD OPERATIONS
    // -------------------------------------------------------------------------

    /**
     * Resets the password for an existing employee.
     * employeeId is trimmed so whitespace from CSV-loaded table cells
     * never causes a "not found" failure in the DAO lookup.
     *
     * @param employeeId The ID of the employee whose password is being reset
     * @return The new plain-text password (show to admin once, then discard)
     * @throws Exception if the employee is not found or the update fails
     */
    public String resetEmployeePassword(String employeeId) throws Exception {
        String cleanId = (employeeId != null) ? employeeId.trim() : "";

        String[] employeeData = employeeDAO.findEmployeeById(cleanId);
        if (employeeData == null) {
            throw new Exception("Employee not found: " + cleanId);
        }

        String newPassword    = generatePassword(cleanId);
        String hashedPassword = hashPassword(newPassword);

        // Grow the array if the CSV row is shorter than expected
        if (employeeData.length <= 20) {
            employeeData = Arrays.copyOf(employeeData, 21);
        }
        employeeData[20] = hashedPassword;

        employeeDAO.updateEmployee(cleanId, employeeData);

        return newPassword;
    }

    /**
     * Gets the current hashed password for an employee.
     * employeeId is trimmed before the DAO lookup.
     */
    public String getEmployeePassword(String employeeId) {
        try {
            String cleanId = (employeeId != null) ? employeeId.trim() : "";
            String[] employeeData = employeeDAO.findEmployeeById(cleanId);
            if (employeeData != null && employeeData.length > 20) {
                return employeeData[20];
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Creates a new employee with a system-generated password.
     * Returns the plain-text password for one-time display to the admin.
     */
    public String createEmployeeWithPassword(Employee employee) throws Exception {
        return generatePassword(employee);
    }

    /**
     * Validates employee login credentials.
     */
    public boolean validateEmployeeLogin(String employeeId, String password) {
        String storedPassword = getEmployeePassword(employeeId);
        if (storedPassword == null) return false;
        return validatePassword(password, storedPassword);
    }

    /**
     * Checks if an employee has a valid BCrypt-hashed password on record.
     */
    public boolean hasValidPassword(String employeeId) {
        String password = getEmployeePassword(employeeId);
        return password != null && isPasswordHashed(password);
    }

    /**
     * Returns all employees with password status information.
     */
    public List<Employee> getAllEmployeesWithPasswordStatus() {
        try {
            String[][] data = employeeDAO.loadEmployees();
            List<Employee> employees = new ArrayList<>();
            for (int i = 1; i < data.length; i++) { // skip header row
                String[] d = data[i];
                if (d.length >= 21) {
                    employees.add(new Employee(
                        d[0], d[1], d[2], d[4], d[10], d[5], d[8],
                        parseDouble(d[17]), parseDouble(d[18]),
                        parseDouble(d[14]), parseDouble(d[15]), parseDouble(d[16]),
                        d[12], d[14], d[13], d[15], d[19], d[20]
                    ));
                }
            }
            return employees;
        } catch (Exception e) {
            return List.of();
        }
    }

    // -------------------------------------------------------------------------
    // HELPERS
    // -------------------------------------------------------------------------

    private double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) return 0.0;
        try { return Double.parseDouble(value.trim()); }
        catch (NumberFormatException e) { return 0.0; }
    }
}