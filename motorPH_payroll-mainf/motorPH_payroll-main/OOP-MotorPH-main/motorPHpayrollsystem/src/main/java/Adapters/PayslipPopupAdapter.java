package Adapters;

import CTRL_SRVS.EmployeeService;
import CTRL_SRVS.PayrollService;
import OOP.Employee;
import OOP.SessionTimeoutManager;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 * Adapter class for PayslipPopup GUI.
 * Bridges between auto-generated GUI and service layer.
 */
public class PayslipPopupAdapter {
    private final EmployeeService employeeService;
    private final PayrollService payrollService;
    private String employeeID;
    
    public PayslipPopupAdapter(String employeeID) {
        this.employeeService = new EmployeeService();
        this.payrollService = new PayrollService();
        this.employeeID = employeeID;
    }
    
    /**
     * Get employee data for display in payslip.
     * @return employee data array or null if not found
     */
    public String[] getEmployeeData() {
        try {
            return employeeService.findEmployeeById(employeeID);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Populate payslip fields with employee data.
     * @param fields map of field names to components
     * @return true if successful, false otherwise
     */
    public boolean populatePayslipFields(Map<String, Component> fields) {
        try {
            String[] employeeData = getEmployeeData();
            if (employeeData == null || employeeData.length < 21) {
                return false;
            }
            
            // Populate fields with employee data
            if (fields.containsKey("employeeId")) {
                setFieldValue(fields.get("employeeId"), employeeData[0]);
            }
            if (fields.containsKey("employeeName")) {
                setFieldValue(fields.get("employeeName"), employeeData[1] + " " + employeeData[2]);
            }
            if (fields.containsKey("sss")) {
                setFieldValue(fields.get("sss"), employeeData[3]);
            }
            if (fields.containsKey("philHealth")) {
                setFieldValue(fields.get("philHealth"), employeeData[4]);
            }
            if (fields.containsKey("tin")) {
                setFieldValue(fields.get("tin"), employeeData[5]);
            }
            if (fields.containsKey("pagIBIG")) {
                setFieldValue(fields.get("pagIBIG"), employeeData[6]);
            }
            if (fields.containsKey("basicSalary")) {
                setFieldValue(fields.get("basicSalary"), employeeData[13]);
            }
            if (fields.containsKey("riceSubsidy")) {
                setFieldValue(fields.get("riceSubsidy"), employeeData[14]);
            }
            if (fields.containsKey("phoneAllowance")) {
                setFieldValue(fields.get("phoneAllowance"), employeeData[15]);
            }
            if (fields.containsKey("clothingAllowance")) {
                setFieldValue(fields.get("clothingAllowance"), employeeData[16]);
            }
            if (fields.containsKey("semiMonthlyRate")) {
                setFieldValue(fields.get("semiMonthlyRate"), employeeData[17]);
            }
            if (fields.containsKey("hourlyRate")) {
                setFieldValue(fields.get("hourlyRate"), employeeData[18]);
            }
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Compute payroll for the employee.
     * @param fields map of field names to components
     * @return calculated net pay or 0.0 if calculation fails
     */
    public double computePayroll(Map<String, Component> fields) {
        try {
            // Get employee data from fields
            double basicSalary = Double.parseDouble(getFieldValue(fields.get("basicSalary")));
            double riceSubsidy = Double.parseDouble(getFieldValue(fields.get("riceSubsidy")));
            double phoneAllowance = Double.parseDouble(getFieldValue(fields.get("phoneAllowance")));
            double clothingAllowance = Double.parseDouble(getFieldValue(fields.get("clothingAllowance")));
            
            // Create employee object for calculation using double values
            Employee employee = new Employee(
                employeeID,
                getFieldValue(fields.get("lastName")),
                getFieldValue(fields.get("firstName")),
                getFieldValue(fields.get("birthday")),
                getFieldValue(fields.get("status")),
                getFieldValue(fields.get("phone")),
                getFieldValue(fields.get("address")),
                basicSalary,
                Double.parseDouble(getFieldValue(fields.get("hourlyRate"))),
                riceSubsidy,
                phoneAllowance,
                clothingAllowance,
                getFieldValue(fields.get("sss")),
                getFieldValue(fields.get("pagIBIG")),
                getFieldValue(fields.get("philHealth")),
                getFieldValue(fields.get("tin")),
                getFieldValue(fields.get("email")),
                getFieldValue(fields.get("password"))
            );
            
            return payrollService.calculateNetPay(employee);
        } catch (Exception e) {
            return 0.0;
        }
    }
    
    /**
     * Validate employee data for payroll calculation.
     * @param fields map of field names to components
     * @return true if valid, false otherwise
     */
    public boolean validateEmployeeForPayroll(Map<String, Component> fields) {
        try {
            // Get employee data from fields
            double basicSalary = Double.parseDouble(getFieldValue(fields.get("basicSalary")));
            double riceSubsidy = Double.parseDouble(getFieldValue(fields.get("riceSubsidy")));
            double phoneAllowance = Double.parseDouble(getFieldValue(fields.get("phoneAllowance")));
            double clothingAllowance = Double.parseDouble(getFieldValue(fields.get("clothingAllowance")));
            
            // Create employee object for validation using double values
            Employee employee = new Employee(
                employeeID,
                getFieldValue(fields.get("lastName")),
                getFieldValue(fields.get("firstName")),
                getFieldValue(fields.get("birthday")),
                getFieldValue(fields.get("status")),
                getFieldValue(fields.get("phone")),
                getFieldValue(fields.get("address")),
                basicSalary,
                Double.parseDouble(getFieldValue(fields.get("hourlyRate"))),
                riceSubsidy,
                phoneAllowance,
                clothingAllowance,
                getFieldValue(fields.get("sss")),
                getFieldValue(fields.get("pagIBIG")),
                getFieldValue(fields.get("philHealth")),
                getFieldValue(fields.get("tin")),
                getFieldValue(fields.get("email")),
                getFieldValue(fields.get("password"))
            );
            
            return payrollService.validateEmployeeForPayroll(employee);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Format currency for display.
     * @param amount the amount to format
     * @return formatted currency string
     */
    public String formatCurrency(double amount) {
        return payrollService.formatCurrency(amount);
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