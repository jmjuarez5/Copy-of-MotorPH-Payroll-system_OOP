package CTRL_SRVS;

import OOP.Employee;
import OOP.ValidationRule;
import OOP.InputValidator;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Oh God, Math.
 */
public class PayrollService {
    
    /**
     * Calculates the net pay for an employee.
     * @param employee The employee to calculate payroll for
     * @return The calculated net pay
     */
    public double calculateNetPay(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        
        return calculateNetPay(
            employee.getBasicSalary(),
            employee.getRiceSubsidy(),
            employee.getPhoneAllowance(),
            employee.getClothingAllowance(),
            employee.getHourlyRate()
        );
    }
    
    /**
     * Calculate net pay based on employee compensation components.
     * 
     * @param basicSalary Basic monthly salary
     * @param riceSubsidy Rice subsidy allowance
     * @param phoneAllowance Phone allowance
     * @param clothingAllowance Clothing allowance
     * @param hourlyRate Hourly rate for overtime calculations
     * @return Net pay after deductions
     */
    public double calculateNetPay(double basicSalary, double riceSubsidy, 
                                 double phoneAllowance, double clothingAllowance, 
                                 double hourlyRate) {
        // Calculate gross pay (basic + allowances)
        double grossPay = basicSalary + riceSubsidy + phoneAllowance + clothingAllowance;
        
        // Calculate deductions (SSS, PhilHealth, Pag-IBIG, Withholding Tax)
        double sssContribution = calculateSSSContribution(basicSalary);
        double philHealthContribution = calculatePhilHealthContribution(basicSalary);
        double pagIBIGContribution = calculatePagIBIGContribution(basicSalary);
        double withholdingTax = calculateWithholdingTax(basicSalary);
        
        double totalDeductions = sssContribution + philHealthContribution + pagIBIGContribution + withholdingTax;
        
        // Calculate net pay
        double netPay = grossPay - totalDeductions;
        
        return Math.max(0, netPay); // Ensure non-negative result
    }
    
    /**
     * Validates employee data for payroll calculation.
     * @param employee The employee to validate
     * @return true if valid for payroll calculation, false otherwise
     */
    public boolean validateEmployeeForPayroll(Employee employee) {
        // Check if required fields are present and valid
        if (employee.getEmployeeNumber() == null || employee.getEmployeeNumber().trim().isEmpty()) {
            return false;
        }
        if (employee.getBasicSalary() <= 0) {
            return false;
        }
        if (employee.getHourlyRate() <= 0) {
            return false;
        }
        return true;
    }
    
    /**
     * Validates payroll form fields.
     * @param fields Map of field names to validation rules
     * @return null if valid, error message if invalid
     */
    public String validatePayrollFields(Map<javax.swing.JTextField, ValidationRule> fields) {
        return InputValidator.validate(fields);
    }
    
    /**
     * Creates a map of validation rules for payroll fields.
     * @param basicSalaryField Basic salary field
     * @param riceSubsidyField Rice subsidy field
     * @param phoneAllowanceField Phone allowance field
     * @param clothingAllowanceField Clothing allowance field
     * @param hourlyRateField Hourly rate field
     * @return Map of fields to validation rules
     */
    public Map<javax.swing.JTextField, ValidationRule> createPayrollValidationRules(
            javax.swing.JTextField basicSalaryField,
            javax.swing.JTextField riceSubsidyField,
            javax.swing.JTextField phoneAllowanceField,
            javax.swing.JTextField clothingAllowanceField,
            javax.swing.JTextField hourlyRateField) {
        
        Map<javax.swing.JTextField, ValidationRule> rules = new HashMap<>();
        
        // All payroll fields should be non-negative numbers
        rules.put(basicSalaryField, ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(riceSubsidyField, ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(phoneAllowanceField, ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(clothingAllowanceField, ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(hourlyRateField, ValidationRule.DECIMAL_NON_NEGATIVE);
        
        return rules;
    }
    
    /**
     * Formats a monetary amount as currency with proper separators.
     * @param amount The amount to format
     * @return Formatted currency string with peso symbol and comma separators
     */
    public String formatCurrency(double amount) {
        return String.format("₱%,.2f", amount);
    }
    
    
    /**
     * Calculate SSS contribution based on salary brackets.
     */
    private double calculateSSSContribution(double basicSalary) {
        // SSS contribution calculation based on current SSS tables
        // This is a simplified version - real implementation would use official SSS tables
        if (basicSalary <= 4250) return 180.00;
        if (basicSalary <= 4749.99) return 202.50;
        if (basicSalary <= 5249.99) return 225.00;
        if (basicSalary <= 5749.99) return 247.50;
        if (basicSalary <= 6249.99) return 270.00;
        if (basicSalary <= 6749.99) return 292.50;
        if (basicSalary <= 7249.99) return 315.00;
        if (basicSalary <= 7749.99) return 337.50;
        if (basicSalary <= 8249.99) return 360.00;
        if (basicSalary <= 8749.99) return 382.50;
        if (basicSalary <= 9249.99) return 405.00;
        if (basicSalary <= 9749.99) return 427.50;
        if (basicSalary <= 10249.99) return 450.00;
        if (basicSalary <= 10749.99) return 472.50;
        if (basicSalary <= 11249.99) return 495.00;
        if (basicSalary <= 11749.99) return 517.50;
        if (basicSalary <= 12249.99) return 540.00;
        if (basicSalary <= 12749.99) return 562.50;
        if (basicSalary <= 13249.99) return 585.00;
        if (basicSalary <= 13749.99) return 607.50;
        if (basicSalary <= 14249.99) return 630.00;
        if (basicSalary <= 14749.99) return 652.50;
        if (basicSalary <= 15249.99) return 675.00;
        if (basicSalary <= 15749.99) return 697.50;
        if (basicSalary <= 16249.99) return 720.00;
        if (basicSalary <= 16749.99) return 742.50;
        if (basicSalary <= 17249.99) return 765.00;
        if (basicSalary <= 17749.99) return 787.50;
        if (basicSalary <= 18249.99) return 810.00;
        if (basicSalary <= 18749.99) return 832.50;
        if (basicSalary <= 19249.99) return 855.00;
        if (basicSalary <= 19749.99) return 877.50;
        return 900.00; // Maximum contribution for salaries above 19,750
    }
    
    /**
     * Calculate PhilHealth contribution (2% of salary, employee share is 1%).
     */
    private double calculatePhilHealthContribution(double basicSalary) {
        return basicSalary * 0.01; // 1% employee share
    }
    
    /**
     * Calculate Pag-IBIG contribution (1% of salary).
     */
    private double calculatePagIBIGContribution(double basicSalary) {
        return basicSalary * 0.01; // 1% employee share
    }
    
    
    /**
     * Calculate working hours from a JTable containing attendance data.
     * 
     * @param table JTable with attendance data (columns: Employee #, Date, Time In, Time Out)
     * @return Total working hours calculated from the table
     */
    public double calculateWorkingHoursFromTable(javax.swing.JTable table) {
        double totalHours = 0.0;
        int rowCount = table.getRowCount();
        
        for (int i = 0; i < rowCount; i++) {
            try {
                String timeInStr = table.getValueAt(i, 2).toString().trim();
                String timeOutStr = table.getValueAt(i, 3).toString().trim();
                
                if (!timeInStr.isEmpty() && !timeOutStr.isEmpty()) {
                    double hours = calculateHoursWorked(timeInStr, timeOutStr);
                    totalHours += hours;
                }
            } catch (Exception e) {
                // Skip invalid rows
                continue;
            }
        }
        
        return totalHours;
    }
    
    /**
     * Calculate working hours from a CSV file containing attendance data.
     * 
     * @param csvFilePath Path to the CSV file
     * @return Total working hours calculated from the CSV
     */
    public double calculateWorkingHoursFromCSV(String csvFilePath) {
        double totalHours = 0.0;
        java.io.BufferedReader br = null;
        
        try {
            br = new java.io.BufferedReader(new java.io.FileReader(csvFilePath));
            String line;
            boolean isFirstRow = true;
            
            while ((line = br.readLine()) != null) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue; // Skip header
                }
                
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String timeInStr = parts[2].trim();
                    String timeOutStr = parts[3].trim();
                    
                    if (!timeInStr.isEmpty() && !timeOutStr.isEmpty()) {
                        double hours = calculateHoursWorked(timeInStr, timeOutStr);
                        totalHours += hours;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return totalHours;
    }
    
    /**
     * Calculate hours worked between two time strings.
     * 
     * @param timeInStr Time in string (HH:mm format)
     * @param timeOutStr Time out string (HH:mm format)
     * @return Hours worked as double
     */
    private double calculateHoursWorked(String timeInStr, String timeOutStr) {
        try {
            java.time.LocalTime timeIn = java.time.LocalTime.parse(timeInStr);
            java.time.LocalTime timeOut = java.time.LocalTime.parse(timeOutStr);
            
            long minutes = java.time.Duration.between(timeIn, timeOut).toMinutes();
            return minutes / 60.0;
        } catch (Exception e) {
            return 0.0;
        }
    }
    
    /**
     * Calculate total deductions (SSS + PhilHealth + Pag-IBIG).
     * 
     * @param sss SSS contribution
     * @param philHealth PhilHealth contribution
     * @param pagIbig Pag-IBIG contribution
     * @return Total deductions
     */
    public double calculateTotalDeductions(double sss, double philHealth, double pagIbig) {
        return sss + philHealth + pagIbig;
    }
    
    /**
     * Calculate taxable income (basic salary - total deductions).
     * 
     * @param basicSalary Basic salary
     * @param totalDeductions Total deductions
     * @return Taxable income
     */
    public double calculateTaxableIncome(double basicSalary, double totalDeductions) {
        return basicSalary - totalDeductions;
    }
    
    /**
     * Calculate withholding tax based on taxable income.
     * 
     * @param taxableIncome Taxable income
     * @return Withholding tax amount
     */
    public double calculateWithholdingTax(double taxableIncome) {
        // Simplified tax calculation
        if (taxableIncome <= 10000) {
            return taxableIncome * 0.05;
        } else if (taxableIncome <= 30000) {
            return 500 + (taxableIncome - 10000) * 0.10;
        } else if (taxableIncome <= 70000) {
            return 2500 + (taxableIncome - 30000) * 0.15;
        } else if (taxableIncome <= 140000) {
            return 8500 + (taxableIncome - 70000) * 0.20;
        } else if (taxableIncome <= 250000) {
            return 22500 + (taxableIncome - 140000) * 0.25;
        } else if (taxableIncome <= 500000) {
            return 50000 + (taxableIncome - 250000) * 0.30;
        } else {
            return 125000 + (taxableIncome - 500000) * 0.35;
        }
    }
    
    /**
     * Calculate gross pay (basic salary + allowances).
     * 
     * @param basicSalary Basic salary
     * @param taxableIncome Taxable income
     * @return Gross pay
     */
    public double calculateGrossPay(double basicSalary, double taxableIncome) {
        // For this implementation, gross pay is basic salary
        // In a real system, this would include allowances and overtime
        return basicSalary;
    }
    
    /**
     * Calculate net pay (gross pay - total deductions - tax).
     * 
     * @param grossPay Gross pay
     * @param totalDeductions Total deductions
     * @param withholdingTax Withholding tax
     * @return Net pay
     */
    public double calculateNetPay(double grossPay, double totalDeductions, double withholdingTax) {
        return grossPay - totalDeductions - withholdingTax;
    }
    
    /**
     * Result class to hold all payroll calculation results.
     */
    public static class PayrollResult {
        private final double workingHours;
        private final double totalDeductions;
        private final double taxableIncome;
        private final double withholdingTax;
        private final double grossPay;
        private final double netPay;
        
        public PayrollResult(double workingHours, double totalDeductions, double taxableIncome,
                           double withholdingTax, double grossPay, double netPay) {
            this.workingHours = workingHours;
            this.totalDeductions = totalDeductions;
            this.taxableIncome = taxableIncome;
            this.withholdingTax = withholdingTax;
            this.grossPay = grossPay;
            this.netPay = netPay;
        }
        
        public double getWorkingHours() { return workingHours; }
        public double getTotalDeductions() { return totalDeductions; }
        public double getTaxableIncome() { return taxableIncome; }
        public double getWithholdingTax() { return withholdingTax; }
        public double getGrossPay() { return grossPay; }
        public double getNetPay() { return netPay; }
    }
    
    /**
     * Calculate complete payroll from primitive values.
     * This method encapsulates all payroll calculation logic.
     * 
     * @param basicSalary Basic salary
     * @param sss SSS contribution
     * @param philHealth PhilHealth contribution
     * @param pagIbig Pag-IBIG contribution
     * @param workingHours Working hours (if manually entered, otherwise 0)
     * @param useManualHours Whether to use manual hours or calculate from table
     * @param workHoursTable JTable containing attendance data (can be null if useManualHours is true)
     * @return PayrollResult containing all calculated values
     */
    public PayrollResult calculatePayrollFromValues(double basicSalary, double sss, double philHealth,
                                                    double pagIbig, double workingHours, boolean useManualHours,
                                                    javax.swing.JTable workHoursTable) {
        // Calculate working hours if not manually overridden
        double calculatedWorkingHours = workingHours;
        if (!useManualHours && workHoursTable != null) {
            calculatedWorkingHours = calculateWorkingHoursFromTable(workHoursTable);
        }
        
        // Calculate payroll values
        double totalDeductions = calculateTotalDeductions(sss, philHealth, pagIbig);
        double taxableIncome = calculateTaxableIncome(basicSalary, totalDeductions);
        double withholdingTax = calculateWithholdingTax(taxableIncome);
        double grossPay = calculateGrossPay(basicSalary, taxableIncome);
        double netPay = calculateNetPay(grossPay, totalDeductions, withholdingTax);
        
        return new PayrollResult(calculatedWorkingHours, totalDeductions, taxableIncome,
                               withholdingTax, grossPay, netPay);
    }
    
}
