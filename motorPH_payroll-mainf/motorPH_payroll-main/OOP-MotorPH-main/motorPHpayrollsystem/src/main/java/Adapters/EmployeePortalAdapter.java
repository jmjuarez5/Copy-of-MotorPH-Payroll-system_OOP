package Adapters;

import CTRL_SRVS.EmployeeService;
import OOP.Employee;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 * Adapter class for EmployeePortal GUI.
 * Bridges between auto-generated GUI and service layer.
 */
public class EmployeePortalAdapter {
    private final EmployeeService employeeService;
    
    public EmployeePortalAdapter() {
        this.employeeService = new EmployeeService();
    }
    
    /**
     * Get employee details for display.
     * @param employeeId the employee ID
     * @return employee object or null if not found
     */
    public Employee getEmployeeDetails(String employeeId) {
        try {
            return employeeService.getEmployeeDetails(employeeId);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Fill employee details into GUI components.
     * @param employee the employee object
     * @param fields map of field names to components
     * @return true if successful, false otherwise
     */
    public boolean fillEmployeeDetails(Employee employee, Map<String, Component> fields) {
        if (employee == null) {
            return false;
        }
        
        try {
            String[] details = employee.getDetails();
            
            // Map field names to component values
            String[] fieldNames = {
                "lastName", "firstName", "birthday", "status", "contact", 
                "address", "basicSalary", "hourlyRate", "riceSubsidy", 
                "phoneSubsidy", "clothingAllowance", "sss", "pagIBIG", 
                "philHealth", "tin", "employeeId"
            };
            
            for (int i = 0; i < fieldNames.length && i < details.length; i++) {
                if (fields.containsKey(fieldNames[i])) {
                    setFieldValue(fields.get(fieldNames[i]), details[i]);
                }
            }
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Validate employee data.
     * @param fields map of field names to components
     * @return true if valid, false otherwise
     */
    public boolean validateEmployeeData(Map<String, Component> fields) {
        // Basic validation - check if required fields are filled
        String[] requiredFields = {"employeeId", "lastName", "firstName", "status"};
        
        for (String fieldName : requiredFields) {
            if (fields.containsKey(fieldName)) {
                String value = getFieldValue(fields.get(fieldName));
                if (value == null || value.trim().isEmpty()) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Set field value based on component type.
     * @param component the component to set
     * @param value the value to set
     */
    private void setFieldValue(Component component, String value) {
        if (component instanceof javax.swing.JTextField) {
            ((javax.swing.JTextField) component).setText(value);
        } else if (component instanceof javax.swing.JTextPane) {
            ((javax.swing.JTextPane) component).setText(value);
        }
    }
    
    /**
     * Get field value based on component type.
     * @param component the component to get value from
     * @return field value as string
     */
    private String getFieldValue(Component component) {
        if (component instanceof javax.swing.JTextField) {
            return ((javax.swing.JTextField) component).getText().trim();
        } else if (component instanceof javax.swing.JTextPane) {
            return ((javax.swing.JTextPane) component).getText().trim();
        } else if (component instanceof javax.swing.JPasswordField) {
            return new String(((javax.swing.JPasswordField) component).getPassword());
        }
        return "";
    }
    
    /**
     * Show error message to user.
     * @param message the error message to display
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Show success message to user.
     * @param message the success message to display
     */
    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}