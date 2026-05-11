package OOP;

import GUI.*;
import javax.swing.*;
import java.io.*;

public class SystemIT extends User {
    public static final String EMPLOYEE_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "EmpData.csv";
    private static final String ADMIN_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "AdminLogin.csv";
    public static final String HR_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "HRLogin.csv";
    private static final String FINANCE_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "FinanceLogin.csv";
    public static final String ATTENDANCE_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "AttendanceRecords.csv";
    public static final String LEAVES_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "Leaves.csv";

    public SystemIT(String email, String password, String role) {
        super(email, password, role);
    }

    // uses the new architecture for the login.
    public static void validateLogin(String email, String password, JFrame loginFrame) {
        System.out.println("[LOGIN STAGE 2] Routing to LoginController");
        System.out.println("   Email: '" + email + "'");
        System.out.println("   Password length: " + password.length());
        
        // Use the new LoginController that returns a LoginResult
        CTRL_SRVS.LoginController.LoginResult result = CTRL_SRVS.LoginController.validateLogin(email, password);
        
        // Handle the result
        if (result.isSuccess()) {
            // Show success message
            JOptionPane.showMessageDialog(loginFrame, result.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Redirect to appropriate portal based on role
            redirectToPortal(result.getUser(), result.getRole(), loginFrame);
        } else {
            // Show error message
            JOptionPane.showMessageDialog(loginFrame, result.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Redirects user to appropriate portal based on role
     */
    private static void redirectToPortal(User user, String role, JFrame loginFrame) {
        try {
            JFrame portal = null;
            
            switch (role) {
                case "Admin":
                    portal = new AdminPortal();
                    break;
                case "HR":
                    portal = new HRPortal();
                    break;
                case "Finance":
                    portal = new FinancePortal();
                    break;
                case "Employee":
                    if (user instanceof Employee) {
                        portal = new EmployeePortal();
                        ((EmployeePortal) portal).fillEmployeeDetails((Employee) user);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(loginFrame, "Unknown role: " + role, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }
            
            if (portal != null) {
                portal.setVisible(true);
                loginFrame.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(loginFrame, "Error opening portal: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}