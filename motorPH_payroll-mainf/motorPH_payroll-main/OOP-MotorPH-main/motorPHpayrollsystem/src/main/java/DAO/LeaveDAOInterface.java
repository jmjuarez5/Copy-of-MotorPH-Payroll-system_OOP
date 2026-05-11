package DAO;

import java.util.List;

public interface LeaveDAOInterface {
    /**
     * Submit a new leave request to the system
     * @param employeeNumber The employee number
     * @param leaveType The type of leave (VL, SL, PL, EL)
     * @param startDate Start date in YYYY-MM-DD format
     * @param endDate End date in YYYY-MM-DD format
     * @param status Leave status (typically "Pending")
     * @return true if successful, false otherwise
     */
    boolean submitLeaveRequest(String employeeNumber, String leaveType, String startDate, String endDate, String status);
    
    /**
     * Update the status of an existing leave request
     * @param employeeNumber The employee number
     * @param leaveType The type of leave
     * @param startDate Start date in YYYY-MM-DD format
     * @param endDate End date in YYYY-MM-DD format
     * @param newStatus New status (Approved/Rejected)
     * @return true if successful, false otherwise
     */
    boolean updateLeaveStatus(String employeeNumber, String leaveType, String startDate, String endDate, String newStatus);
    
    /**
     * Get all leave requests for a specific employee
     * @param employeeNumber The employee number to filter by
     * @return List of leave request data arrays
     */
    List<String[]> getEmployeeLeaveRequests(String employeeNumber);
    
    /**
     * Get all leave requests from the system
     * @return List of all leave request data arrays
     */
    List<String[]> getAllLeaveRequests();
}
