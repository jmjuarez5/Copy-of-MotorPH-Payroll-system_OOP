package CTRL_SRVS;

import DAO.EmployeeDAO;
import OOP.Employee;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service class for authentication and employee validation.
 * Moves business logic from controller to service layer.
 */
public class ValidationService {
    private static final Logger LOGGER = Logger.getLogger(ValidationService.class.getName());
    private EmployeeDAO employeeDAO;
    
    public ValidationService() {
        this.employeeDAO = new EmployeeDAO();
    }
    
    /**
     * Authenticate employee and return employee details if successful.
     * 
     * @param email Employee email address
     * @param password Plain text password to verify
     * @return Employee object if authentication successful, null otherwise
     */
    public Employee authenticateEmployee(String email, String password) {
        System.out.println("[LOGIN STAGE 4] Starting employee authentication");
        System.out.println("   Email: '" + email + "'");
        System.out.println("   Password length: " + password.length());

        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Authentication failed: empty credentials");
            System.out.println("[LOGIN STAGE 4] Authentication failed: empty credentials");
            return null;
        }
        
        try {
            System.out.println("[LOGIN STAGE 4] Loading employee data...");
            String[][] allEmployees = employeeDAO.loadEmployees();
            System.out.println("[LOGIN STAGE 4] Total employees loaded: " + allEmployees.length);
            
            for (int i = 0; i < allEmployees.length; i++) {
                String[] employeeData = allEmployees[i];
                System.out.println("[LOGIN STAGE 4] Checking employee " + (i+1) + ":");
                System.out.println("   Array length: " + employeeData.length);
                System.out.println("   Email index 19: '" + (employeeData.length > 19 ? employeeData[19] : "N/A") + "'");
                System.out.println("   Password index 20: '" + (employeeData.length > 20 ? employeeData[20] : "N/A") + "'");
                
                if (employeeData.length >= 21 &&
                    employeeData[19] != null &&
                    employeeData[20] != null &&
                    employeeData[19].trim().equalsIgnoreCase(email.trim())) {
                    
                    System.out.println("[LOGIN STAGE 4] Email match found!");
                    String storedPassword = employeeData[20].trim();
                    System.out.println("   Stored password: '" + storedPassword + "'");
                    System.out.println("   Password format check: " + OOP.HashUtil.isHashed(storedPassword));
                    
                    boolean passwordMatch;
                    if (OOP.HashUtil.isHashed(storedPassword)) {
                        // Password is already hashed, use BCrypt comparison
                        passwordMatch = OOP.HashUtil.checkPassword(password, storedPassword);
                        System.out.println("[LOGIN STAGE 4] BCrypt comparison: " + passwordMatch);
                    } else {
                        // Password is plaintext, use direct comparison for legacy data
                        passwordMatch = storedPassword.equals(password);
                        System.out.println("[LOGIN STAGE 4] Legacy comparison: " + passwordMatch);
                    }
                    System.out.println("[LOGIN STAGE 4] Password verification: " + passwordMatch);
                    
                    if (passwordMatch) {
                        System.out.println("[LOGIN STAGE 4] Employee authentication SUCCESSFUL!");
                        return createEmployeeFromData(employeeData);
                    } else {
                        System.out.println("[LOGIN STAGE 4] Password mismatch for email match");
                    }
                } else {
                    System.out.println("   No email match - continuing search");
                }
            }
            System.out.println("[LOGIN STAGE 4] No matching employee found");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during employee authentication", e);
            System.out.println("[LOGIN STAGE 4] Exception during authentication: " + e.getMessage());
        }
        return null;
    }
    
    private Employee createEmployeeFromData(String[] employeeData) {
        return new Employee(
            employeeData[19], employeeData[20],  // email, password
            employeeData[0], employeeData[1], employeeData[2],  // empNum, lastName, firstName
            employeeData[7], employeeData[10],  // birthday, position
            employeeData[9], employeeData[8],  // phoneNum, status
            parseDoubleSafe(employeeData[13]),  // basicSalary
            parseDoubleSafe(employeeData[18]),  // hourlyRate
            parseDoubleSafe(employeeData[14]),  // riceSubsidy
            parseDoubleSafe(employeeData[15]),  // phoneAllowance
            parseDoubleSafe(employeeData[16]),  // clothingAllowance
            employeeData[3], employeeData[6], employeeData[4], employeeData[5]  // sss, pagIBIG, philHealth, tin
        );
    }
    
    private double parseDoubleSafe(String value) {
        if (value == null || value.trim().isEmpty()) return 0.0;
        try {
            String cleanValue = value.replace(",", "").trim();
            return Double.parseDouble(cleanValue);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Failed to parse double value: '" + value + "'", e);
            return 0.0;
        }
    }
    
    public boolean validateEmployeeIdUniqueness(String employeeId) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            return false;
        }
        
        try {
            String[][] allEmployees = employeeDAO.loadEmployees();
            for (int i = 0; i < allEmployees.length; i++) {
                String[] employeeData = allEmployees[i];
                if (employeeData.length > 0 && employeeData[0] != null && 
                    employeeData[0].trim().equals(employeeId.trim())) {
                    return false; // Employee ID already exists
                }
            }
            return true; // Employee ID is unique
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error validating employee ID uniqueness", e);
            return false;
        }
    }
}