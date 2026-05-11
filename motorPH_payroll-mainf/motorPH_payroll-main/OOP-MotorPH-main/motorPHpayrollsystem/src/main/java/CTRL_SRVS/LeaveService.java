package CTRL_SRVS;

import DAO.LeaveDAO;
import DAO.LeaveDAOInterface;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LeaveService {
    private LeaveDAOInterface leaveDAO;
    
    public LeaveService() {
        this.leaveDAO = new LeaveDAO();
    }
    
    public LeaveService(LeaveDAOInterface leaveDAO) {
        this.leaveDAO = leaveDAO;
    }
    
    /**
     * Submit a leave request for an employee with validation
     * @param employeeNumber The employee number
     * @param leaveType The type of leave (VL, SL, PL, EL)
     * @param startDate Start date in YYYY-MM-DD format
     * @param endDate End date in YYYY-MM-DD format
     * @return true if successful, false otherwise
     */
    public boolean submitLeaveRequest(String employeeNumber, String leaveType, String startDate, String endDate) {
        // Validate input data
        String validationError = validateLeaveRequest(leaveType, startDate, endDate, employeeNumber);
        if (!validationError.isEmpty()) {
            throw new IllegalArgumentException(validationError);
        }
        
        // Validate date format and logic
        String dateError = validateDates(startDate, endDate);
        if (!dateError.isEmpty()) {
            throw new IllegalArgumentException(dateError);
        }
        
        return leaveDAO.submitLeaveRequest(employeeNumber, leaveType, startDate, endDate, "Pending");
    }
    
    /**
     * Approve a leave request
     * @param employeeNumber The employee number
     * @param leaveType The type of leave
     * @param startDate Start date
     * @param endDate End date
     * @return true if successful, false otherwise
     */
    public boolean approveLeaveRequest(String employeeNumber, String leaveType, String startDate, String endDate) {
        return updateLeaveStatus(employeeNumber, leaveType, startDate, endDate, "Approved");
    }
    
    /**
     * Reject a leave request
     * @param employeeNumber The employee number
     * @param leaveType The type of leave
     * @param startDate Start date
     * @param endDate End date
     * @return true if successful, false otherwise
     */
    public boolean rejectLeaveRequest(String employeeNumber, String leaveType, String startDate, String endDate) {
        return updateLeaveStatus(employeeNumber, leaveType, startDate, endDate, "Rejected");
    }
    
    /**
     * Update leave request status with business rule validation
     * @param employeeNumber The employee number
     * @param leaveType The type of leave
     * @param startDate Start date
     * @param endDate End date
     * @param newStatus New status (Approved/Rejected)
     * @return true if successful, false otherwise
     */
    private boolean updateLeaveStatus(String employeeNumber, String leaveType, String startDate, String endDate, String newStatus) {
        // Validate that the request exists and is in Pending status
        List<String[]> allRequests = leaveDAO.getAllLeaveRequests();
        boolean requestExists = false;
        boolean isPending = false;
        
        for (String[] request : allRequests) {
            if (request.length >= 5 &&
                request[0].trim().equals(employeeNumber) &&
                request[1].trim().equals(leaveType) &&
                request[2].trim().equals(startDate) &&
                request[3].trim().equals(endDate)) {
                requestExists = true;
                isPending = request[4].trim().equals("Pending");
                break;
            }
        }
        
        if (!requestExists) {
            throw new IllegalArgumentException("Leave request not found.");
        }
        
        if (!isPending) {
            throw new IllegalStateException("Cannot update a leave request that is not in Pending status.");
        }
        
        return leaveDAO.updateLeaveStatus(employeeNumber, leaveType, startDate, endDate, newStatus);
    }
    
    /**
     * Get all leave requests for a specific employee
     * @param employeeNumber The employee number to filter by
     * @return List of leave request data arrays [leaveType, startDate, endDate, status]
     */
    public List<String[]> getEmployeeLeaveRequests(String employeeNumber) {
        return leaveDAO.getEmployeeLeaveRequests(employeeNumber);
    }
    
    /**
     * Get all leave requests from the system
     * @return List of all leave request data arrays [employeeNumber, leaveType, startDate, endDate, status]
     */
    public List<String[]> getAllLeaveRequests() {
        return leaveDAO.getAllLeaveRequests();
    }
    
    /**
     * Validate leave request data
     * @param leaveType The type of leave
     * @param startDate Start date
     * @param endDate End date
     * @param employeeNumber Employee number
     * @return Validation result message, empty string if valid
     */
    public String validateLeaveRequest(String leaveType, String startDate, String endDate, String employeeNumber) {
        if (leaveType == null || leaveType.trim().isEmpty()) {
            return "Leave type is required.";
        }
        
        if (!isValidLeaveType(leaveType.trim())) {
            return "Invalid leave type. Valid types are: VL, SL, PL, EL.";
        }
        
        if (startDate == null || startDate.trim().isEmpty()) {
            return "Start date is required.";
        }
        
        if (endDate == null || endDate.trim().isEmpty()) {
            return "End date is required.";
        }
        
        if (employeeNumber == null || employeeNumber.trim().isEmpty()) {
            return "Employee number is required.";
        }
        
        return "";
    }
    
    /**
     * Validate date format and logic - supports both MM/dd/yyyy and YYYY-MM-DD formats
     * @param startDate Start date in MM/dd/yyyy or YYYY-MM-DD format
     * @param endDate End date in MM/dd/yyyy or YYYY-MM-DD format
     * @return Validation error message, empty string if valid
     */
    private String validateDates(String startDate, String endDate) {
        try {
            LocalDate start = parseDate(startDate.trim());
            LocalDate end = parseDate(endDate.trim());
            
            if (start.isAfter(end)) {
                return "Start date cannot be after end date.";
            }
            
            if (start.isBefore(LocalDate.now())) {
                return "Start date cannot be in the past.";
            }
            
            // Check if leave duration is reasonable (max 30 days for most leave types)
            long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(start, end) + 1;
            if (daysBetween > 30) {
                return "Leave duration cannot exceed 30 days.";
            }
            
        } catch (DateTimeParseException e) {
            return "Invalid date format. Please use MM/dd/yyyy or YYYY-MM-DD format.";
        }
        
        return "";
    }
    
    /**
     * Parse date string supporting both MM/dd/yyyy and YYYY-MM-DD formats
     * @param dateStr The date string to parse
     * @return LocalDate object
     * @throws DateTimeParseException if neither format matches
     */
    private LocalDate parseDate(String dateStr) {
        // Try MM/dd/yyyy format first (used by LeaveReqPortal GUI)
        try {
            DateTimeFormatter mmddyyyyFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            return LocalDate.parse(dateStr, mmddyyyyFormatter);
        } catch (DateTimeParseException e1) {
            // Try YYYY-MM-DD format (used by LeaveReqPortalHR GUI)
            try {
                DateTimeFormatter yyyymmddFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(dateStr, yyyymmddFormatter);
            } catch (DateTimeParseException e2) {
                // Re-throw the original exception
                throw e1;
            }
        }
    }
    
    /**
     * Check if leave type is valid
     * @param leaveType The leave type to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidLeaveType(String leaveType) {
        return "VL".equals(leaveType) || 
               "SL".equals(leaveType) || 
               "PL".equals(leaveType) || 
               "EL".equals(leaveType) ||
               "Vacation Leave (VL)".equals(leaveType) ||
               "Sick Leave (SL)".equals(leaveType) ||
               "Paternity Leave (PL)".equals(leaveType) ||
               "Emergency Leave (EL)".equals(leaveType);
    }
    
    /**
     * Get leave request by employee number and date range
     * @param employeeNumber The employee number
     * @param startDate Start date
     * @param endDate End date
     * @return Leave request data array or null if not found
     */
    public String[] getLeaveRequest(String employeeNumber, String startDate, String endDate) {
        List<String[]> allRequests = leaveDAO.getAllLeaveRequests();
        for (String[] request : allRequests) {
            if (request.length >= 5 &&
                request[0].trim().equals(employeeNumber) &&
                request[2].trim().equals(startDate) &&
                request[3].trim().equals(endDate)) {
                return request;
            }
        }
        return null;
    }
}
