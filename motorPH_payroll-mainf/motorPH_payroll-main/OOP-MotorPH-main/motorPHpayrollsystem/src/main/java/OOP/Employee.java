package OOP;

import GUI.EmployeePortal;
import GUI.LogIn;
import javax.swing.table.TableModel;

public class Employee extends User {
    private EmployeeModel employeeModel;
    private CompensationModel compensationModel;
    private GovInfoModel govInfoModel;

    public Employee(String email, String password, String employeeNumber, String lastName, String firstName,
                    String birthday, String status, String contactNumber, String address, 
                    double basicSalary, double hourlyRate, double riceSubsidy, 
                    double phoneSubsidy, double clothingAllowance, 
                    String sssNumber, String pagIbigNumber, 
                    String philHealthNumber, String tinNumber) {
        
        super(email, password, "Employee");
        this.employeeModel = new EmployeeModel(employeeNumber, lastName, firstName, birthday, status, contactNumber, address);
        this.compensationModel = new CompensationModel(basicSalary, hourlyRate, riceSubsidy, phoneSubsidy, clothingAllowance);
        this.govInfoModel = new GovInfoModel(sssNumber, philHealthNumber, pagIbigNumber, tinNumber);
    }

    /**
     * Constructor for PayslipPopupAdapter that accepts double values for compensation fields.
     * This constructor is specifically designed to handle the case where compensation values
     * are already in double format from GUI components.
     */
    public Employee(String employeeId, String lastName, String firstName, String birthday, 
                   String status, String phone, String address, 
                   double basicSalary, double hourlyRate, double riceSubsidy, 
                   double phoneAllowance, double clothingAllowance,
                   String sss, String pagIBIG, String philHealth, String tin,
                   String email, String password) {
        
        super(email, password, "Employee");
        this.employeeModel = new EmployeeModel(employeeId, lastName, firstName, birthday, status, phone, address);
        this.compensationModel = new CompensationModel(basicSalary, hourlyRate, riceSubsidy, phoneAllowance, clothingAllowance);
        this.govInfoModel = new GovInfoModel(sss, philHealth, pagIBIG, tin);
    }

    public Employee(TableModel model, int row) {
        super("", "", "Employee");
        this.employeeModel = new EmployeeModel(
            getValue(model, row, 0),
            getValue(model, row, 1),
            getValue(model, row, 2),
            getValue(model, row, 7),
            getValue(model, row, 10),
            getValue(model, row, 9),
            getValue(model, row, 8)
        );
        this.compensationModel = new CompensationModel(
            parseDouble(model, row, 13),
            parseDouble(model, row, 18),
            parseDouble(model, row, 14),
            parseDouble(model, row, 15),
            parseDouble(model, row, 16)
        );
        this.govInfoModel = new GovInfoModel(
            getValue(model, row, 3),
            getValue(model, row, 4),
            getValue(model, row, 6),
            getValue(model, row, 5)
        );
    }

    private String getValue(TableModel model, int row, int col) {
        Object value = model.getValueAt(row, col);
        return value != null ? value.toString() : "";
    }

    private double parseDouble(TableModel model, int row, int col) {
        try {
            return Double.parseDouble(getValue(model, row, col));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public String getEmployeeNumber() { return employeeModel.getEmployeeNumber(); }
    public String getLastName() { return employeeModel.getLastName(); }
    public String getFirstName() { return employeeModel.getFirstName(); }
    public String getBirthday() { return employeeModel.getBirthday(); }
    public String getStatus() { return employeeModel.getStatus(); }
    public String getContactNumber() { return employeeModel.getContactNumber(); }
    public String getAddress() { return employeeModel.getAddress(); }
    public double getBasicSalary() { return compensationModel.getBasicSalary(); }
    public double getHourlyRate() { return compensationModel.getHourlyRate(); }
    public double getRiceSubsidy() { return compensationModel.getRiceSubsidy(); }
    public double getPhoneSubsidy() { return compensationModel.getPhoneSubsidy(); }
    public double getPhoneAllowance() { return compensationModel.getPhoneSubsidy(); } // Alias for phoneSubsidy
    public double getClothingAllowance() { return compensationModel.getClothingAllowance(); }
    public String getSssNumber() { return govInfoModel.getSssNumber(); }
    public String getPagIbigNumber() { return govInfoModel.getPagIbigNumber(); }
    public String getPhilHealthNumber() { return govInfoModel.getPhilHealthNumber(); }
    public String getTinNumber() { return govInfoModel.getTinNumber(); }
    
    // Additional getter methods for PayslipPopup
    public String getSss() { return govInfoModel.getSssNumber(); }
    public String getPagIBIG() { return govInfoModel.getPagIbigNumber(); }
    public String getPhilHealth() { return govInfoModel.getPhilHealthNumber(); }
    public String getTin() { return govInfoModel.getTinNumber(); }
    
    // Additional getter methods for EmployeeService
    public String getPosition() { return ""; } // Default implementation
    public String getImmediateSupervisor() { return ""; } // Default implementation
    public double getMonthlyRate() { return getBasicSalary() / 2; } // Semi-monthly rate calculation

    public String[] getDetails() {
        String[] employeeDetails = employeeModel.getDetails();
        
        // Combine all details: employee (6) + compensation (5) + govInfo (4) = 15 elements
        // Plus employee number = 16 total
        String[] allDetails = new String[16];
        
        // Employee details (0-5)
        System.arraycopy(employeeDetails, 0, allDetails, 0, employeeDetails.length);
        
        // Compensation details (6-10)
        allDetails[6] = String.valueOf(compensationModel.getBasicSalary());
        allDetails[7] = String.valueOf(compensationModel.getHourlyRate());
        allDetails[8] = String.valueOf(compensationModel.getRiceSubsidy());
        allDetails[9] = String.valueOf(compensationModel.getPhoneSubsidy());
        allDetails[10] = String.valueOf(compensationModel.getClothingAllowance());
        
        // Gov info details (11-14)
        allDetails[11] = govInfoModel.getSssNumber();
        allDetails[12] = govInfoModel.getPagIbigNumber();
        allDetails[13] = govInfoModel.getPhilHealthNumber();
        allDetails[14] = govInfoModel.getTinNumber();
        
        // Employee number (15)
        allDetails[15] = employeeModel.getEmployeeNumber();
        
        return allDetails;
    }
}
