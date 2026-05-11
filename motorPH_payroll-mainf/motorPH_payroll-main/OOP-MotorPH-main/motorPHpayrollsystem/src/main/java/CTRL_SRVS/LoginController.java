package CTRL_SRVS;

import DAO.EmployeeDAO;
import OOP.Admin;
import OOP.Employee;
import OOP.Finance;
import OOP.HR;
import OOP.User;
import javax.swing.*;

/**
 * Service layer for login business logic
 * Handles authentication and validation without GUI dependencies
 */
public class LoginController {
    
    /**
     * Validates login credentials and returns authentication result
     * @param email The email address
     * @param password The password
     * @return LoginResult containing authentication status and user data
     */
    public static LoginResult validateLogin(String email, String password) {
        System.out.println("[LOGIN STAGE 3] Checking admin/HR/finance credentials...");
        System.out.println("   Email: '" + email + "'");
        System.out.println("   Password length: " + password.length());

        // Validate input fields
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return new LoginResult(false, "Please enter both email and password.", null, null);
        }

        // Admin login
        boolean adminCheck = LoginService.checkCredentials("Admin", email, password);
        System.out.println("[LOGIN STAGE 3] Admin check result: " + adminCheck);
        if (adminCheck) {
            return new LoginResult(true, "Successfully Logged In as Admin!", new Admin(email, password), "Admin");
        }

        // HR login
        boolean hrCheck = LoginService.checkCredentials("HR", email, password);
        System.out.println("[LOGIN STAGE 3] HR check result: " + hrCheck);
        if (hrCheck) {
            return new LoginResult(true, "Successfully Logged In as HR!", new HR(email, password), "HR");
        }

        // Finance login
        boolean financeCheck = LoginService.checkCredentials("Finance", email, password);
        System.out.println("[LOGIN STAGE 3] Finance check result: " + financeCheck);
        if (financeCheck) {
            return new LoginResult(true, "Successfully Logged In as Finance!", new Finance(email, password), "Finance");
        }

        // Employee login
        System.out.println("[LOGIN STAGE 3] Proceeding to employee authentication...");
        ValidationService validationService = new ValidationService();
        Employee employee = validationService.authenticateEmployee(email, password);
        if (employee != null) {
            return new LoginResult(true, "Successfully Logged In as Employee!", employee, "Employee");
        }

        // Invalid credentials
        System.out.println("[LOGIN STAGE 3] Employee authentication failed - showing error");
        return new LoginResult(false, "Login Failed! Incorrect email or password.", null, null);
    }
    
    /**
     * Login result container class
     */
    public static class LoginResult {
        private final boolean success;
        private final String message;
        private final User user;
        private final String role;
        
        public LoginResult(boolean success, String message, User user, String role) {
            this.success = success;
            this.message = message;
            this.user = user;
            this.role = role;
        }
        
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public User getUser() { return user; }
        public String getRole() { return role; }
    }
}
